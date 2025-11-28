package com.dagawon.web.common.commSvc;


import com.dagawon.web.common.dto.TbMailLogDto;
import com.dagawon.web.common.entity.TbMailLog;
import com.dagawon.web.common.mapper.TbMailLogMapper;
import com.dagawon.web.common.repo.TbMailLogRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class MailSvc {

    private final JavaMailSender mailSender;
    private final TbMailLogRepository tbMailLogRepository;
    private final TbMailLogMapper tbMailLogMapper;

    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("noreply.myapp@gmail.com");

        mailSender.send(message);
    }


    // HTML 메일 전송
    public Long sendHtmlMail(String toEmail , String subject, String html) {

        // TODO : 발송이메일 관리 필요
        String fromEmail = "dagaone123@gmail.com";
        long mailLogId = 0L;

        // 1. TB_MAIL_LOG 저장(발송 전)
        TbMailLogDto logInfo = TbMailLogDto.builder()
                .toEmail(toEmail)
                .mailTypeCd("10")
                .subject(subject)
                .contentSummary(html)
                .sendStatusYn("N")
                .fromEmail(fromEmail)
                .sendDt(LocalDateTime.now())
                .build();
        TbMailLog res = tbMailLogRepository.save(tbMailLogMapper.toEntity(logInfo));
        mailLogId = res.getMailLogId();

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(html, true); // 두 번째 파라미터 = true → HTML 모드
            helper.setFrom("dagaone123@gmail.com");

            //메일 발송
            mailSender.send(mimeMessage);

            // 성공 시 로그 업데이트
            logInfo.setSendStatusYn("Y");
            tbMailLogRepository.save(tbMailLogMapper.toEntity(logInfo));

        } catch (Exception e) {
            log.error("HTML 메일 전송 실패", e);
            logInfo.setErrorMsg(e.getMessage());
            tbMailLogRepository.save(tbMailLogMapper.toEntity(logInfo));
            throw new RuntimeException("메일 전송 중 오류가 발생했습니다.");
        }

        return  mailLogId;
    }
}
