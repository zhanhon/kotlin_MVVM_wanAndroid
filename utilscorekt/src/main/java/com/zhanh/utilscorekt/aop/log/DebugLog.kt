package com.zhanh.utilscorekt.aop.log

/**
 *
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE,AnnotationTarget.CLASS,AnnotationTarget.CONSTRUCTOR,AnnotationTarget.FUNCTION)
annotation class DebugLog()