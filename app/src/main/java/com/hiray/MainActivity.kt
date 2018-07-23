package com.hiray

import android.databinding.DataBindingUtil.setContentView
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hiray.databinding.ActivityMainBinding
import com.hiray.di.component.DaggerMainComponent
import com.hiray.mvvm.viewmodel.MainViewModel
import com.hiray.mvvm.viewmodel.NetWorkViewModel
import com.hiray.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var networkViewModel: NetWorkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.builder().appComponent((application as App).appComponent)
                .build().inject(this@MainActivity)
        val mainBinding = setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainBinding.recyclerView.addItemDecoration(DividerItemDecoration())
        mainBinding.viewmodel = mainViewModel
        mainBinding.networkViewModel = networkViewModel
        mainBinding.networkErrorLayout!!.networkViewModel = networkViewModel
        mainBinding.networkErrorLayout.executePendingBindings()
        mainBinding.executePendingBindings()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_item_login -> LoginActivity.start(this)
            }
            true
        }

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

    interface UserLoginListener {

        fun onUserLogin()
        fun onUserLogout()
    }
}

