package com.hiray

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.hiray.di.component.AppComponent
import com.hiray.di.component.DaggerAppComponent
import com.hiray.di.module.AppModule
import com.hiray.mvvm.viewmodel.NetWorkViewModel
import com.hiray.util.Toasty
import javax.inject.Inject

class App : Application() {

    var activityCount = 0
    lateinit var appComponent: AppComponent
    @Inject
    lateinit var networkViewModel: NetWorkViewModel

    override fun onCreate() {
        super.onCreate()
        Toasty.init(this)
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)
        registerActivityLifecycle()
    }

    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifeCycleAdapter() {

            override fun onActivityDestroyed(activity: Activity?) {
                if (activityCount-- == 0)
                    networkViewModel.dispose()
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                activityCount++
            }

        })
    }


}

open class ActivityLifeCycleAdapter : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) {}

    override fun onActivityResumed(activity: Activity?) {}

    override fun onActivityStarted(activity: Activity?) {}

    override fun onActivityDestroyed(activity: Activity?) {}

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityStopped(activity: Activity?) {}

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}

}