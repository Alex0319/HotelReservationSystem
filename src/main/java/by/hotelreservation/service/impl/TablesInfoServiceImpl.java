package by.hotelreservation.service.impl;

import by.hotelreservation.dao.TablesInfoDao;
import by.hotelreservation.dao.impl.TablesInfoDaoImpl;
import by.hotelreservation.dao.exception.DAOException;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.TablesInfoService;
import by.hotelreservation.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;

public class TablesInfoServiceImpl extends AbstractService implements TablesInfoService {
    TablesInfoDao tablesInfoDao = new TablesInfoDaoImpl();
    public List<String> getAllTablesNames() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return tablesInfoDao.getNamesTables(connection);
        }catch (DAOException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }
}
