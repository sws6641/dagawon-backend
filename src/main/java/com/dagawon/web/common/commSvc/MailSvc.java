package com.dagawon.web.common.commSvc;


import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class MailSvc {

    private final JavaMailSender mailSender;

    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("noreply.myapp@gmail.com");

        mailSender.send(message);
    }


    // HTML 메일 전송
    public void sendHtmlMail(String to, String subject, String html) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true); // 두 번째 파라미터 = true → HTML 모드
            helper.setFrom("dagaone123@gmail.com");

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("HTML 메일 전송 실패", e);
            throw new RuntimeException("메일 전송 중 오류가 발생했습니다.");
        }
    }
}
