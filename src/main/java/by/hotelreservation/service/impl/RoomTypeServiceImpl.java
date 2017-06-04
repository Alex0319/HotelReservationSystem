package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.RoomType;
import by.hotelreservation.builder.RoomTypeBuilder;
import by.hotelreservation.dao.RoomTypeDao;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.*;
import by.hotelreservation.service.AbstractService;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorRoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Service
public class RoomTypeServiceImpl extends AbstractService implements CrudServiceExtended<RoomType> {
    @Autowired
    private RoomTypeDao roomTypeDao;

    public List<String> getAllHeaders() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return roomTypeDao.getRoomTypeHeaders(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<RoomType> getAll() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return roomTypeDao.getRoomTypes(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public RoomType getById(int id) throws ServiceException {
        return null;
    }

    public RoomType getEntity(int id) throws ServiceException {
        Connection connection = null;
        RoomType roomType;
        try {
            connection = getConnection();
            roomType = roomTypeDao.getRoomType(connection, id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return roomType;
    }

    public List<RoomType> add(RoomType entity) throws ServiceException {
        Connection connection = null;
        List<RoomType> roomTypes;
        try {
            connection = getConnection();
            roomTypeDao.addRoomType(entity, connection);
            roomTypes = roomTypeDao.getRoomTypes(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
        return roomTypes;
    }

    public void delete(RoomType roomType) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            roomTypeDao.removeRoomType(roomType, connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(RoomType entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            roomTypeDao.updateRoomType(entity, connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public RoomType build(Map<String, String[]> params) throws ServiceException {
        ValidatorRoomType validatorRoomType = new ValidatorRoomType();
        try {
            if (validatorRoomType.validate(params)) {
                return new RoomTypeBuilder().id(Integer.parseInt(params.get("id")[0]))
                        .roomsCount(Integer.parseInt(params.get("roomsCount")[0]))
                        .bedsCount(Integer.parseInt(params.get("bedsCount")[0]))
                        .costPerDay(Float.parseFloat(params.get("costPerDay")[0]))
                        .additionalInfo(params.get("additionalInfo")[0])
                        .bathroomsCount(Integer.parseInt(params.get("bathroomsCount")[0]))
                        .size(Integer.parseInt(params.get("size")[0]))
                        .build();
            }
        } catch (IncorrectRoomBedsException | IncorrectCostException
                | IncorrectRoomsCountException | IncorrectRoomBathroomsException
                | IncorrectRoomAdditionalInfoException | IncorrectRoomSizeException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public RoomType getLastInsertedEntity() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return roomTypeDao.getLastInsertedRoomType(connection);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            closeConnection(connection);
        }
    }

}