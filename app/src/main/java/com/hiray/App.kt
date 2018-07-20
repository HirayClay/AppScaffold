package com.hiray

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.hiray.di.component.AppComponent
import com.hiray.di.component.DaggerAppComponent
import com.hiray.di.module.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        registerActivityLifecycle()
    }

    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object :ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity?) {
                
            }

            override fun onActivityResumed(activity: Activity?) {
                
            }

            override fun onActivityStarted(activity: Activity?) {
                
            }

            override fun onActivityDestroyed(activity: Activity?) {

            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                
            }

            override fun onActivityStopped(activity: Activity?) {
                
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                
            }

        })
    }


}