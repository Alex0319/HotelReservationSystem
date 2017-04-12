package com.netcracker.dao.factory;

import com.netcracker.dao.ReservationDao;
import com.netcracker.dao.RoomDao;
import com.netcracker.dao.UserDao;
import com.netcracker.dao.daoimpl.ReservationDaoImpl;
import com.netcracker.dao.daoimpl.RoomDaoImpl;
import com.netcracker.dao.daoimpl.UserDaoImpl;

public class DaoFactory extends AbstractDaoFactory{

    public UserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    public RoomDao getRoomDao() {
        return RoomDaoImpl.getInstance();
    }

    public ReservationDao getReservationDao() {
        return ReservationDaoImpl.getInstance();
    }
}
