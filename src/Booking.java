import javax.sound.sampled.FloatControl;
import java.time.LocalDate;

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

}
