// Room.java
import java.util.Objects;

public class Room {
    private int roomNumber;
    private String type;
    private double price;
    private boolean isBooked;

    public Room() {}

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    // Business logic method
    public double calculateDiscountPrice(double discountPercent) {
        return price * (1 - discountPercent / 100);
    }

    // Getters and Setters
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString() {
        return String.format("Room{number=%d, type='%s', price=%.2f, booked=%s}",
                roomNumber, type, price, isBooked);
    }
}
