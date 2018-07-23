package com.hiray.mvvm.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.util.Log
import com.google.gson.Gson
import com.hiray.BR
import com.hiray.R
import com.hiray.aop.net.NetWorkRequired
import com.hiray.di.ActivityScope
import com.hiray.mvvm.model.RestApi
import com.hiray.mvvm.model.entity.News
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(
        var gson: Gson,
        var restApi: RestApi) {

    companion object {
        const val TAG = "MainViewModel"
    }

    lateinit var date: String
    val isRefreshing = ObservableBoolean(false)
    val isLoadingMore = ObservableBoolean(false)
    val data: ObservableList<NewsItemViewModel> = ObservableArrayList()
    val itemBinding: ItemBinding<NewsItemViewModel> = ItemBinding.of<NewsItemViewModel>(BR.itemViewModel, R.layout.item_news)


    @NetWorkRequired
    fun start() {
        isRefreshing.set(true)
        restApi.fetchLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext({
                    //                    data.clear()
                    date = it.date
                })//save the date
                .doOnComplete({ isRefreshing.set(false) })
                .doOnError({isRefreshing.set(false)})
                .flatMap { Observable.fromIterable(it.news) }// convert into single stream
                .subscribe(object : Observer<News> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        data.clear()
                    }

                    override fun onNext(t: News) {
                        data.add(NewsItemViewModel(t))
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, e.message)
                    }

                })
    }

    @NetWorkRequired
    fun loadMore() {
        isLoadingMore.set(true)
        restApi.fetchNewsBefore(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterNext({
                    date = it.date
                })//save the date
                .doOnComplete({ isLoadingMore.set(false) })
                .flatMap { Observable.fromIterable(it.news) }
                .subscribe(object : Observer<News> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: News) {
                        data.add(NewsItemViewModel(t))
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, e.message)
                    }

                })
    }


}