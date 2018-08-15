package com.training.carsharing;

import com.training.carsharing.model.impl.Message;
import com.training.carsharing.repository.MessageRepository;

public interface MessageService extends AbstractService<Message, MessageRepository, Integer> {

}

