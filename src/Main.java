import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void makeReservation(List<Room> rooms, Scanner scanner) {
        System.out.println("Enter start date (dd/mm/yyyy):");
        String startDate = scanner.nextLine();

        System.out.println("Enter end date (dd/mm/yyyy):");
        String endDate = scanner.nextLine();

        System.out.println("Enter number of beds:");
        int beds = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Rooms available for reservation:");
        for (Room room : rooms) {
            if (!room.isBooked() && room.getBeds() >= beds) {
                System.out.println(room.getNumber() + " - " + room.getView());
            }
        }
        System.out.println("Enter the room number to book:");
        String roomNumber = scanner.nextLine();
        System.out.println("Enter the name for the reservation:");
        String name = scanner.nextLine();

        for (Room room : rooms) {
            if (room.getNumber().equals(roomNumber)) {
                room.book(startDate, endDate, name);
                System.out.println("Reservation successful!");
                return;
            }
        }

        System.out.println("Invalid room number. Reservation failed.");
    }

    public static void listFreeRooms(List<Room> rooms) {
        System.out.println("Free rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println(room.getNumber() + " - " + room.getView());
            }
        }
    }

    public static void checkoutRoom(List<Room> rooms, Scanner scanner) {
        System.out.println("Enter the room number to checkout:");
        String roomNumber = scanner.nextLine();

        for (Room room : rooms) {
            if (room.getNumber().equals(roomNumber) && room.isBooked()) {
                room.checkout();
                System.out.println("Checkout successful!");
                return;
            }
        }

    }

    public static void showBookingStats(List<Room> rooms) {

    }

    public static void updateRoom(List<Room> rooms, Scanner scanner) {

    }

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
                case 1:
                    makeReservation(rooms, scanner);
                    break;
                case 2:
                    listFreeRooms(rooms);
                    break;
                case 3:
                    checkoutRoom(rooms, scanner);
                    break;
                case 4:
                    showBookingStats(rooms);
                    break;
                case 5:
                    updateRoom(rooms, scanner);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
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