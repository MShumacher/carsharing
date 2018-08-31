package com.training.carsharing.controller;

import com.training.carsharing.AbstractService;
import com.training.carsharing.BrandService;
import com.training.carsharing.GearboxService;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.ListDto;
import com.training.carsharing.model.impl.Brand;
import com.training.carsharing.model.impl.Gearbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractController<ENTITY, DTO, ID> implements DBController<ENTITY, DTO, ID> {

    private static final String ADD_MAPPING = "/add";
    private static final String DELETE_MAPPING = "/{id}/delete";
    private static final String FORM_MODEL_ATTRIBUTE_NAME = "formModel";
    private static final String VIEW_DETAILS_MAPPING = "/{id}";
    private static final String EDIT_MAPPING = "/{id}/edit";

    private static final String MAIN_VIEW_POSTFIX = ".list";
    private static final String EDIT_VIEW_POSTFIX = ".edit";
    private static final String MAIN_REDIRECT_PREFIX = "redirect:/";

    private static final String PAGE = "page";
    private static final String SORT = "sort";
    private static final String PATH_VARIABLE_ID = "id";
    private static final String ERROR = "error";
    private static final String READONLY = "readonly";
    private static final String BRAND_FORM_SELECT_NAME = "brandChoices";
    private static final String GEARBOX_FORM_SELECT_NAME = "gearboxChoices";

    @Autowired
    private AbstractToDtoConverter<ENTITY, DTO> toDtoConverter;
    @Autowired
    private AbstractFromDtoConverter<DTO, ENTITY> fromDtoConverter;
    @Autowired
    private AbstractService<ENTITY, ID> service;
    @Autowired
    private BrandService brandService;
    @Autowired
    private GearboxService gearboxService;

    private final Class<? extends DTO> dtoClass;

    private String requestMapping;

    public AbstractController(Class<? extends DTO> dtoClass) {
        this.dtoClass = dtoClass;
        final String dtoClassSimpleName = dtoClass.getSimpleName();
        this.requestMapping = dtoClassSimpleName.substring(0, dtoClassSimpleName.length() - 3).toLowerCase();
    }

    @Override
    @GetMapping()
    public ModelAndView getIndexModelAndView(HttpServletRequest req,
                                             @RequestParam(name = PAGE, required = false) final Integer pageNumber,
                                             @RequestParam(name = SORT, required = false) final String sortColumn) {
        String viewName = requestMapping + MAIN_VIEW_POSTFIX;
        final ListDto<DTO> listDto = getListDto(req);
        listDto.setPage(pageNumber);
        listDto.setSort(sortColumn);
        List<ENTITY> entities = null;
        if (listDto.getSort() == null) {
            entities = service.findAll(listDto.getPage(), listDto.getItemsPerPage());
        } else {
            entities = service.findAll(listDto.getPage(), listDto.getItemsPerPage(), listDto.getSort().getColumn(), listDto.getSort().isAscending());
        }
        listDto.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));
        listDto.setTotalCount(service.count());

        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(ListDto.SESSION_ATTR_NAME, listDto);
//        req.setAttribute(ListDto.SESSION_ATTR_NAME, listDto);
//        return "brand";
        return new ModelAndView(viewName, hashMap);
    }

    @Override
    @GetMapping(ADD_MAPPING)
    public ModelAndView getAddModelAndView() {
        String viewName = requestMapping + EDIT_VIEW_POSTFIX;
        final Map<String, Object> hashMap = getHashMapWithAllCommonForms();
        try {
            final DTO dto = dtoClass.newInstance();
            hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, dto);
        } catch (InstantiationException | IllegalAccessException e) {
            //TODo something with this
            e.printStackTrace();
        }
        return new ModelAndView(viewName, hashMap);
    }

    @Override
    @PostMapping()
    public Object getSaveModelAndView(DTO formModel, BindingResult result) {
        String viewName = requestMapping + EDIT_VIEW_POSTFIX;
        String viewRedirect = MAIN_REDIRECT_PREFIX + requestMapping;
        Map<String, Object> hashMap = null;
        if (result.hasErrors()) {
            hashMap = getHashMapWithAllCommonForms();
            hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, formModel);
            return new ModelAndView(viewName, hashMap);
        } else {
            try {
                final ENTITY entity = fromDtoConverter.apply(formModel);
                service.save(entity);
            } catch (ObjectOptimisticLockingFailureException | PersistenceException e) {
                hashMap = getHashMapWithAllCommonForms();
                hashMap.put(ERROR, e.getClass().getSimpleName());
                return new ModelAndView(viewName, hashMap);
            }
            return viewRedirect;
        }
    }

    @Override
    @GetMapping(DELETE_MAPPING)
    public String deleteEntity(@PathVariable(name = PATH_VARIABLE_ID) final ID id) {
        String viewRedirect = MAIN_REDIRECT_PREFIX + requestMapping;
        service.delete(service.findById(id));
        return viewRedirect;
    }

    @Override
    @GetMapping(VIEW_DETAILS_MAPPING)
    public ModelAndView getViewDetailsModelAndView(@PathVariable(name = PATH_VARIABLE_ID) final ID id) {
        String viewName = requestMapping + EDIT_VIEW_POSTFIX;
        final ENTITY dbModel = service.findOneFullInfo(id);
        final DTO dto = toDtoConverter.apply(dbModel);
        final Map<String, Object> hashMap = getHashMapWithAllCommonForms();
        hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, dto);
        hashMap.put(READONLY, true);
        return new ModelAndView(viewName, hashMap);
    }

    @Override
    @GetMapping(EDIT_MAPPING)
    public ModelAndView getEditModelAndView(@PathVariable(name = PATH_VARIABLE_ID) final ID id) {
        String viewName = requestMapping + EDIT_VIEW_POSTFIX;
        final DTO dto = toDtoConverter.apply(service.findOneFullInfo(id));
        final Map<String, Object> hashMap = getHashMapWithAllCommonForms();
        hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, dto);
        return new ModelAndView(viewName, hashMap);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms() {
        final Map<String, Object> hashMap = new HashMap<>();
        return hashMap;
    }

    protected void loadCommonFormBrands(final Map<String, Object> hashMap) {
        final Map<Long, String> brandsMap = brandService.findAll().stream()
                .collect(Collectors.toMap(Brand::getId, Brand::getName));
        hashMap.put(BRAND_FORM_SELECT_NAME, getSortedMapByValue(brandsMap));
    }

    protected void loadCommonFormGearboxes(final Map<String, Object> hashMap) {
        final Map<Long, String> gearboxesMap = gearboxService.findAll().stream()
                .collect(Collectors.toMap(Gearbox::getId, Gearbox::getName));
        hashMap.put(GEARBOX_FORM_SELECT_NAME, getSortedMapByValue(gearboxesMap));
    }

    private Map<Long, String> getSortedMapByValue(final Map<Long, String> map) {
        final Map<Long, String> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }


    private ListDto<DTO> getListDto(final HttpServletRequest req) {
        final String sessionModelName = getClass().getSimpleName() + "_LIST_MODEL";
        ListDto<DTO> listDto = (ListDto<DTO>) req.getSession().getAttribute(sessionModelName);
        if (listDto == null) {
            listDto = new ListDto<DTO>();
            req.getSession().setAttribute(sessionModelName, listDto);
        }
        return listDto;
    }
}