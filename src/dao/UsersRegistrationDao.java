package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import bean.UsersRegistrationBean;

public class UsersRegistrationDao {
	
	static Connection con;
	
	public static int save(UsersRegistrationBean bean){
		int status=0;
		
		try{
			con=Connector.ConnectDB();
			String sql="insert into registration(username,password,NameOfUser,ContactNo,Email) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getUserName());
			ps.setString(2, bean.getPassword());
			ps.setString(3, bean.getName());
			ps.setString(4, bean.getContactNo());
			ps.setString(5, bean.getEmail());
			
			status=ps.executeUpdate();
			con.close();
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return status;
	}

	public static int update(UsersRegistrationBean bean){
		int status=0;
		
		try{
			con = Connector.ConnectDB();
			String sql="update registration set password=?,NameOfUser=?,ContactNo=?,Email=? where username=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, bean.getPassword());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getContactNo());
			ps.setString(4, bean.getEmail());
			ps.setString(5, bean.getUserName());
			
			status=ps.executeUpdate();
			con.close();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return status;
	}

	public static int delete(String username){
		int status=0;
		
		try{
			con=Connector.ConnectDB();
			String sql="delete from registration where username='"+ username +"'";
			PreparedStatement ps=con.prepareStatement(sql);
			status=ps.executeUpdate();
			con.close();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return status;
	}
	
	public static UsersRegistrationBean exist(String username){
		UsersRegistrationBean bean = new UsersRegistrationBean(username);
		
		try{
			con=Connector.ConnectDB();
			String sql="select username from registration where username='"+ username +"'";
			Statement ps = con.createStatement();
			ResultSet rs=ps.executeQuery(sql);
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "User name already taken!", "Error",JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return bean;
	}
	
}
