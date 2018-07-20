package com.training.carsharing;

import com.training.carsharing.dao.IModel;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ModelServiceTest extends AbstractTest {

    @Before
    public void cleanTables() {
        getModelService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final IModel entity = saveNewModel();

        final IModel entityFromDB = getModelService().get(entity.getId());

        assertEqualsAllFields(entity,entityFromDB);
//        assertEquals(entity.getCreated().getTime(),entityFromDB.getCreated().getTime());
//        assertEquals(entity.getUpdated().getTime(),entityFromDB.getUpdated().getTime());
        assertNotNullAllFields(entityFromDB);

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException {
        final IModel entity = saveNewModel();

        final IModel entityFromDB = getModelService().get(entity.getId());
        final String newName = "new-name-" + getRandomPrefix();
        entityFromDB.setName(newName);
        Thread.sleep(1000); // make a short delay to see a new date in 'updated' column
        getModelService().save(entityFromDB);

        final IModel updatedEntityFromDB = getModelService().get(entityFromDB.getId());
        assertEquals(entity.getId(), updatedEntityFromDB.getId());
        assertEquals(newName, updatedEntityFromDB.getName());
        assertEquals(entity.getBrand(), updatedEntityFromDB.getBrand());
        assertEqualsDates(entity.getCreated(),updatedEntityFromDB.getCreated());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }


    @Test
    public void testDelete() {
        final IModel entity = saveNewModel();
        getModelService().delete(entity.getId());
        assertNull(getModelService().get(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewModel();
        getModelService().deleteAll();
        assertEquals(0, getModelService().selectAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        final int initialCount = getModelService().selectAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewModel();
        }

        final List<IModel> allEntities = getModelService().selectAll();

        for (final IModel entityFromDB : allEntities) {
            assertNotNullAllFields(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
