package com.hjm.payment.global.exception

import com.hjm.payment.global.dto.ErrorResponseDto
import com.hjm.payment.global.enums.ErrorCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(value = [GlobalException::class])
    fun handlingGlobalException(errorCode: ErrorCode): ResponseEntity<ErrorResponseDto> {

        logger.info(
            "GlobalExceptionHandler httpStatus: {}, message: {}",
            errorCode.httpStatus.toString(),
            errorCode.message
        );
        val errorResponseDto: ErrorResponseDto = ErrorResponseDto(errorCode.code, errorCode.message)
        return ResponseEntity(errorResponseDto, errorCode.httpStatus)
    }
}