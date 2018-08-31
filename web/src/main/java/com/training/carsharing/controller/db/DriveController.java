package com.training.carsharing.controller.db;

import com.training.carsharing.dto.DriveDto;
import com.training.carsharing.model.impl.Drive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/drive")
public class DriveController extends AbstractController<Drive, DriveDto, Long> {

    protected DriveController() {
        super(DriveDto.class);
    }
}

