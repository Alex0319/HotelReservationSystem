package by.hotelreservation.service.impl;

import by.hotelreservation.bean.User;
import by.hotelreservation.builder.RoleBuilder;
import by.hotelreservation.builder.UserBuilder;
import by.hotelreservation.dao.UserDao;
import by.hotelreservation.dao.exception.DAOException;
import by.hotelreservation.dao.impl.UserDaoImpl;
import by.hotelreservation.security.MD5;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.exception.*;
import by.hotelreservation.service.validator.ValidatorUser;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class UserServiceImpl extends AbstractService implements CrudServiceExtended<User> {
    private UserDao userDao = new UserDaoImpl();

    public List<String> getAllHeaders() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return userDao.getUserHeaders(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<User> getAllEntities() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return userDao.getUsers(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public User getEntity(int id) throws ServiceException {
        Connection connection = null;
        User user;
        try {
            connection = getConnection();
            user = userDao.getUser(connection, id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return user;
    }

    public List<User> addEntity(User entity) throws ServiceException {
        Connection connection = null;
        List<User> users;
        try {
            connection = getConnection();
            userDao.addUser(entity, connection);
            users = userDao.getUsers(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return users;
    }

    public void removeEntity(User user) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            userDao.removeUser(user, connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void updateEntity(User entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            userDao.updateUser(entity, connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public User buildEntity(Map<String, String[]> params) throws ServiceException {
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

    @Override
    public User getLastInsertedEntity() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return userDao.getLastInsertedUser(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

}