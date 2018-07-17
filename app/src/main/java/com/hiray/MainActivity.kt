package com.hiray

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.DataBindingUtil.setContentView
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
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
        mainBinding.recyclerView.addItemDecoration(DividerItemDecoration())
        mainBinding.viewmodel = mainViewModel
        mainBinding.executePendingBindings()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.start()
    }

    inner class DividerItemDecoration : RecyclerView.ItemDecoration() {
        private val mDivider: Drawable? = ContextCompat.getDrawable(this@MainActivity, R.drawable.divider)

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)

                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = child.bottom + params.bottomMargin
                val bottom = top + mDivider!!.intrinsicHeight

                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }
}

