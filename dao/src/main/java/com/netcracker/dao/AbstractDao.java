package com.netcracker.dao;

import com.netcracker.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public abstract class AbstractDao {
    private static final Logger logger;
    private static DataSource dataSource;

    static {
        try {
            logger = LogManager.getLogger(AbstractDao.class.getName());
            Class.forName(Driver.class.getName());
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/dbconnect");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final Connection getConnection() throws DaoException {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return connection;
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws DaoException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            } finally {
                try {
                    if(connection != null){
                        connection.close();
                    }
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
    }
}