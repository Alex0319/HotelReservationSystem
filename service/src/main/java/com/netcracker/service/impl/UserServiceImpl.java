package com.netcracker.service.impl;

import com.netcracker.dao.UserDao;
import com.netcracker.dao.bean.User;
import com.netcracker.dao.builder.RoleBuilder;
import com.netcracker.dao.builder.UserBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.impl.UserDaoImpl;
import com.netcracker.service.AbstractService;
import com.netcracker.service.CrudServiceExtended;
import com.netcracker.service.exception.ServiceException;

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
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<User> getAllEntities() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return userDao.getUsers(connection);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<User> addEntity(User entity) throws ServiceException {
        Connection connection = null;
        List<User> users;
        try {
            connection = getConnection();
            userDao.addUser(entity, connection);
            users = userDao.getUsers(connection);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return users;
    }

    public void removeEntity(User user) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            userDao.removeUser(user, connection);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public void updateEntity(User entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            userDao.updateUser(entity, connection);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

	public User buildEntity(Map<String,String[]> params) throws ServiceException {
		return new UserBuilder().id(Integer.parseInt(params.get("id")[0]))
				.name(params.get("name")[0])
				.surname(params.get("surname")[0])
				.login(params.get("login")[0])
				.password(params.get("password")[0])
				.passportNumber(params.get("passportNumber")[0])
				.mobilePhone(params.get("mobilePhone")[0])
				.sex(params.get("sex")[0])
				.role(new RoleBuilder().id(Integer.parseInt(params.get("id_role")[0])).build())
				.build();
	}
}