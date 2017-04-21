package com.netcracker.dao.bean;

import com.netcracker.dao.builder.RoomBuilder;

public class Room {
    private int id;
    private int floor;
    private String phone;
    private String name;
    private RoomType roomType;


    public Room(){super();}

    public Room(RoomBuilder roomBuilder){
        this.id = roomBuilder.getId();
        this.name = roomBuilder.getName();
        this.floor = roomBuilder.getFloor();
        this.phone = roomBuilder.getPhone();
        this.roomType = roomBuilder.getRoomType();
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
