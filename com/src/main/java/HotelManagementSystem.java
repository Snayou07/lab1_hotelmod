import java.util.Scanner;

public class HotelManagementSystem {
    private Hotel hotel;
    private Scanner scanner;

    public HotelManagementSystem() {
        hotel = new Hotel("Grand Hotel");
        scanner = new Scanner(System.in);
        initializeTestData();
    }

    private void initializeTestData() {
        // Add sample rooms
        hotel.addRoom(new Room(101, "Single", 100.0));
        hotel.addRoom(new Room(102, "Double", 150.0));
        hotel.addRoom(new Room(201, "Suite", 300.0));

        // Add sample guests
        hotel.addGuest(new Guest(1, "John Doe", "john@email.com", true));
        hotel.addGuest(new Guest(2, "Jane Smith", "jane@email.com", false));
    }

    public void run() {
        System.out.println("Welcome to " + hotel.getName() + " Management System!");

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayRooms();
                    break;
                case 2:
                    displayGuests();
                    break;
                case 3:
                    bookRoom();
                    break;
                case 4:
                    displayBookings();
                    break;
                case 5:
                    displayStatistics();
                    break;
                case 6:
                    exportData();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== HOTEL MENU ===");
        System.out.println("1. View Rooms");
        System.out.println("2. View Guests");
        System.out.println("3. Book Room");
        System.out.println("4. View Bookings");
        System.out.println("5. Statistics");
        System.out.println("6. Export Data");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private void displayRooms() {
        System.out.println("\n=== ROOMS ===");
        hotel.getAllRooms().forEach(System.out::println);
    }

    private void displayGuests() {
        System.out.println("\n=== GUESTS ===");
        hotel.getAllGuests().forEach(System.out::println);
    }

    private void bookRoom() {
        System.out.println("\n=== BOOK ROOM ===");
        System.out.print("Guest ID: ");
        int guestId = scanner.nextInt();
        System.out.print("Room Number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Number of nights: ");
        int nights = scanner.nextInt();

        if (hotel.bookRoom(guestId, roomNumber, nights)) {
            System.out.println("Room booked successfully!");
        } else {
            System.out.println("Booking failed!");
        }
    }

    private void displayBookings() {
        System.out.println("\n=== BOOKINGS ===");
        hotel.getAllBookings().forEach(System.out::println);
    }

    private void displayStatistics() {
        System.out.println("\n=== STATISTICS ===");
        hotel.getBookingStatistics().forEach((key, value) ->
                System.out.println(key + ": " + value));
    }

    private void exportData() {
        try {
            DataExporter.exportToCsv(hotel.getAllGuests(), "guests.csv");
            DataExporter.exportToTxt(hotel.getAllGuests(), "guests.txt");
            System.out.println("Data exported successfully!");
        } catch (Exception e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new HotelManagementSystem().run();
    }
}