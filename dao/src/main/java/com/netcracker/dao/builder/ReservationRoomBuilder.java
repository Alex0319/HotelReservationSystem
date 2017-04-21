package com.netcracker.dao.builder;


import com.netcracker.dao.bean.Reservation;
import com.netcracker.dao.bean.ReservationRoom;
import com.netcracker.dao.bean.Room;

public class ReservationRoomBuilder {
    private Room room;
    private Reservation reservation;

    public ReservationRoomBuilder room(Room room){
        this.room = room;
        return this;
    }

    public ReservationRoomBuilder reservation(Reservation reservation){
        this.reservation = reservation;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public ReservationRoom build(){
        return new ReservationRoom(this);
    }
}
