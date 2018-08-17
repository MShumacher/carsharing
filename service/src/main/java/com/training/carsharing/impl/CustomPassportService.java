package com.training.carsharing.impl;

import com.training.carsharing.PassportService;
import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.PassportRepository;
import com.training.carsharing.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomPassportService extends CustomAbstractService<Passport, Integer> implements PassportService {

    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

    public CustomPassportService() {
        super(Passport.class);
    }

    @Override
    public Passport save(final Passport passport) {
        UserAccount userAccount=passport.getUserAccount();
        final Date modifiedOn = new Date();
        passport.setUpdated(modifiedOn);
        if (userAccount.getId() == null) {
            userAccount.setUpdated(modifiedOn);
            userAccount.setCreated(modifiedOn);
            LOGGER.info("new saved entity: {}", userAccount);
            passport.setUserAccount(userAccountRepository.save(userAccount));
        }
        if (passport.getId() == null) {
            passport.setCreated(modifiedOn);
            LOGGER.info("new saved entity: {}", passport);
        } else {
            LOGGER.info("updated entity: {}", passport);
        }
        return (Passport) passportRepository.save(passport);
    }
}
