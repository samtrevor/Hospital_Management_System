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

import bean.RoomBean;
import dao.Connector;
import dao.RoomDao;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class Room extends JInternalFrame implements ActionListener {

	JLabel lblRoomNo, lblRoomType, lblRoomCharges, lblStatus;
	JTextField txtRoomNo, txtRoomCharges;
	JComboBox<String> roomTypeCombo, statusCombo;
	JButton butNew, butSave, butUpdate, butDelete, butGetData;
	String[] headings = { "Room No.", "Room Type", "Room Charges", "Room Status" };
	JTable table;

	public Room() {
		super("Room", true, true, true, false);
		setSize(500, 500);
		setLocation(425, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.NORTH);
		add(panTable(), BorderLayout.CENTER);

		setVisible(true);
	}

	JPanel panAdd() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout(40, 20));

		pan.add(roomInfo(), BorderLayout.CENTER);
		pan.add(buttons(), BorderLayout.EAST);

		pan.setBorder(BorderFactory.createRaisedBevelBorder());

		return pan;
	}

	JPanel roomInfo() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblRoomNo = new JLabel("Room No");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblRoomNo, c);

		txtRoomNo = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		txtRoomNo.setEditable(false);
		txtRoomNo.addActionListener(this);
		pan.add(txtRoomNo, c);

		lblRoomType = new JLabel("Room Type");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblRoomType, c);

		roomTypeCombo = new JComboBox<String>();
		roomTypeCombo.setModel(new DefaultComboBoxModel<>(new String[] { "General", "Deluxe" }));
		roomTypeCombo.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		roomTypeCombo.addActionListener(this);
		roomTypeCombo.setEnabled(false);
		pan.add(roomTypeCombo, c);

		lblRoomCharges = new JLabel("Room Charges (Per day)");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblRoomCharges, c);

		txtRoomCharges = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		txtRoomCharges.setEditable(false);
		txtRoomCharges.addActionListener(this);
		pan.add(txtRoomCharges, c);

		lblStatus = new JLabel("Room Status");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblStatus, c);

		statusCombo = new JComboBox<String>();
		statusCombo.setModel(new DefaultComboBoxModel<>(new String[] { "Occupied", "Vacant" }));
		statusCombo.setSelectedIndex(-1);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		statusCombo.setEnabled(false);
		statusCombo.addActionListener(this);
		pan.add(statusCombo, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "Room Info", HIDE_ON_CLOSE, DISPOSE_ON_CLOSE,
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
					String sql = "select * from room where RoomNo = '" + table_click + "'";
					PreparedStatement pst = con.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
						enabletxt();
						String addRoomNo = rs.getString("RoomNo");
						txtRoomNo.setText(addRoomNo);

						String addRoomType = rs.getString("RoomType");
						roomTypeCombo.setSelectedItem(addRoomType);

						int addRoomCharges = rs.getInt("RoomCharges");
						String addCharges = Integer.toString(addRoomCharges);
						txtRoomCharges.setText(addCharges);

						String addRoomStatus = rs.getString("RoomStatus");
						statusCombo.setSelectedItem(addRoomStatus);

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

		return scroll;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butNew) {
			butSave.setEnabled(true);
			enabletxt();
			reset();
			butUpdate.setEnabled(false);
			butDelete.setEnabled(false);

		} else if (e.getSource() == butSave) {
			if (txtRoomNo.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter room number", "Error", JOptionPane.ERROR_MESSAGE);
				txtRoomNo.requestFocus();
				return;
			}
			if (roomTypeCombo.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Please select room type", "Error", JOptionPane.ERROR_MESSAGE);
				roomTypeCombo.requestFocus();
				return;
			}
			if (txtRoomCharges.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter room charges", "Error", JOptionPane.ERROR_MESSAGE);
				txtRoomCharges.requestFocus();
				return;
			}
			if (statusCombo.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Please enter room status", "Error", JOptionPane.ERROR_MESSAGE);
				statusCombo.requestFocus();
				return;
			}

			RoomBean b = RoomDao.exist(txtRoomNo.getText());
			if (b.equals(txtRoomNo.getText())) {
				return;
			}

			String roomNo = txtRoomNo.getText();
			String roomType = String.valueOf(roomTypeCombo.getSelectedItem());
			int roomCharges = Integer.parseInt(txtRoomCharges.getText());
			String roomStatus = String.valueOf(statusCombo.getSelectedItem());

			RoomBean bean = new RoomBean(roomNo, roomType, roomCharges, roomStatus);
			int status = RoomDao.save(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Saved successfully!", "", JOptionPane.INFORMATION_MESSAGE);
				butSave.setEnabled(false);
				reset();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, unable to save record!", "", JOptionPane.ERROR_MESSAGE);
				reset();
			}

		} else if (e.getSource() == butUpdate) {
			String roomNo = txtRoomNo.getText();
			String roomType = String.valueOf(roomTypeCombo.getSelectedItem());
			int roomCharges = Integer.parseInt(txtRoomCharges.getText());
			String roomStatus = String.valueOf(statusCombo.getSelectedItem());

			RoomBean bean = new RoomBean(roomNo, roomType, roomCharges, roomStatus);
			int status = RoomDao.update(bean);
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
				RoomDao.delete(txtRoomNo.getText());
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

	private void get_Data() {
		String sql = "select RoomNo as 'Room No',RoomType as 'Room Type',RoomCharges as 'Room Charges',RoomStatus as 'Room Status' from room";
		try {
			Connection con = Connector.ConnectDB();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	void reset() {
		txtRoomNo.setText("");
		txtRoomNo.requestFocus();
		roomTypeCombo.setSelectedIndex(-1);
		txtRoomCharges.setText("");
		statusCombo.setSelectedIndex(-1);
		get_Data();
	}

	void enabletxt() {
		txtRoomNo.setEditable(true);
		roomTypeCombo.setEnabled(true);
		txtRoomCharges.setEditable(true);
		statusCombo.setEnabled(true);
	}

	void disabletxt() {
		txtRoomNo.setEditable(false);
		roomTypeCombo.setEnabled(false);
		txtRoomCharges.setEditable(false);
		statusCombo.setEnabled(false);
	}
}
