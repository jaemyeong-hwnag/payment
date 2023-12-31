package com.hjm.payment.domain.user.service

import com.hjm.payment.domain.user.dto.UserDto
import com.hjm.payment.domain.user.entity.User
import com.hjm.payment.domain.user.enums.UserErrorCode
import com.hjm.payment.domain.user.repository.UserRepository
import com.hjm.payment.global.exception.GlobalException
import org.modelmapper.ModelMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(timeout = 3600)
class UserService(
    private val userRepository: UserRepository,
    private val modelMapper: ModelMapper,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
) {
    fun signUpUser(userDto: UserDto) {
        if (userRepository.findByUserAccount(userDto.account) == null) {
            throw GlobalException(UserErrorCode.DUPLICATE_ACCOUNT)
        }

        userDto.password = passwordEncoder.encode(userDto.password)
        userRepository.save(modelMapper.map(userDto, User::class.java))
    }

    fun loginUser(account: String, password: String) {
        val password = passwordEncoder.encode(password)

        if (userRepository.findByAccountAndPassword(account, password) == null) {
            throw GlobalException(UserErrorCode.UNAUTHENTICATED)
        }
    }
}