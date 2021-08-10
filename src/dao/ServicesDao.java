package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import bean.ServicesBean;

public class ServicesDao {

	static Connection con;

	public static int save(ServicesBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into services(ServiceID,ServiceName,ServiceDate,PatientID,ServiceCharges) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bean.getServiceId());
			ps.setString(2, bean.getServiceName());
			ps.setString(3, bean.getServiceDate());
			ps.setString(4, bean.getPatientId());
			ps.setInt(5, bean.getServiceCharges());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}
	
	public static int update(ServicesBean bean){
		int status=0;
		
		try{
			con= Connector.ConnectDB();
			String sql="update services set ServiceName=?,ServiceDate=?,PatientID=?,ServiceCharges=? where ServiceID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getServiceName());
			ps.setString(2, bean.getServiceDate());
			ps.setString(3, bean.getPatientId());
			ps.setInt(4, bean.getServiceCharges());
			ps.setInt(5, bean.getServiceId());
			status = ps.executeUpdate();
			con.close();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return status;
	}
	
	public static int delete(int id){
		int status=0;
		
		try{
			con=Connector.ConnectDB();
			String sql="delete from services where ServiceId='"+id+"'";
			PreparedStatement ps = con.prepareStatement(sql);
			status=ps.executeUpdate();
			con.close();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return status;
	}
	
	public static ServicesBean exist(int id){
		ServicesBean bean = new ServicesBean(id);
		
		try{
			con= Connector.ConnectDB();
			String sql="select ServiceID from services where ServiceID='"+id+"'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "Service ID already exist!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return bean;
	}
	
	
	
	
	
	
	
	
	
}
