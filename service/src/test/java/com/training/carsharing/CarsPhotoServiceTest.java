package com.training.carsharing;

import com.training.carsharing.model.ICarsPhoto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class CarsPhotoServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getCarsPhotoService().deleteAll();
        getCarService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICarsPhoto entity = saveNewCarsPhoto();

        final ICarsPhoto entityFromDB = getCarsPhotoService().selectFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB, "car");
        assertNotNullFieldsExcept(entityFromDB);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICarsPhoto entity = saveNewCarsPhoto();

        final ICarsPhoto entityFromDB = getCarsPhotoService().selectFullInfo(entity.getId());
        final String newLink = "new-link-" + getRandomPrefix();
        entityFromDB.setLink(newLink);
//        Thread.sleep(1000); // make a short delay to see a new date in 'updated' column
        getCarsPhotoService().save(entityFromDB);

        final ICarsPhoto updatedEntityFromDB = getCarsPhotoService().selectFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version","updated","link", "car");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(newLink, updatedEntityFromDB.getLink());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }

    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final ICarsPhoto entity = saveNewCarsPhoto();
        getCarsPhotoService().delete(entity.getId());
        assertNull(getCarsPhotoService().select(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewCarsPhoto();
        getCarsPhotoService().deleteAll();
        assertEquals(0, getCarsPhotoService().selectAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getCarsPhotoService().selectAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewCarsPhoto();
        }

        final List<ICarsPhoto> allEntities = getCarsPhotoService().selectAllFullInfo();

        for (final ICarsPhoto entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
