package com.hjm.payment.global.exception

import com.hjm.payment.global.enums.ErrorCode

open class GlobalException(
    val errorCode: ErrorCode
) : RuntimeException()