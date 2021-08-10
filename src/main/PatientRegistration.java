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
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
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

import bean.PatientRegistrationBean;
import dao.Connector;
import dao.PatientRegistrationDao;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class PatientRegistration extends JInternalFrame implements ActionListener {

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	JLabel lblPatientId, lblName, lblFatherName, lblAddress, lblContactNo, lblEmail, lblAge, lblGender, lblBloodGroup,
			lblRemarks;
	JTextField txtPatientId, txtName, txtFatherName, txtAddress, txtContactNo, txtEmail, txtAge;
	JTextArea txtRemarks;
	JComboBox<String> comboGender, comboBloodGroup;
	JButton butNew, butSave, butUpdate, butDelete, butGetData;
	JTable table;
	String[] headings = { "Patient ID", "Name", "Father's Name", "Address", "Contact No", "Email", "Age", "Gender",
			"BloodGroup", "Remarks" };

	public PatientRegistration() {
		super("Patient Registration", false, true, false, false);
		setSize(d.width, 700);
		setLocation(0, 0);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.WEST);
		add(scroll(),BorderLayout.CENTER);
		
		setVisible(true);
	}

	JPanel patientDetails() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblPatientId = new JLabel("Patient ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblPatientId, c);

		txtPatientId = new JTextField(20);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		txtPatientId.addActionListener(this);
		txtPatientId.setEditable(false);
		pan.add(txtPatientId, c);

		lblName = new JLabel("Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblName, c);

		txtName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		txtName.addActionListener(this);
		txtName.setEditable(false);
		pan.add(txtName, c);

		lblFatherName = new JLabel("Father's Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblFatherName, c);

		txtFatherName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		txtFatherName.addActionListener(this);
		txtFatherName.setEditable(false);
		pan.add(txtFatherName, c);

		lblAddress = new JLabel("Address");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblAddress, c);

		txtAddress = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		txtAddress.addActionListener(this);
		txtAddress.setEditable(false);
		pan.add(txtAddress, c);

		lblContactNo = new JLabel("Contact No.");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblContactNo, c);

		txtContactNo = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		txtContactNo.addActionListener(this);
		txtContactNo.setEditable(false);
		pan.add(txtContactNo, c);

		lblEmail = new JLabel("Email");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblEmail, c);

		txtEmail = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		txtEmail.addActionListener(this);
		txtEmail.setEditable(false);
		pan.add(txtEmail, c);

		lblAge = new JLabel("Age");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblAge, c);

		txtAge = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		txtAge.addActionListener(this);
		txtAge.setEditable(false);
		pan.add(txtAge, c);

		lblGender = new JLabel("Gender");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblGender, c);

		comboGender = new JComboBox<>();
		comboGender.setModel(new DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
		comboGender.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 10, 10);
		comboGender.addActionListener(this);
		comboGender.setEnabled(false);
		pan.add(comboGender, c);

		lblBloodGroup = new JLabel("Blood Group");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblBloodGroup, c);

		comboBloodGroup = new JComboBox<>();
		comboBloodGroup.setModel(
				new DefaultComboBoxModel<>(new String[] { "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-" }));
		comboBloodGroup.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 8;
		c.insets = new Insets(10, 10, 10, 10);
		comboBloodGroup.addActionListener(this);
		comboBloodGroup.setEnabled(false);
		pan.add(comboBloodGroup, c);

		lblRemarks = new JLabel("Remarks");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 9;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblRemarks, c);

		txtRemarks = new JTextArea();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 0;
		c.weighty = 2;
		c.insets = new Insets(10, 10, 10, 10);
		txtRemarks.setEditable(false);
		JScrollPane pane = new JScrollPane(txtRemarks);
		pan.add(pane, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "Patient Details", HIDE_ON_CLOSE, DISPOSE_ON_CLOSE,
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

		pan.add(patientDetails(), BorderLayout.CENTER);
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
		table.setModel(new DefaultTableModel(new Object[][] {}, headings) {
			boolean[] canEdit = new boolean[] { false, true, true, true, true, true, true, true, true, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				Connection con = Connector.ConnectDB();
				int row = table.getSelectedRow();
				String table_click = table.getModel().getValueAt(row, 0).toString();
				String sql = "select * from patientregistration where PatientID='" + table_click + "'";
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					if (rs.next()) {
						enabletxt();
						String addId = rs.getString("PatientID");
						txtPatientId.setText(addId);

						String addName = rs.getString("Patientname");
						txtName.setText(addName);

						String addFatherName = rs.getString("Fathername");
						txtFatherName.setText(addFatherName);

						String addAddress = rs.getString("Address");
						txtAddress.setText(addAddress);

						String addContact = rs.getString("ContactNo");
						txtContactNo.setText(addContact);

						String addEmail = rs.getString("Email");
						txtEmail.setText(addEmail);

						int addAge = rs.getInt("Age");
						String age = Integer.toString(addAge);
						txtAge.setText(age);

						String addGender = rs.getString("Gen");
						comboGender.setSelectedItem(addGender);

						String addBloodgroup = rs.getString("BG");
						comboBloodGroup.setSelectedItem(addBloodgroup);

						String addRemarks = rs.getString("Remarks");
						txtRemarks.setText(addRemarks);

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
			enabletxt();
			butSave.setEnabled(true);
			reset();
			butUpdate.setEnabled(false);
			butDelete.setEnabled(false);

		} else if (e.getSource() == butSave) {
			String id = txtPatientId.getText();
			String name = txtName.getText();
			String fathersName = txtFatherName.getText();
			String address = txtAddress.getText();
			String contact = txtContactNo.getText();
			String email = txtEmail.getText();
			int age = Integer.parseInt(txtAge.getText());
			String gender = String.valueOf(comboGender.getSelectedItem());
			String bloodGroup = String.valueOf(comboBloodGroup.getSelectedItem());
			String remarks = txtRemarks.getText();

			if (id.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Patient id!", "Error", JOptionPane.ERROR_MESSAGE);
				txtPatientId.requestFocus();
				return;
			} else if (name.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Name!", "Error", JOptionPane.ERROR_MESSAGE);
				txtName.requestFocus();
				return;
			} else if (fathersName.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter father's name!", "Error", JOptionPane.ERROR_MESSAGE);
				txtFatherName.requestFocus();
				return;
			} else if (address.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Address!", "Error", JOptionPane.ERROR_MESSAGE);
				txtAddress.requestFocus();
				return;
			} else if (contact.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Contact No. !", "Error", JOptionPane.ERROR_MESSAGE);
				txtContactNo.requestFocus();
				return;
			} else if (email.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Email!", "Error", JOptionPane.ERROR_MESSAGE);
				txtEmail.requestFocus();
				return;
			} else if (txtAge.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Age!", "Error", JOptionPane.ERROR_MESSAGE);
				txtAge.requestFocus();
				return;
			} else if (gender.equals("")) {
				JOptionPane.showMessageDialog(this, "Please select gender!", "Error", JOptionPane.ERROR_MESSAGE);
				comboGender.requestFocus();
				return;
			} else if (bloodGroup.equals("")) {
				JOptionPane.showMessageDialog(this, "Please select Blood Group! ", "Error", JOptionPane.ERROR_MESSAGE);
				comboBloodGroup.requestFocus();
				return;
			} else if (remarks.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Remarks", "Error", JOptionPane.ERROR_MESSAGE);
				txtRemarks.requestFocus();
				return;
			}

			PatientRegistrationBean b = PatientRegistrationDao.exist(id);
			if (b.equals(id)) {
				return;
			}

			PatientRegistrationBean bean = new PatientRegistrationBean(id, name, fathersName, address, contact, email,
					age, gender, bloodGroup, remarks);
			int status = PatientRegistrationDao.save(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Saved Successfully!", "", JOptionPane.INFORMATION_MESSAGE);
				reset();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, unable to save record!", "", JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butUpdate) {
			String id = txtPatientId.getText();
			String name = txtName.getText();
			String fathersName = txtFatherName.getText();
			String address = txtAddress.getText();
			String contact = txtContactNo.getText();
			String email = txtEmail.getText();
			int age = Integer.parseInt(txtAge.getText());
			String gender = String.valueOf(comboGender.getSelectedItem());
			String bloodGroup = String.valueOf(comboBloodGroup.getSelectedItem());
			String remarks = txtRemarks.getText();

			PatientRegistrationBean bean = new PatientRegistrationBean(id, name, fathersName, address, contact, email,
					age, gender, bloodGroup, remarks);
			int status = PatientRegistrationDao.update(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Successfully updated!", "", JOptionPane.INFORMATION_MESSAGE);
				butUpdate.setEnabled(false);
				butDelete.setEnabled(false);
				reset();
				disabletxt();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, unable to update record!", "", JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butDelete) {
			int status = JOptionPane.showConfirmDialog(this, "Are you sure want to delete?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (status == 0) {
				PatientRegistrationDao.delete(txtPatientId.getText());
				JOptionPane.showMessageDialog(this, "Successfully deleted!", "Record", JOptionPane.INFORMATION_MESSAGE);
				butUpdate.setEnabled(false);
				butDelete.setEnabled(false);
				reset();
				disabletxt();
			}

		} else if (e.getSource() == butGetData) {
			get_Data();
		}
	}

	void get_Data() {
		Connection con = Connector.ConnectDB();
		String sql = "select * from patientregistration";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}catch(Exception e1){
			JOptionPane.showMessageDialog(this, e1);
		}
	}

	void reset() {
		txtPatientId.setText("");
		txtPatientId.requestFocus();
		txtName.setText("");
		txtFatherName.setText("");
		txtAddress.setText("");
		txtContactNo.setText("");
		txtEmail.setText("");
		txtAge.setText("");
		comboGender.setSelectedIndex(-1);
		comboBloodGroup.setSelectedIndex(-1);
		txtRemarks.setText("");
		get_Data();
	}

	void enabletxt() {
		txtPatientId.setEditable(true);
		txtName.setEditable(true);
		txtFatherName.setEditable(true);
		txtAddress.setEditable(true);
		txtContactNo.setEditable(true);
		txtEmail.setEditable(true);
		txtAge.setEditable(true);
		comboGender.setEnabled(true);
		comboBloodGroup.setEnabled(true);
		txtRemarks.setEditable(true);
	}
	
	void disabletxt() {
		txtPatientId.setEditable(false);
		txtName.setEditable(false);
		txtFatherName.setEditable(false);
		txtAddress.setEditable(false);
		txtContactNo.setEditable(false);
		txtEmail.setEditable(false);
		txtAge.setEditable(false);
		comboGender.setEnabled(false);
		comboBloodGroup.setEnabled(false);
		txtRemarks.setEditable(false);
	}
}
