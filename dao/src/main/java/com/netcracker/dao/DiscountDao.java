package com.netcracker.dao;

import com.netcracker.dao.bean.Discount;
import com.netcracker.dao.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public interface DiscountDao {
    List<String> getDiscountHeaders(Connection connection) throws DaoException;
    List<Discount> getDiscounts(Connection connection) throws DaoException;
    void addDiscount(Discount discount, Connection connection) throws DaoException;
    void removeDiscount(Discount discount, Connection connection) throws DaoException;
    void updateDiscount(Discount discount, Connection connection) throws DaoException;
    Discount getDiscount(Integer id, Connection connection) throws DaoException;
}
