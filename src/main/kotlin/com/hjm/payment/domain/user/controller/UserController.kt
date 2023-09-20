package com.hjm.payment.domain.user.controller

import com.hjm.payment.domain.user.dto.LoginUserDto
import com.hjm.payment.domain.user.dto.UserDto
import com.hjm.payment.domain.user.service.UserService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
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

    @PostMapping("/login")
    fun loginUser(
        @RequestBody loginUserDto: LoginUserDto,
        encoder: PasswordEncoder
    ): String {
        // @todo 밸리데이션

        // @todo service 레이어로
        // 사용자가 제공한 아이디와 비밀번호를 사용하여 UsernamePasswordAuthenticationToken을 생성합니다.
        val authenticationToken = UsernamePasswordAuthenticationToken(loginUserDto.account, encoder.encode(loginUserDto.password))

        // AuthenticationManager를 사용하여 인증을 시도합니다.
        /*        val authentication: Authentication = authenticationManager.authenticate(authenticationToken)

                // 인증이 성공하면 SecurityContext에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().authentication = authentication
        */
        // 로그인 성공 후의 리다이렉션 페이지를 반환합니다.
        return "redirect:/"
    }
}