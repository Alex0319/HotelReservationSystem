package by.hotelreservation.dao.impl;

import by.hotelreservation.exception.DAOException;
import by.hotelreservation.dao.TablesInfoDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static by.hotelreservation.dao.constants.Constants.GET_ALL_NAMES_TABLES;

@Repository
public class TablesInfoDaoImpl implements TablesInfoDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getNamesTables() throws DAOException {
        return entityManager.createNativeQuery(GET_ALL_NAMES_TABLES).getResultList();
    }
}
