package com.release.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yancheng
 * @since 2023/1/31
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements Serializable, UserDetails {

    /**
     * 用户
     */
    private User user;

    /**
     * 存储权限信息
     */
    private List<String> permissions;

    /**
     * 存储SpringSecurity所需要的权限信息的集合
     */
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    /**
     * 获取权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities !=null){
            return authorities;
        }
        //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getStudentName();
    }

    /**
     * 指示用户的帐户是否已过期。无法验证过期的帐户。
     * 如果用户的帐户有效（即未过期），则返回true；如果不再有效（即过期），返回false
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指示用户是锁定还是解锁。无法验证锁定的用户。
     * 如果用户未锁定，则返回true，否则返回false
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 如果用户的凭据有效（即未过期），则返回true；
     * 如果不再有效（即过期），返回false
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是启用还是禁用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


}
