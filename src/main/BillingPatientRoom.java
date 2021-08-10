package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bean.BillingPatientRoomBean;
import dao.BillingPatientRoomDao;
import dao.Connector;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class BillingPatientRoom extends JInternalFrame implements ActionListener {

	JLabel lblBillNo, lblDischargeId, lblAdmitId, lblPatientId, lblPatientName, lblGender, lblBloodGroup, lblDisease,
			lblAdmitDate, lblRoomNo, lblDoctorId, lblDoctorName, lblDischargeDate, lblRoomCharges, lblNoOfDays,
			lblTotalRoomCharges, lblServiceCharges, lblBillingDate, lblPaymentMode, lblPaymentModeDetails,
			lblTotalCharges, lblTotalPaid, lblDueCharges, lblServiceId;
	JTextField txtBillNo, txtDischargeId, txtAdmitId, txtPatientId, txtPatientName, txtGender, txtBloodGroup,
			txtDisease, txtAdmitDate, txtRoomNo, txtDoctorId, txtDoctorName, txtDischargeDate, txtRoomCharges,
			txtNoOfDays, txtTotalRoomCharges, txtServiceCharges, txtBillingDate, txtPaymentModeDetails, txtTotalCharges,
			txtTotalPaid, txtDueCharges, txtServiceId;
	JComboBox<String> comboPaymentMode;
	JButton butNew, butSave, butUpdate, butDelete, butGetData, butGo, butGoo, butSearch, butsearchDID,
			butGetServiceCharges, butCalc;
	JTable table;
	String[] headings = { "Bill No", "Discharge ID", "Billing Date", "No.of Days", "Room Charges", "TotalRoomCharges",
			"ServiceCharges", "TotalCharges", "PaymentMode", "PaymentModeDetails", "ChargesPaid", "DueCharges" };
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	public BillingPatientRoom() {
		super("Billing", false, true, true, false);
		setSize(d.width, 700);
		setLocation(0, 0);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.WEST);
		add(scroll(), BorderLayout.CENTER);

		setVisible(true);
	}

	JPanel patientInfo() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblPatientId = new JLabel("Patient ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblPatientId, c);

		txtPatientId = new JTextField(20);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		txtPatientId.setEditable(false);
		pan.add(txtPatientId, c);

		butGo = new JButton(">");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		butGo.setEnabled(false);
		butGo.addActionListener(this);
		pan.add(butGo, c);

		lblPatientName = new JLabel("Patient Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblPatientName, c);

		txtPatientName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		txtPatientName.setEnabled(false);
		pan.add(txtPatientName, c);

		lblGender = new JLabel("Gender");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblGender, c);

		txtGender = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		txtGender.setEnabled(false);
		pan.add(txtGender, c);

		lblBloodGroup = new JLabel("Blood Group");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblBloodGroup, c);

		txtBloodGroup = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		txtBloodGroup.setEnabled(false);
		pan.add(txtBloodGroup, c);

		lblDisease = new JLabel("Disease");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblDisease, c);

		txtDisease = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		txtDisease.setEnabled(false);
		pan.add(txtDisease, c);

		lblAdmitDate = new JLabel("Admit Date");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblAdmitDate, c);

		txtAdmitDate = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 5);
		txtAdmitDate.setEnabled(false);
		pan.add(txtAdmitDate, c);

		lblRoomNo = new JLabel("Room No.");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblRoomNo, c);

		txtRoomNo = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(5, 5, 5, 5);
		txtRoomNo.setEnabled(false);
		pan.add(txtRoomNo, c);

		lblDoctorId = new JLabel("Doctor ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblDoctorId, c);

		txtDoctorId = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 7;
		c.insets = new Insets(5, 5, 5, 5);
		txtDoctorId.setEditable(false);
		pan.add(txtDoctorId, c);

		butGoo = new JButton(">");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 7;
		c.insets = new Insets(5, 5, 5, 5);
		butGoo.setEnabled(false);
		butGoo.addActionListener(this);
		pan.add(butGoo, c);

		lblDoctorName = new JLabel("Doctor Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblDoctorName, c);

		txtDoctorName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 8;
		c.insets = new Insets(5, 5, 5, 5);
		txtDoctorName.setEnabled(false);
		pan.add(txtDoctorName, c);

		lblDischargeDate = new JLabel("Discharge Date");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 9;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblDischargeDate, c);

		txtDischargeDate = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 9;
		c.insets = new Insets(5, 5, 5, 5);
		txtDischargeDate.setEnabled(false);
		pan.add(txtDischargeDate, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "Patient Info", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Cambria", Font.PLAIN, 15), Color.BLACK));

		return pan;
	}

	JPanel paymentDetails() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblRoomCharges = new JLabel("Room Charges");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblRoomCharges, c);

		txtRoomCharges = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		txtRoomCharges.setEnabled(false);
		pan.add(txtRoomCharges, c);

		lblNoOfDays = new JLabel("No. Of Days");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblNoOfDays, c);

		txtNoOfDays = new JTextField(5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		txtNoOfDays.setEditable(false);
		pan.add(txtNoOfDays, c);

		lblTotalRoomCharges = new JLabel("Total Room Charges");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblTotalRoomCharges, c);

		txtTotalRoomCharges = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 5;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		txtTotalRoomCharges.setEnabled(false);
		pan.add(txtTotalRoomCharges, c);

		lblServiceCharges = new JLabel("Service Charges");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblServiceCharges, c);

		txtServiceCharges = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		txtServiceCharges.setEnabled(false);
		pan.add(txtServiceCharges, c);

		lblBillingDate = new JLabel("Billing Date");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblBillingDate, c);

		txtBillingDate = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		txtBillingDate.setEditable(false);
		txtBillingDate.setToolTipText("DD/MM/YYYY");
		pan.add(txtBillingDate, c);

		lblPaymentMode = new JLabel("Payment Mode");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblPaymentMode, c);

		comboPaymentMode = new JComboBox<>(
				new String[] { "by Cash", "by Mpesa", "by Check", "by Credit Card", "by Debit Card" });
		comboPaymentMode.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		comboPaymentMode.setEnabled(false);
		pan.add(comboPaymentMode, c);

		lblPaymentModeDetails = new JLabel("Payment Mode Details");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblPaymentModeDetails, c);

		txtPaymentModeDetails = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		txtPaymentModeDetails.setEditable(false);
		pan.add(txtPaymentModeDetails, c);

		butCalc = new JButton("Calculate");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 5;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		butCalc.setEnabled(false);
		butCalc.addActionListener(this);
		pan.add(butCalc, c);

		lblTotalCharges = new JLabel("Total Charges");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblTotalCharges, c);

		txtTotalCharges = new JTextField(5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		txtTotalCharges.setEnabled(false);
		pan.add(txtTotalCharges, c);

		lblTotalPaid = new JLabel("Total Paid");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblTotalPaid, c);

		txtTotalPaid = new JTextField(5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		txtTotalPaid.setEditable(false);
		pan.add(txtTotalPaid, c);

		lblDueCharges = new JLabel("Due Charges");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblDueCharges, c);

		txtDueCharges = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 5;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		txtDueCharges.setEnabled(false);
		pan.add(txtDueCharges, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "Payment Details", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Cambria", Font.PLAIN, 15), Color.BLACK));

		return pan;
	}

	JPanel buttons() {
		JPanel pan = new JPanel();
		Box b = Box.createVerticalBox();

		b.add(Box.createVerticalStrut(10));
		butNew = new JButton("New");
		butNew.addActionListener(this);
		b.add(butNew);

		b.add(Box.createVerticalStrut(10));
		butSave = new JButton("Save");
		butSave.addActionListener(this);
		butSave.setEnabled(false);
		b.add(butSave);

		b.add(Box.createVerticalStrut(10));
		butUpdate = new JButton("Update");
		butUpdate.setEnabled(false);
		butUpdate.addActionListener(this);
		b.add(butUpdate);

		b.add(Box.createVerticalStrut(10));
		butDelete = new JButton("Delete");
		butDelete.addActionListener(this);
		butDelete.setEnabled(false);
		b.add(butDelete);

		b.add(Box.createVerticalStrut(10));
		butGetData = new JButton("Get Data");
		butGetData.addActionListener(this);
		b.add(butGetData);

		pan.setBorder(BorderFactory.createTitledBorder(""));

		pan.add(b);

		return pan;
	}

	JPanel search() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblAdmitId = new JLabel("Admit ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblAdmitId, c);

		txtAdmitId = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		txtAdmitId.setEditable(false);
		pan.add(txtAdmitId, c);

		butSearch = new JButton(">");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		butSearch.setEnabled(false);
		butSearch.addActionListener(this);
		pan.add(butSearch, c);

		lblDischargeId = new JLabel("Discharge ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblDischargeId, c);

		txtDischargeId = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		txtDischargeId.setEditable(false);
		pan.add(txtDischargeId, c);

		butsearchDID = new JButton(">");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		butsearchDID.setEnabled(false);
		butsearchDID.addActionListener(this);
		pan.add(butsearchDID, c);

		lblServiceId = new JLabel("Service ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblServiceId, c);

		txtServiceId = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		txtServiceId.setEditable(false);
		pan.add(txtServiceId, c);

		butGetServiceCharges = new JButton(">");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		butGetServiceCharges.setEnabled(false);
		butGetServiceCharges.addActionListener(this);
		pan.add(butGetServiceCharges, c);

		lblBillNo = new JLabel("Bill No.");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		pan.add(lblBillNo, c);

		txtBillNo = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5);
		txtBillNo.setEditable(false);
		pan.add(txtBillNo, c);

		return pan;

	}

	JPanel panSpace() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());

		pan.add(buttons(), BorderLayout.NORTH);
		pan.add(search(), BorderLayout.SOUTH);

		return pan;
	}

	JPanel panAdd() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout(40, 20));

		pan.add(patientInfo(), BorderLayout.CENTER);
		pan.add(panSpace(), BorderLayout.EAST);
		pan.add(paymentDetails(), BorderLayout.SOUTH);

		pan.setBorder(BorderFactory.createRaisedBevelBorder());

		return pan;
	}

	JScrollPane scroll() {
		JScrollPane scroll = new JScrollPane();
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, headings) {
			boolean[] canEdit = new boolean[] { false, true, true, true, true, true, true, true, true, true, true,
					true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				Connection con = Connector.ConnectDB();
				int row = table.getSelectedRow();
				String tableClick = table.getModel().getValueAt(row, 0).toString();
				String sql = "select * from bill_room where BillNo='" + tableClick + "'";
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						enabletxt();
						butGo.setEnabled(true);
						butGoo.setEnabled(true);

						int billNo = rs.getInt("BillNo");
						String bill = Integer.toString(billNo);
						txtBillNo.setText(bill);

						int dischargeID = rs.getInt("DischargeID");
						String discharge = Integer.toString(dischargeID);
						txtDischargeId.setText(discharge);

						String billingDate = rs.getString("BillingDate");
						txtBillingDate.setText(billingDate);

						int noOfDays = rs.getInt("NoOfDays");
						String days = Integer.toString(noOfDays);
						txtNoOfDays.setText(days);

						double roomCharges = rs.getDouble("RoomCharges");
						String rCharges = Double.toString(roomCharges);
						txtRoomCharges.setText(rCharges);

						double totalRoomCharges = rs.getDouble("TotalRoomCharges");
						String tRCharges = Double.toString(totalRoomCharges);
						txtTotalRoomCharges.setText(tRCharges);

						double serviceCharges = rs.getDouble("ServiceCharges");
						String sCharges = Double.toString(serviceCharges);
						txtServiceCharges.setText(sCharges);

						double totalCharges = rs.getDouble("TotalCharges");
						String tCharges = Double.toString(totalCharges);
						txtTotalCharges.setText(tCharges);

						String paymentMode = rs.getString("PaymentMode");
						comboPaymentMode.setSelectedItem(paymentMode);

						String paymentModeDetails = rs.getString("PaymentModeDetails");
						txtPaymentModeDetails.setText(paymentModeDetails);

						double chargesPaid = rs.getDouble("ChargesPaid");
						String cPaid = Double.toString(chargesPaid);
						txtTotalPaid.setText(cPaid);

						double dueCharges = rs.getDouble("DueCharges");
						String due = Double.toString(dueCharges);
						txtDueCharges.setText(due);

						butUpdate.setEnabled(true);
						butDelete.setEnabled(true);
						butSave.setEnabled(false);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		scroll.setViewportView(table);
		scroll.setBorder(BorderFactory.createTitledBorder(""));

		return scroll;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butNew) {
			reset();
			enabletxt();
			butSave.setEnabled(true);
			butUpdate.setEnabled(false);
			butDelete.setEnabled(false);

		} else if (e.getSource() == butSave) {
			if (txtPatientId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Patient ID !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtPatientId.requestFocus();
				return;
			} else if (txtAdmitId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Admit ID !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtAdmitId.requestFocus();
				return;
			} else if (txtDoctorId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Doctor ID !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtDoctorId.requestFocus();
				return;
			} else if (txtBillNo.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Bill No !", "Error", JOptionPane.INFORMATION_MESSAGE);
				txtBillNo.requestFocus();
				return;
			} else if (txtDischargeId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Discharge ID !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtDischargeId.requestFocus();
				return;
			} else if (txtNoOfDays.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter No of Days !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtNoOfDays.requestFocus();
				return;
			} else if (txtBillingDate.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Billing date !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtBillingDate.requestFocus();
				return;
			} else if (comboPaymentMode.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Please select Payment Mode !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				comboPaymentMode.requestFocus();
				return;
			} else if (txtPaymentModeDetails.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Payment Mode Details !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtPaymentModeDetails.requestFocus();
				return;
			} else if (txtTotalPaid.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Total Paid !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtTotalPaid.requestFocus();
				return;
			}

			int billNo = Integer.parseInt(txtBillNo.getText());
			int dischargeId = Integer.parseInt(txtDischargeId.getText());
			String billingDate = txtBillingDate.getText();
			int noOfDays = Integer.parseInt(txtNoOfDays.getText());
			double roomCharges = Double.parseDouble(txtRoomCharges.getText());
			double totalRoomCharges = Double.parseDouble(txtTotalRoomCharges.getText());
			double serviceCharges = Double.parseDouble(txtServiceCharges.getText());
			double totalCharges = Double.parseDouble(txtTotalCharges.getText());
			String paymentMode = String.valueOf(comboPaymentMode.getSelectedItem());
			String paymentModeDetails = txtPaymentModeDetails.getText();
			double chargesPaid = Double.parseDouble(txtTotalPaid.getText());
			double dueCharges = Double.parseDouble(txtDueCharges.getText());

			BillingPatientRoomBean b = BillingPatientRoomDao.exist(billNo);
			if (b.equals(billNo)) {
				return;
			}

			BillingPatientRoomBean bean = new BillingPatientRoomBean(billNo, dischargeId, billingDate, noOfDays,
					roomCharges, totalRoomCharges, serviceCharges, totalCharges, paymentMode, paymentModeDetails,
					chargesPaid, dueCharges);
			int status = BillingPatientRoomDao.save(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Saved Successfully !", "", JOptionPane.INFORMATION_MESSAGE);
				butSave.setEnabled(false);
				reset();
				disabletxt();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry...Unable to save record !", "Error",
						JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butUpdate) {
			int billNo = Integer.parseInt(txtBillNo.getText());
			int dischargeId = Integer.parseInt(txtDischargeId.getText());
			String billingDate = txtBillingDate.getText();
			int noOfDays = Integer.parseInt(txtNoOfDays.getText());
			double roomCharges = Double.parseDouble(txtRoomCharges.getText());
			double totalRoomCharges = Double.parseDouble(txtTotalRoomCharges.getText());
			double serviceCharges = Double.parseDouble(txtServiceCharges.getText());
			double totalCharges = Double.parseDouble(txtTotalCharges.getText());
			String paymentMode = String.valueOf(comboPaymentMode.getSelectedItem());
			String paymentModeDetails = txtPaymentModeDetails.getText();
			double chargesPaid = Double.parseDouble(txtTotalPaid.getText());
			double dueCharges = Double.parseDouble(txtDueCharges.getText());

			BillingPatientRoomBean bean = new BillingPatientRoomBean(billNo, dischargeId, billingDate, noOfDays,
					roomCharges, totalRoomCharges, serviceCharges, totalCharges, paymentMode, paymentModeDetails,
					chargesPaid, dueCharges);
			int status = BillingPatientRoomDao.update(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Updated Successfully", "", JOptionPane.INFORMATION_MESSAGE);
				butUpdate.setEnabled(false);
				butDelete.setEnabled(false);
				reset();
				disabletxt();
			} else {
				JOptionPane.showMessageDialog(this, "Error occured while updating!", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butDelete) {
			int status = JOptionPane.showConfirmDialog(this, "Are you sure want to delete?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (status == 0) {
				int billNo = Integer.parseInt(txtBillNo.getText());
				BillingPatientRoomDao.delete(billNo);
				JOptionPane.showMessageDialog(this, "Record deleted Successfully!", "",
						JOptionPane.INFORMATION_MESSAGE);
				butUpdate.setEnabled(false);
				butDelete.setEnabled(false);
				reset();
				disabletxt();
			}

		} else if (e.getSource() == butGetData) {
			get_Data();

		} else if (e.getSource() == butGo) {
			getPatientData(txtPatientId.getText());

		} else if (e.getSource() == butGoo) {
			getDoctorData(txtDoctorId.getText());

		} else if (e.getSource() == butSearch) {
			int admit = Integer.parseInt(txtAdmitId.getText());
			getRoomNoData(admit);
			getRoomCharges(txtRoomNo.getText());

		} else if (e.getSource() == butsearchDID) {
			int id = Integer.parseInt(txtDischargeId.getText());
			getDischargeData(id);

		} else if (e.getSource() == butGetServiceCharges) {
			int charges = Integer.parseInt(txtServiceId.getText());
			getServiceCharges(charges);
		} else if (e.getSource() == butCalc) {
			totalRoomCharges();
			totalCharges();
			dueCharges();
		}
	}

	public double dueCharges() {
		double dueCharges = 0;
		double totalCharges = Double.parseDouble(txtTotalCharges.getText());
		double totalPaid = Double.parseDouble(txtTotalPaid.getText());
		dueCharges = totalCharges - totalPaid;
		if (totalPaid <= totalCharges) {
			String costRemaining = Double.toString(dueCharges);
			txtDueCharges.setText(costRemaining);
		} else {
			double totalBalance = totalPaid - totalCharges;
			String balance = Double.toString(totalBalance);
			txtDueCharges.setText("0.0");
			JOptionPane.showMessageDialog(this, "Balance = " + balance, "", JOptionPane.INFORMATION_MESSAGE);
		}
		return dueCharges;
	}

	public double totalCharges() {
		double totalCharges = 0;
		double totalRoomCharges = Double.parseDouble(txtTotalRoomCharges.getText());
		double serviceCharges = Double.parseDouble(txtServiceCharges.getText());
		totalCharges = totalRoomCharges + serviceCharges;
		String totalCost = Double.toString(totalCharges);
		txtTotalCharges.setText(totalCost);

		return totalCharges;
	}

	public double totalRoomCharges() {
		double totalRoomCharges = 0;
		double days = Double.parseDouble(txtNoOfDays.getText());
		double roomCharges = Double.parseDouble(txtRoomCharges.getText());
		totalRoomCharges = roomCharges * days;
		String cost = Double.toString(totalRoomCharges);
		txtTotalRoomCharges.setText(cost);

		return totalRoomCharges;
	}

	public void getServiceCharges(int charges) {
		Connection con = Connector.ConnectDB();
		String sql = "select ServiceCharges from services where ServiceID='" + charges + "'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String charge = Integer.toString(rs.getInt("ServiceCharges"));
				txtServiceCharges.setText(charge);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	public void getRoomCharges(String RoomNo) {
		Connection con = Connector.ConnectDB();
		String sql = "select RoomCharges from room where RoomNo='" + RoomNo + "'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String charge = Integer.toString(rs.getInt("RoomCharges"));
				txtRoomCharges.setText(charge);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	public void getDischargeData(int dischargeId) {
		Connection con = Connector.ConnectDB();
		String sql = "select DischargeDate from dischargepatient_room where ID='" + dischargeId + "'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String date = rs.getString("DischargeDate");
				txtDischargeDate.setText(date);
			}
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	public void getRoomNoData(int id) {
		try {
			Connection con = Connector.ConnectDB();
			String sql = "select Disease,RoomNo,AdmitDate from admitpatient_room where AdmitID='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String disease = rs.getString("Disease");
				txtDisease.setText(disease);
				String room = rs.getString("RoomNo");
				txtRoomNo.setText(room);
				String date = rs.getString("AdmitDate");
				txtAdmitDate.setText(date);
			}
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	public void getDoctorData(String DoctorId) {
		try {
			Connection con = Connector.ConnectDB();
			String sql = "select DoctorName from doctor where DoctorID='" + DoctorId + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("DoctorName");
				txtDoctorName.setText(name);
			}
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	public void getPatientData(String patientId) {
		try {
			Connection con = Connector.ConnectDB();
			String sql = "select Patientname,Gen,BG from patientregistration where PatientID='" + patientId + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("Patientname");
				txtPatientName.setText(name);
				String gender = rs.getString("Gen");
				txtGender.setText(gender);
				String blood = rs.getString("BG");
				txtBloodGroup.setText(blood);
			}
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void get_Data() {
		Connection con = Connector.ConnectDB();
		String sql = "select * from bill_room";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	void reset() {
		txtDischargeId.setText("");
		txtAdmitId.setText("");
		txtAdmitId.requestFocus();
		txtBillNo.setText("");
		txtPatientId.setText("");
		txtPatientName.setText("");
		txtGender.setText("");
		txtBloodGroup.setText("");
		txtDisease.setText("");
		txtAdmitDate.setText("");
		txtRoomNo.setText("");
		txtDoctorId.setText("");
		txtDoctorName.setText("");
		txtDischargeDate.setText("");
		txtRoomCharges.setText("");
		txtNoOfDays.setText("");
		txtTotalRoomCharges.setText("");
		txtServiceCharges.setText("");
		txtBillingDate.setText("");
		comboPaymentMode.setSelectedIndex(-1);
		txtPaymentModeDetails.setText("");
		txtTotalCharges.setText("");
		txtTotalPaid.setText("");
		txtDueCharges.setText("");
		txtServiceId.setText("");
		get_Data();

	}

	void enabletxt() {
		txtDischargeId.setEditable(true);
		butsearchDID.setEnabled(true);
		txtAdmitId.setEditable(true);
		txtServiceId.setEditable(true);
		txtBillNo.setEditable(true);
		butSearch.setEnabled(true);
		txtPatientId.setEditable(true);
		butGo.setEnabled(true);
		txtDoctorId.setEditable(true);
		butGoo.setEnabled(true);
		txtNoOfDays.setEditable(true);
		txtBillingDate.setEditable(true);
		comboPaymentMode.setEnabled(true);
		txtPaymentModeDetails.setEditable(true);
		txtTotalPaid.setEditable(true);
		butGetServiceCharges.setEnabled(true);
		butCalc.setEnabled(true);

	}

	void disabletxt() {
		txtDischargeId.setEditable(false);
		butsearchDID.setEnabled(false);
		txtAdmitId.setEditable(false);
		txtBillNo.setEditable(false);
		butSearch.setEnabled(false);
		txtPatientId.setEditable(false);
		butGo.setEnabled(false);
		txtDoctorId.setEditable(false);
		butGoo.setEnabled(false);
		txtNoOfDays.setEditable(false);
		txtBillingDate.setEditable(false);
		comboPaymentMode.setEnabled(false);
		txtPaymentModeDetails.setEditable(false);
		txtTotalPaid.setEditable(false);
		butGetServiceCharges.setEnabled(false);
		butCalc.setEnabled(false);
		txtServiceId.setEditable(false);

	}

}
