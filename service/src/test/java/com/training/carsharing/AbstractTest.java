package com.training.carsharing;

import com.training.carsharing.config.ServiceTestConfig;
import com.training.carsharing.model.enums.Role;
import com.training.carsharing.model.impl.*;
import com.training.carsharing.model.impl.Calendar;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(value = "classpath:jdbc-test.properties")
@ContextConfiguration(classes = {ServiceTestConfig.class})
//@ContextConfiguration(locations = "classpath:test-context.xml")
public abstract class AbstractTest {

    @Autowired
    private ModelService modelService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private CarsPhotoService carsPhotoService;
    @Autowired
    private CarService carService;
    @Autowired
    private CarParameterService carParameterService;
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private AdService adService;
    @Autowired
    private PassportService passportService;
    @Autowired
    private DrivingLicenseService drivingLicenseService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private GearboxService gearboxService;
    @Autowired
    private BodyTypeService bodyTypeService;
    @Autowired
    private DriveService driveService;
    @Autowired
    private EngineTypeService engineTypeService;
    @Autowired
    private FuelService fuelService;
    @Autowired
    private MessageService messageService;

    private static final Random RANDOM = new Random();

    public UserAccountService getUserAccountService() {
        return userAccountService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public CarsPhotoService getCarsPhotoService() {
        return carsPhotoService;
    }

    public CarService getCarService() {
        return carService;
    }

    public CarParameterService getCarParameterService() {
        return carParameterService;
    }

    public CalendarService getCalendarService() {
        return calendarService;
    }

    public AdService getAdService() {
        return adService;
    }

    public PassportService getPassportService() {
        return passportService;
    }

    public DrivingLicenseService getDrivingLicenseService() {
        return drivingLicenseService;
    }

    public BrandService getBrandService() {
        return brandService;
    }

    public GearboxService getGearboxService() {
        return gearboxService;
    }

    public BodyTypeService getBodyTypeService() {
        return bodyTypeService;
    }

    public DriveService getDriveService() {
        return driveService;
    }

    public EngineTypeService getEngineTypeService() {
        return engineTypeService;
    }

    public FuelService getFuelService() {
        return fuelService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    protected Model saveNewModel() {
        final Model entity = getModelService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        entity.setBrand(saveNewBrand());
        return getModelService().save(entity);
    }

    protected Brand saveNewBrand() {
        final Brand entity = getBrandService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        return getBrandService().save(entity);
    }

    protected Gearbox saveNewGearbox() {
        final Gearbox entity = getGearboxService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        return getGearboxService().save(entity);
    }

    protected BodyType saveNewBodyType() {
        final BodyType entity = getBodyTypeService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        return getBodyTypeService().save(entity);
    }

    protected Drive saveNewDrive() {
        final Drive entity = getDriveService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        return getDriveService().save(entity);
    }

    protected EngineType saveNewEngineType() {
        final EngineType entity = getEngineTypeService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        entity.setFuel(saveNewFuel());
        return getEngineTypeService().save(entity);
    }

    protected Fuel saveNewFuel() {
        final Fuel entity = getFuelService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        return getFuelService().save(entity);
    }

    protected UserAccount getNewUserAccount() {
        final UserAccount entity = getUserAccountService().createEntity();
        entity.setEmail("Email-" + getRandomPrefix());
        entity.setPassword("Password-" + getRandomPrefix());
        entity.setName("Name-" + getRandomPrefix());
        entity.setPhotoLink("PhotoLink-" + getRandomPrefix());
        entity.setPhone("Phone-" + getRandomPrefix());

        final Role[] roles = Role.values();
        int randomIndex = Math.max(0, RANDOM.nextInt(roles.length) - 1);
        entity.setRole(roles[randomIndex]);
        return entity;
    }

    protected UserAccount saveNewUserAccount() {
        return getUserAccountService().save(getNewUserAccount());
    }

    protected Passport getNewPassport() {
        final Passport entity = getPassportService().createEntity();
        entity.setUserAccount(saveNewUserAccount());
        entity.setFullName("FullName-" + getRandomPrefix());
        entity.setNumber("Number-" + getRandomPrefix());
        entity.setIssuePlace("IssuePlace-" + getRandomPrefix());
        entity.setIssueDate(getRandomDate());
        entity.setBirthPlace("BirthPlace-" + getRandomPrefix());
        entity.setBirthday(getRandomDate());
        return entity;
    }

    protected Passport saveNewPassport() {
        return getPassportService().save(getNewPassport());
    }

    protected DrivingLicense getNewDrivingLicense() {
        final DrivingLicense entity = getDrivingLicenseService().createEntity();
        entity.setUserAccount(saveNewUserAccount());
        entity.setNumber("Number-" + getRandomPrefix());
        entity.setExpirationDate(getRandomDate());
        entity.setCategories("Categories-" + getRandomPrefix());
        return entity;
    }

    protected DrivingLicense saveNewDrivingLicense() {
        return getDrivingLicenseService().save(getNewDrivingLicense());
    }

    protected CarsPhoto saveNewCarsPhoto() {
        final CarsPhoto entity = getCarsPhotoService().createEntity();
        entity.setCar(saveNewCar());
        entity.setLink("Link-" + getRandomPrefix());
        return getCarsPhotoService().save(entity);
    }

    protected Car saveNewCar() {
        return getCarService().save(getNewCar());
    }

    protected Car getNewCar() {
        final Car entity = getCarService().createEntity();

        final int randomObjectsCount = getRandomObjectsCount();
        final List<CarParameter> parameters = new ArrayList<>();
        for (int i = 0; i < randomObjectsCount; i++) {
            parameters.add(saveNewCarParameter());
        }
        entity.getCarParameter().addAll(parameters);

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
        return entity;
    }

    protected Message saveNewMessage() {
        final Message entity = getMessageService().createEntity();
        entity.setMessage("Message" + getRandomPrefix());
        entity.setAd(saveNewAd());
        entity.setSender(saveNewUserAccount());
        entity.setRecipient(saveNewUserAccount());
        entity.setViewed(getRandomBoolean());
        return getMessageService().save(entity);
    }

    protected CarParameter saveNewCarParameter() {
        final CarParameter entity = getCarParameterService().createEntity();
        entity.setName("Name-" + getRandomPrefix());
        return getCarParameterService().save(entity);
    }

    protected Calendar saveNewCalendar() {
        final Calendar entity = getCalendarService().createEntity();
        entity.setRenter(saveNewUserAccount());
        Ad ad = saveNewAd();
        entity.setCar(ad.getCar());
        entity.setStart(getRandomDate());
        entity.setEnd(getRandomDate());
        entity.setTotalPrice(getRandomDouble());
        return getCalendarService().save(entity);
    }

    protected Ad saveNewAd() {
        final Ad entity = getAdService().createEntity();
        entity.setCar(saveNewCar());
        entity.setUserAccount(saveNewUserAccount());
        entity.setAddress("Address-" + getRandomPrefix());
        entity.setPrice(getRandomDouble());
        entity.setBody("Body-" + getRandomPrefix());
        entity.setActive(getRandomBoolean());
        return getAdService().save(entity);
    }

    protected void assertNotNullFieldsExcept(Object entity, String... unverifiableFields) throws IllegalAccessException {
        Collection<String> unverifiableFieldsCollection = Arrays.asList(unverifiableFields);
        Field[] fields = getAllFields(entity);
        for (Field field : fields) {
            field.setAccessible(true);
            final String fieldName = field.getName();
            if (!unverifiableFieldsCollection.stream().anyMatch(fieldName::equalsIgnoreCase)) {
//                System.out.println("Имя: " + field.getName() + " Тип: " + field.getType().getSimpleName() + " Значение: " + field.get(entity));
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
//                System.out.println("field: " + fieldName + " " + field.get(entity) + "==" + field.get(entityFromDB));
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
