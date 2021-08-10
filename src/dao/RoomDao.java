package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import bean.RoomBean;

public class RoomDao {

	static Connection con;

	public static int save(RoomBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into room(RoomNo,RoomType,RoomCharges,RoomStatus) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getRoomNo());
			ps.setString(2, bean.getRoomType());
			ps.setInt(3, bean.getRoomCharges());
			ps.setString(4, bean.getRoomStatus());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public static int update(RoomBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "update room set RoomType=?,RoomCharges=?,RoomStatus=? where RoomNo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getRoomType());
			ps.setInt(2, bean.getRoomCharges());
			ps.setString(3, bean.getRoomStatus());
			ps.setString(4, bean.getRoomNo());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public static int delete(String no) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "delete from room where RoomNo='" + no + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public static RoomBean exist(String roomNo) {
		RoomBean bean = new RoomBean(roomNo);
		try {
			con = Connector.ConnectDB();
			Statement stmt = con.createStatement();
			String sql = "select RoomNo from room where RoomNo='" + roomNo + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Room No. already exists", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

}
