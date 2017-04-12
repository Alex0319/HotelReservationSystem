package com.netcracker.dao.factory;

import com.netcracker.dao.ReservationDao;
import com.netcracker.dao.RoomDao;
import com.netcracker.dao.UserDao;

public abstract class AbstractDaoFactory {
    public abstract UserDao getUserDao();
    public abstract RoomDao getRoomDao();
    public abstract ReservationDao getReservationDao();
}
