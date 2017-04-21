package com.netcracker.dao;

import com.netcracker.dao.bean.Role;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface RoleDao {
    List<String> getRoleHeaders(Connection connection) throws DaoException;
    List<Role> getRoles(Connection connection) throws DaoException;
    void addRole(Role role, Connection connection) throws DaoException;
    void removeRole(Role role, Connection connection) throws DaoException;
    void updateRole(Role role, Connection connection) throws DaoException;
}
