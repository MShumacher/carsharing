package com.training.carsharing.controller.db;

import com.training.carsharing.dto.GearboxDto;
import com.training.carsharing.model.impl.Gearbox;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/gearbox")
public class GearboxController extends AbstractController<Gearbox, GearboxDto, Long> {

    protected GearboxController() {
        super(GearboxDto.class);
    }

    private final static String MAIN_VIEW_NAME = "gearbox.list";
    private final static String EDIT_VIEW_NAME = "gearbox.edit";
    private final static String MAIN_REDIRECT = "redirect:/gearbox";

//    @GetMapping()
//    public ModelAndView index(final HttpServletRequest req,
//                              @RequestParam(name = PAGE, required = false) final Integer pageNumber,
//                              @RequestParam(name = SORT, required = false) final String sortColumn) {
//        return getIndexModelAndView(req, pageNumber, sortColumn, MAIN_VIEW_NAME);
//    }
//
//    @GetMapping(ADD_MAPPING)
//    public ModelAndView showForm() {
//        return getEditModelAndView(EDIT_VIEW_NAME, getHashMapWithAllCommonForms());
//    }
//
//    @PostMapping()
//    public Object save(@Valid @ModelAttribute(FORM_MODEL_ATTRIBUTE_NAME) final GearboxDto formGearbox, final BindingResult result) {
//        return getSaveModelAndView(formGearbox, result, EDIT_VIEW_NAME, MAIN_REDIRECT, getHashMapWithAllCommonForms());
//    }
//
//    @GetMapping(DELETE_MAPPING)
//    public String delete(@PathVariable(name = PATH_VARIABLE_ID) final Long id) {
//        deleteEntity(id);
//        return MAIN_REDIRECT;
//    }
//
//    @GetMapping(VIEW_DETAILS_MAPPING)
//    public ModelAndView viewDetails(@PathVariable(name = PATH_VARIABLE_ID) final Long id) {
//        return getViewDetailsModelAndView(id, EDIT_VIEW_NAME, getHashMapWithAllCommonForms());
//    }
//
//    @GetMapping(EDIT_MAPPING)
//    public ModelAndView edit(@PathVariable(name = PATH_VARIABLE_ID) final Long id) {
//        return getEditModelAndView(id, EDIT_VIEW_NAME, getHashMapWithAllCommonForms());
//    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        return hashMap;
    }
}

