package com.training.carsharing.repository;

import com.training.carsharing.model.impl.Calendar;
import com.training.carsharing.repository.customrepository.CalendarRepositoryCustom;

public interface CalendarRepository extends AbstractRepository<Calendar, Long>, CalendarRepositoryCustom {
}
