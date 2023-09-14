package com.hjm.payment.domain.user.controller

import com.hjm.payment.domain.user.dto.UserDto
import com.hjm.payment.domain.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{userId}")
    fun findUser(@PathVariable userId: Long): String {
        return "유저 정보 $userId"
    }

    @PostMapping("/creat")
    fun creatUser(@RequestBody userDto: UserDto) {
        userService.addUser(userDto)
    }
}