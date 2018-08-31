package com.training.carsharing.controller.db;

import com.training.carsharing.dto.FuelDto;
import com.training.carsharing.model.impl.Fuel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/fuel")
public class FuelController extends AbstractController<Fuel, FuelDto, Long> {

    protected FuelController() {
        super(FuelDto.class);
    }
}

