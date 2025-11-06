package com.dagawon.web.common.userAuth;


import com.dagawon.web.common.dto.TbMembDto;
import com.dagawon.web.common.repo.TbMembRepository;
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
    private final String membNo;
    @Getter
    private final String membNm;
    @Getter
    private final String bizNo;

    public UserPrincipal(String loginId, String membNo, String membNm, String bizNo) {
        this.loginId = loginId;
        this.membNo = membNo;
        this.membNm = membNm;
        this.bizNo = bizNo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();  // 권한이 필요하지 않다면 빈 리스트를 반환
    }

    // TODO: 데이터베이스 필드에 맞게 수정 필요
    public static UserPrincipal create(TbMembDto user) {
        return new UserPrincipal(
                user.getMembId(),
                user.getMembId(),
                user.getMembNm(),
                user.getMembNm());
    }

    public String getId() {
        return loginId;
    }

    public String getMembNo() {
        return membNo;
    }

    @Override
    public String getUsername() {
        return membNm;
    }

    public String getBizNo() {
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
