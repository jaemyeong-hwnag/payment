package com.hjm.payment.global.common

enum class Environment() {
    LOCAL,
    DEVELOPMENT,
    QA,
    STAGING,
    PRODUCTION;

    fun isEqual(): Boolean {
        val environmentValue: String? = System.getenv("ENVIRONMENT")
        val environment: String = if (environmentValue.isNullOrBlank()) LOCAL.toString() else environmentValue

        return this.toString() == environment
    }
}