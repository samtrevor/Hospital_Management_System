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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

import bean.Nurse_WardBoy_Bean;
import dao.Connector;
import dao.Nurse_WardBoy_Dao;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class Nurse_WardBoy extends JInternalFrame implements ActionListener {

	JLabel lblId, lblName, lblCategory, lblAddress, lblContactNo, lblEmailId, lblQualification, lblBloodGroup,
			lblDateOfJoining;
	JTextField txtId, txtName, txtAddress, txtContactNo, txtEmailId, txtQualification, txtDateOfJoining;
	JComboBox<String> comboCategory, comboBloodGroup;
	JButton butNew, butSave, butUpdate, butDelete, butGetData;
	String[] headings = { "Id", "Name", "Category", "Address", "Contact No.", "Email Id", "Qualifications", "Blood Group", "Date Joined" };
	JTable table;

	public Nurse_WardBoy() {
		super("Nurse/WardBoy", true, true, true, false);
		setSize(1300, 600);
		setLocation(25, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.WEST);
		add(scroll(),BorderLayout.CENTER);

		setVisible(true);
	}

	JPanel panDetails() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblId = new JLabel("Id");
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

		lblCategory = new JLabel("Category");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblCategory, c);

		comboCategory = new JComboBox<>();
		comboCategory.setModel(new DefaultComboBoxModel<>(new String[] { "Nurse", "WardBoy" }));
		comboCategory.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		comboCategory.addActionListener(this);
		comboCategory.setEnabled(false);
		pan.add(comboCategory, c);

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

		lblEmailId = new JLabel("Email Id");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblEmailId, c);

		txtEmailId = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 10, 10);
		txtEmailId.addActionListener(this);
		txtEmailId.setEditable(false);
		pan.add(txtEmailId, c);

		lblQualification = new JLabel("Qualifications");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblQualification, c);

		txtQualification = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 10, 10);
		txtQualification.addActionListener(this);
		txtQualification.setEditable(false);
		pan.add(txtQualification, c);

		lblBloodGroup = new JLabel("Blood Group");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblBloodGroup, c);

		comboBloodGroup = new JComboBox<String>();
		comboBloodGroup.setModel(
				new DefaultComboBoxModel<>(new String[] { "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-" }));
		comboBloodGroup.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 10, 10);
		comboBloodGroup.setEnabled(false);
		comboBloodGroup.addActionListener(this);
		pan.add(comboBloodGroup, c);

		lblDateOfJoining = new JLabel("Date Of Joining");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblDateOfJoining, c);

		txtDateOfJoining = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 8;
		c.insets = new Insets(10, 10, 10, 10);
		txtDateOfJoining.addActionListener(this);
		txtDateOfJoining.setEditable(false);
		txtDateOfJoining.setToolTipText("(DD/MM/YYYY)");
		pan.add(txtDateOfJoining, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "Nurse/WardBoy Details", HIDE_ON_CLOSE, DISPOSE_ON_CLOSE,
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

	JPanel panSpace() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());

		pan.add(buttons(), BorderLayout.NORTH);

		return pan;
	}

	JPanel panAdd() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout(40, 20));

		pan.add(panDetails(), BorderLayout.CENTER);
		pan.add(panSpace(), BorderLayout.EAST);

		pan.setBorder(BorderFactory.createRaisedBevelBorder());

		return pan;
	}

	JScrollPane scroll() {
		JScrollPane scroll = new JScrollPane();
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, headings) {
			boolean[] canEdit = new boolean[] { false, true, true, true, true, true, true, true, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				try {
					Connection con = Connector.ConnectDB();
					int row = table.getSelectedRow();
					String table_click = table.getModel().getValueAt(row, 0).toString();
					String sql = "select * from wardboy_nurse_tbl where ID='" + table_click + "'";
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						enabletxt();

						String addId = rs.getString("ID");
						txtId.setText(addId);

						String addName = rs.getString("W_N_Name");
						txtName.setText(addName);

						String addCategory = rs.getString("Category");
						comboCategory.setSelectedItem(addCategory);

						String addAddress = rs.getString("Address");
						txtAddress.setText(addAddress);

						String addContactNo = rs.getString("ContactNo");
						txtContactNo.setText(addContactNo);

						String addEmail = rs.getString("Email");
						txtEmailId.setText(addEmail);

						String addQualifications = rs.getString("Qualifications");
						txtQualification.setText(addQualifications);

						String addBloodGroup = rs.getString("BloodGroup");
						comboBloodGroup.setSelectedItem(addBloodGroup);

						String addDateOfJoining = rs.getString("DateOfJoining");
						txtDateOfJoining.setText(addDateOfJoining);

						butUpdate.setEnabled(true);
						butDelete.setEnabled(true);
						butSave.setEnabled(false);
					}
				} catch (Exception e) {

				}
			}
		});

		scroll = new JScrollPane();
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
			if (txtId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter id number", "Error", JOptionPane.ERROR_MESSAGE);
				txtId.requestFocus();
				return;
			}
			if (txtName.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter name", "Error", JOptionPane.ERROR_MESSAGE);
				txtName.requestFocus();
				return;
			}
			if (comboCategory.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Please select category", "Error", JOptionPane.ERROR_MESSAGE);
				comboCategory.requestFocus();
				return;
			}
			if (txtAddress.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Address", "Error", JOptionPane.ERROR_MESSAGE);
				txtAddress.requestFocus();
				return;
			}
			if (txtContactNo.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Contact No.", "Error", JOptionPane.ERROR_MESSAGE);
				txtContactNo.requestFocus();
				return;
			}
			if (txtEmailId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Email id", "Error", JOptionPane.ERROR_MESSAGE);
				txtEmailId.requestFocus();
				return;
			}
			if (txtQualification.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Qualifications", "Error", JOptionPane.ERROR_MESSAGE);
				txtQualification.requestFocus();
				return;
			}
			if (comboBloodGroup.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Please select Blood group", "Error", JOptionPane.ERROR_MESSAGE);
				comboBloodGroup.requestFocus();
				return;
			}
			if (txtDateOfJoining.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Date of joining", "Error", JOptionPane.ERROR_MESSAGE);
				txtDateOfJoining.requestFocus();
				return;
			}

			Nurse_WardBoy_Bean b = Nurse_WardBoy_Dao.exist(txtId.getText());
			if (b.equals(txtId.getText())) {
				return;
			}

			String id = txtId.getText();
			String name = txtName.getText();
			String category = String.valueOf(comboCategory.getSelectedItem());
			String address = txtAddress.getText();
			String contact = txtContactNo.getText();
			String email = txtEmailId.getText();
			String qualifications = txtQualification.getText();
			String bloodGroup = String.valueOf(comboBloodGroup.getSelectedItem());
			String date = txtDateOfJoining.getText();

			Nurse_WardBoy_Bean bean = new Nurse_WardBoy_Bean(id, name, category, address, contact, email,
					qualifications, bloodGroup, date);
			int status = Nurse_WardBoy_Dao.save(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Saved successfully!", "", JOptionPane.INFORMATION_MESSAGE);
				butSave.setEnabled(false);
				reset();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, unable to save record!", "", JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butUpdate) {
			String id = txtId.getText();
			String name = txtName.getText();
			String category = String.valueOf(comboCategory.getSelectedItem());
			String address = txtAddress.getText();
			String contact = txtContactNo.getText();
			String email = txtEmailId.getText();
			String qualifications = txtQualification.getText();
			String bloodGroup = String.valueOf(comboBloodGroup.getSelectedItem());
			String date = txtDateOfJoining.getText();

			Nurse_WardBoy_Bean bean = new Nurse_WardBoy_Bean(id, name, category, address, contact, email,
					qualifications, bloodGroup, date);
			int status = Nurse_WardBoy_Dao.update(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Successfully updated", "", JOptionPane.INFORMATION_MESSAGE);
				butUpdate.setEnabled(false);
				butDelete.setEnabled(false);
				reset();
				disabletxt();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, unable to update record!", "", JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butDelete) {
			int p = JOptionPane.showConfirmDialog(this, " Are you sure want to delete ?", "Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (p == 0) {
				Nurse_WardBoy_Dao.delete(txtId.getText());
				JOptionPane.showMessageDialog(this, "Successfully deleted", "Record", JOptionPane.INFORMATION_MESSAGE);
				butUpdate.setEnabled(false);
				butDelete.setEnabled(false);
				reset();
				disabletxt();
			}

		} else if (e.getSource() == butGetData) {
			get_Data();
		}

	}
	
	void get_Data(){
		try{
			String sql="select * from wardboy_nurse_tbl";
			Connection con = Connector.ConnectDB();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, e);
		}
	}

	void reset() {
		txtId.setText("");
		txtId.requestFocus();
		txtName.setText("");
		comboCategory.setSelectedIndex(-1);
		txtAddress.setText("");
		txtContactNo.setText("");
		txtEmailId.setText("");
		txtQualification.setText("");
		comboBloodGroup.setSelectedIndex(-1);
		txtDateOfJoining.setText("");
		get_Data();
	}

	void enabletxt() {
		txtId.setEditable(true);
		txtName.setEditable(true);
		comboCategory.setEnabled(true);
		txtAddress.setEditable(true);
		txtContactNo.setEditable(true);
		txtEmailId.setEditable(true);
		txtQualification.setEditable(true);
		comboBloodGroup.setEnabled(true);
		txtDateOfJoining.setEditable(true);
	}
	
	void disabletxt() {
		txtId.setEditable(false);
		txtName.setEditable(false);
		comboCategory.setEnabled(false);
		txtAddress.setEditable(false);
		txtContactNo.setEditable(false);
		txtEmailId.setEditable(false);
		txtQualification.setEditable(false);
		comboBloodGroup.setEnabled(false);
		txtDateOfJoining.setEditable(false);
	}

}
