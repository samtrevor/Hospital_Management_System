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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.AdmitPatientWardBean;
import dao.AdmitPatientWardDao;
import dao.Connector;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class AdmitPatientWard extends JInternalFrame implements ActionListener {

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	JLabel lblAdmitId, lblPatientId, lblPatientName, lblGender, lblBloodGroup, lblDisease, lblAdmitDate, lblWardName,
			lblDoctorId, lblDoctorName, lblRemarks;
	JTextField txtAdmitId, txtPatientId, txtPatientName, txtGender, txtBloodGroup, txtDisease, txtAdmitDate,
			txtDoctorId, txtDoctorName;
	JTextArea txtRemarks;
	JComboBox<String> comboWardName;
	JButton butNew, butSave, butUpdate, butDelete, butGetData, butGo, butGoo;
	JTable table;
	String heading[] = { "Admit ID", "Patient ID", "Disease", "Ward Name", "Admit Date", "Doctor ID", "Remarks" };

	public AdmitPatientWard() {
		super("Admit Patient", false, true, false, false);
		setSize(d.width, 700);
		setLocation(0, 0);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.WEST);
		add(scroll(), BorderLayout.CENTER);
		
		getWardName();

		setVisible(true);
	}

	JPanel admitWardInfo() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblAdmitId = new JLabel("Admit ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblAdmitId, c);

		txtAdmitId = new JTextField(20);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		txtAdmitId.setEditable(false);
		pan.add(txtAdmitId, c);

		lblPatientId = new JLabel("Patient ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblPatientId, c);

		txtPatientId = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		txtPatientId.setEditable(false);
		pan.add(txtPatientId, c);

		butGo = new JButton(">");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		butGo.setEnabled(false);
		butGo.addActionListener(this);
		pan.add(butGo, c);

		lblPatientName = new JLabel("Patient Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblPatientName, c);

		txtPatientName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		txtPatientName.setEnabled(false);
		pan.add(txtPatientName, c);

		lblGender = new JLabel("Gender");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblGender, c);

		txtGender = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		txtGender.setEnabled(false);
		pan.add(txtGender, c);

		lblBloodGroup = new JLabel("Blood Group");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblBloodGroup, c);

		txtBloodGroup = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		txtBloodGroup.setEnabled(false);
		pan.add(txtBloodGroup, c);

		lblDisease = new JLabel("Disease");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblDisease, c);

		txtDisease = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		txtDisease.setEditable(false);
		pan.add(txtDisease, c);

		lblAdmitDate = new JLabel("Admit Date");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblAdmitDate, c);

		txtAdmitDate = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		txtAdmitDate.setEditable(false);
		pan.add(txtAdmitDate, c);

		lblWardName = new JLabel("Ward Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblWardName, c);

		comboWardName = new JComboBox<>();
		comboWardName.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 10, 10);
		comboWardName.setEnabled(false);
		pan.add(comboWardName, c);

		lblDoctorId = new JLabel("Doctor ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblDoctorId, c);

		txtDoctorId = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 8;
		c.insets = new Insets(10, 10, 10, 10);
		txtDoctorId.setEditable(false);
		pan.add(txtDoctorId, c);

		butGoo = new JButton(">");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 8;
		c.insets = new Insets(10, 10, 10, 10);
		butGoo.setEnabled(false);
		butGoo.addActionListener(this);
		pan.add(butGoo, c);

		lblDoctorName = new JLabel("Doctor Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 9;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblDoctorName, c);

		txtDoctorName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 9;
		c.insets = new Insets(10, 10, 10, 10);
		txtDoctorName.setEnabled(false);
		pan.add(txtDoctorName, c);

		lblRemarks = new JLabel("Remarks");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 10;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblRemarks, c);

		txtRemarks = new JTextArea();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 0;
		c.weighty = 2;
		c.insets = new Insets(10, 10, 10, 10);
		txtRemarks.setEditable(false);
		JScrollPane pane = new JScrollPane(txtRemarks);
		pan.add(pane, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "Patient Admit Info", HIDE_ON_CLOSE, DISPOSE_ON_CLOSE,
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

	JPanel panAdd() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout(40, 20));

		pan.add(admitWardInfo(), BorderLayout.CENTER);
		pan.add(panSpace(), BorderLayout.EAST);

		pan.setBorder(BorderFactory.createRaisedBevelBorder());

		return pan;
	}

	JPanel panSpace() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());

		pan.add(buttons(), BorderLayout.NORTH);

		return pan;
	}

	JScrollPane scroll() {
		JScrollPane scroll = new JScrollPane();
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, heading) {
			boolean[] canEdit = new boolean[] { false, true, true, true, true, true, true };

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
				String sql = "select * from admitpatient_ward where AdmitID='" + tableClick + "'";
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						enabletxt();
						butGo.setEnabled(true);
						butGoo.setEnabled(true);
						int id = rs.getInt("AdmitID");
						String admitId = Integer.toString(id);
						txtAdmitId.setText(admitId);

						String patientId = rs.getString("PatientID");
						txtPatientId.setText(patientId);

						String disease = rs.getString("Disease");
						txtDisease.setText(disease);

						String wardName = rs.getString("Wardname");
						comboWardName.setSelectedItem(wardName);

						String date = rs.getString("AdmitDate");
						txtAdmitDate.setText(date);

						String doctorId = rs.getString("DoctorID");
						txtDoctorId.setText(doctorId);

						String remarks = rs.getString("AP_Remarks");
						txtRemarks.setText(remarks);

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
			if (txtAdmitId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Admit ID !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtAdmitId.requestFocus();
				return;
			} else if (txtPatientId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Patient ID !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtPatientId.requestFocus();
				return;
			} else if (txtDisease.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Disease !", "Error", JOptionPane.INFORMATION_MESSAGE);
				txtDisease.requestFocus();
				return;
			} else if (txtAdmitDate.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Admit Date !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtAdmitDate.requestFocus();
				return;
			} else if (comboWardName.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Select Room No. !", "Error", JOptionPane.INFORMATION_MESSAGE);
				comboWardName.requestFocus();
				return;
			} else if (txtDoctorId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Doctor ID !", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				txtDoctorId.requestFocus();
				return;
			} else if (txtRemarks.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Remarks !", "Error", JOptionPane.INFORMATION_MESSAGE);
				txtRemarks.requestFocus();
				return;
			}

			int id = Integer.parseInt(txtAdmitId.getText());
			String patientId = txtPatientId.getText();
			String disease = txtDisease.getText();
			String date = txtAdmitDate.getText();
			String ward = String.valueOf(comboWardName.getSelectedItem());
			String doctorId = txtDoctorId.getText();
			String remarks = txtRemarks.getText();

			AdmitPatientWardBean b = AdmitPatientWardDao.exist(id);
			if (b.equals(id)) {
				return;
			}
			
			AdmitPatientWardBean bean = new AdmitPatientWardBean(id, patientId, disease, date, ward, doctorId, remarks);
			int status = AdmitPatientWardDao.save(bean);
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
			int id = Integer.parseInt(txtAdmitId.getText());
			String patientId = txtPatientId.getText();
			String disease = txtDisease.getText();
			String date = txtAdmitDate.getText();
			String ward = String.valueOf(comboWardName.getSelectedItem());
			String doctorId = txtDoctorId.getText();
			String remarks = txtRemarks.getText();

			AdmitPatientWardBean bean = new AdmitPatientWardBean(id, patientId, disease, date, ward, doctorId, remarks);
			int status = AdmitPatientWardDao.update(bean);
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
				int id = Integer.parseInt(txtAdmitId.getText());
				AdmitPatientWardDao.delete(id);
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

	public void getWardName() {
		try {
			Connection con = Connector.ConnectDB();
			String sql = "select Wardname from ward";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String wardName = rs.getString("Wardname");
				comboWardName.addItem(wardName);
				comboWardName.setSelectedIndex(-1);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	void get_Data() {
		Connection con = Connector.ConnectDB();
		String sql = "select * from admitpatient_ward";
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
		txtAdmitId.setText("");
		txtAdmitId.requestFocus();
		txtPatientId.setText("");
		txtPatientName.setText("");
		txtGender.setText("");
		txtBloodGroup.setText("");
		txtDisease.setText("");
		txtAdmitDate.setText("");
		comboWardName.setSelectedIndex(-1);
		txtDoctorId.setText("");
		txtDoctorName.setText("");
		txtRemarks.setText("");
		get_Data();
	}

	void enabletxt() {
		txtAdmitId.setEditable(true);
		txtPatientId.setEditable(true);
		butGo.setEnabled(true);
		txtDisease.setEditable(true);
		txtAdmitDate.setEditable(true);
		comboWardName.setEnabled(true);
		txtDoctorId.setEditable(true);
		butGoo.setEnabled(true);
		txtRemarks.setEditable(true);
	}

	void disabletxt() {
		txtAdmitId.setEditable(false);
		txtPatientId.setEditable(false);
		butGo.setEnabled(false);
		txtDisease.setEditable(false);
		txtAdmitDate.setEditable(false);
		comboWardName.setEnabled(false);
		txtDoctorId.setEditable(false);
		butGo.setEnabled(false);
		txtRemarks.setEditable(false);
	}
}
