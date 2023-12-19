package projecttrains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, "dev", "dev");
//			System.out.println("연결성공!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 연결접속 해제
	void disconn() {
		try {
			if (conn != null)
				conn.close();
			if (psmt != null)
				psmt.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of disconn
	
	// 로그인 후 예약 추가 
	boolean addReservation(Booking bk) {
		getConn();
		String sql = "insert into booking  "
				+ "values (?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, bk.getBookingId());
			psmt.setString(2, bk.getBookingTrain());
			psmt.setInt(3, bk.getReserSeat());
			int r = psmt.executeUpdate();
			if (r == 1) {

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}// end of addUsers

		
	// 예약조회
		ArrayList<Booking> getReservationList(String idCheck) {
			getConn();
			String sql = "SELECT t.tr_num,t.tr_name,u.user_name,t.tr_time  "
					+ "FROM users u,trains t,booking b  "
					+ "WHERE t.tr_num = b.booking_train  "
					+ "AND u.user_id = b.booking_id "
					+ "and b.booking_id =? ";
			ArrayList<Booking> bk = new ArrayList<Booking>();
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, idCheck);
				rs = psmt.executeQuery();
				while (rs.next()) {
					Booking booking = new Booking();
					booking.setTrainNum(rs.getString("tr_num"));
					booking.setTrainName(rs.getString("tr_name"));
					booking.setUserName(rs.getString("user_name"));
					booking.setTrainTime(rs.getString("tr_time"));
					bk.add(booking);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disconn();
			}
			return bk;
		}// end of getReservationList
		
		// 삭제
		boolean removeReservation(String trainNum,String userid) {
			getConn();
			String sql = "DELETE FROM booking "
					+ "WHERE booking_train = ? "
					+ "AND booking_id = ? ";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, trainNum);
				psmt.setString(2, userid);		
				int r = psmt.executeUpdate();
				if (r > 0) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disconn();
			}
			return false;
		}// end of removeBooking

}//END OF CLASS
