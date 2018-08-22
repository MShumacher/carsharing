package com.training.carsharing.config;

import com.training.carsharing.impl.AuditorAwareImpl;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.training.carsharing.impl")
@Import({JpaConfig.class})
@EnableJpaAuditing
public class ServiceConfig {

    @Bean
    public AuditorAware<UserAccount> auditorProvider() {
        return new AuditorAwareImpl();
    }
//    @Value("${java.mail.username}") String mailUsername;
//    @Value("${java.mail.password}") String mailPassword;
//    @Value("${java.mail.host}") String mailHost;
//
//    /**
//     *  <bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
//     */
//    @Bean(name = "mailSender")
//    public JavaMailSenderImpl getJavaMailSender() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setUsername(mailUsername);
//        javaMailSender.setPassword(mailPassword);
//        javaMailSender.setPort(465);
//
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.auth", true);
//        javaMailProperties.put("mail.smtp.starttls.enable", true);
//        javaMailProperties.put("mail.smtp.starttls.required", true);
//        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        javaMailProperties.put("mail.smtp.host", mailHost);
//
//        javaMailSender.setJavaMailProperties(javaMailProperties);
//        return javaMailSender;
//    }
//
//    /**
//     * <bean id="velocityEngine" class="org.springframework.ui.velocity.VelocityEngineFactoryBean">
//     */
//    @Bean(name = "velocityEngine")
//    public VelocityEngineFactoryBean getVelocityEngineFactoryBean() {
//        VelocityEngineFactoryBean bean = new VelocityEngineFactoryBean();
//        bean.setResourceLoaderPath("/WEB-INF/email-templates/");
//        return bean;
//    }


//        <!--<bean name="bcryptEncoder"-->
//          <!--class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" />-->
}
