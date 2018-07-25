package com.hiray.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.databinding.DataBindingUtil.setContentView
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Toast.LENGTH_SHORT
import com.hiray.App
import com.hiray.R
import com.hiray.databinding.ActivityLoginBinding
import com.hiray.di.component.DaggerLoginComponent
import com.hiray.di.module.AccountModule
import com.hiray.mvvm.viewmodel.LoginViewModel
import com.hiray.mvvm.viewmodel.NetWorkViewModel
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    @Inject
    lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var networkViewModel: NetWorkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerLoginComponent
                .builder()
                .name(TAG)
                .appComponent((application as App).appComponent)
                .accountModule(AccountModule(this))
                .build()
                .inject(this)
        val loginBinding = setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        loginBinding.context = this
        loginBinding.loginViewModel = loginViewModel
        Log.i("NetWorViewModel_Ref", networkViewModel.toString())

    }
}

