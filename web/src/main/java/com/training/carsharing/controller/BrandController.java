package com.training.carsharing.controller;

import com.training.carsharing.dto.BrandDto;
import com.training.carsharing.model.impl.Brand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/brand")
public class BrandController extends AbstractController<Brand, BrandDto, Long> {

    protected BrandController() {
        super(BrandDto.class);
    }
}

