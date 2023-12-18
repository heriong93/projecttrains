package projecttrains;


public class Train {
	protected String trainNum;
	protected String trainName;
	private String trainDepart;
	private String trainDesti;
	protected String trainTime;
	private int trainSeat;
	
	//생성자
	Train(){}
	
	public Train (String trainNum,String trainName,String trainDepart,String trainDesti,String trainTime,int trainSeat) {
		this.trainNum = trainNum;
		this.trainName = trainName;
		this.trainDepart = trainDepart;
		this.trainDesti = trainDesti;
		this.trainTime = trainTime;
		this.trainSeat = trainSeat;
	}
	public Train (String trainNum,String trainName,String trainTime,int trainSeat) {
		this.trainNum = trainNum;
		this.trainName = trainName;
		this.trainTime = trainTime;
		this.trainSeat = trainSeat;
	}
	//메소드 정의 
	void showInfo() {
		System.out.println("기차번호>>"+trainNum +"\n"+"기차명>>"+ trainName +"\n"+ "출발시간>>"+trainTime +"\n"+"예약가능좌석>>"+ trainSeat);
	}
	void showLeftSeat() {
		System.out.println("예약 가능한 좌석 수>> "+trainSeat);
	}
	public void setTrainNum(String trainNum) {
		this.trainNum = trainNum;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public void setTrainDepart(String trainDepart) {
		this.trainDepart = trainDepart;
	}

	public void setTrainDesti(String trainDesti) {
		this.trainDesti = trainDesti;
	}

	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}

	public void setTrainSeat(int trainSeat) {
		this.trainSeat = trainSeat;
	}

	public String getTrainNum() {
		return trainNum;
	}

	public String getTrainName() {
		return trainName;
	}

	public String getTrainDepart() {
		return trainDepart;
	}

	public String getTrainDesti() {
		return trainDesti;
	}

	public String getTrainTime() {
		return trainTime;
	}

	public int getTrainSeat() {
		return trainSeat;
	}
	
	
}//end of class