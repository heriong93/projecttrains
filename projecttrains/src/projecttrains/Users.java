package projecttrains;


public class Users extends Train{
	private String userId; 
	private String userPw;
	private String userName;
	private String userPhone;
	private String trainsToTake;
	
	Users(){}
	
	public Users(String userId,String userPw,String userName,String userPhone,String trainsToTake) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userPhone = userPhone;
		this.trainsToTake = trainsToTake;
	}
	public Users(String trainNum, String trainName, String userName, String trainTime) {
		super.trainNum = trainNum;
		super.trainName = trainName;
		this.userName = userName;
		super.trainTime = trainTime;	
	}
	//메소드 정의 
	void showInfo() {
		System.out.println("기차번호>>"+trainNum+"기차이름>>"+trainName+"승객이름>>"+userName+"출발시간>>"+trainTime);
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPhone() {
		return userPhone;
	}
 
	public String getTrainsToTake() {
		return trainsToTake;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public void setTrainsToTake(String trainsToTake) {
		this.trainsToTake = trainsToTake;
	}

	
}//end of class 