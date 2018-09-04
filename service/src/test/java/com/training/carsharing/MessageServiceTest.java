package com.training.carsharing;

import com.training.carsharing.model.impl.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MessageServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        cleanAllTables();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final Message entity = saveNewMessage();

        final Message entityFromDB = getMessageService().findOneFullInfo(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB, "ad", "sender", "recipient");
        assertEquals(entity.getAd().getId(), entityFromDB.getAd().getId());
        assertEquals(entity.getSender().getId(), entityFromDB.getSender().getId());
        assertEquals(entity.getRecipient().getId(), entityFromDB.getRecipient().getId());
        assertNotNullFieldsExcept(entityFromDB);
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final Message entity = saveNewMessage();

        final Message entityFromDB = getMessageService().findOneFullInfo(entity.getId());
        final String newMessage = "new-message-" + getRandomPrefix();
        entityFromDB.setMessage(newMessage);
        Thread.currentThread().sleep(500);
        getMessageService().save(entityFromDB);

        final Message updatedEntityFromDB = getMessageService().findOneFullInfo(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "message", "ad", "sender", "recipient");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(entity.getAd().getId(), entityFromDB.getAd().getId());
        assertEquals(entity.getSender().getId(), entityFromDB.getSender().getId());
        assertEquals(entity.getRecipient().getId(), entityFromDB.getRecipient().getId());
        assertEquals(newMessage, updatedEntityFromDB.getMessage());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }

    @Test
    public void testDelete() {
        final Message entity = saveNewMessage();
        getMessageService().delete(entity);
        assertNull(getMessageService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewMessage();
        getMessageService().deleteAll();
        assertEquals(0, getMessageService().findAll().size());
    }

    @Test
    public void testSelectAll() throws IllegalAccessException {
        final int initialCount = getMessageService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewMessage();
        }

        final List<Message> allEntities = getMessageService().findAllFullInfo();

        for (final Message entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB, "ad", "parameters");
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
