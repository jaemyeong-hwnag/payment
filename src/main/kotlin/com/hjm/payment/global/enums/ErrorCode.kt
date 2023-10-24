package com.hjm.payment.global.enums

import org.springframework.http.HttpStatus

interface ErrorCode {
    val httpStatus: HttpStatus
    val message: String
    val code: String?
}
