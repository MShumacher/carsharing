package com.training.carsharing;

import com.training.carsharing.model.impl.DrivingLicense;
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
        final DrivingLicense entity = saveNewDrivingLicense();

        final DrivingLicense entityFromDB = getDrivingLicenseService().selectFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB, "userAccount");
        assertNotNullFieldsExcept(entityFromDB);

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final DrivingLicense entity = saveNewDrivingLicense();

        final DrivingLicense entityFromDB = getDrivingLicenseService().selectFullInfo(entity.getId());
        final String newNumber = "new-number-" + getRandomPrefix();
        entityFromDB.setNumber(newNumber);
        getDrivingLicenseService().save(entityFromDB);

        final DrivingLicense updatedEntityFromDB = getDrivingLicenseService().selectFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version", "updated", "number", "userAccount");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getUserAccount().getId(), entityFromDB.getUserAccount().getId());
        assertEquals(newNumber, updatedEntityFromDB.getNumber());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }


    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final DrivingLicense entity = saveNewDrivingLicense();
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

        final List<DrivingLicense> allEntities = getDrivingLicenseService().selectAllFullInfo();

        for (final DrivingLicense entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "userAccount");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
