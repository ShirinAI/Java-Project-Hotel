public class Room {
    public String number;
    public int beds;
    public String view;
    public boolean booked;
    public String bookedStartDate;
    public String bookedEndDate;
    public String bookedName;

    public Room(String number, int beds, String view){
        this.number = number;
        this.beds = beds;
        this.view = view;
        this.booked = false;
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

    public boolean isBooked() {
        return booked;
    }

    public String getBookedName() {
        return bookedName;
    }

    public void book(String startDate, String endDate, String name) {
        this.booked = true;
        this.bookedStartDate = startDate;
        this.bookedEndDate = endDate;
        this.bookedName = name;
    }

    public void checkout() {
        this.booked = false;
        this.bookedStartDate = null;
        this.bookedEndDate = null;
        this.bookedName = null;
    }

    public void updateReservation(String startDate, String endDate) {
        this.bookedStartDate = startDate;
        this.bookedEndDate = endDate;
    }
}
