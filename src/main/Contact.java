package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Contact extends JInternalFrame {

	JLabel lblContact,lblContDet,lblName,lblNameDet,lblEmail,lblEmailDet;
	Font font = new Font("Bookman Old Style", Font.BOLD, 14);
	
	public Contact() {
		super("Contact", false, true, false, false);
		setSize(300, 300);
		setLocation(500, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		add(comp(),BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	JPanel comp(){
		JPanel pan= new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		lblName = new JLabel("Name:");
		lblName.setFont(font);
		c.anchor=GridBagConstraints.WEST;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblName,c);
		
		lblNameDet =new JLabel("Samuel Mbugua Njehia");
		lblNameDet.setFont(font);
		c.anchor=GridBagConstraints.WEST;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=1;
		c.gridy=0;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblNameDet,c);
		
		lblContact = new JLabel("Contact:");
		lblContact.setFont(font);
		c.anchor=GridBagConstraints.WEST;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblContact,c);
		
		lblContDet =new JLabel("+254790127078");
		lblContDet.setFont(font);
		c.anchor=GridBagConstraints.WEST;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=1;
		c.gridy=1;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblContDet,c);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(font);
		c.anchor=GridBagConstraints.WEST;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblEmail,c);
		
		lblEmailDet =new JLabel("samtrevor13@gmail.com");
		lblEmailDet.setFont(font);
		c.anchor=GridBagConstraints.WEST;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=1;
		c.gridy=2;
		c.insets = new Insets(10, 10, 10, 10);
		pan.add(lblEmailDet,c);
		
		return pan;
	}

}
