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
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.ServicesBean;
import dao.Connector;
import dao.ServicesDao;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class Services extends JInternalFrame implements ActionListener {

	JLabel lblServiceId, lblServiceName, lblServiceDate, lblPatientId, lblServiceCharges;
	JTextField txtServiceId, txtServiceName, txtServiceDate, txtPatientId, txtServiceCharges;
	JButton butNew, butSave, butUpdate, butDelete, butGetData;
	JTable table;
	String[] headings = { "Service ID", "Service Name", "Service Date", "Patient ID", "Service Charges" };

	public Services() {
		super("Services", true, true, true, false);
		setSize(550, 600);
		setLocation(400, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.NORTH);
		add(scroll(), BorderLayout.CENTER);

		setVisible(true);
	}

	JPanel serviceInfo() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblServiceId = new JLabel("Service ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblServiceId, c);

		txtServiceId = new JTextField(15);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		txtServiceId.setEditable(false);
		pan.add(txtServiceId, c);

		lblServiceName = new JLabel("Service Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblServiceName, c);

		txtServiceName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		txtServiceName.setEditable(false);
		pan.add(txtServiceName, c);

		lblServiceDate = new JLabel("Service Date");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblServiceDate, c);

		txtServiceDate = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		txtServiceDate.setEditable(false);
		pan.add(txtServiceDate, c);

		lblPatientId = new JLabel("Patient ID");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblPatientId, c);

		txtPatientId = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		txtPatientId.setEditable(false);
		pan.add(txtPatientId, c);

		lblServiceCharges = new JLabel("Service Charges");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblServiceCharges, c);

		txtServiceCharges = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		txtServiceCharges.setEditable(false);
		pan.add(txtServiceCharges, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "Service Info", HIDE_ON_CLOSE, DISPOSE_ON_CLOSE,
				new Font("Cambria", Font.PLAIN, 15), Color.BLACK));

		return pan;
	}

	JPanel panAdd() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout(40, 20));

		pan.add(serviceInfo(), BorderLayout.CENTER);
		pan.add(buttons(), BorderLayout.EAST);

		pan.setBorder(BorderFactory.createRaisedBevelBorder());

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

	JScrollPane scroll() {
		JScrollPane scroll = new JScrollPane();
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, headings) {
			boolean[] canEdit = new boolean[] { false, true, true, true, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				Connection con = Connector.ConnectDB();
				int row = table.getSelectedRow();
				String table_Click = table.getModel().getValueAt(row, 0).toString();
				String sql = "select * from services where ServiceID='" + table_Click + "'";
				PreparedStatement ps;
				try {
					ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						enabletxt();
						int id = rs.getInt("ServiceID");
						String ServiceId = Integer.toString(id);
						txtServiceId.setText(ServiceId);

						String name = rs.getString("ServiceName");
						txtServiceName.setText(name);

						String date = rs.getString("ServiceDate");
						txtServiceDate.setText(date);

						String patientId = rs.getString("PatientID");
						txtPatientId.setText(patientId);

						int charges = rs.getInt("ServiceCharges");
						String ServiceCharges = Integer.toString(charges);
						txtServiceCharges.setText(ServiceCharges);

						butUpdate.setEnabled(true);
						butDelete.setEnabled(true);
						butSave.setEnabled(false);

						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
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
			if (txtServiceId.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Service ID!", "Error", JOptionPane.ERROR_MESSAGE);
				txtServiceId.requestFocus();
				return;
			} else if (txtServiceName.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Service Name!", "Error", JOptionPane.ERROR_MESSAGE);
				txtServiceName.requestFocus();
				return;
			} else if (txtServiceDate.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Service Date!", "Error", JOptionPane.ERROR_MESSAGE);
				txtServiceDate.requestFocus();
				return;
			} else if (txtPatientId.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Patient ID!", "Error", JOptionPane.ERROR_MESSAGE);
				txtPatientId.requestFocus();
				return;
			} else if (txtServiceCharges.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Service Charges!", "Error",
						JOptionPane.ERROR_MESSAGE);
				txtServiceCharges.requestFocus();
				return;
			}

			int id = Integer.parseInt(txtServiceId.getText());
			String name = txtServiceName.getText();
			String date = txtServiceDate.getText();
			String patientId = txtPatientId.getText();
			int charges = Integer.parseInt(txtServiceCharges.getText());

			ServicesBean b = ServicesDao.exist(id);
			if (b.equals(id)) {
				return;
			}

			ServicesBean bean = new ServicesBean(id, name, date, patientId, charges);
			int status = ServicesDao.save(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Saved Successfully!", "", JOptionPane.INFORMATION_MESSAGE);
				butSave.setEnabled(false);
				reset();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, unable to save record!", "", JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butUpdate) {
			int id = Integer.parseInt(txtServiceId.getText());
			String name = txtServiceName.getText();
			String date = txtServiceDate.getText();
			String patientId = txtPatientId.getText();
			int charges = Integer.parseInt(txtServiceCharges.getText());

			ServicesBean bean = new ServicesBean(id, name, date, patientId, charges);
			int status = ServicesDao.update(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Updated Successfully!", "", JOptionPane.INFORMATION_MESSAGE);
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
				int id = Integer.parseInt(txtServiceId.getText());
				ServicesDao.delete(id);
				JOptionPane.showMessageDialog(this, "Record Successfully deleted!", "",
						JOptionPane.INFORMATION_MESSAGE);
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
		String sql = "select * from services";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(this, e);
		}
	}

	void reset() {
		txtServiceId.setText("");
		txtServiceId.requestFocus();
		txtServiceName.setText("");
		txtServiceDate.setText("");
		txtPatientId.setText("");
		txtServiceCharges.setText("");
		get_Data();
	}

	void enabletxt() {
		txtServiceId.setEditable(true);
		txtServiceName.setEditable(true);
		txtServiceDate.setEditable(true);
		txtPatientId.setEditable(true);
		txtServiceCharges.setEditable(true);
	}
	
	void disabletxt(){
		txtServiceId.setEditable(false);
		txtServiceName.setEditable(false);
		txtServiceDate.setEditable(false);
		txtPatientId.setEditable(false);
		txtServiceCharges.setEditable(false);
	}

}
