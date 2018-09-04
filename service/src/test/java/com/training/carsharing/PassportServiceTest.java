package com.training.carsharing;

import com.training.carsharing.model.impl.Passport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PassportServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        getPassportService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final Passport entity = saveNewPassport();

        final Passport entityFromDB = getPassportService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB, "userAccount");
        assertNotNullFieldsExcept(entityFromDB);
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final Passport entity = saveNewPassport();

        final Passport entityFromDB = getPassportService().findOneFullInfo(entity.getId());
        final String newFullName = "new-name-" + getRandomPrefix();
        entityFromDB.setFullName(newFullName);
        Thread.currentThread().sleep(500);
        getPassportService().save(entityFromDB);

        final Passport updatedEntityFromDB = getPassportService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "fullName", "userAccount");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(entity.getUserAccount().getId(), entityFromDB.getUserAccount().getId());
        assertEquals(newFullName, updatedEntityFromDB.getFullName());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }


    @Test
    public void testDelete() {
        final Passport entity = saveNewPassport();
        getPassportService().delete(entity);
        assertNull(getPassportService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewPassport();
        getPassportService().deleteAll();
        assertEquals(0, getPassportService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        final int initialCount = getPassportService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewPassport();
        }

        final List<Passport> allEntities = getPassportService().findAllFullInfo();

        for (final Passport entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "userAccount");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
