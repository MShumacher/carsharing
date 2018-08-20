package com.training.carsharing;

import com.training.carsharing.model.impl.EngineType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class EngineTypeServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getFuelService().deleteAll();
        getEngineTypeService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final EngineType entity = saveNewEngineType();

        final EngineType entityFromDB = getEngineTypeService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB, "fuel");
        assertEquals(entity.getFuel().getId(),entityFromDB.getFuel().getId());
        assertNotNullFieldsExcept(entityFromDB);

    //    assertTrue(entityFromDB.getCreated().isEqual(entityFromDB.getUpdated()));
//        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final EngineType entity = saveNewEngineType();

        final EngineType entityFromDB = getEngineTypeService().findOneFullInfo(entity.getId());
        final String newName = "new-name-" + getRandomPrefix();
        entityFromDB.setName(newName);
        getEngineTypeService().save(entityFromDB);

        final EngineType updatedEntityFromDB = getEngineTypeService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version", "updated", "fuel", "name");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getFuel().getId(),updatedEntityFromDB.getFuel().getId());
        assertEquals(newName, updatedEntityFromDB.getName());

    //    assertTrue(updatedEntityFromDB.getUpdated().isAfter(entity.getUpdated()));
//        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }


    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final EngineType entity = saveNewEngineType();
        getEngineTypeService().delete(entity);
        assertNull(getEngineTypeService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewEngineType();
        getEngineTypeService().deleteAll();
        assertEquals(0, getEngineTypeService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getEngineTypeService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewEngineType();
        }

        final List<EngineType> allEntities = getEngineTypeService().findAllFullInfo();

        for (final EngineType entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
