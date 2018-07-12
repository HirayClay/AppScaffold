package com.hiray.executor

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService


class AppExecutor(private var executor: ExecutorService) : Executor {
    private val mainHandler = Handler(Looper.getMainLooper())
    override fun execute(command: Runnable) {
        executor.execute(command)
    }

    fun runOnUiThread(command: Runnable) {
        mainHandler.post(command)
    }


}