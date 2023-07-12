import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static void makeReservation(List<Room> rooms, Scanner scanner) {
        System.out.println("Enter start date (dd.MM.yyyy):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

        System.out.println("Enter end date (dd.MM.yyyy):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

        System.out.println("Enter number of beds:");
        int beds = scanner.nextInt();
        scanner.nextLine();

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

    public static void checkoutRoom(Scanner scanner, List<Room> rooms) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = today.format(formatter);

        System.out.println("Booked rooms for today (" + formattedDate + "):");
        boolean noBookings = true;

        for (Room room : rooms) {
            for (Booking booking : room.getBookings()) {
                if (booking.getStartDate().equals(today)) {
                    System.out.println("Room " + room.getNumber() + " - Guest: " + booking.getGuestName());
                    noBookings = false;
                }
            }
        }

        if (noBookings) {
            System.out.println("No rooms are booked for today.");
        }

        System.out.println("Please select a room to checkout:");
        String roomNumber = scanner.nextLine();

        Room selectedRoom = findRoomByNumber(rooms, roomNumber);
        if (selectedRoom != null) {
            if (selectedRoom.isBooked()) {
                selectedRoom.checkout();
                System.out.println("Room " + selectedRoom.getNumber() + " checked out.");
            } else {
                System.out.println("Room " + selectedRoom.getNumber() + " is not booked.");
            }
        } else {
            System.out.println("Invalid room number.");
        }
    }



    public static void showBookingStats(List<Room> rooms) {
        System.out.println("Booking Stats:");
        for (Room room : rooms) {
            if (room.isBooked()) {
                System.out.println("Room " + room.getNumber() + " is booked by:");
                for (Booking booking : room.getBookings()) {
                    System.out.println("  Guest: " + booking.getGuestName() + ", Dates: " +
                            booking.getStartDate() + " - " + booking.getEndDate());
                }
            }
        }
    }

    public static void updateRoom(List<Room> rooms, Scanner scanner) {
        System.out.print("Enter the room number to update: ");
        String roomNumber = scanner.nextLine();

        Room selectedRoom = findRoomByNumber(rooms, roomNumber);
        if (selectedRoom != null) {
            if (selectedRoom.isBooked()) {
                System.out.println("Current bookings for room " + selectedRoom.getNumber() + ":");
                int index = 1;
                for (Booking booking : selectedRoom.getBookings()) {
                    System.out.println("Booking " + index + ": " +
                            "Guest: " + booking.getGuestName() + ", Dates: " +
                            booking.getStartDate() + " - " + booking.getEndDate());
                    index++;
                }

                System.out.print("Enter the booking number to update: ");
                int bookingNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (bookingNumber >= 1 && bookingNumber <= selectedRoom.getBookings().size()) {
                    Booking selectedBooking = selectedRoom.getBookings().get(bookingNumber - 1);
                    System.out.print("Enter the new start date (dd.MM.yyyy): ");
                    LocalDate newStartDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

                    System.out.print("Enter the new end date (dd.MM.yyyy): ");
                    LocalDate newEndDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

                    selectedBooking.updateReservation(newStartDate, newEndDate);
                    System.out.println("Room " + selectedRoom.getNumber() + ", Booking " + bookingNumber +
                            " reservation updated.");
                } else {
                    System.out.println("Invalid booking number.");
                }
            } else {
                System.out.println("Room " + selectedRoom.getNumber() + " is not booked.");
            }
        } else {
            System.out.println("Invalid room number.");
        }
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
                    checkoutRoom(scanner, rooms);
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