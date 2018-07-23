package com.hiray.mvvm.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.text.TextUtils
import com.hiray.R
import com.hiray.aop.net.NetWorkRequired
import com.hiray.di.ActivityScope
import com.hiray.executor.AppExecutor
import com.hiray.mvvm.model.entity.User
import com.hiray.repository.IUserRepository
import com.hiray.ui.LoginActivity
import com.hiray.ui.SignUpActivity
import com.hiray.util.Toasty
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class SignUpViewModel @Inject constructor(
        var appContext: Context,
        var userRepo: IUserRepository) {

    val name = ObservableField<String>()
    val password = ObservableField<String>()
    lateinit var activity:Activity

    @NetWorkRequired
    fun signUp() {
        val userName = name.get()
        val pwd = password.get()
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(pwd)
                && userName != null && pwd != null)
            Observable.create(ObservableOnSubscribe<User> { emitter ->
                val user = userRepo.getUser(userName)
                if (user != null)
                    emitter.onError(Throwable(appContext.getString(R.string.user_exist)))
                else {
                    userRepo.saveUser(userName, pwd)
                    emitter.onNext(User(userName, pwd))
                    emitter.onComplete()
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<User> {
                        override fun onComplete() {}

                        override fun onSubscribe(d: Disposable) {}

                        override fun onNext(user: User) {
                            navigateLogin()
                        }

                        override fun onError(e: Throwable) {
                            Toasty.message(e.message!!)
                        }

                    })
    }

    private fun navigateLogin() {
        activity.startActivity(Intent(activity,LoginActivity::class.java))
        activity.finish()
    }

}