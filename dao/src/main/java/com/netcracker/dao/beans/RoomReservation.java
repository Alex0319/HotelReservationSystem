package com.netcracker.dao.beans;

public class RoomReservation {
    private Room room;
    private Reservation reservation;

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
        return "RoomReservation{" +
                "room=" + room +
                ", reservation=" + reservation +
                '}';
    }
}
