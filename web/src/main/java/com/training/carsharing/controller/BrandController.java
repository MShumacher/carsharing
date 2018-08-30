package com.training.carsharing.controller;

import com.training.carsharing.BrandService;
import com.training.carsharing.converter.BrandFromDtoConverter;
import com.training.carsharing.converter.BrandToDtoConverter;
import com.training.carsharing.dto.BrandDto;
import com.training.carsharing.dto.ListDto;
import com.training.carsharing.model.impl.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandToDtoConverter toDtoConverter;
    @Autowired
    private BrandFromDtoConverter fromDtoConverter;

    @GetMapping()
    public ModelAndView brand(final HttpServletRequest req,
                              @RequestParam(name = "page", required = false) final Integer pageNumber,
                              @RequestParam(name = "sort", required = false) final String sortColumn) {

        final ListDto<BrandDto> listDto = getListDto(req);
        listDto.setPage(pageNumber);
        listDto.setSort(sortColumn);
        List<Brand> entities = null;
        if (listDto.getSort() == null) {
            entities = brandService.findAll(listDto.getPage(), listDto.getItemsPerPage());
        } else {
            entities = brandService.findAll(listDto.getPage(), listDto.getItemsPerPage(), listDto.getSort().getColumn(), listDto.getSort().isAscending());
        }
        listDto.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));
        listDto.setTotalCount(brandService.count());

        final HashMap<String, Object> models = new HashMap<>();
        models.put(ListDto.SESSION_ATTR_NAME, listDto);
//        req.setAttribute(ListDto.SESSION_ATTR_NAME, listDto);
//        return "brand";
        return new ModelAndView("brand.list", models);
    }

    @GetMapping("/add")
    public ModelAndView showForm() {
        final Map<String, Object> hashMap = new HashMap<>();
        final BrandDto dto = new BrandDto();
        hashMap.put("formModel", dto);
        loadCommonFormBrands(hashMap);
        return new ModelAndView("brand.edit", hashMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    // @valid стоит для валидации
    public Object save(@Valid @ModelAttribute("formModel") final BrandDto formModel, final BindingResult result) {
        final Map<String, Object> hashMap = new HashMap<>();
        if (result.hasErrors()) {
            hashMap.put("formModel", formModel);
            loadCommonFormBrands(hashMap);
            return new ModelAndView("brand.edit", hashMap);
        } else {
            try {
                final Brand entity = fromDtoConverter.apply(formModel);
                brandService.save(entity);
            } catch (ObjectOptimisticLockingFailureException | PersistenceException e) {
                loadCommonFormBrands(hashMap);
                hashMap.put("error", e.getClass().getSimpleName());
                return new ModelAndView("brand.edit", hashMap);
            }
            return "redirect:/brand";
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id", required = true) final Long id) {
        brandService.delete(brandService.findById(id));
        return "redirect:/brand";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Long id) {
        final Brand dbModel = brandService.findOneFullInfo(id);
        final BrandDto dto = toDtoConverter.apply(dbModel);
        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);
        hashMap.put("readonly", true);
        loadCommonFormBrands(hashMap);
        return new ModelAndView("brand.edit", hashMap);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(name = "id", required = true) final Long id) {
        final BrandDto dto = toDtoConverter.apply(brandService.findOneFullInfo(id));

        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);
        loadCommonFormBrands(hashMap);
        return new ModelAndView("brand.edit", hashMap);
    }

    private ListDto<BrandDto> getListDto(final HttpServletRequest req) {
        final String sessionModelName = getClass().getSimpleName() + "_LIST_MODEL";

        ListDto<BrandDto> listDto = (ListDto<BrandDto>) req.getSession().getAttribute(sessionModelName);
        if (listDto == null) {
            listDto = new ListDto<BrandDto>();

            req.getSession().setAttribute(sessionModelName, listDto);
        }
        return listDto;
    }

    private void loadCommonFormBrands(final Map<String, Object> hashMap) {
        final Map<Long, String> brandsMap = brandService.findAll().stream()
                .collect(Collectors.toMap(Brand::getId, Brand::getName));
        hashMap.put("brandChoices", getSortedMapByValue(brandsMap));
    }

    private Map<Long, String> getSortedMapByValue(final Map<Long, String> map) {
        final Map<Long, String> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }
}

