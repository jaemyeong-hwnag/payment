package com.hjm.payment.domain.user.repository

import com.hjm.payment.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: UserRepositoryCustom, JpaRepository<User, Long> {
}