package by.hotelreservation.service.impl;

import by.hotelreservation.bean.dto.EntityDto;
import by.hotelreservation.bean.dto.UserDto;
import by.hotelreservation.bean.entity.User;
import by.hotelreservation.builder.RoleBuilder;
import by.hotelreservation.builder.UserBuilder;
import by.hotelreservation.dao.UserDao;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.*;
import by.hotelreservation.security.MD5;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements CrudServiceExtended<User> {
    @Autowired
    private UserDao userDao;

    public List<EntityDto> getAllHeaders() throws ServiceException {
        List<EntityDto> resultList = new ArrayList<>();
        List<UserDto> users = getAll();
        for (UserDto userDto: users) {
            resultList.add(new EntityDto(userDto.getId(), userDto.getName() + " " + userDto.getSurname()));
        }
        return resultList;
    }

    public List<UserDto> getAll() throws ServiceException {
        List<UserDto> userDtoList = new ArrayList<>();
        try {
            List<User> users = userDao.getAll();
            for (User user: users){
                userDtoList.add(new UserDto(user));
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDtoList;
    }

    public UserDto getById(int id) throws ServiceException {
        User user;
        try {
            user = userDao.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return new UserDto(user);
    }

    public List<User> add(User entity) throws ServiceException {
        List<User> users;
        try {
            userDao.add(entity);
            users = userDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    public void delete(User user) throws ServiceException {
        try {
            userDao.remove(user.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void update(User entity) throws ServiceException {
        try {
            userDao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public User build(Map<String, String[]> params) throws ServiceException {
        ValidatorUser validatorUser = new ValidatorUser();
        try {
            if (validatorUser.validate(params)) {
                User user = new UserBuilder().id(Integer.parseInt(params.get("id")[0]))
                        .name(params.get("name")[0])
                        .surname(params.get("surname")[0])
                        .login(params.get("login")[0])
                        .email(params.get("email")[0])
                        .mobilePhone(params.get("mobilePhone")[0])
                        .role(new RoleBuilder().id(Integer.parseInt(params.get("idRole")[0])).build())
                        .build();
                if(params.containsKey("password")){
                    user.setPassword(MD5.crypt(params.get("password")[0]));
                }
                if(params.containsKey("passportNumber")){
                    user.setPassportNumber(params.get("passportNumber")[0]);
                }
                return user;
            }
        } catch (IncorrectPassportNumberException
                | IncorrectUserSurnameException | IncorrectPasswordException
                | IncorrectMobilePhoneException | IncorrectLoginException
                | IncorrectUserNameException | IncorrectUserEmailException e) {
            throw new ServiceException(e);
        }
        return null;
    }
}