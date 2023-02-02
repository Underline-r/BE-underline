package com.project.underline.email.service;

import com.project.underline.email.entity.MailHistory;
import com.project.underline.email.entity.repository.MailHistoryRepository;
import com.project.underline.email.web.MailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender emailSender;
    private final MailHistoryRepository repository;

    // helper 에 값을 담을 때 MessagingException을 처리해주어야만 함
    public String sendSimpleMessage(MailDto mailDto) throws MessagingException {
        String checkNumber = createNumber();
        boolean successFlag = false;
        String logMessage = "SUCCESS";
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

            helper.setSubject("underline 에서 보낸 email 확인용 메일입니다.");
            helper.setTo(mailDto.getEmail());
            helper.setFrom("no-reply");
            // TODO : domain 있거나, smtp 구축, 일단 신규 계정 사용

            helper.setText(
                    "방문해주셔서 감사합니다." +
                            "<br>" +
                            "인증 번호는 " + "<b>" + checkNumber + "</b>" + " 입니다." +
                            "<br>" +
                            "해당 인증번호를 인증번호 확인란에 기입하여 주세요."
                    , true);
            emailSender.send(message);

            successFlag = true;
        } catch (Exception e) {
            logMessage = e.getMessage();
            log.error(logMessage);
            throw e;
        } finally {
            MailHistory history = new MailHistory(mailDto.getEmail(), successFlag, logMessage);
            repository.save(history);
        }

        return checkNumber;
    }

    private String createNumber() {
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        return String.valueOf(checkNum);
    }
}
