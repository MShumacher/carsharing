package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Message;
import com.training.carsharing.repository.customrepository.MessageRepositoryCustom;

public interface MessageRepository extends AbstractRepository<Message, Long>, MessageRepositoryCustom {
}
