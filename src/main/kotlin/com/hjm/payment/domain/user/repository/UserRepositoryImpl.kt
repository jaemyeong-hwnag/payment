package com.hjm.payment.domain.user.repository

import com.hjm.payment.domain.user.entity.QUser
import com.hjm.payment.domain.user.entity.User
import com.hjm.payment.global.common.UseYn
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(val jpaQueryFactory: JPAQueryFactory): UserRepositoryCustom {
    private val user: QUser = QUser.user

    override fun findByUserName(userName: String): User? {
        return jpaQueryFactory
            .selectFrom(user)
            .where(user.userName.eq(userName))
            .fetchFirst()
    }

    override fun findByUserAccount(account: String): User? {
        return jpaQueryFactory
            .selectFrom(user)
            .where(user.account.eq(account))
            .fetchFirst()
    }

    override fun findByAccountAndPassword(account: String, password: String): User? {
        return jpaQueryFactory
            .selectFrom(user)
            .where(
                user.account.eq(account), user.password.eq(password), user.useYn.eq(UseYn.USED)
            )
            .fetchFirst()
    }
}