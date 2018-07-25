package com.hiray.mvvm.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.hiray.R
import com.hiray.event.LoginEvent
import com.hiray.ui.LoginActivity
import com.hiray.util.Toasty
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject


class UserViewModel @Inject constructor() {
    lateinit var context:Context

    var login = ObservableBoolean(false)
    var name = ObservableField<String>("登录")

    init {
        EventBus.getDefault().register(this)
    }

    fun tryLogin() {
        if (login.get())
            Toasty.Companion.message(R.string.hi_msg)
        else context.startActivity(Intent(context, LoginActivity::class.java))
    }

    fun dispose() {
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onLoginEvent(event: LoginEvent) {
        //抓取用户数据,暂时这么写
        login.set(event.success)
        name.set(event.name)

    }


}