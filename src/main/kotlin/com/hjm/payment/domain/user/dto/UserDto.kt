package com.hjm.payment.domain.user.dto

import com.hjm.payment.global.common.UseYn
import java.util.*

data class UserDto(
    val userId: Long?,
    val userName: String,
    val account: String,
    val useYn: UseYn?,
    val createAt: Date?,
    val updateAt: Date?,
)