package by.hotelreservation.newdao.impl;

import by.hotelreservation.bean.entity.Role;
import by.hotelreservation.newdao.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role>{
    public RoleDaoImpl() {
        super(Role.class);
    }
}
