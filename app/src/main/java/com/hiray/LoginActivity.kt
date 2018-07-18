package com.hiray

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.databinding.DataBindingUtil
import android.databinding.DataBindingUtil.setContentView
import android.support.design.widget.Snackbar
import android.widget.Toast.LENGTH_SHORT
import com.hiray.databinding.ActivityLoginBinding
import com.hiray.di.component.AppComponent
import com.hiray.di.component.DaggerLoginComponent
import com.hiray.mvvm.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), CallBack {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerLoginComponent
                .builder()
                .appComponent((application as App).appComponent)
                .build()
                .inject(this)
        val loginBinding = setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        loginViewModel.callBack = this
        loginBinding.host = this
        loginBinding.loginViewModel = loginViewModel
    }

    fun attemptLogin() {
        loginViewModel.saveUser()
    }


    override fun onLoginSuccess() {
        Snackbar.make(root_view, resources.getString(R.string.login_success_msg), LENGTH_SHORT).show()
    }

    override fun showMsg(msg: String) {
        Snackbar.make(root_view, msg, LENGTH_SHORT).show()
    }
}

interface CallBack {
    fun onLoginSuccess()
    fun showMsg(msg: String)
}