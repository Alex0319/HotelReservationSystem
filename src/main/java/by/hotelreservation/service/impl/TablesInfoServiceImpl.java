package by.hotelreservation.service.impl;

import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.newdao.TablesInfoDao;
import by.hotelreservation.service.TablesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablesInfoServiceImpl implements TablesInfoService {
    @Autowired
    private TablesInfoDao tablesInfoDao;

    public List<String> getAllTablesNames() throws ServiceException {
        try {
            return tablesInfoDao.getNamesTables();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
