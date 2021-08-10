package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import bean.PatientRegistrationBean;

public class PatientRegistrationDao {

	static Connection con;

	public static int save(PatientRegistrationBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into patientregistration(PatientID,Patientname,Fathername,Address,ContactNo,Email,Age,Gen,BG,Remarks) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getPatientId());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getFathersName());
			ps.setString(4, bean.getAddress());
			ps.setString(5, bean.getContactNo());
			ps.setString(6, bean.getEmail());
			ps.setInt(7, bean.getAge());
			ps.setString(8, bean.getGender());
			ps.setString(9, bean.getBloodGroup());
			ps.setString(10, bean.getRemarks());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static int update(PatientRegistrationBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "update patientregistration set Patientname=?,Fathername=?,Address=?,ContactNo=?,Email=?,Age=?,Gen=?,BG=?,Remarks=? where PatientID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getFathersName());
			ps.setString(3, bean.getAddress());
			ps.setString(4, bean.getContactNo());
			ps.setString(5, bean.getEmail());
			ps.setInt(6, bean.getAge());
			ps.setString(7, bean.getGender());
			ps.setString(8, bean.getBloodGroup());
			ps.setString(9, bean.getRemarks());
			ps.setString(10, bean.getPatientId());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static int delete(String id) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "delete from patientregistration where PatientID='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static PatientRegistrationBean exist(String id) {
		PatientRegistrationBean bean = new PatientRegistrationBean(id);

		try {
			con = Connector.ConnectDB();
			String sql = "select PatientID from patientregistration where PatientID='" + id + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Patient ID already exist", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return bean;
	}

	public static PatientRegistrationBean exist2(String id) {
		PatientRegistrationBean bean = new PatientRegistrationBean(id);

		try {
			con = Connector.ConnectDB();
			String sql = "select PatientID from patientregistration where PatientID='" + id + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return bean;
	}

}
