package com.hiray

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    var done = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate: ${Environment.getExternalStorageDirectory()}")
        Log.i(TAG, "onCreate: ${getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}")
        textView.setOnClickListener {
            if (!done) {
                Log.i(TAG, "onCreate: ${textView.top}")
                textView.translationY = 200f
                done = true
            } else {
                textView.requestLayout()
                textView.bringToFront()
                Log.i(TAG, "onCreate: ${textView.top}")
            }
        }
    }
}
