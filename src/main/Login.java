package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bean.LoginBean;
import dao.LoginDao;

@SuppressWarnings("serial")
public class Login extends JDialog implements ActionListener {

	Look_n_Feel look = new Look_n_Feel();
	JLabel lblUser, lblPass;
	JTextField txtUser;
	JPasswordField pass;
	JButton butLogin, butCancel;
	Font lblFont = new Font("Castellar", Font.BOLD, 20);

	public Login() {
		setTitle("Login");
		setSize(400, 250);
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		look.look_feel();

		JPanel pan = new JPanel();
		pan.setBackground(Color.DARK_GRAY);
		pan.add(components(), BorderLayout.CENTER);
		pan.add(panButtons(), BorderLayout.SOUTH);
		pan.setBorder(BorderFactory.createTitledBorder(null, "Sign In", HIDE_ON_CLOSE, DISPOSE_ON_CLOSE,
				new Font("Cambria", Font.PLAIN, 22), Color.white));

		add(pan, BorderLayout.CENTER);

		setVisible(true);
	}

	JPanel components() {
		JPanel pan = new JPanel();
		GridBagLayout gridBag = new GridBagLayout();
		pan.setLayout(gridBag);
		pan.setBackground(Color.DARK_GRAY);
		pan.setForeground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		lblUser = new JLabel("User Name:");
		// lblUser.setFont(lblFont);
		lblUser.setForeground(Color.WHITE);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15, 15, 15, 15);
		pan.add(lblUser, c);

		txtUser = new JTextField(20);
		// txtUser.setFont(txtFont);
		txtUser.addActionListener(this);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(15, 15, 15, 15);
		pan.add(txtUser, c);

		lblPass = new JLabel("Password:");
		// lblPass.setFont(lblFont);
		lblPass.setForeground(Color.WHITE);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(15, 15, 15, 15);
		pan.add(lblPass, c);

		pass = new JPasswordField(20);
		// pass.setFont(txtFont);
		pass.addActionListener(this);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(15, 15, 15, 15);
		pan.add(pass, c);

		return pan;
	}

	JPanel panButtons() {
		JPanel pan = new JPanel();
		pan.setLayout(new FlowLayout(FlowLayout.RIGHT, 80, 10));
		pan.setBackground(Color.GRAY);

		butLogin = new JButton("Login");
		butLogin.setFont(lblFont);
		butLogin.setForeground(Color.WHITE);
		butLogin.setBackground(Color.BLACK);
		butLogin.addActionListener(this);

		butCancel = new JButton("Cancel");
		butCancel.setFont(lblFont);
		butCancel.setForeground(Color.WHITE);
		butCancel.setBackground(Color.BLACK);
		butCancel.addActionListener(this);

		pan.setBorder(BorderFactory.createRaisedBevelBorder());

		pan.add(butCancel);
		pan.add(butLogin);

		return pan;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Login();

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butLogin) {
			String username = txtUser.getText();
			String password = String.valueOf(pass.getPassword());

			if (username.equals("")) {// condition to make sure username is not
										// null or empty
				JOptionPane.showMessageDialog(this, "Please enter user name!", "Error", JOptionPane.ERROR_MESSAGE);
				txtUser.requestFocus();
				return;
			}
			if (password.equals("")) {// condition to make sure password is not
										// null or empty
				JOptionPane.showMessageDialog(this, "Please enter password!", "Error", JOptionPane.ERROR_MESSAGE);
				pass.requestFocus();
				return;
			}

			try {// this block compares the typed username and password with the
					// ones stored in the database
				LoginBean bean = new LoginBean();
				LoginDao dao = new LoginDao();
				bean = dao.validate(username, password);

				if (bean.getUserName() != null && String.valueOf(bean.getPassword()) != null) {
					new MainMenu();
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Incorrect User Name or Password!", "",
							JOptionPane.ERROR_MESSAGE);
					clear();
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == butCancel) {
			System.exit(0);
		}

	}

	void clear() {
		txtUser.setText("");
		txtUser.requestFocus();
		pass.setText("");
	}

}
