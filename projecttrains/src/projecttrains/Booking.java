package projecttrains;

public class Booking extends Train{
	String bookingId;
	String bookingTrain;
	String bookingName;
	int reserSeat;
	
	 
	Booking(){}
	
	Booking(String bookingId, String bookingTrain, int reserSeat){
		this.bookingId = bookingId;
		this.bookingTrain = bookingTrain;
		this.reserSeat = reserSeat;

	
	}
	
	public Booking(String trainNum, String trainName, String bookingName, String trainTime) {
		this.trainNum = trainNum;
		this.trainName = trainName;
		this.bookingName = bookingName;
		this.trainTime = trainTime;	
	}
	
	Booking(String bookingName, String bookingId, String bookingTrain, int reserSeat){
		this.bookingName = bookingName;
		this.bookingId = bookingId;
		this.bookingTrain = bookingTrain;
		this.reserSeat = reserSeat;

	
	}
	void showInfo() {
		System.out.println(" "+trainNum +"\t"+"\t"+ trainName +"\t"+"\t"+ bookingName +"\t"+"\t"+ trainTime);
		System.out.println(" ");
	}
	

	public String getBookingId() {
		return bookingId;
	}

	public String getBookingTrain() {
		return bookingTrain;
	}


	public int getReserSeat() {
		return reserSeat;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public void setBookingTrain(String bookingTrain) {
		this.bookingTrain = bookingTrain;
	}

	public String getBookingName() {
		return bookingName;
	}
	

	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}

	public void setReserSeat(int reserSeat) {
		this.reserSeat = reserSeat;
	}

	
	
}
