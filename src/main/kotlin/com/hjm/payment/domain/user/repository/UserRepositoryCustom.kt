package com.hjm.payment.domain.user.repository

import com.hjm.payment.domain.user.entity.User

interface UserRepositoryCustom {
    fun findByUserName(userName: String): User?
    fun findByUserAccount(userAccount: String): User?

    fun findByAccountAndPassword(account: String, password: String): User?
}