package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import bean.BillingPatientRoomBean;

public class BillingPatientRoomDao {

	static Connection con;

	public static int save(BillingPatientRoomBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into bill_room(BillNo,DischargeID,BillingDate,NoOfDays,RoomCharges,TotalRoomCharges,ServiceCharges,TotalCharges,PaymentMode,PaymentModeDetails,ChargesPaid,DueCharges) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bean.getBillNo());
			ps.setInt(2, bean.getDischargeID());
			ps.setString(3, bean.getBillingDate());
			ps.setInt(4, bean.getNoOfDays());
			ps.setDouble(5, bean.getRoomCharges());
			ps.setDouble(6, bean.getTotalRoomCharges());
			ps.setDouble(7, bean.getServiceCharges());
			ps.setDouble(8, bean.getTotalCharges());
			ps.setString(9, bean.getPaymentMode());
			ps.setString(10, bean.getPaymentModeDetails());
			ps.setDouble(11, bean.getChargesPaid());
			ps.setDouble(12, bean.getDueCharges());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static int update(BillingPatientRoomBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "update bill_room set DischargeID=?,BillingDate=?,NoOfDays=?,RoomCharges=?,TotalRoomCharges=?,ServiceCharges=?,TotalCharges=?,PaymentMode=?,PaymentModeDetails=?,ChargesPaid=?,DueCharges=? where BillNo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bean.getDischargeID());
			ps.setString(2, bean.getBillingDate());
			ps.setInt(3, bean.getNoOfDays());
			ps.setDouble(4, bean.getRoomCharges());
			ps.setDouble(5, bean.getTotalRoomCharges());
			ps.setDouble(6, bean.getServiceCharges());
			ps.setDouble(7, bean.getTotalCharges());
			ps.setString(8, bean.getPaymentMode());
			ps.setString(9, bean.getPaymentModeDetails());
			ps.setDouble(10, bean.getChargesPaid());
			ps.setDouble(11, bean.getDueCharges());
			ps.setInt(12, bean.getBillNo());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static int delete(int billNo) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "delete from bill_room where BillNo='" + billNo + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static BillingPatientRoomBean exist(int billNo){
		BillingPatientRoomBean bean = new BillingPatientRoomBean(billNo);
		
		try{
			con=Connector.ConnectDB();
			String sql="select BillNo from bill_room where BillNo='"+billNo+"'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "Bill No already exist !", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return bean;
	}
}
