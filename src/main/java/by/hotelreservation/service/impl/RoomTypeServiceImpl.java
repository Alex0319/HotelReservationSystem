package by.hotelreservation.service.impl;

import by.hotelreservation.bean.dto.EntityDto;
import by.hotelreservation.bean.entity.RoomType;
import by.hotelreservation.builder.RoomTypeBuilder;
import by.hotelreservation.dao.EntityDao;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.*;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorRoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("roomTypeService")
@Transactional
public class RoomTypeServiceImpl implements CrudServiceExtended<RoomType> {
    @Autowired
    private EntityDao<RoomType> roomTypeDao;

    public List<EntityDto> getAllHeaders() throws ServiceException {
        List<EntityDto> resultList = new ArrayList<>();
        List<RoomType> roomTypeList = getAll();
        for (RoomType roomType: roomTypeList) {
            resultList.add(new EntityDto(roomType.getId(),"rooms count " + roomType.getRoomsCount()));
        }
        return resultList;
    }

    public List<RoomType> getAll() throws ServiceException {
        try {
            return roomTypeDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public RoomType getById(int id) throws ServiceException {
        try {
            return roomTypeDao.getById(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<RoomType> add(RoomType entity) throws ServiceException {
        List<RoomType> roomTypes;
        try {
            roomTypeDao.add(entity);
            roomTypes = roomTypeDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return roomTypes;
    }

    public void delete(RoomType roomType) throws ServiceException {
        try {
            roomTypeDao.remove(roomType.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void update(RoomType entity) throws ServiceException {
        try {
            roomTypeDao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
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
}