package com.hiray

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by hiray on 2018/7/2.
 *@author hiray
 */
class RollingTextView : TextView {

    lateinit var textArray: List<String>
    lateinit var textPainter: Paint
    lateinit var finishedColumn: BitSet
    /**
     * set speed(pixel) for every character
     */
    lateinit var speed: List<Int>

    val rollingRunnable  = Runnable {

    }


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        textArray = text.map(::trans).toList()
        textPainter = paint
        finishedColumn = BitSet(textArray.size)
        initSpeed()
    }

    private fun initSpeed() {
        var initialCapacity = textArray.size
        speed = ArrayList(initialCapacity)
        for (i in 0..initialCapacity)
            (speed as ArrayList<Int>).add(20-i)
    }


    fun trans(it: Char): String {
        return it.toString()
    }

    override fun onDraw(canvas: Canvas?) {
        for (j in 0 until textArray.size - 1) {
            //针对数字
            for (i in 0 until 10)

        }
    }

    fun start(){
        postOnAnimationDelayed(rollingRunnable,30)
    }


}