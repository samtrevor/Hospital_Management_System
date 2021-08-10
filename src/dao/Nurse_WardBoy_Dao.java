package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import bean.Nurse_WardBoy_Bean;

public class Nurse_WardBoy_Dao {

	static Connection con;

	public static int save(Nurse_WardBoy_Bean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into wardboy_nurse_tbl(ID,W_N_Name,Category,Address,ContactNo,Email,Qualifications,Bloodgroup,DateOfJoining) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getId());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getCategory());
			ps.setString(4, bean.getAddress());
			ps.setString(5, bean.getContactNo());
			ps.setString(6, bean.getEmail());
			ps.setString(7, bean.getQualifications());
			ps.setString(8, bean.getBloodGroup());
			ps.setString(9, bean.getDateOfJoining());

			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static Nurse_WardBoy_Bean exist(String id) {
		Nurse_WardBoy_Bean bean = new Nurse_WardBoy_Bean(id);

		try {
			con = Connector.ConnectDB();
			String sql = "select ID from wardboy_nurse_tbl where ID='" + id + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Id already exists", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, e);
		}

		return bean;
	}

	public static int update(Nurse_WardBoy_Bean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "update wardboy_nurse_tbl set W_N_Name=?, Category=?, Address=?, ContactNo=?, Email=?, Qualifications=?, Bloodgroup=?, DateOfJoining=? where ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getCategory());
			ps.setString(3, bean.getAddress());
			ps.setString(4, bean.getContactNo());
			ps.setString(5, bean.getEmail());
			ps.setString(6, bean.getQualifications());
			ps.setString(7, bean.getBloodGroup());
			ps.setString(8, bean.getDateOfJoining());
			ps.setString(9, bean.getId());

			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static int delete(String id){
		int status=0;
		
		try{
			con=Connector.ConnectDB();
			String sql="delete from wardboy_nurse_tbl where ID='"+ id +"'";
			PreparedStatement ps = con.prepareStatement(sql);
			status=ps.executeUpdate();
			con.close();
		}catch(SQLException e){
			JOptionPane.showInternalMessageDialog(null, e);
		}
		
		return status;
	}
	
}
