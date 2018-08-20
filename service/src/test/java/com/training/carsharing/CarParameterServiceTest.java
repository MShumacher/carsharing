package com.training.carsharing;

import com.training.carsharing.model.impl.CarParameter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class CarParameterServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getCarParameterService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final CarParameter entity = saveNewCarParameter();

        final CarParameter entityFromDB = getCarParameterService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB);
        assertNotNullFieldsExcept(entityFromDB);
     //   assertTrue(entityFromDB.getCreated().isEqual(entityFromDB.getUpdated()));
//        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final CarParameter entity = saveNewCarParameter();

        final CarParameter entityFromDB = getCarParameterService().findOneFullInfo(entity.getId());
        final String newName = "new-name-" + getRandomPrefix();
        entityFromDB.setName(newName);
        getCarParameterService().save(entityFromDB);

        final CarParameter updatedEntityFromDB = getCarParameterService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB,"version", "updated","name");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(newName, updatedEntityFromDB.getName());

    //    assertTrue(updatedEntityFromDB.getUpdated().isAfter(entity.getUpdated()));
//        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }


    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final CarParameter entity = saveNewCarParameter();
        getCarParameterService().delete(entity);
        assertNull(getCarParameterService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewCarParameter();
        getCarParameterService().deleteAll();
        assertEquals(0, getCarParameterService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getCarParameterService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewCarParameter();
        }

        final List<CarParameter> allEntities = getCarParameterService().findAllFullInfo();

        for (final CarParameter entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
