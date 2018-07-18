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

@BindingMethods(
//        BindingMethod(type = SmartRefreshLayout::class, attribute = "onLoadMore", method = "setOnLoadMoreListener"),
        BindingMethod(type = SmartRefreshLayout::class, attribute = "isRefresh", method = "setOnRefreshListener")
)
class VB {

}

@BindingAdapter("imageUrl")
fun bindImage(view: ImageView, imageUrl: String) = with(view) {
    Glide.with(this.context)
            .load(imageUrl)
            .into(this)
}


//@InverseBindingAdapter(attribute = "isRefreshing")
//fun setRefreshing(smartRefreshLayout: SmartRefreshLayout): Boolean {
//    return smartRefreshLayout.state == RefreshState.Refreshing
//
//
//}
//
//@InverseBindingAdapter(attribute = "isLoadingMore")
//fun setLoadingMore(smartRefreshLayout: SmartRefreshLayout): Boolean {
//    return smartRefreshLayout.state == RefreshState.Loading
//
//}

//针对SmartRefreshLayout
@BindingAdapter("onRefresh", "onLoadMore", "isRefreshing", "isLoadingMore", requireAll = false)
fun bindSmartRefreshLayout(smartRefreshLayout: SmartRefreshLayout,
                           onRefreshListener: OnRefreshListener?, onLoadMoreListener: OnLoadMoreListener?,
                           isRefreshing: Boolean,
                           isLoadingMore: Boolean) {
    if (onRefreshListener != null)
        smartRefreshLayout.setOnRefreshListener(onRefreshListener)

    if (onLoadMoreListener != null)
        smartRefreshLayout.setOnLoadMoreListener(onLoadMoreListener)

    if (smartRefreshLayout.state == RefreshState.Refreshing &&!isRefreshing)
        smartRefreshLayout.finishRefresh(true)

    if (smartRefreshLayout.state == RefreshState.Loading &&!isLoadingMore)
        smartRefreshLayout.finishLoadMore(true)
}