package com.hiray.aop.net

import android.util.Log
import com.hiray.R
import com.hiray.mvvm.viewmodel.NetWorkViewModel
import com.hiray.util.Toasty
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
        if (NetWorkViewModel.connected.get())
            proceedingJoinPoint.proceed()
        else
            Toasty.message(R.string.network_error_serious_hint)
    }

    companion object {

        private val TAG = "NetWorkAspectEx"
    }
}
