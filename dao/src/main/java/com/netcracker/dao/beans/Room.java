package com.netcracker.dao.beans;

public class Room {
    private int id;
    private int floor;
    private String phone;
    private RoomType roomType;

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

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (floor != room.floor) return false;
        if (!phone.equals(room.phone)) return false;
        return roomType.equals(room.roomType);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + floor;
        result = 31 * result + phone.hashCode();
        result = 31 * result + roomType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", floor=" + floor +
                ", phone='" + phone + '\'' +
                ", roomType=" + roomType +
                '}';
    }
}
