package com.training.carsharing;

import com.training.carsharing.model.IAd;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AdServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getAdService().deleteAll();
        getCarService().deleteAll();
        getUserAccountService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IAd entity = saveNewAd();

        final IAd entityFromDB = getAdService().selectFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB, "car", "userAccount");
        assertNotNullFieldsExcept(entityFromDB);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getUserAccount().getId(), entityFromDB.getUserAccount().getId());

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IAd entity = saveNewAd();

        final IAd entityFromDB = getAdService().selectFullInfo(entity.getId());
        final String newAddress = "new-address-"+getRandomPrefix();
        entityFromDB.setAddress(newAddress);
        Thread.sleep(5000); // make a short delay to see a new date in 'updated' column
        getAdService().save(entityFromDB);

        final IAd updatedEntityFromDB = getAdService().selectFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version","updated","address", "car", "userAccount");
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getUserAccount().getId(), entityFromDB.getUserAccount().getId());
        assertEquals(newAddress, updatedEntityFromDB.getAddress());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }

    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final IAd entity = saveNewAd();
        getAdService().delete(entity.getId());
        assertNull(getAdService().select(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewAd();
        getAdService().deleteAll();
        assertEquals(0, getAdService().selectAll().size());
    }

    @Test
    public void testSelectAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getAdService().selectAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewAd();
        }

        final List<IAd> allEntities = getAdService().selectAllFullInfo();

        for (final IAd entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
