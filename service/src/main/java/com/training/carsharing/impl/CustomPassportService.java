package com.training.carsharing.impl;

import com.training.carsharing.PassportService;
import com.training.carsharing.model.impl.Passport;
import com.training.carsharing.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomPassportService extends CustomAbstractService<Passport, Long> implements PassportService {

    @Autowired
    private PassportRepository passportRepository;

    public CustomPassportService() {
        super(Passport.class);
    }

    public List<Passport> findByUserAccountIdIsNull(){
        return passportRepository.findByUserAccountIdIsNull();
    }

    public Passport findByUserAccountId(Long id){
        return passportRepository.findByUserAccountId(id);
    }
}
