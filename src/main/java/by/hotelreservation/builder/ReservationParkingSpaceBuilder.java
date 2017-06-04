package by.hotelreservation.builder;

import by.hotelreservation.bean.entity.ParkingSpace;
import by.hotelreservation.bean.entity.Reservation;
import by.hotelreservation.bean.entity.ReservationParkingSpace;

public class ReservationParkingSpaceBuilder {
    private Reservation reservation;
    private ParkingSpace parkingSpace;

    public ReservationParkingSpaceBuilder reservation(Reservation reservation){
        this.reservation = reservation;
        return this;
    }

    public ReservationParkingSpaceBuilder parkingSpace(ParkingSpace parkingSpace){
        this.parkingSpace = parkingSpace;
        return this;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public ReservationParkingSpace build(){
        return new ReservationParkingSpace(this);
    }
}
