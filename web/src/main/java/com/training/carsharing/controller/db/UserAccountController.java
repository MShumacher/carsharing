package com.training.carsharing.controller.db;

import com.training.carsharing.dto.UserAccountDto;
import com.training.carsharing.model.impl.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/useraccount")
public class UserAccountController extends AbstractController<UserAccount, UserAccountDto, Long> {

    protected UserAccountController() {
        super(UserAccountDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormPassportsWithCurrentUserAccountOrWithout(hashMap, id);
        loadCommonFormDrivingLicensesWithCurrentUserAccountOrWithout(hashMap, id);
        loadCommonFormRoles(hashMap);
        return hashMap;
    }
}

