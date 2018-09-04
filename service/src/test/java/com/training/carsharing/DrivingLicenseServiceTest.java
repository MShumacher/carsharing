package com.training.carsharing;

import com.training.carsharing.model.impl.DrivingLicense;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DrivingLicenseServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        getDrivingLicenseService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final DrivingLicense entity = saveNewDrivingLicense();

        final DrivingLicense entityFromDB = getDrivingLicenseService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB, "userAccount");
        assertNotNullFieldsExcept(entityFromDB);
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final DrivingLicense entity = saveNewDrivingLicense();

        final DrivingLicense entityFromDB = getDrivingLicenseService().findOneFullInfo(entity.getId());
        final String newNumber = "new-number-" + getRandomPrefix();
        entityFromDB.setNumber(newNumber);
        Thread.currentThread().sleep(500);
        getDrivingLicenseService().save(entityFromDB);

        final DrivingLicense updatedEntityFromDB = getDrivingLicenseService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "number", "userAccount");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(entity.getUserAccount().getId(), entityFromDB.getUserAccount().getId());
        assertEquals(newNumber, updatedEntityFromDB.getNumber());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }


    @Test
    public void testDelete() {
        final DrivingLicense entity = saveNewDrivingLicense();
        getDrivingLicenseService().delete(entity);
        assertNull(getDrivingLicenseService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewDrivingLicense();
        getDrivingLicenseService().deleteAll();
        assertEquals(0, getDrivingLicenseService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        final int initialCount = getDrivingLicenseService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewDrivingLicense();
        }

        final List<DrivingLicense> allEntities = getDrivingLicenseService().findAllFullInfo();

        for (final DrivingLicense entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "userAccount");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
