package com.training.carsharing.impl;

import com.training.carsharing.BrandService;
import com.training.carsharing.model.impl.Brand;
import com.training.carsharing.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomBrandService extends CustomAbstractService<Brand, Long> implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public CustomBrandService() {
        super(Brand.class);
    }

    public List<Brand> findByName(String name) {
        final List<Brand> entityByName = brandRepository.findByName(name);
        LOGGER.info("entitiesByName[{}]: {}", name, entityByName);
        return entityByName;
    }
}
