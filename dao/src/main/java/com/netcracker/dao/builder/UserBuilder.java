package com.netcracker.dao.builder;

import com.netcracker.dao.bean.Role;
import com.netcracker.dao.bean.User;

public class UserBuilder {
    private int id;
    private String name;
    private String surname;
    private String mobilePhone;
    private String login;
    private String passportNumber;
    private String password;
    private String sex;
    private Role role;

    public UserBuilder id(int id){
        this.id = id;
        return this;
    }

    public UserBuilder name(String name){
        this.name = name;
        return this;
    }

    public UserBuilder surname(String surname){
        this.surname = surname;
        return this;
    }

    public UserBuilder mobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
        return this;
    }

    public UserBuilder login(String login){
        this.login = login;
        return this;
    }

    public UserBuilder passportNumber(String passportNumber){
        this.passportNumber = passportNumber;
        return this;
    }

    public UserBuilder password(String password){
        this.password = password;
        return this;
    }

    public UserBuilder sex(String sex){
        this.sex = sex;
        return this;
    }

    public UserBuilder role(Role role){
        this.role = role;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getLogin() {
        return login;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public Role getRole() {
        return role;
    }

    public User build(){
        return new User(this);
    }
}
