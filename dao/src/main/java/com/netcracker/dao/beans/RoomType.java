package com.netcracker.dao.beans;

public class RoomType {
    private int id, roomsCount, bedsCount, costPerDay;
    private String additionalInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public int getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(int bedsCount) {
        this.bedsCount = bedsCount;
    }

    public int getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomType roomType = (RoomType) o;

        if (id != roomType.id) return false;
        if (roomsCount != roomType.roomsCount) return false;
        if (bedsCount != roomType.bedsCount) return false;
        if (costPerDay != roomType.costPerDay) return false;
        return additionalInfo.equals(roomType.additionalInfo);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + roomsCount;
        result = 31 * result + bedsCount;
        result = 31 * result + costPerDay;
        result = 31 * result + additionalInfo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", roomsCount=" + roomsCount +
                ", bedsCount=" + bedsCount +
                ", costPerDay=" + costPerDay +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
