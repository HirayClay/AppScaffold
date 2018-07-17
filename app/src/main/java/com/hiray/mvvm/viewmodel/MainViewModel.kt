package com.hiray.mvvm.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.hiray.BR
import com.hiray.R
import com.hiray.di.ActivityScope
import com.hiray.mvvm.model.LatestResponse
import com.hiray.mvvm.model.Response
import com.hiray.mvvm.model.RestApi
import com.hiray.mvvm.model.entity.News
import com.hiray.mvvm.model.entity.Tip
import com.hiray.mvvm.model.entity.TopStory
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(
        var appContext: Context,
        var gson: Gson,
        var restApi: RestApi,
        var sharedPreferences: SharedPreferences) {

    companion object {
        const val TAG = "MainViewModel"
    }

    lateinit var date: String
    val data: ObservableList<News> = ObservableArrayList<News>()
    val itemBinding: ItemBinding<News> = ItemBinding.of<News>(BR.news, R.layout.item_news)
    fun start() {
        val ins = appContext.resources.openRawResource(R.raw.products).bufferedReader()
        val tips = gson.fromJson<List<Tip>>(JsonReader(ins), object : TypeToken<List<Tip>>() {}.type)
        restApi.fetchLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<LatestResponse<News, TopStory>> {
                    private lateinit var disposeable: Disposable
                    override fun onComplete() {
                        disposeable.dispose()
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposeable = d
                    }

                    override fun onNext(t: LatestResponse<News, TopStory>) {
                        Log.i(TAG, t.t.size.toString())
                        data.clear()
                        data.addAll(t.t)
                    }

                    override fun onError(e: Throwable) {
                    }


                })
    }

    fun loadMore() {
        restApi.fetchNewsBefore(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    //                   data.addAll(it.stories)
                }
    }


}