package com.hiray.mvvm.viewmodel

import android.databinding.ObservableField
import android.text.TextUtils
import com.hiray.aop.net.NetWorkRequired
import com.hiray.ui.CallBack
import com.hiray.di.ActivityScope
import com.hiray.executor.AppExecutor
import com.hiray.repository.IUserRepository
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
        var userRepo: IUserRepository,
        var appExecutor: AppExecutor) {

    lateinit var callBack: CallBack

    @NetWorkRequired
    fun login() {
        val runnable = Runnable {
            val userName = name.get()
            val fruit = fruit.get()
            if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(fruit) && userName != null && fruit != null)
                appExecutor.execute { userRepo.saveUser(userName, fruit) }
            else appExecutor.runOnUiThread(Runnable { callBack.showMsg("userName or fruit cannot be empty") })
        }

        appExecutor.execute(runnable)

    }

    val name = ObservableField<String>()
    val fruit = ObservableField<String>()


}