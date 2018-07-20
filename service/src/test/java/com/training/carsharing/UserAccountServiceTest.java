package com.training.carsharing;

import com.training.carsharing.dao.IUserAccount;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserAccountServiceTest extends AbstractTest {

    @Before
    public void cleanTables() {
        getUserAccountService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        System.out.println("Start create test");
        final IUserAccount entity = saveNewUserAccount();

        final IUserAccount entityFromDB = getUserAccountService().get(entity.getId());

        assertEqualsAllFields(entity,entityFromDB);
//        assertEquals(entity.getCreated().getTime(),entityFromDB.getCreated().getTime());
//        assertEquals(entity.getUpdated().getTime(),entityFromDB.getUpdated().getTime());
        assertNotNullAllFields(entityFromDB);

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
        System.out.println("End create test");
    }

    @Test
    public void testUpdate() throws InterruptedException {
        System.out.println("Start update test");
        final IUserAccount entity = saveNewUserAccount();

        final IUserAccount entityFromDB = getUserAccountService().get(entity.getId());
        final String newEmail = "new-email-" + getRandomPrefix();
        entityFromDB.setEmail(newEmail);
        Thread.sleep(1000); // make a short delay to see a new date in 'updated' column
        getUserAccountService().save(entityFromDB);

        final IUserAccount updatedEntityFromDB = getUserAccountService().get(entityFromDB.getId());
        assertEquals(entity.getId(), updatedEntityFromDB.getId());
        assertEquals(newEmail, updatedEntityFromDB.getEmail());
        assertEquals(entity.getPassword(), updatedEntityFromDB.getPassword());
        assertEquals(entity.getName(), updatedEntityFromDB.getName());
        assertEquals(entity.getCity(), updatedEntityFromDB.getCity());
        assertEquals(entity.getPhone(), updatedEntityFromDB.getPhone());
        assertEqualsDates(entity.getCreated(),updatedEntityFromDB.getCreated());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
        System.out.println("End update test");
     }


    @Test
    public void testDelete() {
        System.out.println("Start delete test");
        final IUserAccount entity = saveNewUserAccount();
        getUserAccountService().delete(entity.getId());
        assertNull(getUserAccountService().get(entity.getId()));
        System.out.println("End delete test");
    }

    @Test
    public void testDeleteAll() {
        System.out.println("Start delete all test");
        saveNewUserAccount();
        getUserAccountService().deleteAll();
        assertEquals(0, getUserAccountService().selectAll().size());
        System.out.println("End delete all test");
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        System.out.println("Start get all test");
        final int initialCount = getUserAccountService().selectAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewUserAccount();
        }

        final List<IUserAccount> allEntities = getUserAccountService().selectAll();

        for (final IUserAccount entityFromDB : allEntities) {
            assertNotNullAllFields(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
        System.out.println("End get all test");
    }
}
