package com.hiray.mvvm.viewmodel

import android.databinding.ObservableField
import android.view.View
import com.hiray.aop.net.NetWorkRequired
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

    @NetWorkRequired
    fun onItemClick(view: View) {
        WebViewActivity.load(view.context, url.get()!!)
    }
}