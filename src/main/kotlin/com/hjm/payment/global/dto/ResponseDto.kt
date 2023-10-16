package com.hjm.payment.global.dto

data class ResponseDto(
    val result: Boolean? = true,
    val message: String? = "Success",
    val data: T,

)