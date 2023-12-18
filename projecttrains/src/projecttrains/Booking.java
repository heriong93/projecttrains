package projecttrains;


public class Booking extends Train{
	private String bookingNum;
	private String bookingTrain;
	private String bookingDate;
	 
	Booking(){}
	
	public Booking (String bookingNum, String bookingTrain, String bookingDate) {
		this.bookingNum= bookingNum;
		super.trainNum = bookingTrain;
		this.bookingDate = bookingDate;
	}
	
	public Booking (String bookingNum, String bookingTrain) {
		this.bookingNum= bookingNum;
		super.trainNum = bookingTrain;
	}

	public String getBookingNum() {
		return bookingNum;
	}

	public String getBookingTrain() {
		return bookingTrain;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingNum(String bookingNum) {
		this.bookingNum = bookingNum;
	}

	public void setBookingTrain(String bookingTrain) {
		this.bookingTrain = bookingTrain;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	
}//end of class
