package com.netcracker.dao.daoimpl;

import com.netcracker.dao.AbstractDao;
import com.netcracker.dao.UserDao;
import com.netcracker.dao.bean.User;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.netcracker.dao.constant.Constants.*;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private UserDaoImpl(){}

    public static class Holder{
        private final static UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance(){
        return UserDaoImpl.Holder.INSTANCE;
    }

    public List<User> getUsers() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<User>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_ALL_USERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(fillUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection, statement,resultSet);
        }
        return users;
    }

    public void addUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ADD_USER);
            statement = fillStatement(statement, user);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection, statement,null);
        }
    }

    public void removeUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement.setInt(1, user.getId());
            statement = connection.prepareStatement(REMOVE_USER);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,null);
        }
    }

    public void updateUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_USER);
            statement = fillStatement(statement, user);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,null);
        }
    }

    public User getUser(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_USER);
            resultSet = statement.executeQuery();
            user = fillUser(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeConnection(connection,statement,resultSet);
        }
        return user;
    }

    private PreparedStatement fillStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getPassportNumber());
        statement.setString(2, user.getName());
        statement.setString(3, user.getSurname());
        statement.setString(4, user.getMobilePhone());
        statement.setString(5, user.getPassword());
        statement.setString(6, user.getLogin());
        return statement;
    }

    private User fillUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setPassportNumber(resultSet.getString("passport_number"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setMobilePhone(resultSet.getString("mobile_phone"));
        user.setPassword(resultSet.getString("password"));
        user.setLogin(resultSet.getString("login"));
        return user;
    }
}