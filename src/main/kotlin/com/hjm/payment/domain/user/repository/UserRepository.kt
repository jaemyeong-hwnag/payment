package com.hjm.payment.domain.user.repository

import com.hjm.payment.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: UserRepositoryCustom, JpaRepository<User, Long> {
}