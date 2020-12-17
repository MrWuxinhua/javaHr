package com.wuxinhua.emailserver.receiver;

import com.wuxinhua.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.lang.model.SourceVersion;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class MailReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;


    @Autowired
    private TemplateEngine templateEngine;

    //注解是监听队列
    @RabbitListener(queues = "wuxinhua.mail")
    public void handler(Employee employee) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper msgHelp = new MimeMessageHelper(mimeMessage);
        try {
            msgHelp.setTo(employee.getEmail());
            msgHelp.setFrom(mailProperties.getUsername());
            msgHelp.setSentDate(new Date());
            msgHelp.setSubject("入职欢迎");

            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("positionName", employee.getPosition().getName());
            context.setVariable("jobLevelName", employee.getJobLevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            msgHelp.setText(mail,true);
            javaMailSender.send(mimeMessage);
            logger.info("邮件发送成功 ： " + employee.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("邮件发送失败 : " + employee.getEmail() + " " + e.getMessage());
        }

    }

}
