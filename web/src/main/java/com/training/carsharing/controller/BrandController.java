package com.training.carsharing.controller;

import com.training.carsharing.BrandService;
import com.training.carsharing.converter.BrandToDtoConverter;
import com.training.carsharing.dto.BrandDto;
import com.training.carsharing.model.impl.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandToDtoConverter toDtoConverter;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(final HttpServletRequest req,
                              @RequestParam(name = "page", required = false) final Integer pageNumber,
                              @RequestParam(name = "sort", required = false) final String sortColumn) {

        final List<BrandDto> listDto = getListDto(req);

        final List<Brand> entities = brandService.findAllFullInfo();
        listDto.addAll(entities.stream().map(toDtoConverter).collect(Collectors.toList()));

        final HashMap<String, Object> models = new HashMap<>();
        models.put("listDto", listDto);
        return new ModelAndView("brand.list", models);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
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
            final Brand entity = new Brand();//fromDtoConverter.apply(formModel);
            try {
                brandService.save(entity);
            } catch (PersistenceException e) {
                loadCommonFormBrands(hashMap);
                hashMap.put("error", "PersistenceException");
                return new ModelAndView("brand.edit", hashMap);
            }
            return "redirect:/brand";
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id", required = true) final Long id) {
 //       brandService.delete(id);
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

    private List<BrandDto> getListDto(final HttpServletRequest req) {
        final String sessionModelName = getClass().getSimpleName() + "_LIST_MODEL";

        List<BrandDto> listDto = (List<BrandDto>) req.getSession().getAttribute(sessionModelName);
        if (listDto == null) {
            listDto = new ArrayList<BrandDto>();

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

