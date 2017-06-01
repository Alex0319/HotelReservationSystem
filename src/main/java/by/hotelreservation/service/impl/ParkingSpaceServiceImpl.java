package by.hotelreservation.service.impl;


import by.hotelreservation.bean.entity.ParkingSpace;
import by.hotelreservation.builder.ParkingSpaceBuilder;
import by.hotelreservation.dao.ParkingSpaceDao;
import by.hotelreservation.dao.impl.ParkingSpaceDaoImpl;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.IncorrectParkingSpaceLevelException;
import by.hotelreservation.exception.validateexception.IncorrectParkingSpaceReservationException;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorParkingSpace;

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
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<ParkingSpace> getAll() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return parkingSpaceDao.getParkingSpaces(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<ParkingSpace> add(ParkingSpace entity) throws ServiceException {
        List<ParkingSpace> parkingSpaces;
        Connection connection = null;
        try {
            connection = getConnection();
            parkingSpaceDao.addParkingSpace(entity, connection);
            parkingSpaces = parkingSpaceDao.getParkingSpaces(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return parkingSpaces;
    }

    public void delete(ParkingSpace parkingSpace) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            parkingSpaceDao.removeParkingSpace(parkingSpace, connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(ParkingSpace entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            parkingSpaceDao.updateParkingSpace(entity, connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public ParkingSpace build(Map<String, String[]> params) throws ServiceException {
        ValidatorParkingSpace validatorParkingSpace = new ValidatorParkingSpace();
        try {
            if (validatorParkingSpace.validate(params)) {
                return new ParkingSpaceBuilder().id(Integer.parseInt(params.get("id")[0]))
                        .level(Integer.parseInt(params.get("level")[0]))
                        .reserved(Byte.parseByte(params.get("isReserved")[0]))
                        .build();
            }
        } catch (IncorrectParkingSpaceLevelException | IncorrectParkingSpaceReservationException e) {
            throw new ServiceException(e);
        }
        return null;

    }

    @Override
    public ParkingSpace getLastInsertedEntity() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return parkingSpaceDao.getLastInsertedParkingSpace(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }


}