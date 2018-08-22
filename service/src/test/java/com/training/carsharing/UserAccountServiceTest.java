package com.training.carsharing;

import com.training.carsharing.model.impl.UserAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class UserAccountServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables()throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        getUserAccountService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final UserAccount entity = saveNewUserAccount();

        final UserAccount entityFromDB = getUserAccountService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB);
        assertNotNullFieldsExcept(entityFromDB, "passport", "drivingLicense");
    }

    @Test
    public void testUpdate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InterruptedException {
        final UserAccount entity = saveNewUserAccount();

        final UserAccount entityFromDB = getUserAccountService().findOneFullInfo(entity.getId());
        final String email = "new-email-" + getRandomPrefix();
        entityFromDB.setEmail(email);
        Thread.currentThread().sleep(500);
        getUserAccountService().save(entityFromDB);

        final UserAccount updatedEntityFromDB = getUserAccountService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "email");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(email, updatedEntityFromDB.getEmail());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }


    @Test
    public void testDelete() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final UserAccount entity = saveNewUserAccount();
        getUserAccountService().delete(entity);
        assertNull(getUserAccountService().findById(entity.getId()));
    }

//    @Test
//    public void testDeleteAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        saveNewUserAccount();
//        getUserAccountService().deleteAll();
//        assertEquals(0, getUserAccountService().findAllFullInfo().size());
//    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getUserAccountService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewUserAccount();
        }

        final List<UserAccount> allEntities = getUserAccountService().findAllFullInfo();

        for (final UserAccount entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "passport", "drivingLicense", "photoLink");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
