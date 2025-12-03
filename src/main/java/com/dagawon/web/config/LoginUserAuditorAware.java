package com.dagawon.web.config;

import com.dagawon.web.common.userAuth.UserAuthSvc;
import com.dagawon.web.common.userAuth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginUserAuditorAware implements AuditorAware<Long> {

    private final UserAuthSvc userAuthSvc;

    @Override
    public Optional<Long> getCurrentAuditor() {
        try {
            UserPrincipal userPrincipal = userAuthSvc.getSessionUser();
            if (StringUtils.hasText(String.valueOf(userPrincipal.getMembNo())))
                return Optional.ofNullable(userPrincipal.getMembNo());

            return Optional.of(0L);
        } catch (Exception e) {
//            return Optional.of("nosession");
            return Optional.of(0L);
        }
    }
}
