package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bean.LoginBean;

public class LoginDao {

	static Connector con = new Connector();

	public LoginBean validate(String user, String pass) throws ClassNotFoundException {

		LoginBean bean = new LoginBean();
		Connection connect = null;

		try {
			connect = Connector.ConnectDB();
			String sql = "select * from users where username ='" + user + "' and user_password ='" + pass + "'";
			PreparedStatement prepareStatement = connect.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			if (rs.next()) {
				bean.setUserName(rs.getString(1));
				bean.setPassword(rs.getString(2));
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

		return bean;
	}

	public static int save(LoginBean bean) {
		int status = 0;

		try {
			Connection con = Connector.ConnectDB();
			String sql = "insert into users(username,user_password) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getUserName());
			ps.setString(2, bean.getPassword());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static int delete(String user) {
		int status = 0;

		try {
			Connection con = Connector.ConnectDB();
			String sql = "delete from users where username='" + user + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			status = ps.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static List<LoginBean> view() {
		List<LoginBean> list = new ArrayList<>();
		try {
			Connection con = Connector.ConnectDB();
			PreparedStatement ps = con.prepareStatement("select * from users");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LoginBean bean = new LoginBean();
				bean.setUserName(rs.getString("username"));
				bean.setPassword(rs.getString("user_password"));
				list.add(bean);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
}
