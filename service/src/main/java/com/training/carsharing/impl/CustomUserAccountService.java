package com.training.carsharing.impl;

import com.training.carsharing.UserAccountService;
import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
public class CustomUserAccountService extends CustomAbstractService<UserAccount, Long> implements UserAccountService {

    public CustomUserAccountService() {
        super(UserAccount.class);
    }

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        return userAccount;
    }

    @Override
    public UserAccount save(UserAccount entity) throws ObjectOptimisticLockingFailureException, PersistenceException, DataIntegrityViolationException {
        try {
            LOGGER.info("saved entity: {}", entity);
            final String dbPassword = userAccountRepository.findById(entity.getId()).get().getPassword();
            //если новый юзер или пароль не совпадает с тем, что в базе, то кодируем его и сохраняем
            if ((entity.getId() == null) || (passwordEncoder.matches(entity.getPassword(), dbPassword))) {
                entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            }
            return userAccountRepository.save(entity);
        } catch (ObjectOptimisticLockingFailureException | PersistenceException e) {
            LOGGER.warn(e.getClass().getSimpleName()
                    + ": Row was updated/deleted by another transaction (or unsaved-value mapping was incorrect)");
            throw e;
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn(e.getClass().getSimpleName() + ": Duplicate entry");
            throw e;
        }
    }

    @Override
    public UserAccount findByEmail(String email) {
        List<UserAccount> byEmail = userAccountRepository.findByEmail(email);
        UserAccount userAccount = byEmail.get(0);
        return userAccount;
    }

    @Override
    public boolean isPasswordCorrect(final UserAccount userAccount, final String password) {
        //       return password.equals(userAccount.getPassword());
        return (passwordEncoder.matches(password, userAccount.getPassword()));
    }

    @Override
    public List<UserAccount> findWithoutPassport() {
        return userAccountRepository.findWithoutPassport();
    }

    @Override
    public List<UserAccount> findWithoutDrivingLicense() {
        return userAccountRepository.findWithoutDrivingLicense();
    }

    @Override
    public UserAccount findByDrivingLicense(Long id) {
        return userAccountRepository.findByDrivingLicenseId(id);
    }

    @Override
    public UserAccount findByPassport(Long id) {
        return userAccountRepository.findByPassportId(id);
    }

    @Override
    public UserAccount findByVerifyKey(String verifyKey) {
        return userAccountRepository.findByVerifyKey(verifyKey);
    }
}
