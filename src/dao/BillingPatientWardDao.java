package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import bean.BillingPatientWardBean;

public class BillingPatientWardDao {

	static Connection con;
	
	public static int save(BillingPatientWardBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "insert into bill_ward(BillNo,DischargeID,BillingDate,BedCharges,NoOfDays,TotalBedCharges,ServiceCharges,TotalCharges,PaymentMode,PaymentModeDetails,ChargesPaid,DueCharges) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bean.getBillNo());
			ps.setInt(2, bean.getDischargeID());
			ps.setString(3, bean.getBillingDate());
			ps.setDouble(4, bean.getBedCharges());
			ps.setInt(5, bean.getNoOfDays());
			ps.setDouble(6, bean.getTotalBedCharges());
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
	
	public static int update(BillingPatientWardBean bean) {
		int status = 0;

		try {
			con = Connector.ConnectDB();
			String sql = "update bill_ward set DischargeID=?,BillingDate=?,BedCharges=?,NoOfDays=?,TotalBedCharges=?,ServiceCharges=?,TotalCharges=?,PaymentMode=?,PaymentModeDetails=?,ChargesPaid=?,DueCharges=? where BillNo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bean.getDischargeID());
			ps.setString(2, bean.getBillingDate());
			ps.setDouble(3, bean.getBedCharges());
			ps.setInt(4, bean.getNoOfDays());
			ps.setDouble(5, bean.getTotalBedCharges());
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
			String sql = "delete from bill_ward where BillNo='" + billNo + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return status;
	}

	public static BillingPatientWardBean exist(int billNo){
		BillingPatientWardBean bean = new BillingPatientWardBean(billNo);
		
		try{
			con=Connector.ConnectDB();
			String sql="select BillNo from bill_ward where BillNo='"+billNo+"'";
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
