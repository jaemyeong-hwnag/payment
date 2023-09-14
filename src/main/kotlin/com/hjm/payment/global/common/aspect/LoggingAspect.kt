package com.hjm.payment.global.common.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
@Order(1)
// @todo 아이디 비밀번호 마스킹 처리
class LoggingAspect {
    private val logger: Logger = LoggerFactory.getLogger(LoggingAspect::class.java)

    @Before("PointcutList.allScheduler()")
    fun allSchedulerStartLogging(joinPoint: JoinPoint) {
        logger.info(
            "Scheduler Start - {}",
            generateLoggingContextByScheduler(joinPoint)
        )
    }

    @After("PointcutList.allScheduler()")
    fun allSchedulerFinishLogging(joinPoint: JoinPoint) {
        logger.info(
            "Scheduler End - {}",
            generateLoggingContextByScheduler(joinPoint)
        )
    }

    @Before("PointcutList.allController()")
    fun requestLog(joinPoint: JoinPoint) {
        logger.info("Request - {}", generateLoggingContextByHttpRequest(joinPoint))
    }

    @After("PointcutList.allController()")
    fun responseLog(joinPoint: JoinPoint) {
        logger.info("Response - {}", generateLoggingContextByHttpRequest(joinPoint))
    }

    private fun generateLoggingContextByScheduler(joinPoint: JoinPoint): HashMap<String, String> {
        return generateLoggingContext(joinPoint)
    }

    private fun generateLoggingContextByHttpRequest(joinPoint: JoinPoint): HashMap<String, String> {
        val requestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
        val request = requestAttributes.request
        val requestURI = request.requestURI
        val httpMethod = request.method
        val body = JSONObject(request.parameterMap).toString()

        val httpGenerateLoggingContext: HashMap<String, String> = generateLoggingContext(joinPoint)
        httpGenerateLoggingContext.putAll(
            hashMapOf<String, String>(
                "Header" to request.headerNames.toString(),
                "RequestURI" to requestURI,
                "HttpMethod" to httpMethod,
                "Body" to body,
            )
        )

        return httpGenerateLoggingContext
    }

    private fun generateLoggingContext(joinPoint: JoinPoint): HashMap<String, String> {
        val className = joinPoint.signature.declaringTypeName
        val methodName = joinPoint.signature.name

        return hashMapOf<String, String>(
            "Controller" to className,
            "Method" to methodName,
        )
    }
}