package com.training.carsharing;

import com.training.carsharing.model.ICarsPhoto;
import com.training.carsharing.model.IModel;
import com.training.carsharing.model.IUserAccount;
import com.training.carsharing.model.impl.Car;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public abstract class AbstractTest {

    @Autowired
    private IModelService modelService;
    @Autowired
    private IUserAccountService userAccountService;
    @Autowired
    private ICarsPhotoService carsPhotoService;

    private static final Random RANDOM = new Random();

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

    protected static boolean assertEqualsDates(Date date1, Date date2) {
        String d1 = formatter.format(date1);
        String d2 = formatter.format(date2);
        return d1.equals(d2);
    }

    protected int getRandomPrefix() {
        return RANDOM.nextInt(99999);
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    public IUserAccountService getUserAccountService() {
        return userAccountService;
    }

    public IModelService getModelService() {
        return modelService;
    }

    public ICarsPhotoService getCarsPhotoService() {
        return carsPhotoService;
    }

    protected IModel saveNewModel() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IModel entity = getModelService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        entity.setBrand("Brand-" + getRandomPrefix());
        getModelService().save(entity);
        return entity;
    }

    protected IUserAccount saveNewUserAccount() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final IUserAccount entity = getUserAccountService().createEntity();
        entity.setEmail("Email-" + getRandomPrefix());
        entity.setPassword("Password-" + getRandomPrefix());
        entity.setName("Name-" + getRandomPrefix());
        entity.setPhotoLink("PhotoLink-" + getRandomPrefix());
        entity.setPhone("Phone-" + getRandomPrefix());
        entity.setRole("Role-" + getRandomPrefix());
        getUserAccountService().save(entity);
        return entity;
    }

    protected ICarsPhoto saveNewCarsPhoto() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICarsPhoto entity = getCarsPhotoService().createEntity();
        //TODO change new Car() to saveNewCar()
        entity.setCar(new Car());
        entity.setLink("Link-" + getRandomPrefix());
        getCarsPhotoService().save(entity);
        return entity;
    }

    protected void assertNotNullAllFields(Object entity) throws IllegalAccessException {
        Field[] fields = getAllFields(entity);
        for (Field field : fields) {
            field.setAccessible(true);
//            System.out.println("Имя: " + field.getName()+" Тип: " + fieldType.getSimpleName()+" Значение: "+field.get(entity));
            assertNotNull(field.get(entity));
        }
    }

    protected void assertEqualsAllFields(Object entity, Object entityFromDB) throws IllegalAccessException {
        Field[] fields = getAllFields(entity);
        for (Field field : fields) {
            field.setAccessible(true);
//            System.out.println("field: "+field.getName() + " " + field.get(entity)+"=="+field.get(entityFromDB));
            if ("Date".equals(field.getType().getSimpleName().toString())) {
                assertEqualsDates((Date) field.get(entity), (Date) field.get(entityFromDB));
            } else {
                assertEquals(field.get(entity), field.get(entityFromDB));
            }
        }
    }

    protected void assertEqualsAllFieldsExceptUpdatedAndVersionAndLast(Object entity, Object entityFromDB) throws IllegalAccessException {
        Field[] fields = getAllFields(entity);
        for (int i = 0; i < fields.length - 1; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (!"version".equals(field.getName())) {
//                System.out.println("field: "+field.getName() + " " + field.get(entity)+"=="+field.get(entityFromDB));
                if ("Date".equals(field.getType().getSimpleName())) {
                    assertEqualsDates((Date) field.get(entity), (Date) field.get(entityFromDB));
                } else {
                    assertEquals(field.get(entity), field.get(entityFromDB));
                }
            }
        }
    }

    private Field[] getAllFields(Object entity) {
        Class c = entity.getClass();
        Field[] fields = c.getDeclaredFields();
        while (c.getSuperclass() != Object.class) {
            c = c.getSuperclass();
            fields = Stream.concat(Arrays.stream(c.getDeclaredFields()), Arrays.stream(fields))
                    .toArray(Field[]::new);
        }
        return fields;
    }
}
