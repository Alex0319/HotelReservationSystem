package com.netcracker.dao.bean;

public class RoomReservation extends Entity{
    private Room room;
    private Reservation reservation;

    public RoomReservation(){}

    public RoomReservation(Room room, Reservation reservation) {
        this.room = room;
        this.reservation = reservation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomReservation that = (RoomReservation) o;

        if (!room.equals(that.room)) return false;
        return reservation.equals(that.reservation);
    }

    @Override
    public int hashCode() {
        int result = room.hashCode();
        result = 31 * result + reservation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoomReservation{");
        sb.append("room=").append(room);
        sb.append(", reservation=").append(reservation);
        sb.append('}');
        return sb.toString();
    }
}
