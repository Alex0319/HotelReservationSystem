package by.hotelreservation.bean.dto;

import by.hotelreservation.bean.entity.Role;
import by.hotelreservation.bean.entity.User;

public class UserDto extends EntityDto{
    private String surname;
    private String email;
    private String mobilePhone;
    private String login;
    private Role role;

    public UserDto(){}

    public UserDto(User user){
        super(user.getId(), user.getName());
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.mobilePhone = user.getMobilePhone();
        this.login = user.getLogin();
        this.role = user.getRole();
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDto userDto = (UserDto) o;

        if (surname != null ? !surname.equals(userDto.surname) : userDto.surname != null) {
            return false;
        }
        if (email != null ? !email.equals(userDto.email) : userDto.email != null) {
            return false;
        }
        if (mobilePhone != null ? !mobilePhone.equals(userDto.mobilePhone) : userDto.mobilePhone != null) {
            return false;
        }
        return login != null ? login.equals(userDto.login) : userDto.login == null;
    }
}
