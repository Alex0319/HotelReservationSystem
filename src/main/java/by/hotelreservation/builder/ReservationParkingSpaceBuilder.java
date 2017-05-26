package by.hotelreservation.builder;

import by.hotelreservation.bean.ParkingSpace;
import by.hotelreservation.bean.Reservation;
import by.hotelreservation.bean.ReservationParkingSpace;

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
