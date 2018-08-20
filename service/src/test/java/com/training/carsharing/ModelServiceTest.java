package com.training.carsharing;

import com.training.carsharing.model.impl.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class ModelServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getBrandService().deleteAll();
        getModelService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Model entity = saveNewModel();

        final Model entityFromDB = getModelService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB, "brand");
        assertEquals(entity.getBrand().getId(),entityFromDB.getBrand().getId());
        assertNotNullFieldsExcept(entityFromDB);

    //    assertTrue(entityFromDB.getCreated().isEqual(entityFromDB.getUpdated()));
//        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Model entity = saveNewModel();

        final Model entityFromDB = getModelService().findOneFullInfo(entity.getId());
        final String newName = "new-name-" + getRandomPrefix();
        entityFromDB.setName(newName);
        getModelService().save(entityFromDB);

        final Model updatedEntityFromDB = getModelService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version", "updated", "brand", "name");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getBrand().getId(),updatedEntityFromDB.getBrand().getId());
        assertEquals(newName, updatedEntityFromDB.getName());

    //    assertTrue(updatedEntityFromDB.getUpdated().isAfter(entity.getUpdated()));
//        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }


    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final Model entity = saveNewModel();
        getModelService().delete(entity);
        assertNull(getModelService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewModel();
        getModelService().deleteAll();
        assertEquals(0, getModelService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getModelService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewModel();
        }

        final List<Model> allEntities = getModelService().findAllFullInfo();

        for (final Model entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
