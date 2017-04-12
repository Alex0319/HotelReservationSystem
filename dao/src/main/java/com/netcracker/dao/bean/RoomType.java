package com.netcracker.dao.bean;

public class RoomType extends Entity{
    private int id, roomsCount, bedsCount, costPerDay;
    private String additionalInfo;

    public RoomType(){}

    public RoomType(int id, int roomsCount, int bedsCount, int costPerDay, String additionalInfo) {
        this.id = id;
        this.roomsCount = roomsCount;
        this.bedsCount = bedsCount;
        this.costPerDay = costPerDay;
        this.additionalInfo = additionalInfo;
    }

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
        final StringBuilder sb = new StringBuilder("RoomType{");
        sb.append("id=").append(id);
        sb.append(", roomsCount=").append(roomsCount);
        sb.append(", bedsCount=").append(bedsCount);
        sb.append(", costPerDay=").append(costPerDay);
        sb.append(", additionalInfo='").append(additionalInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
