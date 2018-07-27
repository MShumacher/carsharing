package com.training.carsharing;

import com.training.carsharing.model.*;
import com.training.carsharing.model.enums.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public abstract class AbstractTest {

    @Autowired
    private IModelService modelService;
    @Autowired
    private IUserAccountService userAccountService;
    @Autowired
    private ICarsPhotoService carsPhotoService;
    @Autowired
    private ICarService carService;
    @Autowired
    private ICarParameterService carParameterService;
    @Autowired
    private ICalendarService calendarService;
    @Autowired
    private IAdService adService;
    @Autowired
    private IPassportService passportService;
    @Autowired
    private IDrivingLicenseService drivingLicenseService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IGearboxService gearboxService;
    @Autowired
    private IBodyTypeService bodyTypeService;
    @Autowired
    private IDriveService driveService;
    @Autowired
    private IEngineTypeService engineTypeService;
    @Autowired
    private IFuelService fuelService;
    @Autowired
    private IMessageService messageService;

    private static final Random RANDOM = new Random();

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

    public IUserAccountService getUserAccountService() {
        return userAccountService;
    }
    public IModelService getModelService() {
        return modelService;
    }
    public ICarsPhotoService getCarsPhotoService() {
        return carsPhotoService;
    }
    public ICarService getCarService() { return carService; }
    public ICarParameterService getCarParameterService() { return carParameterService; }
    public ICalendarService getCalendarService() { return calendarService; }
    public IAdService getAdService() { return adService; }
    public IPassportService getPassportService() { return passportService; }
    public IDrivingLicenseService getDrivingLicenseService() { return drivingLicenseService; }
    public IBrandService getBrandService() {
        return brandService;
    }
    public IGearboxService getGearboxService() {
        return gearboxService;
    }
    public IBodyTypeService getBodyTypeService() {
        return bodyTypeService;
    }
    public IDriveService getDriveService() {
        return driveService;
    }
    public IEngineTypeService getEngineTypeService() {
        return engineTypeService;
    }
    public IFuelService getFuelService() {
        return fuelService;
    }
    public IMessageService getMessageService() {
        return messageService;
    }


    protected IModel saveNewModel() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IModel entity = getModelService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        entity.setBrand(saveNewBrand());
        getModelService().save(entity);
        return entity;
    }

    protected IBrand saveNewBrand() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IBrand entity = getBrandService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        getBrandService().save(entity);
        return entity;
    }

    protected IGearbox saveNewGearbox() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IGearbox entity = getGearboxService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        getGearboxService().save(entity);
        return entity;
    }

    protected IBodyType saveNewBodyType() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IBodyType entity = getBodyTypeService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        getBodyTypeService().save(entity);
        return entity;
    }

    protected IDrive saveNewDrive() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IDrive entity = getDriveService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        getDriveService().save(entity);
        return entity;
    }

    protected IEngineType saveNewEngineType() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
       final IEngineType entity = getEngineTypeService().createEntity();
       entity.setName("Name-" + getRandomPrefix());
       entity.setFuel(saveNewFuel());
       getEngineTypeService().save(entity);
       return entity;
    }

    protected IFuel saveNewFuel() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IFuel entity = getFuelService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        getFuelService().save(entity);
        return entity;
    }

    protected IUserAccount saveNewUserAccount() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final IUserAccount entity = getUserAccountService().createEntity();
//        entity.setPassport(saveNewPassport());
//        entity.setDrivingLicense(saveNewDrivingLicense());
        entity.setEmail("Email-" + getRandomPrefix());
        entity.setPassword("Password-" + getRandomPrefix());
        entity.setName("Name-" + getRandomPrefix());
        entity.setPhotoLink("PhotoLink-" + getRandomPrefix());
        entity.setPhone("Phone-" + getRandomPrefix());
        entity.setRole("Role-" + getRandomPrefix());
        getUserAccountService().save(entity);
        return entity;
    }

    protected IPassport saveNewPassport() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final IPassport entity = getPassportService().createEntity();
        entity.setUserAccount(saveNewUserAccount());
        entity.setFullName("FullName-" + getRandomPrefix());
        entity.setNumber("Number-" + getRandomPrefix());
        entity.setIssuePlace("IssuePlace-" + getRandomPrefix());
        entity.setIssueDate(getRandomDate());
        entity.setBirthPlace("BirthPlace-" + getRandomPrefix());
        entity.setBirthday(getRandomDate());
        getPassportService().save(entity);
        return entity;
    }

    protected IDrivingLicense saveNewDrivingLicense() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final IDrivingLicense entity = getDrivingLicenseService().createEntity();
        entity.setUserAccount(saveNewUserAccount());
        entity.setNumber("Number-" + getRandomPrefix());
        entity.setExpirationDate(getRandomDate());
        entity.setCategories("Categories-" + getRandomPrefix());
        getDrivingLicenseService().save(entity);
        return entity;
    }

        protected ICarsPhoto saveNewCarsPhoto() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICarsPhoto entity = getCarsPhotoService().createEntity();
        entity.setCar(saveNewCar());
        entity.setLink("Link-" + getRandomPrefix());
        getCarsPhotoService().save(entity);
        return entity;
    }

    protected ICar saveNewCar() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final ICar entity = getCarService().createEntity();

        final int randomObjectsCount = getRandomObjectsCount();
        final List<ICarParameter> parameters = new ArrayList<>();
        for (int i = 0; i < randomObjectsCount; i++) {
            parameters.add(saveNewCarParameter());
        }
        entity.getCarParameter().addAll(parameters);

//        entity.setUserAccount(saveNewUserAccount());
        entity.setModel(saveNewModel());
        entity.setYear(getRandomPrefix());
        entity.setPlate("Plate-" + getRandomPrefix());
        entity.setMileage(getRandomPrefix());
        entity.setSeats(getRandomPrefix());
        entity.setGearbox(saveNewGearbox());
        entity.setBodyType(saveNewBodyType());
        entity.setDrive(saveNewDrive());
        entity.setEngineType(saveNewEngineType());
        entity.setCharge(getRandomDouble());
        entity.setConditions("Conditions-" + getRandomPrefix());
        entity.setInsurance("Insurance-" + getRandomPrefix());
        getCarService().save(entity);
        return entity;
    }

    protected IMessage saveNewMessage() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final IMessage entity = getMessageService().createEntity();
        entity.setMessage("Message"+getRandomPrefix());
        entity.setAd(saveNewAd());
        entity.setSender(saveNewUserAccount());
        entity.setRecipient(saveNewUserAccount());
        entity.setViewed(getRandomBoolean());
        getMessageService().save(entity);
        return entity;
    }

    protected ICarParameter saveNewCarParameter() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICarParameter entity = getCarParameterService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        getCarParameterService().save(entity);
        return entity;
    }

    protected ICalendar saveNewCalendar() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ICalendar entity = getCalendarService().createEntity();
        entity.setRenter(saveNewUserAccount());
        IAd ad=saveNewAd();
        entity.setCar(ad.getCar());
        entity.setStart(getRandomDate());
        entity.setEnd(getRandomDate());
        entity.setTotalPrice(getRandomDouble());
        getCalendarService().save(entity);
        return entity;
    }

    protected IAd saveNewAd() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final IAd entity = getAdService().createEntity();
        entity.setUserAccount(saveNewUserAccount());
        entity.setCar(saveNewCar());
        entity.setAddress("Address-"+getRandomPrefix());
        entity.setPrice(getRandomDouble());
        entity.setBody("Body-"+getRandomPrefix());
        entity.setActive(getRandomBoolean());
        getAdService().save(entity);
        return entity;
    }

    protected static void assertEqualsDates(Date date1, Date date2) {
        String d1 = formatter.format(date1);
        String d2 = formatter.format(date2);
        assertTrue( d1.equals(d2));
    }

    protected void assertNotNullFieldsExcept(Object entity, String... unverifiableFields) throws IllegalAccessException {
        Collection<String> unverifiableFieldsCollection = Arrays.asList(unverifiableFields);
        Field[] fields = getAllFields(entity);
        for (Field field : fields) {
            field.setAccessible(true);
            final String fieldName = field.getName();
            if (!unverifiableFieldsCollection.stream().anyMatch(fieldName::equalsIgnoreCase)) {
                System.out.println("Имя: " + field.getName() + " Тип: " + field.getType().getSimpleName() + " Значение: " + field.get(entity));
                assertNotNull(field.get(entity));
            }
        }
    }


    protected void assertEqualsFieldsExcept(Object entity, Object entityFromDB, String... unverifiableFields) throws IllegalAccessException {
        Collection<String> unverifiableFieldsCollection = Arrays.asList(unverifiableFields);
        Field[] fields = getAllFields(entity);
        for (Field field : fields) {
            field.setAccessible(true);
            final String fieldName = field.getName();
            if (!unverifiableFieldsCollection.stream().anyMatch(fieldName::equalsIgnoreCase)) {
                System.out.println("field: "+ fieldName + " " + field.get(entity)+"=="+field.get(entityFromDB));
//                if ("Date".equalsIgnoreCase(field.getType().getSimpleName())) {
//                    assertEquals((Date) field.get(entity), (Date) field.get(entityFromDB));
//                } else {
                    assertEquals(field.get(entity), field.get(entityFromDB));
                }
//            }
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

    protected int getRandomPrefix() {
        return RANDOM.nextInt(99999);
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    protected Boolean getRandomBoolean() {
        return RANDOM.nextBoolean();
    }

    protected Double getRandomDouble() {
        final double min = 0;
        final double max = 100.00;
        final double diff = max - min;
        final double randomValue = min + RANDOM.nextDouble() * diff;
        final double res = Math.floor(randomValue * 100);
        return res / 100;
    }

    protected Date getRandomDate() {
        final long millisInDay = 60 * 60 * 24 * 1000;
        final long rand = RANDOM.nextInt(17600);
        final long dateMillis = Math.abs(System.currentTimeMillis() - rand * millisInDay);
        final long dateOnly = (dateMillis / millisInDay) * millisInDay;
        final Date date = new Date(dateOnly);
        return date;
    }
}
