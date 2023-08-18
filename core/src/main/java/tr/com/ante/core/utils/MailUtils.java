package tr.com.ante.core.utils;

import org.springframework.mail.SimpleMailMessage;

public class MailUtils {

    public static SimpleMailMessage generateMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        return message;
    }
}
