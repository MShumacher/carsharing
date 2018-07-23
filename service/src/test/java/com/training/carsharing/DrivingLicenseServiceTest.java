package com.training.carsharing;

import com.training.carsharing.model.IDrivingLicense;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class DrivingLicenseServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getDrivingLicenseService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IDrivingLicense entity = saveNewDrivingLicense();

        final IDrivingLicense entityFromDB = getDrivingLicenseService().select(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB, "userAccount");
        assertNotNullFieldsExcept(entityFromDB);

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IDrivingLicense entity = saveNewDrivingLicense();

        final IDrivingLicense entityFromDB = getDrivingLicenseService().select(entity.getId());
        final String newNumber = "new-number-" + getRandomPrefix();
        entityFromDB.setNumber(newNumber);
        Thread.sleep(1000); // make a short delay to see a new date in 'updated' column
        getDrivingLicenseService().save(entityFromDB);

        final IDrivingLicense updatedEntityFromDB = getDrivingLicenseService().select(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version", "updated", "number");
        assertEquals(newNumber, updatedEntityFromDB.getNumber());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }


    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final IDrivingLicense entity = saveNewDrivingLicense();
        getDrivingLicenseService().delete(entity.getId());
        assertNull(getDrivingLicenseService().select(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewDrivingLicense();
        getDrivingLicenseService().deleteAll();
        assertEquals(0, getDrivingLicenseService().selectAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getDrivingLicenseService().selectAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewDrivingLicense();
        }

        final List<IDrivingLicense> allEntities = getDrivingLicenseService().selectAllFullInfo();

        for (final IDrivingLicense entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "userAccount");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
