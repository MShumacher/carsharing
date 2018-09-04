package com.training.carsharing;

import com.training.carsharing.model.impl.Drive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DriveServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        getDriveService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final Drive entity = saveNewDrive();

        final Drive entityFromDB = getDriveService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB);
        assertNotNullFieldsExcept(entityFromDB);
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final Drive entity = saveNewDrive();

        final Drive entityFromDB = getDriveService().findOneFullInfo(entity.getId());
        final String newName = "new-name-" + getRandomPrefix();
        entityFromDB.setName(newName);
        Thread.currentThread().sleep(500);
        getDriveService().save(entityFromDB);

        final Drive updatedEntityFromDB = getDriveService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "name");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(newName, updatedEntityFromDB.getName());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }


    @Test
    public void testDelete() {
        final Drive entity = saveNewDrive();
        getDriveService().delete(entity);
        assertNull(getDriveService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewDrive();
        getDriveService().deleteAll();
        assertEquals(0, getDriveService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        final int initialCount = getDriveService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewDrive();
        }

        final List<Drive> allEntities = getDriveService().findAllFullInfo();

        for (final Drive entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
