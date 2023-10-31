package com.hjm.payment.app.api.auth.controller

import com.hjm.payment.domain.auth.dto.AuthToken
import com.hjm.payment.global.util.JWTUtil
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class AuthController {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping("/get-token")
    fun getToken(
        @RequestBody authToken: AuthToken
    ): ResponseEntity<String> {
        return try {
            ResponseEntity.ok().body(JWTUtil.generateToken(authToken.key, authToken.serviceCode))
        } catch (t: Throwable) {
            logger.error(t.message)
            // 인증 실패의 경우 무조건 404로 넘긴다 - 요즘은 인증 실패하거나 권한이 없는경우 404로 넘기고 있다.
            ResponseEntity.notFound().build()
        }
    }
}