package com.hjm.payment.domain.auth.dto

data class AuthToken(
    val key: String,
    val serviceCode: String,
)
