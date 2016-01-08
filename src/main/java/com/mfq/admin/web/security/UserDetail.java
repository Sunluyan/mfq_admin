package com.mfq.admin.web.security;

import java.util.Collection;
import java.util.Collections;

import com.mfq.admin.web.bean.SysUser;
import com.mfq.admin.web.constants.Status;

/**
 * 管理后台用户上下文信息
 * 
 */
public class UserDetail {

    SysUser sysUser;
    Collection<Long> authorities;

    public UserDetail(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public UserDetail(SysUser sysUser, Collection<Long> authorities) {
        this.sysUser = sysUser;
        this.authorities = Collections.unmodifiableCollection(authorities);
    }

    public Collection<Long> getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getUsername() {
        return sysUser.getUsername();
    }

    public boolean isEnabled() {
        return sysUser.getStatus() == null ? false
                : sysUser.getStatus() == Status.NORMAL;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    @Override
    public String toString() {
        return String.format(
                "UserDetail [getUsername()=%s, getAuthorities()=%s]",
                getUsername(), getAuthorities());
    }

}
