package com.netcracker.dao;

import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface TablesInfoDao {
    List<String> getNamesTables(Connection connection) throws DaoException;
}
