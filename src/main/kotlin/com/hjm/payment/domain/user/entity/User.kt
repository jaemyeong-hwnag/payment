package com.hjm.payment.domain.user.entity

import com.hjm.payment.global.common.UseYn
import jakarta.persistence.*
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.util.Date

@Table(name = "user")
@Entity(name = "user")
@DynamicUpdate
@DynamicInsert
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long,
    @Column(name = "user_name", nullable = false)
    val userName: String,
    @Column(name = "account", nullable = false, unique = true)
    val account: String,
    @Column(name = "password", nullable = false)
    val password: String,
    @Column(name = "use_yn", nullable = true)
    val useYn: UseYn = UseYn.USED,
    @Column(name = "create_at", nullable = true)
    val createAt: Date,
    @Column(name = "update_at", nullable = true)
    val updateAt: Date,
)