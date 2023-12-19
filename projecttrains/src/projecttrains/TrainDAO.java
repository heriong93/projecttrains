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
		String sql = "SELECT tr_num, tr_name,tr_time, tr_seat " + "FROM trains " + "WHERE tr_depart =?"
				+ "AND tr_destin = ?";
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