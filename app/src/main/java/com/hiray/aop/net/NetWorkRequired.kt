package com.hiray.aop.net

import android.widget.Toast
import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

//network annotation for methods that associated with network
@Target(AnnotationTarget.FUNCTION)
annotation class NetWorkRequired

@Aspect
open class NetWorkAspect {

    @Around("execution(@com.hiray.aop.net.NetWorkRequired * *(..))")
    @Throws(Throwable::class)
    fun networkRequired(proceedingJoinPoint: ProceedingJoinPoint) {

        val signature = proceedingJoinPoint.signature as MethodSignature
        Log.i(TAG, "intercept: ===" + signature.name)

        if (true) {
            proceedingJoinPoint.proceed()
        } else {
            val ev = proceedingJoinPoint.`this`
            if (ev is Activity)
                Toast.makeText(ev.application, "网络未连接", Toast.LENGTH_SHORT).show()
            else if (ev is Fragment)
                Toast.makeText(ev.activity!!.application, "网络未连接", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {

        private val TAG = "NetWorkAspectEx"
    }
}
