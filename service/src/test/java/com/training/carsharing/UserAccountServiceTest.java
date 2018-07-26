package com.training.carsharing;

import com.training.carsharing.model.IUserAccount;
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
        getUserAccountService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException  {
        System.out.println("Start create test");
        final IUserAccount entity = saveNewUserAccount();

        final IUserAccount entityFromDB = getUserAccountService().selectFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity,entityFromDB);
        assertNotNullFieldsExcept(entityFromDB, "passport", "drivingLicense");

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
        System.out.println("End create test");
    }

    @Test
    public void testUpdate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InterruptedException {
        System.out.println("Start update test");
        final IUserAccount entity = saveNewUserAccount();

        final IUserAccount entityFromDB = getUserAccountService().selectFullInfo(entity.getId());
        final String newRole = "new-role-" + getRandomPrefix();
        entityFromDB.setRole(newRole);
//        Thread.sleep(1000); // make a short delay to see a new date in 'updated' column
        getUserAccountService().save(entityFromDB);

        final IUserAccount updatedEntityFromDB = getUserAccountService().selectFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB,"version", "updated","role");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(newRole, updatedEntityFromDB.getRole());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
        System.out.println("End update test");
     }


    @Test
    public void testDelete() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("Start delete test");
        final IUserAccount entity = saveNewUserAccount();
        getUserAccountService().delete(entity.getId());
        assertNull(getUserAccountService().select(entity.getId()));
        System.out.println("End delete test");
    }

    @Test
    public void testDeleteAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("Start delete all test");
        saveNewUserAccount();
        getUserAccountService().deleteAll();
        assertEquals(0, getUserAccountService().selectAllFullInfo().size());
        System.out.println("End delete all test");
    }

    @Test
    public void testGetAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("Start get all test");
        final int initialCount = getUserAccountService().selectAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewUserAccount();
        }

        final List<IUserAccount> allEntities = getUserAccountService().selectAllFullInfo();

        for (final IUserAccount entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "passport", "drivingLicense");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
        System.out.println("End get all test");
    }
}
