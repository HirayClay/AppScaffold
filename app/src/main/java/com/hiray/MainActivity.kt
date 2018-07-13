package com.hiray

import android.databinding.DataBindingUtil
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.hiray.databinding.ActivityMainBinding
import com.hiray.di.component.DaggerMainComponent
import com.hiray.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.builder().appComponent((application as App).appComponent)
                .build().inject(this@MainActivity)
        val mainBinding = setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainBinding.viewmodel = mainViewModel
        mainBinding.executePendingBindings()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.start()
    }
}
