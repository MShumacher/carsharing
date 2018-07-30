package com.training.carsharing;

import com.training.carsharing.model.impl.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class MessageServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getAdService().deleteAll();
        getUserAccountService().deleteAll();
        getMessageService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Message entity = saveNewMessage();

        final Message entityFromDB = getMessageService().selectFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB,"ad", "sender", "recipient");
        assertEquals(entity.getAd().getId(), entityFromDB.getAd().getId());
        assertEquals(entity.getSender().getId(), entityFromDB.getSender().getId());
        assertEquals(entity.getRecipient().getId(), entityFromDB.getRecipient().getId());
        assertNotNullFieldsExcept(entityFromDB);

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Message entity = saveNewMessage();

        final Message entityFromDB = getMessageService().selectFullInfo(entity.getId());
        final String newMessage = "new-message-" + getRandomPrefix();
        entityFromDB.setMessage(newMessage);
        getMessageService().save(entityFromDB);

        final Message updatedEntityFromDB = getMessageService().selectFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB,"version","updated","message", "ad", "sender","recipient");
        assertEquals(entity.getVersion(),updatedEntityFromDB.getVersion(),1);
        assertEquals(entity.getAd().getId(), entityFromDB.getAd().getId());
        assertEquals(entity.getSender().getId(), entityFromDB.getSender().getId());
        assertEquals(entity.getRecipient().getId(), entityFromDB.getRecipient().getId());
        assertEquals(newMessage, updatedEntityFromDB.getMessage());
        long time = updatedEntityFromDB.getUpdated().getTime();
        final long time1 = entity.getUpdated().getTime();
        assertTrue(time > time1);
     }

    @Test
    public void testDelete() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final Message entity = saveNewMessage();
        getMessageService().delete(entity.getId());
        assertNull(getMessageService().select(entity.getId()));
    }

    @Test
    public void testDeleteAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        saveNewMessage();
        getMessageService().deleteAll();
        assertEquals(0, getMessageService().selectAll().size());
    }

    @Test
    public void testSelectAll() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final int initialCount = getMessageService().selectAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewMessage();
        }

        final List<Message> allEntities = getMessageService().selectAllFullInfo();

        for (final Message entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "ad", "parameters");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
