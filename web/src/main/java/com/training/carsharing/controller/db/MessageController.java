package com.training.carsharing.controller.db;

import com.training.carsharing.dto.MessageDto;
import com.training.carsharing.model.impl.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/message")
public class MessageController extends AbstractController<Message, MessageDto, Long> {

    protected MessageController() {
        super(MessageDto.class);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(Long id) {
        final Map<String, Object> hashMap = new HashMap<>();
        loadCommonFormAds(hashMap);
        loadCommonFormAllUserAccounts(hashMap);
        return hashMap;
    }
}

