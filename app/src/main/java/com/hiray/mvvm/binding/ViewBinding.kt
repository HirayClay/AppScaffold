package com.hiray.mvvm.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * class for attribute binding in xml or create two-way binding for custom views
 */


@BindingAdapter("imageUrl")
fun bindImage(view: ImageView, imageUrl: String) = with(view) {
    Glide.with(this.context)
            .load(imageUrl)
            .into(this)
}


