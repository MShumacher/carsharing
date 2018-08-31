package com.training.carsharing.controller.db;

import com.training.carsharing.dto.CarParameterDto;
import com.training.carsharing.model.impl.CarParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/carparameter")
public class CarParameterController extends AbstractController<CarParameter, CarParameterDto, Long> {

    protected CarParameterController() {
        super(CarParameterDto.class);
    }
}

