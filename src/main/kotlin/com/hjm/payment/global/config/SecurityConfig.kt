package com.hjm.payment.global.config

import com.hjm.payment.app.api.common.filter.JwtAuthenticationFilter
import com.hjm.payment.global.common.aspect.LoggingAspect
import com.hjm.payment.global.util.JwtProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfig(
    val jwtAuthenticationFilter: JwtAuthenticationFilter
) {
    private val logger: Logger = LoggerFactory.getLogger(LoggingAspect::class.java)
    private val allowedUrls = arrayOf("/auth/get-token", "/actuator/health", "/error")

    @Order(1)
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }    // 세션을 사용하지 않으므로 STATELESS 설정
            .authorizeHttpRequests {
                it
                    .requestMatchers(* allowedUrls).permitAll()    // requestMatchers의 인자로 전달된 url은 모두에게 허용
                    .anyRequest().authenticated() // 모든 요청은 인증 필요
            }
            .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter::class.java)
            .formLogin(withDefaults())

        return http.build()!!
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}