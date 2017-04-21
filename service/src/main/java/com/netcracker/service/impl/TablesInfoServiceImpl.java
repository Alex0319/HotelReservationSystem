package com.netcracker.service.impl;

import com.netcracker.dao.TablesInfoDao;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.impl.TablesInfoDaoImpl;
import com.netcracker.service.AbstractService;
import com.netcracker.service.TablesInfoService;
import com.netcracker.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;

public class TablesInfoServiceImpl extends AbstractService implements TablesInfoService {
    TablesInfoDao tablesInfoDao = new TablesInfoDaoImpl();
    public List<String> getAllTablesNames() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return tablesInfoDao.getNamesTables(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }
}
