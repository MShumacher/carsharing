package com.training.carsharing;

import com.training.carsharing.dao.IMessageDao;
import com.training.carsharing.model.impl.Message;

public interface IMessageService extends IAbstractService<Message, IMessageDao, Integer>{

}

