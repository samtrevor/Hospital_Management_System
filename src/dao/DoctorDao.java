package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import bean.DoctorBean;

public class DoctorDao {

	static Connection con;

	public static int save(DoctorBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into doctor(DoctorID,DoctorName,FatherName,Address,ContactNo,Email,Qualifications,Specialization,Gender,Bloodgroup,DateOfJoining) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getId());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getFatherName());
			ps.setString(4, bean.getAddress());
			ps.setString(5, bean.getContactNo());
			ps.setString(6, bean.getEmail());
			ps.setString(7, bean.getQualification());
			ps.setString(8, bean.getSpecialization());
			ps.setString(9, bean.getGender());
			ps.setString(10, bean.getBloodGroup());
			ps.setString(11, bean.getDateOfJoining());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, e);
		}

		return status;
	}

	public static int update(DoctorBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "update doctor set DoctorName=?,FatherName=?,Address=?,ContactNo=?,Email=?,Qualifications=?,Specialization=?,Gender=?,Bloodgroup=?,DateOfJoining=? where DoctorID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getFatherName());
			ps.setString(3, bean.getAddress());
			ps.setString(4, bean.getContactNo());
			ps.setString(5, bean.getEmail());
			ps.setString(6, bean.getQualification());
			ps.setString(7, bean.getSpecialization());
			ps.setString(8, bean.getGender());
			ps.setString(9, bean.getBloodGroup());
			ps.setString(10, bean.getDateOfJoining());
			ps.setString(11, bean.getId());
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
			String sql = "delete from doctor where DoctorID='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static DoctorBean exist(String id) {
		DoctorBean bean = new DoctorBean(id);

		try {
			con = Connector.ConnectDB();
			String sql = "select DoctorID from doctor where DoctorID='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Doctor ID already exist!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return bean;
	}

}
