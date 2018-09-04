package com.training.carsharing;

import com.training.carsharing.model.impl.Gearbox;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GearboxServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        getGearboxService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final Gearbox entity = saveNewGearbox();

        final Gearbox entityFromDB = getGearboxService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB);
        assertNotNullFieldsExcept(entityFromDB);
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final Gearbox entity = saveNewGearbox();

        final Gearbox entityFromDB = getGearboxService().findOneFullInfo(entity.getId());
        final String newName = "new-name-" + getRandomPrefix();
        entityFromDB.setName(newName);
        Thread.currentThread().sleep(500);
        getGearboxService().save(entityFromDB);

        final Gearbox updatedEntityFromDB = getGearboxService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "name");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(newName, updatedEntityFromDB.getName());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }


    @Test
    public void testDelete() {
        final Gearbox entity = saveNewGearbox();
        getGearboxService().delete(entity);
        assertNull(getGearboxService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewGearbox();
        getGearboxService().deleteAll();
        assertEquals(0, getGearboxService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        final int initialCount = getGearboxService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewGearbox();
        }

        final List<Gearbox> allEntities = getGearboxService().findAllFullInfo();

        for (final Gearbox entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
