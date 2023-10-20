package com.hjm.payment.domain.user.enums

import com.hjm.payment.global.enums.ErrorCode
import org.springframework.http.HttpStatus

enum class UserErrorCode(override val httpStatus: HttpStatus, override val message: String, override val code: String? = ""): ErrorCode {
    DUPLICATE_ACCOUNT(HttpStatus.CONFLICT, "중복 아아디 입니다."),
}
