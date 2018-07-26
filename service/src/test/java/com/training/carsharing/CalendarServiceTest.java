package com.training.carsharing;

import com.training.carsharing.model.ICalendar;
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
        final ICalendar entity = saveNewCalendar();

        final ICalendar entityFromDB = getCalendarService().selectFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB, "car", "renter");
        assertNotNullFieldsExcept(entityFromDB);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getRenter().getId(), entityFromDB.getRenter().getId());

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICalendar entity = saveNewCalendar();

        final ICalendar entityFromDB = getCalendarService().selectFullInfo(entity.getId());
        final Date newStart = getRandomDate();
        entityFromDB.setStart(newStart);
//        Thread.sleep(1000); // make a short delay to see a new date in 'updated' column
        getCalendarService().save(entityFromDB);

        final ICalendar updatedEntityFromDB = getCalendarService().selectFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version","updated","start", "car", "renter");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getRenter().getId(), entityFromDB.getRenter().getId());
        assertEqualsDates(newStart, updatedEntityFromDB.getStart());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }

    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final ICalendar entity = saveNewCalendar();
        getCalendarService().delete(entity.getId());
        assertNull(getCalendarService().select(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewCalendar();
        getCalendarService().deleteAll();
        assertEquals(0, getCalendarService().selectAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getCalendarService().selectAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewCalendar();
        }

        final List<ICalendar> allEntities = getCalendarService().selectAllFullInfo();

        for (final ICalendar entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
