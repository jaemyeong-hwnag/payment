package com.hjm.payment.global.dto

data class ResponseDto<T>(
    var result: Boolean = true,
    var message: String = "Success",
    val data: T? = null,
    val dataList: List<T>? = null,
)