package com.aclic.lottery.Utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class MailUtil {
    /**
     * Text或者HTML格式邮件的方法
     *
     * @param text           要发送的内容
     * @param subject        邮件的主题也就是邮件的标题
     * @param emailAdress    目的地
     * @param javaMailSender 发送邮件的核心类（在xml文件中已经配置好了）
     * @return
     * @throws
     */
    public int sendMail(String text, String subject, String emailAdress,
                           JavaMailSender javaMailSender) {
        MimeMessage mMessage = javaMailSender.createMimeMessage();// 创建邮件对象
        MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
        try {
            // 从配置文件中拿到发件人邮箱地址
            prop.load(this.getClass().getResourceAsStream("/mail.properties"));
            String from = prop.get("mail.smtp.username") + "";
            mMessageHelper = new MimeMessageHelper(mMessage, true, "UTF-8");
            // 发件人邮箱
            mMessageHelper.setFrom(from);
            // 收件人邮箱
            mMessageHelper.setTo(emailAdress);
            // 邮件的主题也就是邮件的标题
            mMessageHelper.setSubject(subject);
            // 邮件的文本内容，true表示文本以html格式打开
            mMessageHelper.setText(text, false);

            mMessage.addRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(prop.getProperty("mail.smtp.username")));

            javaMailSender.send(mMessage);// 发送邮件
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
