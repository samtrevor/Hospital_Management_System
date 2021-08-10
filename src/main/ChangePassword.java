package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bean.ChangePasswordBean;
import dao.ChangePasswordDao;

@SuppressWarnings("serial")
public class ChangePassword extends JInternalFrame implements ActionListener {

	JLabel lblUserName, lblOldPassword, lblNewPassword, lblConfirmPassword;
	JTextField txtUserName;
	JPasswordField txtOldPassword, txtNewPassword, txtConfirmPassword;
	JButton butChangePassword;

	public ChangePassword() {
		super("Change Password", true, true, false, false);
		setSize(400, 350);
		setLocation(480, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.CENTER);

		setVisible(true);
	}

	JPanel panDetails() {
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

		txtUserName = new JTextField(10);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		txtUserName.addActionListener(this);
		pan.add(txtUserName, c);

		lblOldPassword = new JLabel("Old Password");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblOldPassword, c);

		txtOldPassword = new JPasswordField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		txtOldPassword.addActionListener(this);
		pan.add(txtOldPassword, c);

		lblNewPassword = new JLabel("New Password");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblNewPassword, c);

		txtNewPassword = new JPasswordField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		txtNewPassword.addActionListener(this);
		pan.add(txtNewPassword, c);

		lblConfirmPassword = new JLabel("Confirm Password");
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblConfirmPassword, c);

		txtConfirmPassword = new JPasswordField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 10, 10);
		txtConfirmPassword.addActionListener(this);
		pan.add(txtConfirmPassword, c);

		return pan;
	}

	JPanel button() {
		JPanel pan = new JPanel();
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(20));
		butChangePassword = new JButton("Change Password");
		butChangePassword.setFont(new Font("Georgia", Font.BOLD, 20));
		butChangePassword.addActionListener(this);
		b.add(butChangePassword);

		pan.add(b);

		return pan;
	}

	JPanel panAdd() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());

		pan.add(panDetails(), BorderLayout.CENTER);
		pan.add(button(), BorderLayout.SOUTH);

		pan.setBorder(BorderFactory.createTitledBorder(""));

		return pan;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butChangePassword) {
			String username = txtUserName.getText();
			String oldpass = String.valueOf(txtOldPassword.getPassword());
			String newpass = String.valueOf(txtNewPassword.getPassword());
			String confirmpass = String.valueOf(txtConfirmPassword.getPassword());

			if (username.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter user name!", "Error", JOptionPane.ERROR_MESSAGE);
				txtUserName.requestFocus();
				return;
			} else if (oldpass.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter old password!", "Error", JOptionPane.ERROR_MESSAGE);
				txtOldPassword.requestFocus();
				return;
			} else if (newpass.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter new password!", "Error", JOptionPane.ERROR_MESSAGE);
				txtNewPassword.requestFocus();
				return;
			} else if (confirmpass.equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter confirm password!", "Error",
						JOptionPane.ERROR_MESSAGE);
				txtConfirmPassword.requestFocus();
				return;
			} else if (newpass.length() < 5) {
				JOptionPane.showMessageDialog(this, "The New Password Should be of Atleast 5 Characters", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (newpass.equals(oldpass)) {
				JOptionPane.showMessageDialog(this, "Password is same..Re-enter new password", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (!(newpass.equals(confirmpass))) {
				JOptionPane.showMessageDialog(this, "New Password doesn't match with Confirmed Password", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			ChangePasswordBean bean = new ChangePasswordBean(username, oldpass, newpass);
			ChangePasswordDao dao = new ChangePasswordDao();
			dao.update(bean);
			JOptionPane.showMessageDialog(this, "Successfully updated!", "", JOptionPane.INFORMATION_MESSAGE);
			reset();
			
		}

	}

	void reset() {
		txtUserName.setText("");
		txtOldPassword.setText("");
		txtNewPassword.setText("");
		txtConfirmPassword.setText("");
	}

}
