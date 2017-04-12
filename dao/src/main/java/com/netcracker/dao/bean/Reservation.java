package com.netcracker.dao.bean;

import java.sql.Date;

public class Reservation extends Entity{
    private int id;
    private User user;
    private int roomNumber;
    private Date dateIn, dateOut;

    public Reservation(){}

    public Reservation(int id, User user, int roomNumber, Date dateIn, Date dateOut, int daysCount) {
        this.id = id;
        this.user = user;
        this.roomNumber = roomNumber;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != that.id) return false;
        if (roomNumber != that.roomNumber) return false;
        if (!user.equals(that.user)) return false;
        if (!dateIn.equals(that.dateIn)) return false;
        return dateOut.equals(that.dateOut);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + roomNumber;
        result = 31 * result + dateIn.hashCode();
        result = 31 * result + dateOut.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", dateIn=").append(dateIn);
        sb.append(", dateOut=").append(dateOut);
        sb.append('}');
        return sb.toString();
    }
}
