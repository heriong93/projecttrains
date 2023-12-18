package projecttrains;


import java.util.ArrayList;
import java.util.Scanner;


public class TrainApp {
	public static void main(String[] args) {
		boolean run = true;

		Scanner scn = new Scanner(System.in);
		TrainDAO dao = new TrainDAO();

		while (run) {
			System.out.println("1.기차 조회 2.잔여 좌석 조회 3.기차표 구매 4.예약 조회 5.예약 취소 6.종료");
			int menu = Integer.parseInt(scn.nextLine());

			switch (menu) {
			case 1: // 기차 조회
				System.out.println("출발지 입력>>");
				String depart = scn.nextLine();
				System.out.println("목적지 입력>>");
				String arrival = scn.nextLine();
				Train tr = dao.getTrainList(depart, arrival);
				if (tr != null) {
					tr.showInfo();
				} else {
					System.out.println("예약 가능한 기차가 없습니다");
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
				System.out.println("구매를 위한 정보를 입력해주세요");
				
					System.out.println("아이디>>");
					String id = scn.nextLine();
					System.out.println("비밀번호>>");
					String pw = scn.nextLine();
					System.out.println("성함>>");
					String name = scn.nextLine();
					System.out.println("전화번호>>");
					String phone = scn.nextLine();
					System.out.println("탑승할 기차 번호>>");
					String trnum = scn.nextLine();
					Users users = new Users(id, pw, name, phone, trnum);
					if (dao.addUsers(users)) {
						System.out.println("예약 완료");
					} else {
						System.out.println("예약 중 오류");
					}
					break;
			case 4: //예약조회 
				System.out.println("아이디 입력>>");
				String idcheck = scn.nextLine();
				Users us = dao.getReservationList(idcheck);
				if (us != null) {
					us.showInfo();
				} else {
					System.out.println("조회된 예약이 없습니다");
				}
				break;
			case 5: //예약취소 
				System.out.println("예약자 아이디>>");
				String checkid = scn.nextLine();
				System.out.println("예약할 기차번호>>");
				String train = scn.nextLine();
				
				
				break;
			case 6:
				System.out.println("프로그램을 종료합니다");
				run = false;

			}// end of switch

		} // end of while

	}// end of main
}// end of class