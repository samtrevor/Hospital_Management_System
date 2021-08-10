/**
 * This class generates the main user interface
 */
package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * @author SAMUEL
 *
 */
@SuppressWarnings("serial")
public class MainMenu extends javax.swing.JFrame implements ActionListener {

	Look_n_Feel look = new Look_n_Feel();
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	JLabel lblBackground;
	JMenu menuEntry, menuUsers, menuDoctor, menuPatients, menuHelp, menuAdmit, menuDischarge, menuBilling;
	JMenuItem itemWard, itemRoom, itemNurseWardBoy, itemRegistration, itemChangePass, itemLoginDetails, itemProfile,
			itemPatientRegister, itemServices, itemAdmitRoom, itemAdmitWard, itemDischargeRoom, itemDischargeWard,
			itemBillingRoom, itemBillingWard, itemAbout, itemContact;
	JDesktopPane desktopPane;

	public MainMenu() {
		super("Hospital_Management_System");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setAlwaysOnTop(true);
		setSize(d.width, d.height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setResizable(false);

		desktopPane = new JDesktopPane() {// setting the background
			ImageIcon imgPic = new ImageIcon(getClass().getResource("/images/uri.jpg"));
			Image img = imgPic.getImage();
			Image newImage = img.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(newImage, 0, 0, this);
			}
		};

		look.look_feel();
		setJMenuBar(menuBar());

		add(desktopPane, BorderLayout.CENTER);

		setVisible(true);
	}

	JMenuBar menuBar() {
		JMenuBar bar = new JMenuBar();
		// creating menuEntry and its menuItems with Listeners
		menuEntry = new JMenu("Master Entry");
		menuEntry.setMnemonic('m');

		itemWard = new JMenuItem("Ward");
		itemWard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		itemWard.addActionListener(this);

		itemRoom = new JMenuItem("Room");
		itemRoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		itemRoom.addActionListener(this);

		itemNurseWardBoy = new JMenuItem("Nurse/Wardboy");
		itemNurseWardBoy
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK | ActionEvent.CTRL_MASK));
		itemNurseWardBoy.addActionListener(this);

		menuEntry.add(itemWard);
		menuEntry.add(itemRoom);
		menuEntry.addSeparator();
		menuEntry.add(itemNurseWardBoy);

		// creating menuUsers and its menuItems with Listeners
		menuUsers = new JMenu("Users");
		menuUsers.setMnemonic('u');

		itemRegistration = new JMenuItem("Registration");
		itemRegistration.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		itemRegistration.addActionListener(this);

		itemChangePass = new JMenuItem("Change Password");
		itemChangePass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		itemChangePass.addActionListener(this);

		itemLoginDetails = new JMenuItem("Login Details");
		itemLoginDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		itemLoginDetails.addActionListener(this);

		menuUsers.add(itemRegistration);
		menuUsers.add(itemChangePass);
		menuUsers.addSeparator();
		menuUsers.add(itemLoginDetails);

		// creating menuDoctor and its menuItems with Listeners
		menuDoctor = new JMenu("Doctor");
		menuDoctor.setMnemonic('d');

		itemProfile = new JMenuItem("Profile Entry");
		itemProfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		itemProfile.addActionListener(this);

		menuDoctor.add(itemProfile);

		// creating menuPatients and its menuItems with Listeners
		menuPatients = new JMenu("Patient");
		menuPatients.setMnemonic('p');

		itemPatientRegister = new JMenuItem("Patient Registration");
		itemPatientRegister.addActionListener(this);

		itemServices = new JMenuItem("Services");
		itemServices.addActionListener(this);

		menuAdmit = new JMenu("Admit");// sub Menu inside menuPatients with its
										// menuItems

		itemAdmitRoom = new JMenuItem("Room");
		itemAdmitRoom.addActionListener(this);

		itemAdmitWard = new JMenuItem("Ward");
		itemAdmitWard.addActionListener(this);

		menuAdmit.add(itemAdmitRoom);
		menuAdmit.add(itemAdmitWard);

		menuDischarge = new JMenu("Discharge");// sub Menu inside menuPatients
												// with its menuItems

		itemDischargeRoom = new JMenuItem("Room");
		itemDischargeRoom.addActionListener(this);

		itemDischargeWard = new JMenuItem("Ward");
		itemDischargeWard.addActionListener(this);

		menuDischarge.add(itemDischargeRoom);
		menuDischarge.add(itemDischargeWard);

		menuBilling = new JMenu("Billing");// sub Menu inside menuPatients with
											// its menuItems

		itemBillingRoom = new JMenuItem("Room");
		itemBillingRoom.addActionListener(this);

		itemBillingWard = new JMenuItem("Ward");
		itemBillingWard.addActionListener(this);

		menuBilling.add(itemBillingRoom);
		menuBilling.add(itemBillingWard);// End of sub menus

		menuPatients.add(itemPatientRegister);
		menuPatients.add(itemServices);
		menuPatients.add(menuAdmit);
		menuPatients.add(menuDischarge);
		menuPatients.add(menuBilling);

		// creating menuHelp and its menuItems with Listeners
		menuHelp = new JMenu("Help");
		menuHelp.setMnemonic('h');

		itemAbout = new JMenuItem("About");
		itemAbout.addActionListener(this);

		itemContact = new JMenuItem("Contact");
		itemContact.addActionListener(this);

		menuHelp.add(itemAbout);
		menuHelp.add(itemContact);

		// adding created menus to the menubar
		bar.add(menuEntry);
		bar.add(menuUsers);
		bar.add(menuDoctor);
		bar.add(menuPatients);
		bar.add(menuHelp);

		return bar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == itemWard) {
			desktopPane.add(new Ward());
		} else if (e.getSource() == itemRoom) {
			desktopPane.add(new Room());
		} else if (e.getSource() == itemNurseWardBoy) {
			desktopPane.add(new Nurse_WardBoy());
		} else if (e.getSource() == itemRegistration) {
			desktopPane.add(new UsersRegistration());
		} else if (e.getSource() == itemChangePass) {
			desktopPane.add(new ChangePassword());
		} else if (e.getSource() == itemLoginDetails) {
			desktopPane.add(new LoginDetails());
		} else if (e.getSource() == itemProfile) {
			desktopPane.add(new Doctor());
		} else if (e.getSource() == itemPatientRegister) {
			desktopPane.add(new PatientRegistration());
		} else if (e.getSource() == itemServices) {
			desktopPane.add(new Services());
		} else if (e.getSource() == itemAdmitRoom) {
			desktopPane.add(new AdmitPatientRoom());
		} else if (e.getSource() == itemAdmitWard) {
			desktopPane.add(new AdmitPatientWard());
		} else if (e.getSource() == itemDischargeRoom) {
			desktopPane.add(new DischargePatientRoom());
		} else if (e.getSource() == itemDischargeWard) {
			desktopPane.add(new DischargePatientWard());
		} else if (e.getSource() == itemBillingRoom) {
			desktopPane.add(new BillingPatientRoom());
		} else if (e.getSource() == itemBillingWard) {
			desktopPane.add(new BillingPatientWard());
		} else if (e.getSource() == itemAbout) {
			desktopPane.add(new About());
		} else if (e.getSource() == itemContact) {
			desktopPane.add(new Contact());
		}
	}

}
