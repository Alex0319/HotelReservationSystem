package by.hotelreservation.dao.impl;

import by.hotelreservation.bean.entity.Discount;
import by.hotelreservation.builder.DiscountBuilder;
import by.hotelreservation.dao.AbstractDao;
import by.hotelreservation.dao.DiscountDao;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.util.ErrorStringBuilder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.hotelreservation.dao.constants.Constants.*;

@Repository
public class DiscountDaoImpl extends AbstractDao implements DiscountDao {
    public List<String> getDiscountHeaders(Connection connection) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> headers = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_DISCOUNTS_HEADERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getInt("id") + " ");
                stringBuilder.append(resultSet.getString("name"));
                headers.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeStatement(statement, resultSet);
        }
        return headers;
    }

    public List<Discount> getDiscounts(Connection connection) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Discount> discounts = new ArrayList<Discount>();
        DiscountBuilder discountBuilder = new DiscountBuilder();
        try {
            statement = connection.prepareStatement(GET_ALL_DISCOUNTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                discounts.add(fillDiscount(resultSet, discountBuilder));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeStatement(statement, resultSet);
        }
        return discounts;
    }

    public void addDiscount(Discount discount, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_DISCOUNT);
            statement = fillStatement(statement, discount);
            statement.execute();
        } catch (SQLException | NullPointerException e) {
            throw new DAOException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void removeDiscount(Discount discount, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(REMOVE_DISCOUNT);
            statement.setInt(1, discount.getId());
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DAOException(buildMessage(discount, e.getMessage()), e);
        } catch (SQLException | NullPointerException e) {
            throw new DAOException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    public void updateDiscount(Discount discount, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_DISCOUNT);
            statement = fillStatement(statement, discount);
            statement.setInt(2, discount.getId());
            statement.execute();
        } catch (SQLException | NullPointerException e) {
            throw new DAOException(e);
        } finally {
            closeStatement(statement, null);
        }
    }

    @Override
    public Discount getLastInsertedDiscount(Connection connection) throws DAOException {
        PreparedStatement statement = null;
        Discount discount = null;
        ResultSet resultSet;
        DiscountBuilder discountBuilder = new DiscountBuilder();
        try {
            statement = connection.prepareStatement(GET_LAST_INSERTED_DISCOUNT);
           // statement.setString(1,"discount");
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                discount = fillDiscount(resultSet, discountBuilder);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeStatement(statement, null);
        }
        return discount;
    }

    private PreparedStatement fillStatement(PreparedStatement statement, Discount discount) throws SQLException {
        statement.setString(1, discount.getName());
        return statement;
    }

    private Discount fillDiscount(ResultSet resultSet, DiscountBuilder discountBuilder) throws SQLException {
        return discountBuilder.id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    private String buildMessage(Discount discount, String errorMessage) {
        Map<String, String> idNames = new HashMap<String, String>();
        idNames.put("id", Integer.toString(discount.getId()));
        return ErrorStringBuilder.buildDeleteErrorString(idNames, errorMessage);
    }
}