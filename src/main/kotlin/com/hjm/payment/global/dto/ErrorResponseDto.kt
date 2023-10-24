package com.hjm.payment.global.dto

import org.springframework.http.HttpStatus

data class ErrorResponseDto(
    val code: String?,
    val message: String? = "Fail",
    val result: Boolean = false,
): ResponseDto
