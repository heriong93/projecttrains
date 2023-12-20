package projecttrains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

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

	// 사용자 추가 
	boolean addUsers(Users user) {
		getConn();
		String sql = "INSERT INTO users VALUES (?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, user.getUserId());
			psmt.setString(2, user.getUserPw());
			psmt.setString(3, user.getUserName());
			psmt.setString(4, user.getUserPhone());
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

	//
	// 로그인
	boolean loginUserId(String userName, String userId) {
		getConn();
		String sql = "SELECT * " 
					+ "FROM USERS " 
					+ "WHERE USER_NAME = ? " 
					+ "AND USER_ID = ? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userName);
			psmt.setString(2, userId);

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
	}// end of loginUserId

	
	// 중복 아이디 가입 방지
	boolean checkUserId(String userId) {
		getConn();
		String sql = "SELECT * " 
					+ "FROM USERS " 
					+ "WHERE USER_ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);

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

	

	

}// END OF CLASS
