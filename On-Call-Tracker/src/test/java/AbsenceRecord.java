import java.time.LocalDateTime;

public class AbsenceRecord{
	
	private LocalDateTime dateAndTime;
	private String reason;
	
	public AbsenceRecord(LocalDateTime dateAndTimeIn, String reasonIn) {
		dateAndTime = dateAndTimeIn;
		reason = reasonIn;
	}
	
	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setDateAndTime(LocalDateTime dateAndTimeIn) {
		dateAndTime = dateAndTimeIn;
	}
	
	public void setReason(String reasonIn) {
		reason = reasonIn;
	}
}