package com.training.carsharing.controller;

import com.training.carsharing.dto.ListDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface DBController<ENTITY, DTO, ID> {

    ModelAndView getIndexModelAndView(HttpServletRequest req, Integer pageNumber, String sortColumn);

    ModelAndView getAddModelAndView();

    Object getSaveModelAndView(DTO formModel, BindingResult result);

    String deleteEntity(ID id);

    ModelAndView getViewDetailsModelAndView(ID id);

    ModelAndView getEditModelAndView(ID id);

    Map<String, Object> getHashMapWithAllCommonForms();
}
