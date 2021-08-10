package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.DoctorBean;
import dao.Connector;
import dao.DoctorDao;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class Doctor extends JInternalFrame implements ActionListener {

	JLabel lblId, lblName, lblFatherName, lblAddress, lblContactNo, lblEmail, lblQualifications, lblSpecialization,
			lblGender, lblBloodGroup, lblDateofJoining;
	JTextField txtId, txtName, txtFatherName, txtAddress, txtContactNo, txtEmail, txtQualifications, txtSpecialization,
			txtDateofJoining;
	JComboBox<String> comboGender, comboBloodGroup;
	JButton butNew, butSave, butUpdate, butDelete, butGetData;
	JTable table;
	String[] headings = { "ID", "Name", "Father's Name", "Address", "Contact No", "Email", "Qualifications",
			"Specialization", "Gender", "Blood Group", "Joining Date" };

	public Doctor() {
		super("Doctor", true, true, true, false);
		setSize(1300, 600);
		setLocation(25, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.WEST);
		add(scroll(), BorderLayout.CENTER);

		setVisible(true);
	}

	JPanel docDetails() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblId = new JLabel("ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblId, c);

		txtId = new JTextField(15);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		txtId.addActionListener(this);
		txtId.setEditable(false);
		pan.add(txtId, c);

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

		lblQualifications = new JLabel("Qualifications");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblQualifications, c);

		txtQualifications = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		txtQualifications.addActionListener(this);
		txtQualifications.setEditable(false);
		pan.add(txtQualifications, c);

		lblSpecialization = new JLabel("Specialization");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblSpecialization, c);

		txtSpecialization = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 10, 10);
		txtSpecialization.addActionListener(this);
		txtSpecialization.setEditable(false);
		pan.add(txtSpecialization, c);

		lblGender = new JLabel("Gender");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblGender, c);

		comboGender = new JComboBox<String>();
		comboGender.setModel(new DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
		comboGender.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 8;
		c.insets = new Insets(10, 10, 10, 10);
		comboGender.addActionListener(this);
		comboGender.setEnabled(false);
		pan.add(comboGender, c);

		lblBloodGroup = new JLabel("Blood Group");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 9;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblBloodGroup, c);

		comboBloodGroup = new JComboBox<String>();
		comboBloodGroup.setModel(
				new DefaultComboBoxModel<>(new String[] { "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-" }));
		comboBloodGroup.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 9;
		c.insets = new Insets(10, 10, 10, 10);
		comboBloodGroup.addActionListener(this);
		comboBloodGroup.setEnabled(false);
		pan.add(comboBloodGroup, c);

		lblDateofJoining = new JLabel("Date of Joining");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 10;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblDateofJoining, c);

		txtDateofJoining = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 10;
		c.insets = new Insets(10, 10, 10, 10);
		txtDateofJoining.addActionListener(this);
		txtDateofJoining.setEditable(false);
		txtDateofJoining.setToolTipText("DD/MM/YYYY");
		pan.add(txtDateofJoining, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "Doctors Details", HIDE_ON_CLOSE, DISPOSE_ON_CLOSE,
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

		pan.add(docDetails(), BorderLayout.CENTER);
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
			boolean[] canEdit = new boolean[] { false, true, true, true, true, true, true, true, true, true, true };

			public boolean isCellEditable(int rowIdex, int columnIndex) {
				return canEdit[columnIndex];
			}

		});
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				Connection con = Connector.ConnectDB();
				int row = table.getSelectedRow();
				String table_clicked = table.getModel().getValueAt(row, 0).toString();
				String sql = "select * from doctor where DoctorID='" + table_clicked + "'";
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					if (rs.next()) {
						enabletxt();
						String id = rs.getString("DoctorID");
						txtId.setText(id);

						String name = rs.getString("DoctorName");
						txtName.setText(name);

						String father = rs.getString("FatherName");
						txtFatherName.setText(father);

						String address = rs.getString("Address");
						txtAddress.setText(address);

						String contact = rs.getString("ContactNo");
						txtContactNo.setText(contact);

						String email = rs.getString("Email");
						txtEmail.setText(email);

						String qualification = rs.getString("Qualifications");
						txtQualifications.setText(qualification);

						String specialization = rs.getString("Specialization");
						txtSpecialization.setText(specialization);

						String gender = String.valueOf(rs.getString("Gender"));
						comboGender.setSelectedItem(gender);

						String blood = String.valueOf(rs.getString("Bloodgroup"));
						comboBloodGroup.setSelectedItem(blood);

						String date = rs.getString("DateOfJoining");
						txtDateofJoining.setText(date);

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
			reset();
			butSave.setEnabled(true);
			butUpdate.setEnabled(false);
			butDelete.setEnabled(false);

		} else if (e.getSource() == butSave) {
			String id = txtId.getText();
			String name = txtName.getText();
			String father = txtFatherName.getText();
			String address = txtAddress.getText();
			String contact = txtContactNo.getText();
			String email = txtEmail.getText();
			String qualification = txtQualifications.getText();
			String specialization = txtSpecialization.getText();
			String gender = String.valueOf(comboGender.getSelectedItem());
			String blood = String.valueOf(comboBloodGroup.getSelectedItem());
			String date = txtDateofJoining.getText();

			if (id.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter id!", "Error", JOptionPane.ERROR_MESSAGE);
				txtId.requestFocus();
				return;
			} else if (name.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter name!", "Error", JOptionPane.ERROR_MESSAGE);
				txtName.requestFocus();
				return;
			} else if (father.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter father's name!", "Error", JOptionPane.ERROR_MESSAGE);
				txtFatherName.requestFocus();
				return;
			} else if (address.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter address!", "Error", JOptionPane.ERROR_MESSAGE);
				txtAddress.requestFocus();
				return;
			} else if (contact.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter contact No.!", "Error", JOptionPane.ERROR_MESSAGE);
				txtContactNo.requestFocus();
				return;
			} else if (email.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter email!", "Error", JOptionPane.ERROR_MESSAGE);
				txtEmail.requestFocus();
				return;
			} else if (qualification.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter qualifications!", "Error", JOptionPane.ERROR_MESSAGE);
				txtQualifications.requestFocus();
				return;
			} else if (specialization.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter specialization!", "Error", JOptionPane.ERROR_MESSAGE);
				txtSpecialization.requestFocus();
				return;
			} else if (gender.equals("")) {
				JOptionPane.showMessageDialog(this, "Please select gender!", "Error", JOptionPane.ERROR_MESSAGE);
				comboGender.requestFocus();
				return;
			} else if (blood.equals("")) {
				JOptionPane.showMessageDialog(this, "Please blood type!", "Error", JOptionPane.ERROR_MESSAGE);
				comboBloodGroup.requestFocus();
				return;
			} else if (date.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter date of joining!", "Error",
						JOptionPane.ERROR_MESSAGE);
				txtDateofJoining.requestFocus();
				return;
			}

			DoctorBean b = DoctorDao.exist(id);
			if (b.equals(id)) {
				return;
			}

			DoctorBean bean = new DoctorBean(id, name, father, address, contact, email, qualification, specialization,
					gender, blood, date);
			int status = DoctorDao.save(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Saved Successfully!", "", JOptionPane.INFORMATION_MESSAGE);
				butSave.setEnabled(false);
				reset();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry... unable to save record!", "Error",
						JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butUpdate) {
			String id = txtId.getText();
			String name = txtName.getText();
			String father = txtFatherName.getText();
			String address = txtAddress.getText();
			String contact = txtContactNo.getText();
			String email = txtEmail.getText();
			String qualification = txtQualifications.getText();
			String specialization = txtSpecialization.getText();
			String gender = String.valueOf(comboGender.getSelectedItem());
			String blood = String.valueOf(comboBloodGroup.getSelectedItem());
			String date = txtDateofJoining.getText();

			DoctorBean bean = new DoctorBean(id, name, father, address, contact, email, qualification, specialization,
					gender, blood, date);
			int status = DoctorDao.update(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Updated successfully!", "", JOptionPane.INFORMATION_MESSAGE);
				butUpdate.setEnabled(false);
				butDelete.setEnabled(false);
				reset();
				disabletxt();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry... unable to update record!", "Error",
						JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butDelete) {
			int status = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete ?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (status == 0) {
				DoctorDao.delete(txtId.getText());
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
		String sql = "select * from doctor";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	void reset() {
		txtId.setText("");
		txtId.requestFocus();
		txtName.setText("");
		txtFatherName.setText("");
		txtAddress.setText("");
		txtContactNo.setText("");
		txtEmail.setText("");
		txtQualifications.setText("");
		txtSpecialization.setText("");
		comboGender.setSelectedIndex(-1);
		comboBloodGroup.setSelectedIndex(-1);
		txtDateofJoining.setText("");
		get_Data();
	}

	void enabletxt() {
		txtId.setEditable(true);
		txtName.setEditable(true);
		txtFatherName.setEditable(true);
		txtAddress.setEditable(true);
		txtContactNo.setEditable(true);
		txtEmail.setEditable(true);
		txtQualifications.setEditable(true);
		txtSpecialization.setEditable(true);
		comboGender.setEnabled(true);
		comboBloodGroup.setEnabled(true);
		txtDateofJoining.setEditable(true);
	}
	
	void disabletxt() {
		txtId.setEditable(false);
		txtName.setEditable(false);
		txtFatherName.setEditable(false);
		txtAddress.setEditable(false);
		txtContactNo.setEditable(false);
		txtEmail.setEditable(false);
		txtQualifications.setEditable(false);
		txtSpecialization.setEditable(false);
		comboGender.setEnabled(false);
		comboBloodGroup.setEnabled(false);
		txtDateofJoining.setEditable(false);
	}

}
