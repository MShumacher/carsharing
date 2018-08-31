package com.training.carsharing.controller.db;

import com.training.carsharing.BrandService;
import com.training.carsharing.exception.ResourceNotFoundException;
import com.training.carsharing.model.impl.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BrandRestController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    // то же, что и @RequestMapping(value="/brands", method=RequestMethod.GET)
    public List<Brand> getAllBrands() {
        return brandService.findAll();
    }

    @PostMapping("/brands")
    public Brand createBrand(@Valid @RequestBody Brand brand) {
        return brandService.save(brand);
    }

    @GetMapping("/brands/{id}")
    public Brand getBrandById(@PathVariable(value = "id") Long id) {
        return Optional.ofNullable(brandService.findById(id)).orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));
    }

    // Update a Brand
    @PutMapping("/brands/{id}")
    public Brand updateBrand(@PathVariable(value = "id") Long brandId,
                             @Valid @RequestBody Brand brandDetails) {

        Brand brand = Optional.ofNullable(brandService.findById(brandId))
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", brandId));

        brand.setName(brandDetails.getName());

        Brand updatedBrand = brandService.save(brand);
        return updatedBrand;
    }

    // Delete a Brand
    @DeleteMapping("/brands/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable(value = "id") Long brandId) {
        Brand brand = Optional.ofNullable(brandService.findById(brandId))
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", brandId));

        brandService.delete(brand);

        return ResponseEntity.ok().build();
    }
}