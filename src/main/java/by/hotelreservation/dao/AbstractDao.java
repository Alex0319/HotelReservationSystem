package by.hotelreservation.dao;

import by.hotelreservation.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractDao<T> implements EntityDao<T>{
    private Class<T> entityBeanType;

    @PersistenceContext
    protected EntityManager entityManager;

    public  AbstractDao(Class<T> entityBeanType){
        this.entityBeanType = entityBeanType;
    }

    @Override
    public List<T> getAll() throws DAOException {
        TypedQuery<T> namedQuery = entityManager.createQuery(String.format("SELECT c FROM %s c", entityBeanType.getName()), entityBeanType);
        return namedQuery.getResultList();
    }

    @Override
    public void add(T entity) throws DAOException {
        entityManager.persist(entity);
    }

    @Override
    public void remove(int id) throws DAOException {
        entityManager.remove(getById(id));
    }

    @Override
    public void update(T entity) throws DAOException {
        entityManager.merge(entity);
    }

    @Override
    public T getById(int id) throws DAOException {
        return entityManager.find(entityBeanType, id);
    }
}
