package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.builder.RoleBuilder;
import by.hotelreservation.builder.UserBuilder;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.*;
import by.hotelreservation.dao.UserDao;
import by.hotelreservation.security.MD5;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements CrudServiceExtended<User> {
    @Autowired
    private UserDao userDao;

    public List<String> getAllHeaders() throws ServiceException {
        try {
            return null;// userDao.getUserHeaders(connection);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAll() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public User getById(int id) throws ServiceException {
        User user;
        try {
            user = userDao.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
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
                return new UserBuilder().id(Integer.parseInt(params.get("id")[0]))
                        .name(params.get("name")[0])
                        .surname(params.get("surname")[0])
                        .login(params.get("login")[0])
                        .email(params.get("email")[0])
                        .password((MD5.crypt(params.get("password")[0])))
                        .passportNumber(params.get("passportNumber")[0])
                        .mobilePhone(params.get("mobilePhone")[0])
                        .role(new RoleBuilder().id(Integer.parseInt(params.get("idRole")[0])).build())
                        .build();
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