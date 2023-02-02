package com.idb.reminder.Utils.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSenderUtil {
    @Autowired
    private JavaMailSender emailSender;

    public Boolean sendHtmlEmail(String subject, String htmlMsgContent, String[] receiver, String[] ccReceiver) throws MessagingException {
        // Boolean success = false;

        MimeMessage message = emailSender.createMimeMessage();

        Boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3>Reminder email from iDB</h3>" +
            "<img src='https://2sao.vietnamnetjsc.vn/images/2020/07/06/17/45/IU.jpg' alt='My Idol IU' />";

        if(htmlMsgContent != null) {
            htmlMsg = htmlMsgContent;
        }

        // message.setContent(htmlMsg, "text/html");
        message.setContent(htmlMsg, "text/html; charset=UTF-8");

        // message.setFrom("noreply@idb.com.vn");

        helper.setFrom("noreply@gmail.com");

        helper.setTo(receiver);

        helper.setCc(ccReceiver);

        helper.setSubject(subject);

        emailSender.send(message);

       return true;
    }
}
