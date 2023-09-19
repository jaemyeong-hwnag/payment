package com.hjm.payment.domain.user.dto

import org.springframework.security.crypto.password.PasswordEncoder

class LoginUserDto(
    val account: String,
    val password: String,
)