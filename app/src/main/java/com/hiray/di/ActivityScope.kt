package com.hiray.di

import javax.inject.Qualifier
import javax.inject.Scope

@Target(AnnotationTarget.CLASS,AnnotationTarget.FUNCTION)
@Scope
annotation class ActivityScope {
}