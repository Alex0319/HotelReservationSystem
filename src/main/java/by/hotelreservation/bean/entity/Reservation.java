package by.hotelreservation.bean.entity;

import by.hotelreservation.builder.ReservationBuilder;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservation")
public class Reservation extends AbstractEntity {
    @Temporal(TemporalType.DATE)
    private Date dateIn;

    @Temporal(TemporalType.DATE)
    private Date dateOut;

    private int costAdditionalServices;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDiscount")
    private Discount discount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reservation_room",
            joinColumns = @JoinColumn(name = "idReservation"),
            inverseJoinColumns = @JoinColumn(name = "idRoom"))
    @JsonBackReference
    private Set<Room> rooms = new HashSet<>();

    public Reservation(){super();}

    public Reservation(ReservationBuilder reservationBuilder){
        this.id = reservationBuilder.getId();
        this.dateIn = reservationBuilder.getDateIn();
        this.dateOut = reservationBuilder.getDateOut();
        this.user = reservationBuilder.getUser();
        this.costAdditionalServices = reservationBuilder.getCostAdditionalServices();
        this.discount = reservationBuilder.getDiscount();
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public int getCostAdditionalServices() {
        return costAdditionalServices;
    }

    public void setCostAdditionalServices(int costAdditionalServices) {
        this.costAdditionalServices = costAdditionalServices;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (costAdditionalServices != that.costAdditionalServices) return false;
        if (!dateIn.equals(that.dateIn)) return false;
        if (!dateOut.equals(that.dateOut)) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return discount != null ? discount.equals(that.discount) : that.discount == null;
    }

    @Override
    public int hashCode() {
        int result = dateIn.hashCode();
        result = 31 * result + dateOut.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + costAdditionalServices;
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }
}
