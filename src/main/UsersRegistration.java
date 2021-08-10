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
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bean.LoginBean;
import bean.UsersRegistrationBean;
import dao.Connector;
import dao.LoginDao;
import dao.UsersRegistrationDao;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class UsersRegistration extends JInternalFrame implements ActionListener {

	JLabel lblName, lblUserName, lblPassword, lblEmail, lblContactNo;
	JTextField txtName, txtUserName, txtEmail, txtContactNo;
	JPasswordField password;
	JButton butNew, butSave, butUpdate, butDelete, butGetData;
	JTable table;
	String[] headings = { "User Name", "Password", "Name", "Contact No", "Email" };

	public UsersRegistration() {
		super("User Registration", true, true, true, false);
		setSize(500, 600);
		setLocation(425, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.NORTH);
		add(panTable(), BorderLayout.CENTER);

		setVisible(true);
	}

	JPanel userDetails() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		lblUserName = new JLabel("User Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblUserName, c);

		txtUserName = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		txtUserName.addActionListener(this);
		txtUserName.setEditable(false);
		pan.add(txtUserName, c);

		lblPassword = new JLabel("Password");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblPassword, c);

		password = new JPasswordField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		password.addActionListener(this);
		password.setEditable(false);
		pan.add(password, c);

		lblName = new JLabel("Name");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblName, c);

		txtName = new JTextField(15);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		txtName.addActionListener(this);
		txtName.setEditable(false);
		pan.add(txtName, c);

		lblContactNo = new JLabel("Contact No.");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblContactNo, c);

		txtContactNo = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		txtContactNo.addActionListener(this);
		txtContactNo.setEditable(false);
		pan.add(txtContactNo, c);

		lblEmail = new JLabel("Email");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblEmail, c);

		txtEmail = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 10, 10);
		txtEmail.addActionListener(this);
		txtEmail.setEditable(false);
		pan.add(txtEmail, c);

		pan.setBorder(BorderFactory.createTitledBorder(null, "User Details", HIDE_ON_CLOSE, DISPOSE_ON_CLOSE,
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

		b.add(Box.createVerticalStrut(20));
		butSave = new JButton("Save");
		butSave.addActionListener(this);
		butSave.setEnabled(false);
		b.add(butSave);

		b.add(Box.createVerticalStrut(20));
		butUpdate = new JButton("Update");
		butUpdate.setEnabled(false);
		butUpdate.addActionListener(this);
		b.add(butUpdate);

		b.add(Box.createVerticalStrut(20));
		butDelete = new JButton("Delete");
		butDelete.addActionListener(this);
		butDelete.setEnabled(false);
		b.add(butDelete);

		b.add(Box.createVerticalStrut(20));
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
		pan.add(userDetails(), BorderLayout.CENTER);
		pan.add(buttons(), BorderLayout.EAST);
		pan.setBorder(BorderFactory.createRaisedBevelBorder());
		return pan;
	}

	JScrollPane panTable() {
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
				try {
					Connection con = Connector.ConnectDB();
					int row = table.getSelectedRow();
					String table_click = table.getModel().getValueAt(row, 0).toString();
					String sql = "select * from registration where username='" + table_click + "'";
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						enabletxt();
						String addUsername = rs.getString("username");
						txtUserName.setText(addUsername);

						String addPassword = String.valueOf(rs.getString("password"));
						password.setText(addPassword);

						String name = rs.getString("NameOfUser");
						txtName.setText(name);

						String contact = rs.getString("ContactNo");
						txtContactNo.setText(contact);

						String email = rs.getString("Email");
						txtEmail.setText(email);

						butUpdate.setEnabled(true);
						butDelete.setEnabled(true);
						butSave.setEnabled(false);
						password.setEnabled(false);

					}
					// con.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});

		scroll = new JScrollPane(table);
		// scroll.setViewportView(table);
		scroll.setBorder(BorderFactory.createTitledBorder(""));

		return scroll;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butNew) {
			enabletxt();
			reset();
			butSave.setEnabled(true);
			butDelete.setEnabled(false);
			butUpdate.setEnabled(false);

		} else if (e.getSource() == butSave) {
			if (txtName.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Name!", "Error", JOptionPane.ERROR_MESSAGE);
				txtName.requestFocus();
				return;
			}
			if (txtUserName.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter User Name!", "Error", JOptionPane.ERROR_MESSAGE);
				txtUserName.requestFocus();
				return;
			}
			if (String.valueOf(password.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Password!", "Error", JOptionPane.ERROR_MESSAGE);
				password.requestFocus();
				return;
			}
			if (txtEmail.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Email!", "Error", JOptionPane.ERROR_MESSAGE);
				txtEmail.requestFocus();
				return;
			}
			if (txtContactNo.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter Contact No.", "Error", JOptionPane.ERROR_MESSAGE);
				txtContactNo.requestFocus();
				return;
			}

			UsersRegistrationBean b = UsersRegistrationDao.exist(txtUserName.getText());
			if (b.equals(txtUserName.getText())) {
				return;
			}

			String name = txtName.getText();
			String username = txtUserName.getText();
			String pass = String.valueOf(password.getPassword());
			String email = txtEmail.getText();
			String contact = txtContactNo.getText();

			UsersRegistrationBean bean = new UsersRegistrationBean(username, pass, name, contact, email);
			int status = UsersRegistrationDao.save(bean);
			if (status > 0) {
				JOptionPane.showMessageDialog(this, "Saved Successfully!", "", JOptionPane.INFORMATION_MESSAGE);
				butSave.setEnabled(false);
				reset();
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, unable to save record!", "", JOptionPane.ERROR_MESSAGE);
				reset();
			}

			LoginBean bean2 = new LoginBean(username, pass);
			int status2 = LoginDao.save(bean2);
			if (status2 != 0) {
				return;
			}

		} else if (e.getSource() == butUpdate) {
			String name = txtName.getText();
			String username = txtUserName.getText();
			String pass = String.valueOf(password.getPassword());
			String email = txtEmail.getText();
			String contact = txtContactNo.getText();

			UsersRegistrationBean bean = new UsersRegistrationBean(username, pass, name, contact, email);
			int status = UsersRegistrationDao.update(bean);
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
			int status = JOptionPane.showConfirmDialog(this, " Are you sure want to delete ?", "Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (status == 0) {
				UsersRegistrationDao.delete(txtUserName.getText());
				LoginDao.delete(txtUserName.getText());
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

	public void get_Data() {
		try {
			Connection con = Connector.ConnectDB();
			String sql = "select username as 'User Name',password as 'Password',NameOfUser as 'Name',ContactNo as 'Contact No',email as 'Email' from registration";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	void reset() {
		txtUserName.setText("");
		txtUserName.requestFocus();
		password.setText("");
		txtName.setText("");
		txtContactNo.setText("");
		txtEmail.setText("");
		get_Data();
	}

	void enabletxt() {
		txtUserName.setEditable(true);
		password.setEditable(true);
		password.setEnabled(true);
		txtName.setEditable(true);
		txtContactNo.setEditable(true);
		txtEmail.setEditable(true);
	}

	void disabletxt()

	{
		txtUserName.setEditable(false);
		password.setEditable(false);
		password.setEnabled(false);
		txtName.setEditable(false);
		txtContactNo.setEditable(false);
		txtEmail.setEditable(false);
	}
}
