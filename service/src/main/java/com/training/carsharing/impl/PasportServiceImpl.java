package com.training.carsharing.impl;

import com.training.carsharing.IPassportService;
import com.training.carsharing.dao.IPassportDao;
import com.training.carsharing.model.IPassport;
import com.training.carsharing.model.impl.Passport;
import org.springframework.stereotype.Service;

@Service
public class PasportServiceImpl extends AbstractServiceImpl<IPassport, IPassportDao, Integer> implements IPassportService {

    public PasportServiceImpl() { super(Passport.class); }

}
