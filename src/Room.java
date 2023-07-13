import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class Room {
    public String number;
    public int beds;
    public String view;
    public List<Booking> bookings;

    public Room(String number, int beds, String view){
        this.number = number;
        this.beds = beds;
        this.view = view;
        this.bookings = new ArrayList<>();
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
        return !bookings.isEmpty();
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
}
