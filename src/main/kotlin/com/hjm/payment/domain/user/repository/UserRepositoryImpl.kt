package com.hjm.payment.domain.user.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(val jpaQueryFactory: JPAQueryFactory): UserRepositoryCustom {
    // val user:QUser = Quser.user
}