package com.training.carsharing;

import com.training.carsharing.config.JpaConfig;
import com.training.carsharing.config.ServiceConfig;
import com.training.carsharing.config.WebConfig;
import com.training.carsharing.impl.CustomAbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@SpringBootApplication//(scanBasePackages = {"com.training.carsharing.dto", "com.training.carsharing.controller",  "com.training.carsharing.converter"})
//@EnableConfigurationProperties(WebConfig.class)
@Import({ServiceConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
