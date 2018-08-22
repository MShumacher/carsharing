package com.training.carsharing.controller;

import com.training.carsharing.BrandService;
import com.training.carsharing.model.impl.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/")
    public Brand index() {
        Brand brand = brandService.createEntity();
        brand.setName("Audi");
// return       brandService.save(brand);
        return brandService.findOneFullInfo(442L);
 //       return brandService.findById(259L);
    }

}