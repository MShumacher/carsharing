package com.training.carsharing;

import com.training.carsharing.model.IPassport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class PassportServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getPassportService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IPassport entity = saveNewPassport();

        final IPassport entityFromDB = getPassportService().select(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB,"userAccount");
        assertNotNullFieldsExcept(entityFromDB);

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IPassport entity = saveNewPassport();

        final IPassport entityFromDB = getPassportService().select(entity.getId());
        final String newFullName = "new-name-" + getRandomPrefix();
        entityFromDB.setFullName(newFullName);
        getPassportService().save(entityFromDB);

        final IPassport updatedEntityFromDB = getPassportService().select(entityFromDB.getId());
        assertEqualsFieldsExcept(entity,updatedEntityFromDB,"version", "updated", "fullName", "userAccount");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getUserAccount().getId(), entityFromDB.getUserAccount().getId());
        assertEquals(newFullName, updatedEntityFromDB.getFullName());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }


    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final IPassport entity = saveNewPassport();
        getPassportService().delete(entity.getId());
        assertNull(getPassportService().select(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewPassport();
        getPassportService().deleteAll();
        assertEquals(0, getPassportService().selectAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getPassportService().selectAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewPassport();
        }

        final List<IPassport> allEntities = getPassportService().selectAllFullInfo();

        for (final IPassport entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "userAccount");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
