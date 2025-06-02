// Hotel.java
import java.util.*;

public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Guest> guests;
    private List<Booking> bookings;
    private int nextBookingId;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.nextBookingId = 1;
    }

    // CRUD operations for Rooms
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(int roomNumber) {
        rooms.removeIf(room -> room.getRoomNumber() == roomNumber);
    }

    public Room findRoom(int roomNumber) {
        return rooms.stream()
                .filter(room -> room.getRoomNumber() == roomNumber)
                .findFirst()
                .orElse(null);
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    // CRUD operations for Guests
    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    public void removeGuest(int guestId) {
        guests.removeIf(guest -> guest.getId() == guestId);
    }

    public Guest findGuest(int guestId) {
        return guests.stream()
                .filter(guest -> guest.getId() == guestId)
                .findFirst()
                .orElse(null);
    }

    public List<Guest> getAllGuests() {
        return new ArrayList<>(guests);
    }

    // Business logic - Book room
    public boolean bookRoom(int guestId, int roomNumber, int nights) {
        Guest guest = findGuest(guestId);
        Room room = findRoom(roomNumber);

        if (guest == null || room == null || room.isBooked()) {
            return false;
        }

        Booking booking = new Booking(nextBookingId++, guest, room, nights);
        bookings.add(booking);
        room.setBooked(true);

        return true;
    }

    // Business logic - Get statistics
    public Map<String, Integer> getBookingStatistics() {
        Map<String, Integer> stats = new HashMap<>();

        stats.put("Total Bookings", bookings.size());
        stats.put("Booked Rooms", (int) rooms.stream().filter(Room::isBooked).count());
        stats.put("Available Rooms", (int) rooms.stream().filter(room -> !room.isBooked()).count());

        return stats;
    }

    // Getters
    public String getName() { return name; }
    public List<Booking> getAllBookings() { return new ArrayList<>(bookings); }
}