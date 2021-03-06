package com.training.carsharing;

import com.training.carsharing.model.impl.Fuel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FuelServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        getFuelService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final Fuel entity = saveNewFuel();

        final Fuel entityFromDB = getFuelService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB);
        assertNotNullFieldsExcept(entityFromDB);
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final Fuel entity = saveNewFuel();

        final Fuel entityFromDB = getFuelService().findOneFullInfo(entity.getId());
        final String newName = "new-name-" + getRandomPrefix();
        entityFromDB.setName(newName);
        Thread.currentThread().sleep(500);
        getFuelService().save(entityFromDB);

        final Fuel updatedEntityFromDB = getFuelService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "name");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(newName, updatedEntityFromDB.getName());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }


    @Test
    public void testDelete() {
        final Fuel entity = saveNewFuel();
        getFuelService().delete(entity);
        assertNull(getFuelService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewFuel();
        getFuelService().deleteAll();
        assertEquals(0, getFuelService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        final int initialCount = getFuelService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewFuel();
        }

        final List<Fuel> allEntities = getFuelService().findAllFullInfo();

        for (final Fuel entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
