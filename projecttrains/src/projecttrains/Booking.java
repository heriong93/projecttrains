package projecttrains;

public class Booking extends Train{
	String bookingId;
	String bookingTrain;
	String userName;
	int reserSeat;
	
	Booking(){}
	
	Booking(String bookingId, String bookingTrain, int reserSeat){
		this.bookingId = bookingId;
		this.bookingTrain = bookingTrain;
		this.reserSeat = reserSeat;
	
	}
	void showInfo() {
		System.out.println("기차번호>>"+trainNum +"\n"+"기차명>>"+ trainName +"\n"+ "출발시간>>"+trainTime +"\n"+"예약가능좌석>>"+ trainSeat);
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

	public String getUserName() {
		return userName;
	}

	public void setReserSeat(int reserSeat) {
		this.reserSeat = reserSeat;
	}

	public void setUserName(String string) {
		
	}

	
	
	
	
}
