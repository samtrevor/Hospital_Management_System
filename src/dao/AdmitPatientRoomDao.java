package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import bean.AdmitPatientRoomBean;

public class AdmitPatientRoomDao {

	static Connection con;

	public static int save(AdmitPatientRoomBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into admitpatient_room(AdmitID,PatientID,Disease,RoomNo,AdmitDate,DoctorID,AP_Remarks) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bean.getAdmitId());
			ps.setString(2, bean.getPatientId());
			ps.setString(3, bean.getDisease());
			ps.setString(4, bean.getRoomNo());
			ps.setString(5, bean.getAdmitDate());
			ps.setString(6, bean.getDoctorId());
			ps.setString(7, bean.getRemarks());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static int update(AdmitPatientRoomBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "update admitpatient_room set PatientID=?,Disease=?,RoomNo=?,AdmitDate=?,DoctorID=?,AP_Remarks=? where AdmitID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getPatientId());
			ps.setString(2, bean.getDisease());
			ps.setString(3, bean.getRoomNo());
			ps.setString(4, bean.getAdmitDate());
			ps.setString(5, bean.getDoctorId());
			ps.setString(6, bean.getRemarks());
			ps.setInt(7, bean.getAdmitId());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static int delete(int id) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "delete from admitpatient_room where AdmitID='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static AdmitPatientRoomBean exist(int id) {
		AdmitPatientRoomBean bean = new AdmitPatientRoomBean(id);

		try {
			con = Connector.ConnectDB();
			String sql = "select AdmitID from admitpatient_room where AdmitID='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Admit ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return bean;
	}

}
