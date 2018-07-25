package com.hiray.mvvm.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.databinding.ObservableField
import android.text.TextUtils
import com.hiray.R
import com.hiray.aop.net.NetWorkRequired
import com.hiray.di.ActivityScope
import com.hiray.event.LoginEvent
import com.hiray.executor.AppExecutor
import com.hiray.mvvm.model.entity.User
import com.hiray.repository.IUserRepository
import com.hiray.ui.SignUpActivity
import com.hiray.util.Toasty
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
        var appContext: Application,
        var userRepo: IUserRepository,
        var activity: Activity?) {

    @NetWorkRequired
    fun login() {
        val userName = name.get()
        val pwd = password.get()
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(pwd)
                && userName != null && pwd != null)
            Observable.create(ObservableOnSubscribe<User> { emitter ->
                val user = userRepo.getUser(userName)
                if (user != null) {
                    userRepo.saveUser(userName, pwd)
                    emitter.onNext(user)
                    emitter.onComplete()
                } else emitter.onError(Throwable(appContext.getString(R.string.user_not_exist)))
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<User> {
                        override fun onComplete() {}

                        override fun onSubscribe(d: Disposable) {}

                        override fun onNext(user: User) {
                            Toasty.message(R.string.login_success_msg)
                            EventBus.getDefault().post(LoginEvent(true,user.userName))
                            activity!!.finish()
                        }

                        override fun onError(e: Throwable) {
                            Toasty.message(e.message!!)
                        }

                    })
        else Toasty.message(appContext.getString(R.string.username_pwd_empty))
    }

    fun navigateSignUp(activity:Activity){
        activity.startActivity(Intent(activity,SignUpActivity::class.java))
        activity.finish()
    }

    val name = ObservableField<String>()
    val password = ObservableField<String>()


}