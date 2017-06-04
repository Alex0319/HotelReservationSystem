package by.hotelreservation.builder;

import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.bean.entity.ReservationRoom;
import by.hotelreservation.bean.entity.Room;

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
