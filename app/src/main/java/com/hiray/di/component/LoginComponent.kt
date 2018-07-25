package com.hiray.di.component

import com.hiray.di.ActivityScope
import com.hiray.di.module.AccountModule
import com.hiray.di.module.BindsModule
import com.hiray.ui.LoginActivity
import com.hiray.ui.SignUpActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [AccountModule::class, BindsModule::class], dependencies = [AppComponent::class])
interface LoginComponent {

    fun inject(activity: LoginActivity)

    fun inject(activity: SignUpActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun name(name: String): Builder

        fun accountModule(accountModule: AccountModule): Builder

        fun appComponent(app: AppComponent): Builder

        fun build(): LoginComponent
    }
}