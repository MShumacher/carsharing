package com.training.carsharing;

import com.training.carsharing.model.ICar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CarServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getAdService().deleteAll();
        getCarService().deleteAll();
        getUserAccountService().deleteAll();
        getModelService().deleteAll();
        getParameterService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICar entity = saveNewCar();

        final ICar entityFromDB = getCarService().selectFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB,"model", "parameters");
        assertEquals(entity.getModel().getId(), entityFromDB.getModel().getId());
        assertNotNullFieldsExcept(entityFromDB, "ad", "parameters");

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICar entity = saveNewCar();

        final ICar entityFromDB = getCarService().selectFullInfo(entity.getId());
        final String newInsurance = "new-insurance-" + getRandomPrefix();
        entityFromDB.setInsurance(newInsurance);
        getCarService().save(entityFromDB);

        final ICar updatedEntityFromDB = getCarService().selectFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB,"version","updated","insurance", "model", "parameters");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getModel().getId(), entityFromDB.getModel().getId());
        assertEquals(newInsurance, updatedEntityFromDB.getInsurance());
        long time = updatedEntityFromDB.getUpdated().getTime();
        final long time1 = entity.getUpdated().getTime();
        assertTrue(time > time1);
     }

    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final ICar entity = saveNewCar();
        getCarService().delete(entity.getId());
        assertNull(getCarService().select(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewCar();
        getCarService().deleteAll();
        assertEquals(0, getCarService().selectAll().size());
    }

    @Test
    public void testSelectAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getCarService().selectAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewCar();
        }

        final List<ICar> allEntities = getCarService().selectAllFullInfo();

        for (final ICar entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "ad", "parameters");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
