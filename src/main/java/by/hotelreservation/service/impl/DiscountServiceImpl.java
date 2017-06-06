package by.hotelreservation.service.impl;

import by.hotelreservation.bean.entity.Discount;
import by.hotelreservation.builder.DiscountBuilder;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.exception.ServiceException;
import by.hotelreservation.exception.validateexception.IncorrectDiscountNameException;
import by.hotelreservation.dao.EntityDao;
import by.hotelreservation.service.CrudServiceExtended;
import by.hotelreservation.service.validator.ValidatorDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DiscountServiceImpl implements CrudServiceExtended<Discount> {
    @Autowired
    private EntityDao<Discount> discountDao;

    public List<String> getAllHeaders() throws ServiceException {
        try {
            return null;// discountDao.getDiscountHeaders();
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public List<Discount> getAll() throws ServiceException {
        try {
            return discountDao.getAll();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Discount getById(int id) throws ServiceException {
        try {
            return discountDao.getById(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<Discount> add(Discount entity) throws ServiceException {
        List<Discount> discounts;
        try {
            discountDao.add(entity);
            discounts = discountDao.getAll();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return discounts;
    }

    public void delete(Discount discount) throws ServiceException {
        try {
            discountDao.remove(discount.getId());
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public void update(Discount entity) throws ServiceException {
        try {
            discountDao.update(entity);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public Discount build(Map<String, String[]> params) throws ServiceException {
        ValidatorDiscount validatorDiscount = new ValidatorDiscount();
        try {
            if(validatorDiscount.validate(params)) {
                return new DiscountBuilder().id(Integer.parseInt(params.get("id")[0]))
                        .name(params.get("name")[0])
                        .build();
            }
        } catch (IncorrectDiscountNameException e) {
            throw new ServiceException(e);
        }
        return null;
    }
}