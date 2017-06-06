package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.Room;
import by.hotelreservation.builder.RoomBuilder;
import by.hotelreservation.builder.RoomTypeBuilder;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.IncorrectRoomNameException;
import by.hotelreservation.exception.validateexception.IncorrectRoomPathException;
import by.hotelreservation.exception.validateexception.IncorrectRoomPhoneNumberException;
import by.hotelreservation.dao.EntityDao;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoomServiceImpl implements CrudServiceExtended<Room> {
    @Autowired
    private EntityDao<Room> roomDao;

    public List<String> getAllHeaders() throws ServiceException {
        try {
            return null;//roomTypeDao.getRoomTypeHeaders(connection);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Room> getAll() throws ServiceException {
        try {
            return roomDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Room getById(int id) throws ServiceException {
        Room room;
        try {
            room = roomDao.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return room;
    }

    public List<Room> add(Room entity) throws ServiceException {
        List<Room> rooms;
        try {
            roomDao.add(entity);
            rooms = roomDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rooms;
    }

    public void delete(Room room) throws ServiceException {
        try {
            roomDao.remove(room.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void update(Room entity) throws ServiceException {
        try {
            roomDao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Room build(Map<String, String[]> params) throws ServiceException {
        ValidatorRoom validatorRoom = new ValidatorRoom();
        try {
            if (validatorRoom.validate(params)) {
                return new RoomBuilder().id(Integer.parseInt(params.get("id")[0]))
                        .roomType(new RoomTypeBuilder().id(Integer.parseInt(params.get("idRoomType")[0]))
                                .build())
                        .name(params.get("name")[0])
                        .floor(Integer.parseInt(params.get("floor")[0]))
                        .phone(params.get("phone")[0])
                        .path(params.get("path")[0])
                        .build();
            }
        }catch (IncorrectRoomPhoneNumberException | IncorrectRoomNameException | IncorrectRoomPathException e) {
            throw new ServiceException(e);
        }
        return null;
    }
}