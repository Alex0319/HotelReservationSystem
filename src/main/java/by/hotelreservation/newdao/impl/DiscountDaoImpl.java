package by.hotelreservation.newdao.impl;

import by.hotelreservation.bean.entity.Discount;
import by.hotelreservation.newdao.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountDaoImpl extends AbstractDao<Discount> {
    public DiscountDaoImpl() {
        super(Discount.class);
    }
}
