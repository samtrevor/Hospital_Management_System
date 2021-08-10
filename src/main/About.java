package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class About extends JInternalFrame implements ActionListener {

	JButton butOk;

	public About() {
		super("About", false, true, false, false);
		setSize(300, 300);
		setLocation(500, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panAdd(), BorderLayout.NORTH);
		add(panel1(), BorderLayout.CENTER);
		add(button(), BorderLayout.SOUTH);

		setVisible(true);
	}

	JPanel panAdd(){
		JPanel pan=new JPanel();
		pan.setLayout(new BorderLayout());
		
		pan.add(panHead(),BorderLayout.NORTH);
		pan.add(panel(),BorderLayout.CENTER);
		
		return pan;
	}
	
	JPanel panHead() {
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());

		JLabel lblHeading = new JLabel("Hospital Management System");
		lblHeading.setFont(new Font("Elephant", Font.ROMAN_BASELINE, 18));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);

		pan.add(lblHeading, BorderLayout.NORTH);

		return pan;
	}

	JPanel panel() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel lblPic = new JLabel(new ImageIcon(getClass().getResource("/images/images.jpg")));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblPic, c);

		JLabel lblVersion = new JLabel("Version 1.0");
		lblVersion.setFont(new Font("Castellar", Font.ROMAN_BASELINE, 15));
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblVersion, c);

		return pan;
	}

	JPanel panel1() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Font font = new Font("Bookman Old Style", Font.PLAIN, 13);

		JLabel lblDeveloper = new JLabel("Developed By: ");
		lblDeveloper.setFont(font);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblDeveloper, c);

		JLabel lblDevName = new JLabel("Samuel Mbugua Njehia.");
		lblDevName.setFont(font);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblDevName, c);

		JLabel lblSubmittedTo = new JLabel("Submitted To: ");
		lblSubmittedTo.setFont(font);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblSubmittedTo, c);

		JLabel lblname = new JLabel("Mr Patrick Waiyego.");
		lblname.setFont(font);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblname, c);

		return pan;
	}

	JPanel button() {
		JPanel pan = new JPanel();
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(10));
		butOk = new JButton("Ok");
		butOk.addActionListener(this);
		b.add(butOk);
		pan.add(b);
		return pan;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butOk) {
			dispose();
		}
	}

}
