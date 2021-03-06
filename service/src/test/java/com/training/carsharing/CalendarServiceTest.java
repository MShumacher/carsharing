package com.training.carsharing;

import com.training.carsharing.model.impl.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CalendarServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        cleanAllTables();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final Calendar entity = saveNewCalendar();

        final Calendar entityFromDB = getCalendarService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB, "car", "renter");
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getRenter().getId(), entityFromDB.getRenter().getId());
        assertNotNullFieldsExcept(entityFromDB);
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final Calendar entity = saveNewCalendar();

        final Calendar entityFromDB = getCalendarService().findOneFullInfo(entity.getId());
        final Double newTotalPrice = getRandomDouble();
        entityFromDB.setTotalPrice(newTotalPrice);
        Thread.currentThread().sleep(500);
        getCalendarService().save(entityFromDB);

        final Calendar updatedEntityFromDB = getCalendarService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "totalPrice", "car", "renter");
        assertEquals(newTotalPrice, updatedEntityFromDB.getTotalPrice());
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getRenter().getId(), entityFromDB.getRenter().getId());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }

    @Test
    public void testDelete() {
        final Calendar entity = saveNewCalendar();
        getCalendarService().delete(entity);
        assertNull(getCalendarService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewCalendar();
        getCalendarService().deleteAll();
        assertEquals(0, getCalendarService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        final int initialCount = getCalendarService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewCalendar();
        }

        final List<Calendar> allEntities = getCalendarService().findAllFullInfo();

        for (final Calendar entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
