package com.hiray.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hiray.App
import com.hiray.R
import com.hiray.databinding.ActivitySignUpBinding
import com.hiray.di.component.DaggerLoginComponent
import com.hiray.mvvm.viewmodel.LoginViewModel
import com.hiray.mvvm.viewmodel.SignUpViewModel
import javax.inject.Inject

class SignUpActivity : AppCompatActivity() {
    private val TAG = "SignUpActivity"
    @Inject
    lateinit var signupViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var binding = DataBindingUtil.setContentView<ActivitySignUpBinding>(this,
                R.layout.activity_sign_up)

        DaggerLoginComponent.builder()
                .appComponent((application as App).appComponent)
                .name(TAG)
                .build().inject(this)
        signupViewModel.activity = this
        binding.viewmodel = signupViewModel
    }
}
