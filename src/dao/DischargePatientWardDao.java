package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import bean.DischargePatientWardBean;

public class DischargePatientWardDao {
	
	static Connection con;
	
	public static int save(DischargePatientWardBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into dischargepatient_ward(ID,AdmitID,DischargeDate,DP_Remarks) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bean.getDischargeId());
			ps.setInt(2, bean.getAdmitId());
			ps.setString(3, bean.getDischargeDate());
			ps.setString(4, bean.getRemarks());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static int update(DischargePatientWardBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "update dischargepatient_ward set AdmitID=?,DischargeDate=?,DP_Remarks=? where ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bean.getAdmitId());
			ps.setString(2, bean.getDischargeDate());
			ps.setString(3, bean.getRemarks());
			ps.setInt(4, bean.getDischargeId());
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
			String sql = "delete from dischargepatient_ward where ID='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static DischargePatientWardBean exist(int id) {
		DischargePatientWardBean bean = new DischargePatientWardBean(id);

		try {
			con = Connector.ConnectDB();
			String sql = "select ID from dischargepatient_ward where ID='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Discharge ID already exist!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return bean;
	}
	
}
