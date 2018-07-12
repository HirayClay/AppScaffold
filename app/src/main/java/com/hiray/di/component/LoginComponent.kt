package com.hiray.di.component

import com.hiray.LoginActivity
import com.hiray.di.ActivityScope
import com.hiray.di.module.AccountModule
import dagger.Component

@ActivityScope
@Component(modules = [AccountModule::class],dependencies = [AppComponent::class])
interface LoginComponent {

    fun inject(activity: LoginActivity)
}