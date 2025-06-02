import java.io.*;
import java.util.List;

public class DataExporter {

    public static void exportToTxt(List<Guest> guests, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("GUESTS LIST");
            writer.println("===========");
            for (Guest guest : guests) {
                writer.println(guest.toString());
            }
        }
    }

    public static void exportToCsv(List<Guest> guests, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("ID,Name,Email,VIP");
            for (Guest guest : guests) {
                writer.printf("%d,%s,%s,%s%n",
                        guest.getId(), guest.getName(), guest.getEmail(), guest.isVip());
            }
        }
    }
}