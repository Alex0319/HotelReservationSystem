package by.hotelreservation.dao.impl;

import by.hotelreservation.bean.entity.Room;
import by.hotelreservation.dao.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDaoImpl extends AbstractDao<Room>{
    public RoomDaoImpl() {
        super(Room.class);
    }
}
