package com.hjm.payment.domain.user.service

import com.hjm.payment.domain.user.dto.UserDto
import com.hjm.payment.domain.user.entity.User
import com.hjm.payment.domain.user.repository.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val modelMapper: ModelMapper
) {
    fun addUser(userDto: UserDto) {
        userRepository.save(modelMapper.map(userDto, User::class.java))
    }

    fun updateUser() {
        // userRepository.
    }
}