package com.hiray.mvvm.binding

import android.databinding.*
import android.widget.AbsListView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

/**
 * class for attribute binding in xml or create two-way binding for custom views
 */
class VB {

}

@BindingAdapter("imageUrl")
fun bindImage(view: ImageView, imageUrl: String) = with(view) {
    Glide.with(this.context)
            .load(imageUrl)
            .into(this)
}


//针对SmartRefreshLayout
@BindingAdapter("onRefresh", "onLoadMore", "isRefreshing", "isLoadingMore", "refreshLoadMoreEnableOnNetChange", requireAll = false)
fun bindSmartRefreshLayout(smartRefreshLayout: SmartRefreshLayout,
                           onRefreshListener: OnRefreshListener?, onLoadMoreListener: OnLoadMoreListener?,
                           isRefreshing: Boolean,
                           isLoadingMore: Boolean, isConnected: Boolean) {
    if (onRefreshListener != null)
        smartRefreshLayout.setOnRefreshListener(onRefreshListener)

    if (onLoadMoreListener != null)
        smartRefreshLayout.setOnLoadMoreListener(onLoadMoreListener)

    if (smartRefreshLayout.state == RefreshState.Refreshing && !isRefreshing)
        smartRefreshLayout.finishRefresh(true)

    if (smartRefreshLayout.state == RefreshState.Loading && !isLoadingMore)
        smartRefreshLayout.finishLoadMore(true)

    smartRefreshLayout.setEnableLoadMore(isConnected)
    smartRefreshLayout.setEnableRefresh(isConnected)

}