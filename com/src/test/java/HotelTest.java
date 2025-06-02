// HotelTest.java (Unit Tests)
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {
    private Hotel hotel;
    private Guest guest;
    private Room room;

    @BeforeEach
    void setUp() {
        hotel = new Hotel("Test Hotel");
        guest = new Guest(1, "Test Guest", "test@email.com", false);
        room = new Room(101, "Single", 100.0);
        hotel.addGuest(guest);
        hotel.addRoom(room);
    }

    @Test
    void testAddRoom() {
        Room newRoom = new Room(102, "Double", 150.0);
        hotel.addRoom(newRoom);
        assertEquals(2, hotel.getAllRooms().size());
    }

    @Test
    void testAddGuest() {
        Guest newGuest = new Guest(2, "New Guest", "new@email.com", false);
        hotel.addGuest(newGuest);
        assertEquals(2, hotel.getAllGuests().size());
    }

    @Test
    void testBookRoom() {
        boolean result = hotel.bookRoom(1, 101, 2);
        assertTrue(result);
        assertEquals(1, hotel.getAllBookings().size());
        assertTrue(room.isBooked());
    }

    @Test
    void testBookNonExistentRoom() {
        boolean result = hotel.bookRoom(1, 999, 2);
        assertFalse(result);
    }

    @Test
    void testBookWithNonExistentGuest() {
        boolean result = hotel.bookRoom(999, 101, 2);
        assertFalse(result);
    }

    @Test
    void testGuestDiscountPercent() {
        assertEquals(0.0, guest.getDiscountPercent());
        guest.setVip(true);
        assertEquals(10.0, guest.getDiscountPercent());
    }

    @Test
    void testRoomDiscountCalculation() {
        double discountPrice = room.calculateDiscountPrice(10);
        assertEquals(90.0, discountPrice, 0.01);
    }

    @Test
    void testBookingTotalAmount() {
        Booking booking = new Booking(1, guest, room, 2);
        assertEquals(200.0, booking.getTotalAmount(), 0.01);
    }

    @Test
    void testVipBookingDiscount() {
        guest.setVip(true);
        Booking booking = new Booking(1, guest, room, 2);
        assertEquals(180.0, booking.getTotalAmount(), 0.01); // 10% discount
    }

    @Test
    void testHotelStatistics() {
        hotel.bookRoom(1, 101, 2);
        var stats = hotel.getBookingStatistics();
        assertEquals(1, stats.get("Total Bookings"));
        assertEquals(1, stats.get("Booked Rooms"));
        assertEquals(0, stats.get("Available Rooms"));
    }

    @Test
    void testRoomEquals() {
        Room room2 = new Room(101, "Double", 200.0);
        assertEquals(room, room2); // Same room number
    }

    @Test
    void testGuestEquals() {
        Guest guest2 = new Guest(1, "Different Name", "different@email.com", true);
        assertEquals(guest, guest2); // Same ID
    }
}