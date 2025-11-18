package com.dagawon.web.common.auth.pwd.svc;


import com.dagawon.web.common.commSvc.MailSvc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 회원 패스워드
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PwdSvc {

    private final MailSvc mailSvc;

    /**
     * 패스워드 인증 메일 발송 API
     *
     * @param :
     * @return :
     */
    @Transactional
    public void sendAuthCodeMail() throws Exception {
        mailSvc.sendSimpleMail("발송할 이메일", "테스트", "메일 발송 성공!");
    }

}
