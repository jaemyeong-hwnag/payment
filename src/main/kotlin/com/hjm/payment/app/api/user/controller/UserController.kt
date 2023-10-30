package com.hjm.payment.app.api.user.controller

import com.hjm.payment.domain.user.dto.UserDto
import com.hjm.payment.domain.user.service.UserService
import com.hjm.payment.app.api.common.dto.SuccessResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    val userService: UserService
) {
    @GetMapping("/{userId}")
    fun findUser(@PathVariable userId: Long): String {
        return "유저 정보 $userId"
    }

    @PostMapping("/signup")
    fun signup(@RequestBody userDto: UserDto): ResponseEntity<SuccessResponseDto<UserDto>> {
        userService.signUpUser(userDto)
        return ResponseEntity.ok(SuccessResponseDto())
    }
}