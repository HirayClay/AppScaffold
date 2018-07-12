package com.hiray.mvvm.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField


class ItemViewModel {

    val text = ObservableField<String>("null")
    val completed = ObservableBoolean(false)

    fun itemClicked() {

    }
}