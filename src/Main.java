import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void makeReservation(List<Room> rooms, Scanner scanner) {
        System.out.println("Enter start date (yyyy-mm-dd):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter end date (yyyy-mm-dd):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter number of beds:");
        int beds = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter desired view(park/sea");
        String view = scanner.nextLine();

        List<Room> availableRooms = findAvailableRooms(rooms, startDate, endDate, beds);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for the specified criteria.");
        } else {
            System.out.println("Available rooms:");
            for (Room room : availableRooms) {
                System.out.println(room.getNumber() + " (" + room.getView() + ")");
            }

            System.out.print("Enter the room number to book: ");
            String roomNumber = scanner.nextLine();

            Room selectedRoom = findRoomByNumber(rooms, roomNumber);
            if (selectedRoom != null) {
                System.out.print("Enter the guest name: ");
                String guestName = scanner.nextLine();

                selectedRoom.book(startDate, endDate, guestName);
                System.out.println("Room " + selectedRoom.getNumber() + " booked for " + guestName);
            } else {
                System.out.println("Invalid room number.");
            }
        }

//        System.out.println("Rooms available for reservation:");
//        for (Room room : rooms) {
//            if (!room.isBooked() && room.getBeds() >= beds) {
//                System.out.println(room.getNumber() + " - " + room.getView());
//            }
//        }
//        System.out.println("Enter the room number to book:");
//        String roomNumber = scanner.nextLine();
//        System.out.println("Enter the name for the reservation:");
//        String name = scanner.nextLine();
//
//        for (Room room : rooms) {
//            if (room.getNumber().equals(roomNumber)) {
//              //  room.book(startDate, endDate, name);
//                return;
//            }
//        }
//
//        System.out.println("Invalid room number. Reservation failed.");
    }
    public static Room findRoomByNumber(List<Room> rooms, String roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }

    public static List<Room> findAvailableRooms(List<Room> rooms, LocalDate startDate, LocalDate endDate, int beds) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked() && room.getBeds() == beds) {
                if (!checkDateClash(room.getBookings(), startDate, endDate)) {
                    availableRooms.add(room);
                }
            }
        }
        return availableRooms;
    }

    public static boolean checkDateClash(List<Booking> bookings, LocalDate startDate, LocalDate endDate) {
        for (Booking booking : bookings) {
            if (startDate.isBefore(booking.getEndDate()) && endDate.isAfter(booking.getStartDate())) {
                return true;
            }
        }
        return false;
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
        System.out.println("Invalid room number. Checkout failed.");
    }

    public static void showBookingStats(List<Room> rooms) {
        System.out.println("Room booking stats:");
        for (Room room : rooms) {
            if (room.isBooked()) {
      //          System.out.println(room.getNumber() + " - " + room.getBookedName());
            }
        }
    }

    public static void updateRoom(List<Room> rooms, Scanner scanner) {
        System.out.println("Enter the room number to update:");
        String roomNumber = scanner.nextLine();

        for (Room room : rooms) {
            if (room.getNumber().equals(roomNumber)) {
                if (room.isBooked()) {
                    System.out.println("Enter new start date (dd/mm/yyyy):");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());

                    System.out.println("Enter new end date (dd/mm/yyyy):");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    Booking.updateReservation(startDate, endDate);
                    System.out.println("Reservation updated!");
                } else {
                    System.out.println("Room is not currently booked.");
                }
                return;
            }
        }

        System.out.println("Invalid room number. Update failed.");
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
            scanner.nextLine();

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
                    System.out.println("Invalid option. Please choose from the options below.");
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