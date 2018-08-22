package com.training.carsharing.impl;

import com.training.carsharing.UserAccountService;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<UserAccount> {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public Optional<UserAccount> getCurrentAuditor() {
        return Optional.ofNullable(userAccountService.getCurrentUserAccount());
    }
}