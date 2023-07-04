import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Room> rooms = createRooms();

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Select an option:");
            System.out.println("1. Make a reservation");
            System.out.println("2. List free rooms");
            System.out.println("3. Checkout room");
            System.out.println("4. Show room booking stats");
            System.out.println("5. Update room");

            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {

            }
        } while (option != 0);
    }

    private static List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", 2, "park"));
        rooms.add(new Room("102", 1, "sea"));
        rooms.add(new Room("103", 3, "park"));
        rooms.add(new Room("104", 2, "sea"));
        rooms.add(new Room("105", 1, "park"));
        return rooms;
    }
}