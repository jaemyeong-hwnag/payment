package com.hjm.payment.global.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*

@Component
object JwtProvider {
    val EXPIRATION_TIME = 1000L * 60L * 60L * 24L
    // @Value("spring.jwt.secret")
    private const val SECRET_KEY: String = "test-key" // @todo ENV 로 빼고 app 별로 구분 필요
    private val signingKey = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8))
        ?: throw IllegalStateException("Token을 발급하기 위한 Key가 적절하게 생성되지 않음")

    fun generateToken(
        identifier: String,
        addData: Any,
        expirationInMillisecond: Long = EXPIRATION_TIME,
    ): String {
        val now = Date()
        val expiration = Date(now.time + expirationInMillisecond)

        if (identifier != SECRET_KEY) {
            throw RuntimeException("사용 불가능한 키입니다.")
        }

        return Jwts.builder()
            .claim("data", addData)
            .setSubject(identifier)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun resolveToken(request: HttpServletRequest): String {
        return request.getHeader("X-AUTH-TOKEN")!!
    }

    fun authenticateToken(request: HttpServletRequest): Boolean {
        return authenticateToken(request.getHeader("X-AUTH-TOKEN"))
    }

    fun authenticateToken(token: String): Boolean {
        return token == SECRET_KEY
    }

    fun getAuthenticationByToken(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }
}