package com.training.carsharing;

import com.training.carsharing.impl.AuditorAwareImpl;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import java.util.Properties;

@SpringBootApplication
@EnableJpaAuditing
public class Application implements CommandLineRunner {

    //    private resources final Logger LOGGER = LoggerFactory.getLogger("Application");
    @Bean
    public AuditorAware<UserAccount> auditorProvider() {
        return new AuditorAwareImpl();
    }


    /**
     * Java Mail Configuration
     */
    @Value("${mail.username}")
    String mailUsername;
    @Value("${mail.password}")
    String mailPassword;
    @Value("${mail.smtp.auth}")
    String mailAuth;
    @Value("${mail.smtp.starttls.enable}")
    String mailStartTtlsEnable;
    @Value("${mail.smtp.host}")
    String mailHost;
    @Value("${mail.smtp.port}")
    String mailPort;
    @Value("${mail.from}")
    String mailFrom;

    @Bean
    public Authenticator getAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        };
    }

    @Bean
    public Session getMySession() {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", mailAuth);
        properties.put("mail.smtp.starttls.enable", mailStartTtlsEnable);
        properties.put("mail.smtp.host", mailHost);
        properties.put("mail.smtp.port", mailPort);
        properties.put("mail.from", mailFrom);

        Session session = Session.getInstance(properties, getAuthenticator());
        return session;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    public void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Transactional(readOnly = true)
    @Override
    public void run(String... args) {

    }

}
