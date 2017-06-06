package by.hotelreservation.dao.impl;

import by.hotelreservation.bean.entity.Discount;
import by.hotelreservation.dao.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountDaoImpl extends AbstractDao<Discount> {
    public DiscountDaoImpl() {
        super(Discount.class);
    }
}
