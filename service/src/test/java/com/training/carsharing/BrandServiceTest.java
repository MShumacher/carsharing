package com.training.carsharing;

import com.training.carsharing.model.impl.Brand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BrandServiceTest extends AbstractTest {

    @Before
    @After
    public void cleanTables() {
        getBrandService().deleteAll();
    }

    @Test
    public void testCreate() throws IllegalAccessException {
        final Brand entity = saveNewBrand();

        final Brand entityFromDB = getBrandService().findById(entity.getId());

        assertEqualsFieldsExcept(entity, entityFromDB);
        assertNotNullFieldsExcept(entityFromDB);
    }

    @Test
    public void testUpdate() throws InterruptedException, IllegalAccessException {
        final Brand entity = saveNewBrand();

        final Brand entityFromDB = getBrandService().findById(entity.getId());
        final String newName = "new-name-" + getRandomPrefix();
        entityFromDB.setName(newName);
        Thread.currentThread().sleep(500);
        getBrandService().save(entityFromDB);

        final Brand updatedEntityFromDB = getBrandService().findById(entityFromDB.getId());
        assertEqualsFieldsExcept(entity, updatedEntityFromDB, "version", "lastModifiedDate", "name");
        assertEquals(entity.getVersion(), updatedEntityFromDB.getVersion(), 1);
        assertEquals(newName, updatedEntityFromDB.getName());
        assertTrue(updatedEntityFromDB.getLastModifiedDate().get().isAfter(entity.getLastModifiedDate().get()));
    }


    @Test
    public void testDelete() {
        final Brand entity = saveNewBrand();
        getBrandService().delete(entity);
        assertNull(getBrandService().findById(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewBrand();
        getBrandService().deleteAll();
        assertEquals(0, getBrandService().findAll().size());
    }

    @Test
    public void testGetAll() throws IllegalAccessException {
        final int initialCount = getBrandService().findAllFullInfo().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewBrand();
        }

        final List<Brand> allEntities = getBrandService().findAllFullInfo();

        for (final Brand entityFromDB : allEntities) {
            assertNotNullFieldsExcept(entityFromDB);
        }
        assertEquals(randomObjectsCount + initialCount, allEntities.size());
    }
}
