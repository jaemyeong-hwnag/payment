package com.hjm.payment.global.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.util.*

object JWTUtil {
    private const val ONE_DAY = 1000L * 60L * 60L * 24L
    const val EXPIRATION_TIME = ONE_DAY
    private const val SECRET_KEY = "test-key" // @todo ENV 로 빼야됨
    private val signingKey = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8))
        ?: throw IllegalStateException("Token을 발급하기 위한 Key가 적절하게 생성되지 않음")

    fun generateToken(
        identifier: String,
        expirationInMillisecond: Long = EXPIRATION_TIME,
        addData: Any
    ): String {
        val now = Date()
        val expiration = Date(now.time + expirationInMillisecond)

        return Jwts.builder()
            .claim("data", addData)
            .setSubject(identifier)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact()
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