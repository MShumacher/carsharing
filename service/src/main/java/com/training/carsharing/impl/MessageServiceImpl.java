package com.training.carsharing.impl;

import com.training.carsharing.IMessageService;
import com.training.carsharing.dao.IMessageDao;
import com.training.carsharing.model.IMessage;
import com.training.carsharing.model.impl.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends AbstractServiceImpl<IMessage, IMessageDao, Integer> implements IMessageService {

    public MessageServiceImpl() { super(Message.class); }

}
