package com.project.underline.email.service;

import com.project.underline.email.web.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender emailSender;

    public String sendSimpleMessage(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply");
        message.setTo(mailDto.getEmail());
        message.setSubject("underline 에서 보낸 email 확인용 메일입니다.");

        String checkNumber = createNumber();

        message.setText(
                "방문해주셔서 감사합니다." +
                "인증 번호는 " + checkNumber + " 입니다." +
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요."
        );
        emailSender.send(message);

        return checkNumber;
    }

    private String createNumber() {
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        return String.valueOf(checkNum);
    }
}
