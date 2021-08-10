package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import bean.ChangePasswordBean;

public class ChangePasswordDao {

	Connection con;

	public int update(ChangePasswordBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "select username, user_password from users where username='" + bean.getUsername() + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String user = rs.getString("username").trim();
				String pass = rs.getString("user_password").trim();
				if (bean.getUsername().equals(user) && bean.getOldPass().equals(pass)) {
					String sql1 = "update users set user_password='" + bean.getNewPass() + "' where username='"
							+ bean.getUsername() + "' and user_password='" + bean.getOldPass() + "'";
					Statement stmt = con.createStatement();
					stmt.execute(sql1.toString());
					stmt.close();

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}
}
