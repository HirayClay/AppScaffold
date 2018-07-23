package com.hiray.mvvm.viewmodel

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.databinding.ObservableBoolean
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Log
import com.hiray.di.ActivityScope
import javax.inject.Inject
import javax.inject.Singleton

class NetWorkViewModel @Inject constructor(var appContext: Application) {
    companion object {
        lateinit var connected: ObservableBoolean
    }

    private val connectivityManager: ConnectivityManager
        get() {
            val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm
        }
    private val connectivityReceiver: BroadcastReceiver?
        get() {
            var value: BroadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    val b = connectivityManager.activeNetworkInfo != null
                            && connectivityManager.activeNetworkInfo.isConnected
                    connected.set(b)
                }
            }
            return value
        }

    init {
        init()
        var intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        appContext.registerReceiver(connectivityReceiver, intentFilter)
    }


    private fun init() {
        val cm = connectivityManager
        connected = ObservableBoolean(cm.activeNetworkInfo != null
                && cm.activeNetworkInfo.isConnected)
    }

    fun checkSetting() {
        appContext.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    fun dispose() {
        appContext.unregisterReceiver(connectivityReceiver)
    }

}