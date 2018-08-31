package com.training.carsharing;

import com.training.carsharing.impl.AuditorAwareImpl;
import com.training.carsharing.model.impl.Brand;
import com.training.carsharing.model.impl.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Random;

//@SpringBootApplication
//@EnableJpaAuditing
public class Application implements CommandLineRunner {

//    @Bean
//    public AuditorAware<UserAccount> auditorProvider() {
//        return new AuditorAwareImpl();
//    }

//    private resources final Logger LOGGER = LoggerFactory.getLogger("Application");
//    private resources final Random RANDOM = new Random();
//
//    @Autowired
//    BrandService brandService;
//
//    public resources void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//
//    //@Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {
//
//        LOGGER.info("\n1.save(Brand brand)...");
//        LOGGER.info(saveNewBrand().toString());
//
//
//        LOGGER.info("\n2.findAll()...");
//        for (Brand brand : brandService.findAll()) {
//            LOGGER.info(brand.toString());
//        }
//
//        LOGGER.info("\n3.findByName(String name)...");
//        for (Brand brand : brandService.findByName("Audi")) {
//            LOGGER.info(brand.toString());
//        }
//
//        LOGGER.info("Done!");
    }
//
//    protected Brand saveNewBrand() {
//        final Brand entity = brandService.createEntity();
//        entity.setName("Name-" + getRandomPrefix());
//        return brandService.save(entity);
//    }
//
//    protected int getRandomPrefix() {
//        return RANDOM.nextInt(99999);
//    }
}
