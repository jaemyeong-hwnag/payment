package com.hjm.payment.domain.user.util

import com.hjm.payment.domain.user.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.createAuthorityList()
    override fun getPassword(): String {
        // FIXME mock 실제 데이터로 변경 필요
        return "test"
    }
    override fun getUsername(): String = user.userName
    override fun isAccountNonExpired(): Boolean = isEnabled
    override fun isAccountNonLocked(): Boolean = isEnabled
    override fun isCredentialsNonExpired(): Boolean = isEnabled
    override fun isEnabled(): Boolean {
        return true
    }
}