package by.hotelreservation.newdao.impl;

import by.hotelreservation.bean.entity.User;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.newdao.AbstractDao;
import by.hotelreservation.newdao.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao{
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User checkUserEmail(String email) throws DAOException {
        Query query = entityManager.createQuery("FROM User WHERE email = :email")
                .setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public User checkUser(String email, String password) throws DAOException {
        if(checkUserEmail(email) != null){
            Query query = entityManager.createQuery("FROM User WHERE email = :email AND password = :password")
                    .setParameter("email", email)
                    .setParameter("password", password);
            return (User) query.getSingleResult();
        }
        return null;
    }
}
