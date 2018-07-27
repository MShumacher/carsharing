package com.training.carsharing.impl;

import com.training.carsharing.IDriveService;
import com.training.carsharing.dao.IDriveDao;
import com.training.carsharing.model.IDrive;
import com.training.carsharing.model.impl.Drive;
import org.springframework.stereotype.Service;

@Service
public class DriveServiceImpl extends AbstractServiceImpl<IDrive, IDriveDao, Integer> implements IDriveService {

    public DriveServiceImpl() { super(Drive.class); }

}
