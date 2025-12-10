package com.dagawon.web.common.commSvc;



import com.dagawon.web.common.dto.TbMailLogDto;
import com.dagawon.web.common.entity.TbMailLog;
import com.dagawon.web.common.mapper.TbMailLogMapper;
import com.dagawon.web.common.repo.TbMailLogRepository;
import com.dagawon.web.config.exception.DefaultException;
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
    private final TransactionHandler transactionHandler;


    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("noreply.myapp@gmail.com");

        mailSender.send(message);
    }


    // HTML 메일 전송
    public Long sendHtmlMail(String toEmail , String fromEmail , String subject, String htmlContent) {


        // 1. TB_MAIL_LOG 저장(발송 전)
        TbMailLogDto logInfo = TbMailLogDto.builder()
                .toEmail(toEmail)
                .mailTypeCd("10")
                .subject(subject)
                .content(htmlContent)
                .sendStatusYn("N")
                .fromEmail(fromEmail)
                .sendDt(LocalDateTime.now())
                .build();
        logInfo = transactionHandler.savePreMailLog(logInfo);

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // 두 번째 파라미터 = true → HTML 모드
            helper.setFrom(fromEmail);

            //메일 발송
            mailSender.send(mimeMessage);

            // 성공 시 로그 업데이트
            logInfo.setSendStatusYn("Y");
            transactionHandler.savePreMailLog(logInfo);

        } catch (Exception e) {
            log.error("HTML 메일 전송 실패", e);
            logInfo.setErrorMsg(e.getMessage());
            logInfo.setSendStatusYn("E");
            transactionHandler.savePreMailLog(logInfo);
            throw new DefaultException("HTML 메일 전송 실패: " + e.getMessage());
        }

        return  logInfo.getMailLogId();
    }
}
