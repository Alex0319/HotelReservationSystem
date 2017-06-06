package by.hotelreservation.newdao.impl;

import by.hotelreservation.bean.entity.RoomType;
import by.hotelreservation.newdao.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoomTypeDaoImpl extends AbstractDao<RoomType>{
    public RoomTypeDaoImpl(){
        super(RoomType.class);
    }
}
