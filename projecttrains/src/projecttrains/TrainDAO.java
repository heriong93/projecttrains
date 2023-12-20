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
		String url = "jdbc:oracle:thin:@192.168.0.19:1521:xe";
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

	
	// 목록 보기

	ArrayList<Train> getTrainList(String trainDepart, String trainDesti) {
		getConn();
		String sql = "SELECT tr_num, tr_name,tr_time, tr_seat  "
				+ "FROM trains  "
				+ "WHERE tr_depart =? "
				+ "AND tr_destin = ? "
				+ "ORDER BY TR_TIME ";
		ArrayList<Train> trains = new ArrayList<Train>();
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
				trains.add(train);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return trains;
	}// end of getTrainList
	
	
	//기차 노선 확인  
	boolean checkTrainDest(String trdp, String trar ) {
		getConn();
		String sql = "SELECT * "
				+ "FROM TRAINS "
				+ "WHERE TR_DEPART = ?"
				+ "AND TR_DESTIN = ?"
				+ "ORDER BY TR_TIME";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, trdp);
			psmt.setString(2, trar);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	// 존재하지 않는 기차 예약 방지 
		boolean checkTrainNum(String trnum) {
			getConn();
			String sql = "SELECT * " 
						+ "FROM trains " 
						+ "WHERE tr_num = ?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, trnum);

				int r = psmt.executeUpdate();
				if (r == 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconn();
			}
			return false;
		}

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


		
	
}// end of class