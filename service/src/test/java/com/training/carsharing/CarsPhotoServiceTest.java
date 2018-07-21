package com.training.carsharing;

import com.training.carsharing.model.ICarsPhoto;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.*;

public class CarsPhotoServiceTest extends AbstractTest {

    @Before
    public void cleanTables() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        getCarsPhotoService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICarsPhoto entity = saveNewCarsPhoto();

        final ICarsPhoto entityFromDB = getCarsPhotoService().selectFullInfo(entity.getId());

        assertEqualsAllFields(entity,entityFromDB);
        assertNotNullAllFields(entityFromDB);

        assertEquals(entityFromDB.getCreated().getTime(),entityFromDB.getUpdated().getTime());
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICarsPhoto entity = saveNewCarsPhoto();

        final ICarsPhoto entityFromDB = getCarsPhotoService().selectFullInfo(entity.getId());
        final String newLink = "new-link-" + getRandomPrefix();
        entityFromDB.setLink(newLink);
        Thread.sleep(1000); // make a short delay to see a new date in 'updated' column
        getCarsPhotoService().save(entityFromDB);

        final ICarsPhoto updatedEntityFromDB = getCarsPhotoService().selectFullInfo(entityFromDB.getId());
        assertEqualsAllFieldsExceptUpdatedAndVersionAndLast(entity,updatedEntityFromDB);
        assertEquals(newLink, updatedEntityFromDB.getLink());
        assertTrue(updatedEntityFromDB.getUpdated().getTime() >= entity.getUpdated().getTime());
     }

}
