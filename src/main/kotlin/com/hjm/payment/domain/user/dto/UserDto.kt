package com.hjm.payment.domain.user.dto

import com.hjm.payment.global.common.UseYn
import java.util.*

data class UserDto(
    val id: Long?,
    val userName: String,
    val account: String,
    val password: String,
    val useYn: UseYn?,
    val createAt: Date?,
    val updateAt: Date?,
)