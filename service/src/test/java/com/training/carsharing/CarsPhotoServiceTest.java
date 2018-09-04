package com.training.carsharing;

import com.training.carsharing.model.impl.CarsPhoto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CarsPhotoServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        cleanAllTables();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final CarsPhoto entity = saveNewCarsPhoto();

        final CarsPhoto entityFromDB = getCarsPhotoService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB, "car");
        assertNotNullFieldsExcept(entityFromDB);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final CarsPhoto entity = saveNewCarsPhoto();

        final CarsPhoto entityFromDB = getCarsPhotoService().findOneFullInfo(entity.getId());
        final String newLink = "new-link-" + getRandomPrefix();
        entityFromDB.setLink(newLink);
        Thread.currentThread().sleep(500);
        getCarsPhotoService().save(entityFromDB);

        final CarsPhoto updatedEntityFromDB = getCarsPhotoService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "link", "car");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(entity.getCar().getId(), entityFromDB.getCar().getId());
        assertEquals(newLink, updatedEntityFromDB.getLink());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }

    @Test
    public void testDelete() {
        final CarsPhoto entity = saveNewCarsPhoto();
        getCarsPhotoService().delete(entity);
        assertNull(getCarsPhotoService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewCarsPhoto();
        getCarsPhotoService().deleteAll();
        assertEquals(0, getCarsPhotoService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        final int initialCount = getCarsPhotoService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewCarsPhoto();
        }

        final List<CarsPhoto> allEntities = getCarsPhotoService().findAllFullInfo();

        for (final CarsPhoto entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
