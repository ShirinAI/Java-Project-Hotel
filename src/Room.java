import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class Room {
    public String number;
    public int beds;
    public String view;
    public List<String> bookedDates;
//    public String bookedStartDate;
//    public String bookedEndDate;
//    public String bookedName;
    public List<Booking> bookings;

    public Room(String number, int beds, String view){
        this.number = number;
        this.beds = beds;
        this.view = view;
        this.bookings = new ArrayList<>();
     //   this.bookedDates = new ArrayList<>();
     //   this.bookedName = null;

    }
    public String getNumber(){
        return number;
    }
    public int getBeds(){
        return beds;
    }

    public String getView() {
        return view;
    }
    public List<Booking> getBookings(){
        return bookings;
    }
    public boolean isBooked() {
        return !bookedDates.isEmpty();
    }
    public void book(LocalDate startDate, LocalDate endDate, String guestName){
        bookings.add(new Booking(startDate, endDate, guestName));
    }

    public void checkout() {
        if (isBooked()) {
            bookings.clear();
            System.out.println("Room " + number + " checked out.");
        } else {
            System.out.println("Room " + number + " is not currently booked.");
        }
    }
//    public String getBookedName() {
//        return bookedName;
//    }

//    public void book(String startDate, String endDate, String name) {
//        for (String date : bookedDates) {
//            if (isDateClashing(startDate, endDate, date)) {
//                System.out.println("Booking failed. The room is already booked during the specified dates.");
//                return;
//            }
//        }
//        bookedDates.add(startDate);
//        bookedDates.add(endDate);
//        this.bookedName = name;
//        System.out.println("Reservation successful!");
//    }


//    public void checkout() {
//        this.bookedDates.clear();
//        this.bookedName = null;
//        System.out.println("Checkout successful!");
//    }

//    public void updateReservation(String startDate, String endDate) {
//        if (!isBooked()) {
//            System.out.println("Cannot update reservation. The room is not currently booked.");
//            return;
//        }
//        List<String> updatedDates = new ArrayList<>();
//        for (String date : bookedDates) {
//            if (!isDateClashing(startDate, endDate, date)) {
//                updatedDates.add(date);
//            }
//        }
//        if (updatedDates.size() < bookedDates.size()) {
//            bookedDates = updatedDates;
//            bookedDates.add(startDate);
//            bookedDates.add(endDate);
//            System.out.println("Reservation updated!");
//        } else {
//            System.out.println("Cannot update reservation. The new dates clash with existing booking.");
//        }
//    }
//    private boolean isDateClashing(String startDate1, String endDate1, String date2) {
//        // Assuming date format: "dd/mm/yyyy"
//        String[] parts1 = startDate1.split("/");
//        String[] parts2 = endDate1.split("/");
//        String[] parts3 = date2.split("/");
//
//        int startDay1 = Integer.parseInt(parts1[0]);
//        int startMonth1 = Integer.parseInt(parts1[1]);
//        int startYear1 = Integer.parseInt(parts1[2]);
//
//        int endDay1 = Integer.parseInt(parts2[0]);
//        int endMonth1 = Integer.parseInt(parts2[1]);
//        int endYear1 = Integer.parseInt(parts2[2]);
//
//        int day2 = Integer.parseInt(parts3[0]);
//        int month2 = Integer.parseInt(parts3[1]);
//        int year2 = Integer.parseInt(parts3[2]);
//
//        if (startYear1 > year2 || (startYear1 == year2 && startMonth1 > month2) || (startYear1 == year2 && startMonth1 == month2 && startDay1 > day2)) {
//            return endYear1 >= year2 && (endYear1 > year2 || (endYear1 == year2 && endMonth1 >= month2) || (endYear1 == year2 && endMonth1 == month2 && endDay1 >= day2));
//        } else {
//            return startYear1 <= year2 && (startYear1 < year2 || (startYear1 == year2 && startMonth1 <= month2) || (startYear1 == year2 && startMonth1 == month2 && startDay1 <= day2));
//        }
//    }
}
