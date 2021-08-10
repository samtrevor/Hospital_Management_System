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
import java.sql.DriverManager;
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

import bean.WardBean;
import dao.Connector;
import dao.WardDao;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class Ward extends JInternalFrame implements ActionListener {

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	JLabel lblWardName, lblWardType, lblBeds, lblCharges;
	JTextField txtWardName, txtBeds, txtCharges;
	JComboBox<String> wardTypeCombo;
	JButton butNew, butSave, butUpdate, butDelete, butGetData;
	String[] headings = { "Ward Name", "Ward Type", "No.of Beds", "Charges per Bed" };
	JTable table;
	JScrollPane scroll;

	public Ward() {
		super("Ward", true, true, true, false);
		setSize(500, 500);
		setLocation(425, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setResizable(false);
		
		add(panAdd(), BorderLayout.NORTH);
		add(panTable(), BorderLayout.CENTER);

		setVisible(true);
	}

	JPanel panAdd() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout(40, 20));

		pan.add(wardInfo(), BorderLayout.CENTER);
		pan.add(buttons(), BorderLayout.EAST);

		pan.setBorder(BorderFactory.createRaisedBevelBorder());

		return pan;
	}

	JPanel wardInfo() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblWardName = new JLabel("Ward Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblWardName, c);

		txtWardName = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		txtWardName.setEditable(false);
		txtWardName.addActionListener(this);
		pan.add(txtWardName, c);

		lblWardType = new JLabel("Ward Type");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblWardType, c);

		wardTypeCombo = new JComboBox<String>();
		wardTypeCombo.setModel(new DefaultComboBoxModel<>(new String[] { "General", "Special" }));
		wardTypeCombo.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		wardTypeCombo.addActionListener(this);
		wardTypeCombo.setEnabled(false);
		pan.add(wardTypeCombo, c);

		lblBeds = new JLabel("No.Of Beds");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblBeds, c);

		txtBeds = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		txtBeds.setEditable(false);
		txtBeds.addActionListener(this);
		pan.add(txtBeds, c);

		lblCharges = new JLabel("Charges per bed");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblCharges, c);

		txtCharges = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		txtCharges.setEditable(false);
		txtCharges.addActionListener(this);
		pan.add(txtCharges, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "Ward Info", HIDE_ON_CLOSE, DISPOSE_ON_CLOSE,
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

	JScrollPane panTable() {
		JScrollPane scroll = new JScrollPane();
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, headings) {

			boolean[] canEdit = new boolean[] { false, true, true, true };

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
					String sql = "select * from ward where wardname = '" + table_click + "'";
					PreparedStatement pst = con.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
						enabletxt();
						String addWardName = rs.getString("wardname");
						txtWardName.setText(addWardName);

						String addWardType = rs.getString("wardtype");
						wardTypeCombo.setSelectedItem(addWardType);

						int addNoOfBeds = rs.getInt("NoOfBeds");
						String addBeds = Integer.toString(addNoOfBeds);
						txtBeds.setText(addBeds);

						int addCharges = rs.getInt("Charges");
						String Charges = Integer.toString(addCharges);
						txtCharges.setText(Charges);

						butUpdate.setEnabled(true);
						butDelete.setEnabled(true);
						butSave.setEnabled(false);

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});

		scroll = new JScrollPane();
		scroll.setViewportView(table);
		scroll.setBorder(BorderFactory.createTitledBorder(""));

		//scroll.setBorder(BorderFactory.createLoweredBevelBorder());
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
			if (txtWardName.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter ward name", "Error", JOptionPane.ERROR_MESSAGE);
				txtWardName.requestFocus();
				return;
			}
			if (wardTypeCombo.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Please select ward type", "Error", JOptionPane.ERROR_MESSAGE);
				wardTypeCombo.requestFocus();
				return;
			}
			if (txtBeds.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter no. of beds", "Error", JOptionPane.ERROR_MESSAGE);
				txtBeds.requestFocus();
				return;
			}
			if (txtCharges.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter charges", "Error", JOptionPane.ERROR_MESSAGE);
				txtCharges.requestFocus();
				return;
			}

			WardBean b = WardDao.exist(txtWardName.getText());
			if (b.equals(txtWardName.getText())) {// Checks whether the given
													// ward name already exist
													// in the database
				return;
			}

			String name = txtWardName.getText();
			String type = String.valueOf(wardTypeCombo.getSelectedItem());
			int beds = Integer.parseInt(txtBeds.getText());
			int charges = Integer.parseInt(txtCharges.getText());

			WardBean bean = new WardBean(name, type, beds, charges);
			int status = WardDao.save(bean);

			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Saved successfully!", "", JOptionPane.INFORMATION_MESSAGE);
				butSave.setEnabled(false);
				reset();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, unable to save record!", "", JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butUpdate) {
			String name = txtWardName.getText();
			String type = String.valueOf(wardTypeCombo.getSelectedItem());
			int beds = Integer.parseInt(txtBeds.getText());
			int charges = Integer.parseInt(txtCharges.getText());

			WardBean bean = new WardBean(name,type,beds,charges);
			int status = WardDao.update(bean);
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
			int p = JOptionPane.showConfirmDialog(null, " Are you sure want to delete ?", "Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (p == 0) {
				WardDao.delete(txtWardName.getText());
				JOptionPane.showMessageDialog(this, "Successfully deleted", "Record", JOptionPane.INFORMATION_MESSAGE);
				butUpdate.setEnabled(false);
				butDelete.setEnabled(false);
				reset();
				disabletxt();
			}

		} else if (e.getSource() == butGetData) {
			get_Data();
			// table.setModel(new DefaultTableModel(get_data(), headings));
		}
	}

	private void get_Data() {
		String sql = "select WardName as 'Ward Name',WardType as 'Ward Type',NoOfbeds as 'No Of Beds',Charges from Ward";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	/*
	 * Object[][] get_data() { List<WardBean> list = WardDao.getData(); int size
	 * = list.size();
	 * 
	 * String[][] data = new String[size][headings.length]; int row = 0; for
	 * (WardBean bean : list) { data[row][0] = bean.getWardName(); data[row][1]
	 * = bean.getWardType(); data[row][2] = String.valueOf(bean.getNoOfBeds());
	 * data[row][3] = String.valueOf(bean.getCharges());
	 * 
	 * row++; }
	 * 
	 * return data; }
	 */

	void reset() {
		txtWardName.setText("");
		txtWardName.requestFocus();
		wardTypeCombo.setSelectedIndex(-1);
		txtBeds.setText("");
		txtCharges.setText("");
		get_Data();
	}

	void enabletxt() {
		txtWardName.setEditable(true);
		wardTypeCombo.setEnabled(true);
		txtBeds.setEditable(true);
		txtCharges.setEditable(true);
	}

	void disabletxt() {
		txtWardName.setEditable(false);
		wardTypeCombo.setEnabled(false);
		txtBeds.setEditable(false);
		txtCharges.setEditable(false);
	}
}
