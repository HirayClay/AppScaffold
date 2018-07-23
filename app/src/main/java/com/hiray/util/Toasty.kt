package com.hiray.util

import android.app.Application
import android.content.Context
import android.os.Build
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.util.TypedValue
import android.view.Gravity
import android.widget.Toast
import com.hiray.R


/**
 *
 *
 * @author hiray
 */

class Toasty {
    companion object {
        private var toast: Toast? = null
        private var mApp: Application? = null
        private var mTextView: AppCompatTextView? = null

        fun init(app: Application) {
            mApp = app
        }

        fun message(msg: String) {
            createIfNull()
            mTextView!!.text = msg
            toast!!.show()
        }

        fun message(@StringRes msgResId: Int) {
            message(mApp!!.getString(msgResId))
        }

        private fun createIfNull() {
            if (toast == null)
                synchronized(Toasty::class.java) {
                    if (toast == null)
                        toast = Toast(mApp)
                    mTextView = AppCompatTextView(mApp!!)
                    mTextView!!.setBackgroundResource(R.drawable.rice)
                    mTextView!!.setPadding(dp2px(15f), dp2px(8f), dp2px(15f), dp2px(8f))
                    mTextView!!.setTextColor(ContextCompat.getColor(mApp as Context, R.color.toast_text_color))
                    mTextView!!.gravity = Gravity.CENTER_VERTICAL
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mTextView!!.elevation = 10f
                        mTextView!!.translationZ = 10f
                    }
                    toast!!.duration = Toast.LENGTH_SHORT
                    toast!!.view = mTextView
                }
        }

        private fun dp2px(dp: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mApp!!.resources.displayMetrics).toInt()
        }
    }


}