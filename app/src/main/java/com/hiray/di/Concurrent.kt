package com.hiray.di

import javax.inject.Qualifier

@Target(AnnotationTarget.FUNCTION,AnnotationTarget.VALUE_PARAMETER)
@Qualifier
annotation class Concurrent {
}