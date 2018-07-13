package com.hiray.mvvm.viewmodel

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.hiray.BR
import com.hiray.R
import com.hiray.databinding.ItemTipBinding
import com.hiray.di.ActivityScope
import com.hiray.executor.AppExecutor
import com.hiray.mvvm.model.RestApi
import com.hiray.mvvm.model.entity.Tip
import me.tatarka.bindingcollectionadapter2.ItemBinding
import timber.log.Timber
import java.io.InputStreamReader
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(
        private var appExecutor: AppExecutor,
        private var appContext: Context,
        private var gson: Gson,
        private var restApi: RestApi) {

    companion object {
        const val TAG = "MainViewModel"
    }

    val data: ObservableList<Tip> = ObservableArrayList<Tip>()
    val itemBinding = ItemBinding.of<Tip>(BR.itemTipViewModel, R.layout.item_tip)
    fun start() {
        val runnable = Runnable {
            val ins = appContext.resources.openRawResource(R.raw.products).bufferedReader()
            val tips = gson.fromJson<List<Tip>>(JsonReader(ins), object : TypeToken<List<Tip>>() {}.type)
            restApi.fetchLatestNews().subscribe { res ->
                res.t.forEach {
                    Log.i(TAG,it.toString())
                }

            }
            appExecutor.runOnUiThread(Runnable { data.addAll(tips) })
        }
        appExecutor.execute(runnable)
    }

}