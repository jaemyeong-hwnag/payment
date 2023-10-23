package com.hjm.payment.domain.user.controller

import com.hjm.payment.domain.user.dto.LoginUserDto
import com.hjm.payment.domain.user.dto.UserDto
import com.hjm.payment.domain.user.service.UserService
import com.hjm.payment.global.dto.ResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
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
    fun signup(@RequestBody userDto: UserDto): ResponseEntity<ResponseDto<UserDto>> {
        userService.signUpUser(userDto)
        return ResponseEntity.ok(ResponseDto())
    }

    @PostMapping("/login")
    fun loginUser(
        @RequestBody account: String,
        @RequestBody password: String,
    ): ResponseEntity<String> = ResponseEntity.ok().body(userService.loginUser(account, password))

}