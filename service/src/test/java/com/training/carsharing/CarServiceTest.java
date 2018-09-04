package com.training.carsharing;

import com.training.carsharing.model.impl.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CarServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        cleanAllTables();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final Car entity = saveNewCar();

        final Car entityFromDB = getCarService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB, "ad", "model", "carParameter", "gearbox", "bodyType", "drive", "engineType");
        assertEquals(entity.getModel().getId(), entityFromDB.getModel().getId());
        assertEquals(entity.getGearbox().getId(), entityFromDB.getGearbox().getId());
        assertEquals(entity.getBodyType().getId(), entityFromDB.getBodyType().getId());
        assertEquals(entity.getDrive().getId(), entityFromDB.getDrive().getId());
        assertEquals(entity.getEngineType().getId(), entityFromDB.getEngineType().getId());
        assertNotNullFieldsExcept(entityFromDB, "ad", "carParameter");
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final Car entity = saveNewCar();

        final Car entityFromDB = getCarService().findOneFullInfo(entity.getId());
        final String newInsurance = "new-insurance-" + getRandomPrefix();
        entityFromDB.setInsurance(newInsurance);
        Thread.currentThread().sleep(500);
        getCarService().save(entityFromDB);

        final Car updatedEntityFromDB = getCarService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "insurance", "ad", "model", "carParameter", "gearbox", "bodyType", "drive", "engineType");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(entity.getModel().getId(), entityFromDB.getModel().getId());
        assertEquals(entity.getGearbox().getId(), entityFromDB.getGearbox().getId());
        assertEquals(entity.getBodyType().getId(), entityFromDB.getBodyType().getId());
        assertEquals(entity.getDrive().getId(), entityFromDB.getDrive().getId());
        assertEquals(entity.getEngineType().getId(), entityFromDB.getEngineType().getId());
        assertEquals(newInsurance, updatedEntityFromDB.getInsurance());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }

    @Test
    public void testDelete() {
        final Car entity = saveNewCar();
        getCarService().delete(entity);
        assertNull(getCarService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewCar();
        getCarService().deleteAll();
        assertEquals(0, getCarService().findAll().size());
    }

    @Test
    public void testSelectAll() throws IllegalAccessException {
        final int initialCount = getCarService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewCar();
        }

        final List<Car> allEntities = getCarService().findAllFullInfo();

        for (final Car entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "ad", "carParameter");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
