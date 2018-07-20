package com.hiray.mvvm.viewmodel

import android.content.Context
import android.database.Observable
import android.databinding.ObservableField
import android.view.View
import com.hiray.mvvm.model.entity.News
import com.hiray.ui.WebViewActivity

class NewsItemViewModel {


    val title: ObservableField<String> = ObservableField()
    val image: ObservableField<String> = ObservableField()
    val url: ObservableField<String> = ObservableField<String>()

    constructor(news: News) {
        title.set(news.title)
        image.set(news.image)
        url.set(news.shareUrl)
    }

    fun onItemClick(view: View) {
        WebViewActivity.load(view.context, url.get()!!)
    }
}