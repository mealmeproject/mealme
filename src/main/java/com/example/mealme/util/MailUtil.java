package com.example.mealme.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {

    static final String smtp_host = "smtp.gmail.com";
    static final int smtp_port = 465;  // TLS : 587, SSL : 465

    public static void Send(String emailAddress, String randomCode, String myEmail, String myPw) throws Exception {
        System.out.println("전송할 이메일 주소" + emailAddress + "랜덤 코드" + randomCode);
        System.out.println("id" + myEmail + "password" + myPw);
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtp_host);
        props.put("mail.smtp.port", smtp_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", smtp_host);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myEmail, myPw);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myPw));

            // 받는 이메일
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailAddress)
            );

            // 제목
            message.setSubject("비밀번호 코드입니다..");

            // 내용
            message.setText("보안을 위해 비밀번호를 변경하는 메일입니다.\n\n " +
                    "새로운 비밀번호를 아래의 코드로 변경하였으니 로그인 후 설정에서 비밀번호를 변경하시길 바랍니다.\n\n" +
                    "새 비밀번호 : " + randomCode);

            // 발송
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}