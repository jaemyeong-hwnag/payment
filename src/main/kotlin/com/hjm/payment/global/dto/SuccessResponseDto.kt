package com.hjm.payment.global.dto

import org.springframework.http.HttpStatus

data class SuccessResponseDto<T>(
    val data: T? = null,
    val dataList: List<T>? = null,
    val code: HttpStatus = HttpStatus.OK,
    val message: String = "Success",
    val result: Boolean = true,
): ResponseDto