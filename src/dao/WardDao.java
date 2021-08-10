package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bean.WardBean;

public class WardDao {
	static Connection con;

	public static int save(WardBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into ward(wardname,wardtype,NoOfBeds,Charges) values(?,?,?,?)";

			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, bean.getWardName());
			prep.setString(2, bean.getWardType());
			prep.setInt(3, bean.getNoOfBeds());
			prep.setInt(4, bean.getCharges());

			status = prep.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public static WardBean exist(String wardName) {
		WardBean bean = new WardBean(wardName);
		try {
			con = Connector.ConnectDB();
			Statement stmt = con.createStatement();
			String sql = "select wardname from ward where wardname='" + wardName + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
                JOptionPane.showMessageDialog( null, "ward name already exists","Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

	public static int update(WardBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "update ward set wardtype=?,NoOfBeds=?,Charges=? where wardname=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getWardType());
			ps.setInt(2, bean.getNoOfBeds());
			ps.setInt(3, bean.getCharges());
			ps.setString(4, bean.getWardName());
			status = ps.executeUpdate();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public static List<WardBean> getData() {
		List<WardBean> list = new ArrayList<WardBean>();
		WardBean bean = new WardBean();
		try {
			con = Connector.ConnectDB();
			String sql = "select * from ward";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				bean.setWardName(rs.getString("wardname"));
				bean.setWardType(rs.getString("wardtype"));
				bean.setNoOfBeds(rs.getInt("NoOfBeds"));
				bean.setCharges(rs.getInt("Charges"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static int delete(String wardName) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "delete from ward where wardname='"+wardName+"'";
			PreparedStatement ps = con.prepareStatement(sql);
			status = ps.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}
}
