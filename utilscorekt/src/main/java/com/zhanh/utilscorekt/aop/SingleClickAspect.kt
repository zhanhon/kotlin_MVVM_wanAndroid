package com.zhanh.utilscorekt.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import java.util.*

//使用@Aspect注解标示这是一个切面类
@Aspect
class SingleClickAspect {
    var mLastClickTime: Long = 0

    //@Pointcut来标识所要寻找的切点，定义的@ SingleClick注解
    @Pointcut("execution(@com.zhanh.utilscorekt.aop.SingleClick * *(..))")
    fun methodAnnotated(){

    }

    @Around("methodAnnotated()")
    @Throws(Throwable::class)
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint){
        val methodSingature = joinPoint.signature as MethodSignature
        val method = methodSingature.method
        if (!method.isAnnotationPresent(SingleClick::class.java)){
            return
        }
        val singleClick = method.getAnnotation(SingleClick::class.java)
        if (!isFastDoubleClick(singleClick.value)){
            //执行原方法
            joinPoint.proceed()
        }
    }

    fun isFastDoubleClick(intervalMillis: Long): Boolean {
        val currentTime = Calendar.getInstance().timeInMillis
        val timeInterval = Math.abs(currentTime - mLastClickTime)
        return if (timeInterval < intervalMillis) {
            true
        } else {
            mLastClickTime = currentTime
            false
        }
    }

}