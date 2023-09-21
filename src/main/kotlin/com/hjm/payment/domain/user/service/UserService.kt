package com.hjm.payment.domain.user.service

import com.hjm.payment.domain.user.dto.LoginUserDto
import com.hjm.payment.domain.user.dto.UserDto
import com.hjm.payment.domain.user.entity.User
import com.hjm.payment.domain.user.repository.UserRepository
import com.hjm.payment.domain.user.util.JwtUtils
import org.modelmapper.ModelMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val modelMapper: ModelMapper,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils
) {
    fun addUser(userDto: UserDto) {
        userRepository.save(modelMapper.map(userDto, User::class.java))
    }

    fun loginUser(loginUserDto: LoginUserDto): String {
        // 인증시도
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginUserDto.account, "test", null)
        )

        // 토큰 생성
        val token = jwtUtils.createToken(loginUserDto.account)

        return token
    }
}