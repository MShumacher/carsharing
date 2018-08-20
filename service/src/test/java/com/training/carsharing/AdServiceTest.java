package com.training.carsharing;

import com.training.carsharing.model.impl.Ad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class AdServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getAdService().deleteAll();
        getCarService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Ad entity = saveNewAd();

        final Ad entityFromDB = getAdService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB, "car", "userAccount");
        assertNotNullFieldsExcept(entityFromDB);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getUserAccount().getId(), entityFromDB.getUserAccount().getId());

      //  assertTrue(entityFromDB.getCreated().isEqual(entityFromDB.getUpdated()));
//        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Ad entity = saveNewAd();

        final Ad entityFromDB = getAdService().findOneFullInfo(entity.getId());
        final String newAddress = "new-address-"+getRandomPrefix();
        entityFromDB.setAddress(newAddress);
        getAdService().save(entityFromDB);

        final Ad updatedEntityFromDB = getAdService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version","updated","address", "car", "userAccount");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(entity.getUserAccount().getId(), entityFromDB.getUserAccount().getId());
        assertEquals(newAddress, updatedEntityFromDB.getAddress());

     //   assertTrue(updatedEntityFromDB.getUpdated().isAfter(entity.getUpdated()));
//        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }

    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final Ad entity = saveNewAd();
        getAdService().delete(entity);
        assertNull(getAdService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewAd();
        getAdService().deleteAll();
        assertEquals(0, getAdService().findAll().size());
    }

    @Test
    public void testSelectAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getAdService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewAd();
        }

        final List<Ad> allEntities = getAdService().findAllFullInfo();

        for (final Ad entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
