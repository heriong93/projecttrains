package projecttrains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, "dev", "dev");
			System.out.println("연결성공!");
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

	
	// 목록 보기

	Train getTrainList(String trainDepart, String trainDesti) {
		getConn();
		String sql = "SELECT tr_num, tr_name,tr_time, tr_seat " + "FROM trains " + "WHERE tr_depart =?"
				+ "AND tr_destin = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, trainDepart);
			psmt.setString(2, trainDesti);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Train train = new Train();
				train.setTrainNum(rs.getString("tr_num"));
				train.setTrainName(rs.getString("tr_name"));
				train.setTrainTime(rs.getString("tr_time"));
				train.setTrainSeat(rs.getInt("tr_seat"));
				return train;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return null;
	}// end of getTrainList

	// 잔여좌석 보기
	Train getTrainSeat(String trainNum) {
		getConn();
		String sql = "SELECT tr_seat " + "FROM trains " + "WHERE tr_num = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, trainNum);
			rs = psmt.executeQuery();
			if (rs.next()) {
				Train train = new Train();
				train.setTrainSeat(rs.getInt("tr_seat"));
				return train;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return null;
	}// end of getTrainSeat

	// 사용자 추가 및 표 예약
		boolean addUsers(Users user) {
			getConn();
			String sql = "INSERT INTO users VALUES (?,?,?,?,?)";
			try {
				psmt = conn.prepareStatement(sql);

				psmt.setString(1, user.getUserId());
				psmt.setString(2, user.getUserPw());
				psmt.setString(3, user.getUserName());
				psmt.setString(4, user.getUserPhone());
				psmt.setString(5, user.getTrainsToTake());
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
		Users getReservationList(String userid) {
			getConn();
			String sql = "SELECT t.tr_num,t.tr_name,u.user_name,t.tr_time "
					+ "FROM users u,trains  "
					+ "WHERE t.tr_num = u.tr_totake "
					+ "AND user_id = ?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, userid);
			
				rs = psmt.executeQuery();
				while (rs.next()) {
					
					Users user = new Users();
					user.setTrainNum(rs.getString("tr_num"));
					user.setTrainName(rs.getString("tr_name"));
					user.setUserName(rs.getString("user_name"));
					user.setTrainTime(rs.getString("tr_time"));
					return user;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disconn();
			}
			return null;
		}// end of getTrainList

		
	// 삭제
	boolean removeReservation(String trainNum) {
		getConn();
		String sql = "DELETE FROM users" + "WHERE tr_totake = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, trainNum);
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

}// end of class