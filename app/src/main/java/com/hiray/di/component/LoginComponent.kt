package com.hiray.di.component

import com.hiray.di.ActivityScope
import com.hiray.di.module.AccountModule
import com.hiray.ui.LoginActivity
import com.hiray.ui.SignUpActivity
import dagger.Component

@ActivityScope
@Component(modules = [AccountModule::class], dependencies = [AppComponent::class])
interface LoginComponent {

    fun inject(activity: LoginActivity)

    fun inject(activity: SignUpActivity)
}