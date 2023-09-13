package com.hjm.payment.global.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfig {
    @Bean
    fun modelMapper(): ModelMapper = ModelMapper().apply {
        configuration.isFieldMatchingEnabled = true // 필드 일치 여부
        configuration.fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE // 접근 레벨 제한
    }
}