package com.training.carsharing.repository;

import com.training.carsharing.model.impl.UserAccount;
import com.training.carsharing.repository.customrepository.UserAccountRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAccountRepository extends AbstractRepository<UserAccount, Long>, UserAccountRepositoryCustom {

    List<UserAccount> findByEmail(String name);

    @Query("SELECT u FROM UserAccount u WHERE u.id NOT IN (SELECT p.userAccount.id FROM Passport p WHERE p.userAccount <> null) ORDER BY u.email")
    //@Query(SELECT * FROM user_account u WHERE u.id NOT IN (SELECT p.user_account_id FROM passport p WHERE p.user_account_id != null) ORDER BY u.email
    List<UserAccount> findWithoutPassport();

    @Query("SELECT u FROM UserAccount u WHERE u.id NOT IN (SELECT d.userAccount.id FROM DrivingLicense d WHERE d.userAccount <> null) ORDER BY u.email")
    List<UserAccount> findWithoutDrivingLicense();

    @Query("SELECT u FROM UserAccount u WHERE u.id = (SELECT d.userAccount.id FROM DrivingLicense d WHERE d.id = :drivingLicenseId)")
    UserAccount findByDrivingLicenseId(@Param("drivingLicenseId") Long drivingLicenseId);

    @Query("SELECT u FROM UserAccount u WHERE u.id = (SELECT p.userAccount.id FROM Passport p WHERE p.id = :passportId)")
    UserAccount findByPassportId(@Param("passportId") Long passportId);
}
