package com.dagawon.web.common.userAuth;


import com.dagawon.web.common.dto.TbMembDto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@ToString
public class UserPrincipal implements UserDetails {

    @Getter
    private final String loginId;
    @Getter
    private final Long membNo;
    @Getter
    private final String membNm;
    @Getter
    private final Long bizNo;

    public UserPrincipal(String loginId, Long membNo, String membNm, Long bizNo) {
        this.loginId = loginId;
        this.membNo = membNo;
        this.membNm = membNm;
        this.bizNo = bizNo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();  // 권한이 필요하지 않다면 빈 리스트를 반환
    }

    public static UserPrincipal create(TbMembDto user) {
        return new UserPrincipal(
                user.getMembId(),
                user.getMembNo(),
                user.getMembNm(),
                user.getBizNo().getBizNo());
    }

    public String getId() {
        return loginId;
    }

    public Long getMembNo() {
        return membNo;
    }

    @Override
    public String getUsername() {
        return membNm;
    }

    public Long getBizNo() {
        return bizNo;
    }

    @Override
    public String getPassword() {
        return null;    // 패스워드가 필요 없으면 null 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
