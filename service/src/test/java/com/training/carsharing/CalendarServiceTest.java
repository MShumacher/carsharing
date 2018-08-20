package com.training.carsharing;

import com.training.carsharing.model.impl.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CalendarServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getCalendarService().deleteAll();
        getCarService().deleteAll();
        getUserAccountService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Calendar entity = saveNewCalendar();

        final Calendar entityFromDB = getCalendarService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB, "car", "renter");
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getRenter().getId(), entityFromDB.getRenter().getId());
        assertNotNullFieldsExcept(entityFromDB);
    //    assertTrue(entityFromDB.getCreated().isEqual(entityFromDB.getUpdated()));
//        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Calendar entity = saveNewCalendar();

        final Calendar entityFromDB = getCalendarService().findOneFullInfo(entity.getId());
        final Date newStart = getRandomDate();
        entityFromDB.setStart(newStart);
        Thread.currentThread().sleep(2000);
        getCalendarService().save(entityFromDB);

        final Calendar updatedEntityFromDB = getCalendarService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version","updated","start", "car", "renter");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getRenter().getId(), entityFromDB.getRenter().getId());
        assertEqualsDates(newStart, updatedEntityFromDB.getStart());

   //     assertTrue(updatedEntityFromDB.getUpdated().isAfter(entity.getUpdated()));
//        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }

    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final Calendar entity = saveNewCalendar();
        getCalendarService().delete(entity);
        assertNull(getCalendarService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewCalendar();
        getCalendarService().deleteAll();
        assertEquals(0, getCalendarService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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
