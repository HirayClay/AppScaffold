package com.hiray.di.component

import com.hiray.MainActivity
import com.hiray.di.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}