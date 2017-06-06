package by.hotelreservation.dao.impl;

import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.exception.DAOException;
import by.hotelreservation.dao.AbstractDao;
import by.hotelreservation.dao.ReservationDao;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReservationDaoImpl extends AbstractDao<Reservation> implements ReservationDao{
    public ReservationDaoImpl() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> getReservationByUser(int userId) throws DAOException {
        TypedQuery<Reservation> namedQuery = entityManager.createQuery("SELECT c FROM Reservation c WHERE c.user.id = :id_user", Reservation.class)
                .setParameter("id_user", userId);
        return namedQuery.getResultList();
    }
}
