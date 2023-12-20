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
		System.out.println(" -------령레일 기차표 예약 시스템------");
		while (run) {
			System.out.println("=========================================================");
			System.out.println("1.기차 조회 2.잔여 좌석 조회 3.기차표 구매 4.예약 조회 5.예약 취소 6.종료");
			System.out.println("=========================================================");
			int menu = Integer.parseInt(scn.nextLine());

			switch (menu) {
			case 1: // 기차 조회
				System.out.println("출발지 입력>>");
				String depart = scn.nextLine();
				System.out.println("목적지 입력>>");
				String arrival = scn.nextLine();
				if (!dao.checkTrainDest(depart,arrival)) {
					System.out.println("운행 가능한 기차가 없습니다");
					break;
					}
				ArrayList<Train> trAry = dao.getTrainList(depart, arrival);
				for (Train train : trAry) {
					if (train != null) {
						System.out.println("=========================================================");
						System.out.println("<<기차번호>>     <<기차명>>       <<출발시간>>      <<남은 좌석>>  ");
						train.showInfo();
					}
				}
				break;
			case 2: // 잔여좌석 조회
				System.out.println("조회할 기차번호 입력>>");
				String num = scn.nextLine();
				Train trs = dao.getTrainSeat(num);
				if (trs != null) {
					trs.showLeftSeat();
				} 
				System.out.println("선택하신 노선의 기차가 없습니다");
				break;
			case 3: // 기차표 구매
				System.out.println("================");
				System.out.println("1.회원가입   2.구매");
				System.out.println("================");
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
						System.out.println("=============");
						System.out.println("  회원가입 완료  ");
						System.out.println("=============");
					} else {
						System.out.println("가입 중 오류");
					}
					break;
				case 2:// 구매를 위한 로그인
					System.out.println("예약자 성함>>");
					String lgnm = scn.nextLine();
					System.out.println("아이디>>");
					String lgid = scn.nextLine();
					if (udao.loginUserId(lgnm, lgid)) {
						System.out.println("=============");
						System.out.println("|  로그인 완료  |");
						System.out.println("=============");
					}
					System.out.println("탑승할 기차 번호>>");
					String trainnum = scn.nextLine();
					if (dao.checkTrainNum(trainnum)) {
						System.out.println("존재하지 않는 기차입니다");
						break;
					}
					System.out.println("구매수량>>");
					int ticket = Integer.parseInt(scn.nextLine());
					Booking bk = new Booking(lgnm,lgid,trainnum,ticket);
					if (bdao.addReservation(bk)) {
						System.out.println("==========================");
						System.out.println("|        예약 완료          |");
						System.out.println("|   구매해주셔서 감사합니다      |");
						System.out.println("==========================");
					} else {
						System.out.println("예약 오류");
					}
					break;
				}// end of innerswitch
				break;
			case 4: // 예약조회
				
				System.out.println("아이디 입력>>");
				String idcheck = scn.nextLine();
				if (!bdao.checkReserv(idcheck)) {
					System.out.println("예약내역이 존재하지 않습니다");
					break;
					}
				ArrayList<Booking> resAry = bdao.getReservationList(idcheck);
				for (Booking res : resAry) {
					if (res != null) {
						System.out.println("=========================================================");
						System.out.println("<<기차번호>>     <<기차명>>       <<예약자명>>      <<출발 시간>>  ");
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
					System.out.println("================");
					System.out.println("|  예약 취소 완료  |");
					System.out.println("================");
				} else {
					System.out.println("취소할 예약건이 없습니다");
				}
				break;
			case 6:
				System.out.println("=========================");
				System.out.println(" 령레일을 이용해 주셔서 감사합니다 ");
				System.out.println("      좋은 하루 되세요 ^^     ");
				System.out.println("=========================");
				run = false;

			}// end of switch
		} // end of while
	}// end of main
}// end of class