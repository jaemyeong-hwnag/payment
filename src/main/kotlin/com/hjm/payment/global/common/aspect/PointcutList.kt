package com.hjm.payment.global.common.aspect

import org.aspectj.lang.annotation.Pointcut


class PointcutList {
    @Pointcut("execution(* com.hjm.payment.domain..controller.*.*(..)))")
    fun allController() {
    }

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    fun allScheduler() {
    }
}