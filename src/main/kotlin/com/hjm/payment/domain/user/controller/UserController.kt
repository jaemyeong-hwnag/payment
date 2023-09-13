package com.hjm.payment.domain.user.controller

import com.hjm.payment.domain.user.dto.UserDto
import com.hjm.payment.domain.user.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/creat")
    fun creatUser(@RequestBody userDto: UserDto) {
        userService.addUser(userDto)
    }
}