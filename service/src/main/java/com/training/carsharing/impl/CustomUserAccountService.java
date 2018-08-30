package com.training.carsharing.impl;

import com.training.carsharing.UserAccountService;
import com.training.carsharing.model.enums.Role;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class CustomUserAccountService extends CustomAbstractService<UserAccount, Long> implements UserAccountService {

    public CustomUserAccountService() {
        super(UserAccount.class);
    }

    @Autowired
    private UserAccountRepository userAccountRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

//    @Override
//    public String getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
//        }
//
//        return ((User) authentication.getPrincipal()).getUsername();
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserAccount getCurrentUserAccount() {
        //TODO get current user
        UserAccount userAccount = userAccountRepository.findByEmail("admin@itools.ru").get(0);
        //       final UserAccount userAccount = userAccountRepository.findById(23L).get();
        return userAccount;
    }

    @Override
    public UserAccount getByEmail(String email){
        List<UserAccount> byEmail = userAccountRepository.findByEmail(email);
        UserAccount userAccount = byEmail.get(0);
        return userAccount;
    }

    @Override
    public boolean isPasswordCorrect(final UserAccount userAccount, final String password) {
//        return (passwordEncoder.matches(password, userAccount.getPassword()));
        return true;
    }
}
