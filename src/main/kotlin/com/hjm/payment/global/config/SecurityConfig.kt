package com.hjm.payment.global.config

import com.hjm.payment.global.common.aspect.LoggingAspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    private val logger: Logger = LoggerFactory.getLogger(LoggingAspect::class.java)
    private val allowedUrls = arrayOf("/", "/join", "/login")

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf {} //  CSRF(교차 사이트 요청 위조)
            .authorizeHttpRequests {
                it.requestMatchers(* allowedUrls).permitAll()    // requestMatchers의 인자로 전달된 url은 모두에게 허용
                    .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }    // 세션을 사용하지 않으므로 STATELESS 설정

        return http.build()!!
    }
}