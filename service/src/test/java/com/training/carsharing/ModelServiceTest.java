package com.training.carsharing;

import com.training.carsharing.model.IModel;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class ModelServiceTest extends AbstractTest {

    @Before
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getModelService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IModel entity = saveNewModel();

        final IModel entityFromDB = getModelService().select(entity.getId());

        assertEqualsAllFields(entity,entityFromDB);
        assertNotNullAllFields(entityFromDB);

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IModel entity = saveNewModel();

        final IModel entityFromDB = getModelService().select(entity.getId());
        final String newBrand = "new-brand-" + getRandomPrefix();
        entityFromDB.setBrand(newBrand);
        Thread.sleep(1000); // make a short delay to see a new date in 'updated' column
        getModelService().save(entityFromDB);

        final IModel updatedEntityFromDB = getModelService().select(entityFromDB.getId());
        assertEqualsAllFieldsExceptUpdatedAndVersionAndLast(entity,updatedEntityFromDB);
        assertEquals(newBrand, updatedEntityFromDB.getBrand());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }


    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final IModel entity = saveNewModel();
        getModelService().delete(entity.getId());
        assertNull(getModelService().select(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewModel();
        getModelService().deleteAll();
        assertEquals(0, getModelService().selectAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getModelService().selectAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewModel();
        }

        final List<IModel> allEntities = getModelService().selectAllFullInfo();

        for (final IModel entityFromDB : allEntities) {
            assertNotNullAllFields(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
