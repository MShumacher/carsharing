package com.training.carsharing.impl;

import com.training.carsharing.DriveService;
import com.training.carsharing.model.impl.Drive;
import com.training.carsharing.repository.DriveRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomDriveService extends CustomAbstractService<Drive, Long> implements DriveService {

    public CustomDriveService() { super(Drive.class); }

}
