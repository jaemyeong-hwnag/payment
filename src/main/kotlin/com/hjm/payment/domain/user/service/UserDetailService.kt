package com.hjm.payment.domain.user.service

import com.hjm.payment.domain.user.repository.UserRepository
import com.hjm.payment.domain.user.repository.UserRepositoryImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService(
    userRepositoryImpl: UserRepositoryImpl
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        // TODO("Not yet implemented")
        // todo db 확인 추가

    }
}