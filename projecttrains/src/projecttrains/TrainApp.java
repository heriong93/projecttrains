package projecttrains;

import java.util.ArrayList;
import java.util.Scanner;

public class TrainApp {
	public static void main(String[] args) {
		boolean run = true;

		Scanner scn = new Scanner(System.in);
		TrainDAO dao = new TrainDAO();
		UserDAO udao = new UserDAO();
		BookingDAO bdao = new BookingDAO();

		while (run) {
			System.out.println("1.기차 조회 2.잔여 좌석 조회 3.기차표 구매 4.예약 조회 5.예약 취소 6.종료");
			int menu = Integer.parseInt(scn.nextLine());

			switch (menu) {
			case 1: // 기차 조회
				System.out.println("출발지 입력>>");
				String depart = scn.nextLine();
				System.out.println("목적지 입력>>");
				String arrival = scn.nextLine();

				ArrayList<Train> trAry = dao.getTrainList(depart, arrival);
				for (Train train : trAry) {
					if (train != null) {
						train.showInfo();
					} else {
						System.out.println("값을 입력해주세요");
					}
				}
				break;
			case 2: // 잔여좌석 조회
				System.out.println("조회할 기차번호 입력>>");
				String num = scn.nextLine();
				Train trs = dao.getTrainSeat(num);
				if (trs != null) {
					trs.showLeftSeat();
				} else {
					System.out.println("잔여석이 없습니다");
				}
				break;
			case 3: // 기차표 구매
				System.out.println("3-1)회원가입 3-2)로그인");
				int idmenu = Integer.parseInt(scn.nextLine());
				switch (idmenu) {
				case 1: // 구매를 위한 회원가입
					System.out.println("아이디>>");
					String id = scn.nextLine();
					if (!udao.checkUserId(id)) {
						System.out.println("존재하는 아이디입니다");
						break;
					}
					System.out.println("비밀번호>>");
					String pw = scn.nextLine();
					System.out.println("성함>>");
					String name = scn.nextLine();
					System.out.println("전화번호>>");
					String phone = scn.nextLine();
					Users users = new Users(id, pw, name, phone);
					if (udao.addUsers(users)) {
						System.out.println("회원가입 완료");
					} else {
						System.out.println("가입 중 오류");
					}
					break;
				case 2:// 구매를 위한 로그인
					System.out.println("아이디>>");
					String lgid = scn.nextLine();
					System.out.println("비밀번호>>");
					String lgpw = scn.nextLine();

					if (udao.loginUserId(lgid, lgpw)) {
						System.out.println("<<로그인 완료>>");
					} 
					System.out.println("탑승할 기차 번호>>");
					String trainnum = scn.nextLine();
					System.out.println("구매수량");
					int ticket = Integer.parseInt(scn.nextLine());
					Booking bk = new Booking(lgid,trainnum,ticket);
					
					if (bdao.addReservation(bk)) {
						System.out.println("기차표 예약 완료");
					} else {
						System.out.println("예약 오류");
					}
					break;
				}// end of innerswitch
				break;
			case 4: // 예약조회
				System.out.println("아이디 입력>>");
				String idcheck = scn.nextLine();
				ArrayList<Booking> resAry = bdao.getReservationList(idcheck);
				for (Booking res : resAry) {
					if (res != null) {
						res.showInfo();
					}
				}
				break;
			case 5: // 예약취소
				System.out.println("취소할 기차번호>>");
				String train = scn.nextLine();
				System.out.println("예약자 아이디>>");
				String checkid = scn.nextLine();
				if (bdao.removeReservation(train,checkid)) {
					System.out.println("예약 취소 완료");
				} else {
					System.out.println("정보를 확인하십시오");
				}
				break;
			case 6:
				System.out.println("프로그램을 종료합니다");
				run = false;

			}// end of switch
		} // end of while
	}// end of main
}// end of class