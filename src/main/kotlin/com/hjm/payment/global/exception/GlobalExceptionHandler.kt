package com.hjm.payment.global.exception

import com.hjm.payment.global.dto.ErrorResponseDto
import com.hjm.payment.global.enums.ErrorCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler{

    @ExceptionHandler(value = [GlobalException::class])
    fun handlingGlobalException(errorCode: ErrorCode): ResponseEntity<ErrorResponseDto> {
        val errorResponseDto: ErrorResponseDto = ErrorResponseDto(errorCode.httpStatus, errorCode.message)
        return ResponseEntity(errorResponseDto, errorCode.httpStatus)
    }
}