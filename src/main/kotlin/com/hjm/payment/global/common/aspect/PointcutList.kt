package com.hjm.payment.common.aspect

import org.aspectj.lang.annotation.Pointcut


class PointcutList {
    @Pointcut("execution(public * com.hjm.payment.*.controller.*.*(..)))\"")
    fun allController() {
    }

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    fun allScheduler() {
    }
}