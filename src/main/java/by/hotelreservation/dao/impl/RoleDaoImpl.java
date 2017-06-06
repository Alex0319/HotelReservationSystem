package by.hotelreservation.dao.impl;

import by.hotelreservation.bean.entity.Role;
import by.hotelreservation.dao.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role>{
    public RoleDaoImpl() {
        super(Role.class);
    }
}
