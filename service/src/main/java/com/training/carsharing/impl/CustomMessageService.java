package com.training.carsharing.impl;

import com.training.carsharing.MessageService;
import com.training.carsharing.model.impl.Message;
import com.training.carsharing.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageService extends CustomAbstractService<Message, MessageRepository, Integer> implements MessageService {

    public CustomMessageService() { super(Message.class); }

}
