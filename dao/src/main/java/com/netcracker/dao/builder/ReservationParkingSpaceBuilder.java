package com.netcracker.dao.builder;


import com.netcracker.dao.bean.ParkingSpace;
import com.netcracker.dao.bean.Reservation;
import com.netcracker.dao.bean.ReservationParkingSpace;

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
