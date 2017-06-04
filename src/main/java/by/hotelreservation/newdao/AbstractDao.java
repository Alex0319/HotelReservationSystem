package by.hotelreservation.newdao;

import by.hotelreservation.exception.DAOException;
import by.hotelreservation.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractDao<T> implements EntityDao<T>{
    @PersistenceContext
    protected EntityManager entityManager;

    private Class getEntityClass(){
        return ReflectionUtils.getGenericParameterClass(this.getClass(), AbstractDao.class, 0);
    }

    @Override
    public List<T> getAll() throws DAOException {
        TypedQuery<T> namedQuery = entityManager.createQuery(String.format("SELECT c FROM %s c", getEntityClass().getName()), getEntityClass());
        return namedQuery.getResultList();
    }

    @Override
    public void add(T entity) throws DAOException {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(int id) throws DAOException {
        entityManager.getTransaction().begin();
        entityManager.remove(getById(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(T entity) throws DAOException {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public T getById(int id) throws DAOException {
        return (T) entityManager.find(getEntityClass(), id);
    }
}
