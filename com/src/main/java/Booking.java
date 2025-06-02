import java.util.Objects;

public class Booking {
    private int bookingId;
    private Guest guest;
    private Room room;
    private int nights;
    private double totalAmount;

    public Booking() {}

    public Booking(int bookingId, Guest guest, Room room, int nights) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.nights = nights;
        this.totalAmount = calculateTotalAmount();
    }

    // Business logic method
    public double calculateTotalAmount() {
        double baseAmount = room.getPrice() * nights;
        double discount = guest.getDiscountPercent();
        return baseAmount * (1 - discount / 100);
    }

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public int getNights() { return nights; }
    public void setNights(int nights) { this.nights = nights; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

    @Override
    public String toString() {
        return String.format("Booking{id=%d, guest='%s', room=%d, nights=%d, amount=%.2f}",
                bookingId, guest.getName(), room.getRoomNumber(), nights, totalAmount);
    }
}
