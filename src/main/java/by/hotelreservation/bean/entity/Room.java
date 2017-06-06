package by.hotelreservation.bean.entity;

import by.hotelreservation.builder.RoomBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room extends AbstractEntity {
    private int floor;
    private String path;
    private String phone;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRoomType")
    private RoomType roomType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="reservation_room",
            joinColumns = @JoinColumn(name = "idRoom"),
            inverseJoinColumns = @JoinColumn(name = "idReservation"))
    private Set<Reservation> reservations = new HashSet<>();

    public Room(){super();}

    public Room(RoomBuilder roomBuilder){
        this.id = roomBuilder.getId();
        this.path = roomBuilder.getPath();
        this.name = roomBuilder.getName();
        this.floor = roomBuilder.getFloor();
        this.phone = roomBuilder.getPhone();
        this.roomType = roomBuilder.getRoomType();
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
        @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Room room = (Room) o;

        if (floor != room.floor) {
            return false;
        }
        if (!phone.equals(room.phone)) {
            return false;
        }
        if (!name.equals(room.name)) {
            return false;
        }
        return roomType != null ? roomType.equals(room.roomType) : room.roomType == null;
    }

    @Override
    public int hashCode() {
        int result = floor;
        result = 31 * result + phone.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        return result;
    }
}
