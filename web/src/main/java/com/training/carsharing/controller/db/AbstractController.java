package com.training.carsharing.controller.db;

import com.training.carsharing.*;
import com.training.carsharing.converter.AbstractFromDtoConverter;
import com.training.carsharing.converter.AbstractToDtoConverter;
import com.training.carsharing.dto.BaseDto;
import com.training.carsharing.dto.ListDto;
import com.training.carsharing.model.enums.Role;
import com.training.carsharing.model.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractController<ENTITY, DTO, ID> implements DBController<ENTITY, DTO, ID> {

    private static final String ADD_MAPPING = "/add";
    private static final String DELETE_MAPPING = "/{id}/delete";
    private static final String FORM_MODEL_ATTRIBUTE_NAME = "formModel";
    private static final String VIEW_DETAILS_MAPPING = "/{id}";
    private static final String EDIT_MAPPING = "/{id}/edit";

    private static final String MAIN_VIEW_POSTFIX = ".list";
    private static final String EDIT_VIEW_POSTFIX = ".edit";
    private static final String MAIN_REDIRECT_PREFIX = "redirect:/";

    private static final String PAGE = "page";
    private static final String SORT = "sort";
    private static final String PATH_VARIABLE_ID = "id";
    private static final String ERROR = "error";
    private static final String READONLY = "readonly";

    private static final String BRAND_FORM_SELECT_NAME = "brandChoices";
    private static final String GEARBOX_FORM_SELECT_NAME = "gearboxChoices";
    private static final String DRIVE_FORM_SELECT_NAME = "driveChoices";
    private static final String FUEL_FORM_SELECT_NAME = "fuelChoices";
    private static final String BODY_TYPE_FORM_SELECT_NAME = "bodyTypeChoices";
    private static final String MODEL_FORM_SELECT_NAME = "modelChoices";
    private static final String ENGINE_TYPE_FORM_SELECT_NAME = "engineTypeChoices";
    private static final String CAR_PARAMETER_FORM_SELECT_NAME = "carParameterChoices";
    private static final String ROLE_FORM_SELECT_NAME = "roleChoices";
    private static final String AD_FORM_SELECT_NAME = "adChoices";
    private static final String CAR_FORM_SELECT_NAME = "carChoices";

    private static final String ALL_USER_ACCOUNT_FORM_SELECT_NAME = "userAccountChoices";
    private static final String USER_ACCOUNT_WITHOUT_PASSWORD_FORM_SELECT_NAME = "userAccountWithoutPassportChoices";
    private static final String USER_ACCOUNT_WITHOUT_DRIVING_LICENSE_FORM_SELECT_NAME = "userAccountWithoutDrivingLicenseChoices";
    private static final String USER_ACCOUNT_WITH_CURRENT_PASSPORT_OR_WITHOUT_FORM_SELECT_NAME = "userAccountWithCurrentPassportOrWithoutChoices";
    private static final String USER_ACCOUNT_WITH_CURRENT_DRIVING_LICENSE_OR_WITHOUT_FORM_SELECT_NAME = "userAccountWithCurrentDrivingLicenseOrWithoutChoices";

    private static final String ALL_PASSPORT_FORM_SELECT_NAME = "passportChoices";
    private static final String PASSPORT_WITHOUT_USER_ACCOUNT_FORM_SELECT_NAME = "passportWithoutuserAccountChoices";
    private static final String PASSPORT_WITH_CURRENT_USER_ACCOUNT_OR_WITHOUT_FORM_SELECT_NAME = "passportWithCurrentUserAccountOrWithoutChoices";

    private static final String ALL_DRIVING_LICENSE_FORM_SELECT_NAME = "drivingLicenseChoices";
    private static final String DRIVING_LICENSE_WITHOUT_USER_ACCOUNT_FORM_SELECT_NAME = "drivingLicenseWithoutUserAccountChoices";
    private static final String DRIVING_LICENSE_WITH_CURRENT_USER_ACCOUNT_OR_WITHOUT_FORM_SELECT_NAME = "drivingLicenseWithCurrentUserAccountOrWithoutChoices";

    private static final String CAR_WITH_CURRENT_AD_OR_WITHOUT_FORM_SELECT_NAME = "carWithCurrentAdOrWithoutChoices";

    @Autowired
    private AbstractToDtoConverter<ENTITY, DTO> toDtoConverter;
    @Autowired
    private AbstractFromDtoConverter<DTO, ENTITY> fromDtoConverter;
    @Autowired
    private AbstractService<ENTITY, ID> service;
    @Autowired
    private BrandService brandService;
    @Autowired
    private GearboxService gearboxService;
    @Autowired
    private DriveService driveService;
    @Autowired
    private FuelService fuelService;
    @Autowired
    private BodyTypeService bodyTypeService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private EngineTypeService engineTypeService;
    @Autowired
    private CarParameterService carParameterService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private PassportService passportService;
    @Autowired
    private DrivingLicenseService drivingLicenseService;
    @Autowired
    private AdService adService;
    @Autowired
    private CarService carService;

    private final Class<? extends DTO> dtoClass;

    private String requestMapping;

    public AbstractController(Class<? extends DTO> dtoClass) {
        this.dtoClass = dtoClass;
        final String dtoClassSimpleName = dtoClass.getSimpleName();
        this.requestMapping = dtoClassSimpleName.substring(0, dtoClassSimpleName.length() - 3).toLowerCase();
    }

    @Override
    @GetMapping()
    public ModelAndView getIndexModelAndView(HttpServletRequest req,
                                             @RequestParam(name = PAGE, required = false) final Integer pageNumber,
                                             @RequestParam(name = SORT, required = false) final String sortColumn) {
        String viewName = requestMapping + MAIN_VIEW_POSTFIX;
        final ListDto<DTO> listDto = getListDto(req);
        listDto.setPage(pageNumber);
        listDto.setSort(sortColumn);
        List<ENTITY> entities = null;
        if (listDto.getSort() == null) {
            entities = service.findAll(listDto.getPage(), listDto.getItemsPerPage());
        } else {
            entities = service.findAll(listDto.getPage(), listDto.getItemsPerPage(), listDto.getSort().getColumn(), listDto.getSort().isAscending());
        }
        listDto.setList(entities.stream().map(toDtoConverter).collect(Collectors.toList()));
        listDto.setTotalCount(service.count());

        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(ListDto.SESSION_ATTR_NAME, listDto);
//        req.setAttribute(ListDto.SESSION_ATTR_NAME, listDto);
//        return "brand";
        return new ModelAndView(viewName, hashMap);
    }

    @Override
    @GetMapping(ADD_MAPPING)
    public ModelAndView getAddModelAndView() {
        String viewName = requestMapping + EDIT_VIEW_POSTFIX;
        final Map<String, Object> hashMap = getHashMapWithAllCommonForms(null);
        try {
            final DTO dto = dtoClass.newInstance();
            hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, dto);
        } catch (InstantiationException | IllegalAccessException e) {
            //TODo something with this
            e.printStackTrace();
        }
        return new ModelAndView(viewName, hashMap);
    }

    @Override
    @PostMapping()
    public Object getSaveModelAndView(@Valid @ModelAttribute(FORM_MODEL_ATTRIBUTE_NAME) final DTO formModel, BindingResult result) {
        String viewName = requestMapping + EDIT_VIEW_POSTFIX;
        String viewRedirect = MAIN_REDIRECT_PREFIX + requestMapping;
        Map<String, Object> hashMap = null;
        if (result.hasErrors()) {
            final BaseDto baseDto = (BaseDto) formModel;
            hashMap = getHashMapWithAllCommonForms((ID) baseDto.getId());
            hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, formModel);
            return new ModelAndView(viewName, hashMap);
        } else {
            try {
                final ENTITY entity = fromDtoConverter.apply(formModel);
                service.save(entity);
            } catch (ObjectOptimisticLockingFailureException | PersistenceException e) {
                hashMap = getHashMapWithAllCommonForms(null);
                hashMap.put(ERROR, e.getClass().getSimpleName());
                return new ModelAndView(viewName, hashMap);
            }
            return viewRedirect;
        }
    }

    @Override
    @GetMapping(DELETE_MAPPING)
    public String deleteEntity(@PathVariable(name = PATH_VARIABLE_ID) final ID id) {
        String viewRedirect = MAIN_REDIRECT_PREFIX + requestMapping;
        service.delete(service.findById(id));
        return viewRedirect;
    }

    @Override
    @GetMapping(VIEW_DETAILS_MAPPING)
    public ModelAndView getViewDetailsModelAndView(@PathVariable(name = PATH_VARIABLE_ID) final ID id) {
        String viewName = requestMapping + EDIT_VIEW_POSTFIX;
        final ENTITY dbModel = service.findOneFullInfo(id);
        final DTO dto = toDtoConverter.apply(dbModel);
        final Map<String, Object> hashMap = getHashMapWithAllCommonForms(id);
        hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, dto);
        hashMap.put(READONLY, true);
        return new ModelAndView(viewName, hashMap);
    }

    @Override
    @GetMapping(EDIT_MAPPING)
    public ModelAndView getEditModelAndView(@PathVariable(name = PATH_VARIABLE_ID) final ID id) {
        String viewName = requestMapping + EDIT_VIEW_POSTFIX;
        final DTO dto = toDtoConverter.apply(service.findOneFullInfo(id));
        final Map<String, Object> hashMap = getHashMapWithAllCommonForms(id);
        hashMap.put(FORM_MODEL_ATTRIBUTE_NAME, dto);
        return new ModelAndView(viewName, hashMap);
    }

    @Override
    public Map<String, Object> getHashMapWithAllCommonForms(ID id) {
        final Map<String, Object> hashMap = new HashMap<>();
        return hashMap;
    }

    protected void loadCommonFormBrands(final Map<String, Object> hashMap) {
        final Map<Long, String> map = brandService.findAll().stream()
                .collect(Collectors.toMap(Brand::getId, Brand::getName));
        hashMap.put(BRAND_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormGearboxes(final Map<String, Object> hashMap) {
        final Map<Long, String> map = gearboxService.findAll().stream()
                .collect(Collectors.toMap(Gearbox::getId, Gearbox::getName));
        hashMap.put(GEARBOX_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormDrives(final Map<String, Object> hashMap) {
        final Map<Long, String> map = driveService.findAll().stream()
                .collect(Collectors.toMap(Drive::getId, Drive::getName));
        hashMap.put(DRIVE_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormBodyTypes(final Map<String, Object> hashMap) {
        final Map<Long, String> map = bodyTypeService.findAll().stream()
                .collect(Collectors.toMap(BodyType::getId, BodyType::getName));
        hashMap.put(BODY_TYPE_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormFuels(final Map<String, Object> hashMap) {
        final Map<Long, String> map = fuelService.findAll().stream()
                .collect(Collectors.toMap(Fuel::getId, Fuel::getName));
        hashMap.put(FUEL_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormModels(final Map<String, Object> hashMap) {
        final Map<Long, String> map = modelService.findAll().stream()
                .collect(Collectors.toMap(Model::getId, Model::getName));
        hashMap.put(MODEL_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormEngineTypes(final Map<String, Object> hashMap) {
        final Map<Long, String> map = engineTypeService.findAll().stream()
                .collect(Collectors.toMap(EngineType::getId, EngineType::getName));
        hashMap.put(ENGINE_TYPE_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormCarParameters(final Map<String, Object> hashMap) {
        final Map<Long, String> map = carParameterService.findAll().stream()
                .collect(Collectors.toMap(CarParameter::getId, CarParameter::getName));
        hashMap.put(CAR_PARAMETER_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormAllUserAccounts(final Map<String, Object> hashMap) {
        final Map<Long, String> map = userAccountService.findAll().stream()
                .collect(Collectors.toMap(UserAccount::getId, UserAccount::getEmail));
        hashMap.put(ALL_USER_ACCOUNT_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormUserAccountsWithoutPassport(final Map<String, Object> hashMap) {
        final Map<Long, String> map = userAccountService.findWithoutPassport().stream()
                .collect(Collectors.toMap(UserAccount::getId, UserAccount::getEmail));
        hashMap.put(USER_ACCOUNT_WITHOUT_PASSWORD_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormUserAccountsWithCurrentPassportOrWithout(final Map<String, Object> hashMap, Long id) {
        final Map<Long, String> map = userAccountService.findWithoutPassport().stream()
                .collect(Collectors.toMap(UserAccount::getId, UserAccount::getEmail));
        UserAccount userAccountWithCurrentPassport = userAccountService.findByPassport(id);
        if (userAccountWithCurrentPassport != null) {
            map.put(userAccountWithCurrentPassport.getId(), userAccountWithCurrentPassport.getEmail());
        }
        hashMap.put(USER_ACCOUNT_WITH_CURRENT_PASSPORT_OR_WITHOUT_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormUserAccountsWithoutDrivingLicense(final Map<String, Object> hashMap) {
        final Map<Long, String> map = userAccountService.findWithoutDrivingLicense().stream()
                .collect(Collectors.toMap(UserAccount::getId, UserAccount::getEmail));
        hashMap.put(USER_ACCOUNT_WITHOUT_DRIVING_LICENSE_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormUserAccountsWithCurrentDrivingLicenseOrWithout(final Map<String, Object> hashMap, Long id) {
        final Map<Long, String> map = userAccountService.findWithoutDrivingLicense().stream()
                .collect(Collectors.toMap(UserAccount::getId, UserAccount::getEmail));
        UserAccount userAccountWithCurrentDrivingLicense = userAccountService.findByDrivingLicense(id);
        if (userAccountWithCurrentDrivingLicense != null) {
            map.put(userAccountWithCurrentDrivingLicense.getId(), userAccountWithCurrentDrivingLicense.getEmail());
        }
        hashMap.put(USER_ACCOUNT_WITH_CURRENT_DRIVING_LICENSE_OR_WITHOUT_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormAllPassports(final Map<String, Object> hashMap) {
        final Map<Long, String> map = passportService.findAll().stream()
                .collect(Collectors.toMap(Passport::getId, Passport::getNumber));
        hashMap.put(ALL_PASSPORT_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormPassportsWithoutUserAccount(final Map<String, Object> hashMap) {
        final Map<Long, String> map = passportService.findByUserAccountIdIsNull().stream()
                .collect(Collectors.toMap(Passport::getId, Passport::getNumber));
        hashMap.put(PASSPORT_WITHOUT_USER_ACCOUNT_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormPassportsWithCurrentUserAccountOrWithout(final Map<String, Object> hashMap, Long id) {
        final Map<Long, String> map = passportService.findByUserAccountIdIsNull().stream()
                .collect(Collectors.toMap(Passport::getId, Passport::getNumber));
        Passport passportWithCurrentUserAccount = passportService.findByUserAccountId(id);
        if (passportWithCurrentUserAccount != null) {
            map.put(passportWithCurrentUserAccount.getId(), passportWithCurrentUserAccount.getNumber());
        }
        hashMap.put(PASSPORT_WITH_CURRENT_USER_ACCOUNT_OR_WITHOUT_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormAllDrivingLicenses(final Map<String, Object> hashMap) {
        final Map<Long, String> map = drivingLicenseService.findAll().stream()
                .collect(Collectors.toMap(DrivingLicense::getId, DrivingLicense::getNumber));
        hashMap.put(ALL_DRIVING_LICENSE_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormDrivingLicensesWithoutUserAccount(final Map<String, Object> hashMap) {
        final Map<Long, String> map = drivingLicenseService.findByUserAccountIsNull().stream()
                .collect(Collectors.toMap(DrivingLicense::getId, DrivingLicense::getNumber));
        hashMap.put(DRIVING_LICENSE_WITHOUT_USER_ACCOUNT_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormDrivingLicensesWithCurrentUserAccountOrWithout(final Map<String, Object> hashMap, Long id) {
        final Map<Long, String> map = drivingLicenseService.findByUserAccountIsNull().stream()
                .collect(Collectors.toMap(DrivingLicense::getId, DrivingLicense::getNumber));
        DrivingLicense drivingLicenseWithCurrentUserAccount = drivingLicenseService.findByUserAccount(id);
        if (drivingLicenseWithCurrentUserAccount != null) {
            map.put(drivingLicenseWithCurrentUserAccount.getId(), drivingLicenseWithCurrentUserAccount.getNumber());
        }
        hashMap.put(DRIVING_LICENSE_WITH_CURRENT_USER_ACCOUNT_OR_WITHOUT_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormCars(final Map<String, Object> hashMap) {
        final Map<Long, String> map = carService.findAll().stream()
                .collect(Collectors.toMap(Car::getId, Car::getPlate));
        hashMap.put(CAR_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormAds(final Map<String, Object> hashMap) {
        final Map<Long, Long> map = adService.findAll().stream().collect(Collectors.toMap(Ad::getId, Ad::getId));
        //no sorted because id:id
        hashMap.put(AD_FORM_SELECT_NAME, map);
    }

    protected void loadCommonFormCarsWithCurrentAdOrWithout(final Map<String, Object> hashMap, Long id) {
        final Map<Long, String> map = carService.findWithoutAd().stream()
                .collect(Collectors.toMap(Car::getId, Car::getPlate));
        Car carWithCurrentAd = carService.findByAd(id);
        if (carWithCurrentAd != null) {
            map.put(carWithCurrentAd.getId(), carWithCurrentAd.getPlate());
        }
        hashMap.put(CAR_WITH_CURRENT_AD_OR_WITHOUT_FORM_SELECT_NAME, getSortedMapByValue(map));
    }

    protected void loadCommonFormRoles(final Map<String, Object> hashMap) {
        final List<Role> rolesList = Arrays.asList(Role.values());
        final Map<String, String> rolesMap = rolesList.stream().collect(Collectors.toMap(Role::name, Role::name));
        hashMap.put(ROLE_FORM_SELECT_NAME, rolesMap);
    }

    private Map<Long, String> getSortedMapByValue(final Map<Long, String> map) {
        final Map<Long, String> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }

    private ListDto<DTO> getListDto(final HttpServletRequest req) {
        final String sessionModelName = getClass().getSimpleName() + "_LIST_MODEL";
        ListDto<DTO> listDto = (ListDto<DTO>) req.getSession().getAttribute(sessionModelName);
        if (listDto == null) {
            listDto = new ListDto<DTO>();
            req.getSession().setAttribute(sessionModelName, listDto);
        }
        return listDto;
    }
}