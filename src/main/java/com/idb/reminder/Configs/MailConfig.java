package com.idb.reminder.Configs;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    @Value("${idb.properties.smtp.username}")
    private String username;

    @Value("${idb.properties.smtp.host}")
    private String host;

    @Value("${idb.properties.smtp.password}")
    private String password;

    @Value("${idb.properties.smtp.port}")
    private Integer port;
    
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // mailSender.setHost("smtp.gmail.com");
        mailSender.setHost(host);
        // mailSender.setPort(587);
        mailSender.setPort(port);
        mailSender.setDefaultEncoding("UTF-8");

        // mailSender.setUsername("lethuthao2002.ct@gmail.com");
        mailSender.setUsername(username);
        // mailSender.setPassword("ThuThao@2002");
        // mailSender.setPassword("xaftooofmrexqkdc");
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}