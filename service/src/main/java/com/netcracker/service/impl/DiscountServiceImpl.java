package com.netcracker.service.impl;

import com.netcracker.dao.DiscountDao;
import com.netcracker.dao.bean.Discount;
import com.netcracker.dao.builder.DiscountBuilder;
import com.netcracker.dao.exception.DaoException;
import com.netcracker.dao.impl.DiscountDaoImpl;
import com.netcracker.service.AbstractService;
import com.netcracker.service.CrudServiceExtended;
import com.netcracker.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class DiscountServiceImpl extends AbstractService implements CrudServiceExtended<Discount> {
    private DiscountDao discountDao = new DiscountDaoImpl();

    public List<String> getAllHeaders() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return discountDao.getDiscountHeaders(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<Discount> getAllEntities() throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            return discountDao.getDiscounts(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public List<Discount> addEntity(Discount entity) throws ServiceException {
        Connection connection = null;
        List<Discount> discounts;
        try {
            connection = getConnection();
            discountDao.addDiscount(entity,connection);
            discounts = discountDao.getDiscounts(connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
        return discounts;
    }

    public void removeEntity(Discount discount) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            discountDao.removeDiscount(discount,connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public void updateEntity(Discount entity) throws ServiceException {
        Connection connection = null;
        try {
            connection = getConnection();
            discountDao.updateDiscount(entity,connection);
        }catch (DaoException e){
            throw new ServiceException(e);
        }finally {
            closeConnection(connection);
        }
    }

    public Discount buildEntity(Map<String, String[]> params) throws ServiceException {
        return new DiscountBuilder().id(Integer.parseInt(params.get("id")[0]))
                .name(params.get("name")[0])
                .build();
    }
}