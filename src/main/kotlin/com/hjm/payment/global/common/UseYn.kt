package com.hjm.payment.global.common

enum class UseYn(val value: Char) {
    USED(value = 'y'), UNUSED(value = 'n');

    fun isUsed() = this == USED
    fun isUnused() = this == UNUSED

    companion object {
        infix fun from(value: Char?) = values().firstOrNull { it.value == value }
            ?: throw IllegalArgumentException("Invalid value for ${this::class} enum: $value")

        infix fun from(isUsed: Boolean?) = values().firstOrNull { it.isUsed() == isUsed }
            ?: throw IllegalArgumentException("Invalid boolean for ${this::class} enum: $isUsed")
    }
}