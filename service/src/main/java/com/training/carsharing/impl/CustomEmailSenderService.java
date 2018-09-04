package com.training.carsharing.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.training.carsharing.EmailSenderService;
import com.training.carsharing.model.impl.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomEmailSenderService implements EmailSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger("emailSenderLogger");

    @Autowired
    private Session mailSession;

    private void sendEmail(final String to, final String subject, final String textBody, final String htmlBody) throws MessagingException {
        final MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
            final InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());

            final Multipart content = new MimeMultipart("alternative");
            final MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(textBody, "text/plain");
            content.addBodyPart(textPart);
            final MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html;charset=\"UTF-8\"");
            message.setSubject(subject, StandardCharsets.UTF_8.name());
            content.addBodyPart(htmlPart);
            message.setContent(content);
            Transport.send(message);
        } catch (final MessagingException e) {
            LOGGER.warn(e.getMessage());
            throw e;
        }
    }

//    @Override
//    public void sendEmailFromWebSite(final IMessage entity) throws MessagingException {
//        final String name = entity.getName();
//        final String phone = entity.getPhone();
//        final String emailFrom = entity.getEmail();
//        final String message = entity.getMessage();
//        final String subject = "Message for Shmotel from " + name;
//        final String text = String.format("You have a message:\n name: %s, phone: %s, email: %s\n\n Message:\n%s", name,
//                phone, emailFrom, message);
//        final String html = String.format("You have a message:<br> name: %s, phone: %s, email: %s<br><br> Message:\n%s",
//                name, phone, emailFrom, message);
//        try {
//            sendEmail(mailSession.getProperty("mail.from"), subject, text, html);
//            LOGGER.info("Message from website was sended to default email");
//        } catch (MessagingException e) {
//            throw e;
//        }
//    }

    @Override
    public void sendVerifyKey(final UserAccount userAccount, final String verifyKey) throws MessagingException {
        final String email = userAccount.getEmail();
        final String subject = "Verify key from carsharing company";
        final String name = userAccount.getName();
        final String url = "http://localhost:8080/registration/verifying/";
        final String fullUrl = url + verifyKey;
        final String text = String.format("Dear %s, thanks for registering. \n Your verify key: %s"
                + "\n You can enter it here:%s. Or you can go with link: %s"
                + "\n\n Don't answer this e-mail, please! ", name, verifyKey, url, fullUrl);
        final String html = String.format("Dear %s, thanks for registering. <br> Your verify key: %s"
                + "<br> You can enter it <a href=\"%s\">here</a>. Or you can go with link: <a href=\"%s\">%s</a>"
                + "<br><br> Don't answer this e-mail, please! ", name, verifyKey, url, fullUrl, fullUrl);
        try {
            sendEmail(email, subject, text, html);
            LOGGER.info("verify key {} was send to email {}", verifyKey, userAccount.getEmail());
        } catch (MessagingException e) {
            throw e;
        }
    }
}
