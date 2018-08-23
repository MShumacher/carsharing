package com.training.carsharing;

import com.training.carsharing.impl.AuditorAwareImpl;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication//(scanBasePackages = {"com.training.carsharing.dto", "com.training.carsharing.controller",  "com.training.carsharing.converter"})
//@EnableConfigurationProperties(WebConfig.class)
@EnableJpaAuditing
public class Application {

    @Bean
    public AuditorAware<UserAccount> auditorProvider() {
        return new AuditorAwareImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
