package com.hiray.mvvm.viewmodel

import android.databinding.ObservableField
import android.support.annotation.NonNull
import com.hiray.CallBack
import com.hiray.LoginActivity
import com.hiray.di.ActivityScope
import com.hiray.repository.IUserRepository
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
        var userRepo: IUserRepository) {

    lateinit var callBack: CallBack

    fun saveUser() {
        val userName = name.get()
        val fruit = fruit.get()
        if (userName != null && fruit != null)
            userRepo.saveUser(userName, fruit)
        else callBack.showMsg("{$userName?'userName cannot be null' } {$fruit?'fruit cannot be null'}")
    }

    val name = ObservableField<String>()
    val fruit = ObservableField<String>()


}