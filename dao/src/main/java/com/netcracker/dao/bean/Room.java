package com.netcracker.dao.bean;

public class Room extends Entity{
    private int id;
    private int floor;
    private String phone;
    private RoomType roomType;

    public Room() {}

    public Room(int id, int floor, String phone, RoomType roomType) {
        this.id = id;
        this.floor = floor;
        this.phone = phone;
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
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("id=").append(id);
        sb.append(", floor=").append(floor);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", roomType=").append(roomType);
        sb.append('}');
        return sb.toString();
    }
}
