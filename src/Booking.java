import javax.sound.sampled.FloatControl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Booking {
    public LocalDate startDate;
    public LocalDate endDate;
    public String guestName;
    public Booking(LocalDate startDate, LocalDate endDate, String guestName){
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestName = guestName;
    }
    public LocalDate getStartDate(){
        return startDate;
    }
    public LocalDate getEndDate(){
        return endDate;
    }
    public String getGuestName(){
        return guestName;
    }

    public void updateReservation(LocalDate newStartDate, LocalDate newEndDate){
        this.startDate = newStartDate;
        this.endDate = newEndDate;
    }
    public String getFormattedStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return startDate.format(formatter);
    }

    public String getFormattedEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return endDate.format(formatter);
    }
}
