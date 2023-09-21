package com.hjm.payment.domain.user.service

import com.hjm.payment.domain.user.repository.UserRepository
import com.hjm.payment.domain.user.util.UserDetailsImpl
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Transactional
class UserDetailService(
    private val userService: UserService,
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        // todo db 확인 추가
        val user = userRepository.findByUserName(username) ?: throw UsernameNotFoundException("존재하지 않는 username 입니다.")

        return UserDetailsImpl(user)
    }
}