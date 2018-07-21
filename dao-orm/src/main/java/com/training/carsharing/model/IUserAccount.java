package com.training.carsharing.model;

public interface IUserAccount extends IBaseEntity {
    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getName();

    void setName(String name);

    String getPhotoLink();

    void setPhotoLink(String photoLink);

    String getPhone();

    void setPhone(String phone);

    String getRole();

    void setRole(String role);
}
