package com.netcracker.service.impl;

import com.netcracker.dao.ParkingSpaceDao;
import com.netcracker.dao.bean.ParkingSpace;
import com.netcracker.dao.builder.ParkingSpaceBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.impl.ParkingSpaceDaoImpl;
import com.netcracker.service.AbstractService;
import com.netcracker.service.CrudServiceExtended;
import com.netcracker.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ParkingSpaceServiceImpl extends AbstractService implements CrudServiceExtended<ParkingSpace> {
    private ParkingSpaceDao parkingSpaceDao = new ParkingSpaceDaoImpl();

    public List<String> getAllHeaders() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return parkingSpaceDao.getParkingSpaceHeaders(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<ParkingSpace> getAllEntities() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return parkingSpaceDao.getParkingSpaces(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<ParkingSpace> addEntity(ParkingSpace entity) throws ServiceException {
        List<ParkingSpace> parkingSpaces;
        Connection connection = null;
        try {
            connection = getConnection();
            parkingSpaceDao.addParkingSpace(entity,connection);
            parkingSpaces = parkingSpaceDao.getParkingSpaces(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return parkingSpaces;
    }

    public void removeEntity(ParkingSpace parkingSpace) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            parkingSpaceDao.removeParkingSpace(parkingSpace,connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public void updateEntity(ParkingSpace entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            parkingSpaceDao.updateParkingSpace(entity,connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public ParkingSpace buildEntity(Map<String, String[]> params) throws ServiceException {
        return new ParkingSpaceBuilder().id(Integer.parseInt(params.get("id")[0]))
                .level(Integer.parseInt(params.get("level")[0]))
                .reserved(Byte.parseByte(params.get("reserved")[0]))
                .build();
    }
}
