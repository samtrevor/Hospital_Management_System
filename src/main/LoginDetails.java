package main;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bean.LoginBean;
import dao.LoginDao;

/*
 * 
 * REDO THIS CLASS 
 * BUGS FOUND
 * 
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class LoginDetails extends JInternalFrame {

	JTable table;
	String[] headings = { "User Name", "Password" };

	public LoginDetails() {
		super("Login Details", false, true, false, false);
		setSize(400, 350);
		setLocation(480, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(viewUsers());

		setVisible(true);
	}

	JScrollPane viewUsers() {
		JScrollPane scroll = new JScrollPane();
		JTable table = new JTable();
		// Code to view data in JTable
		List<LoginBean> list = LoginDao.view();
		int size = list.size();

		String data[][] = new String[size][2];
		int row = 0;
		for (LoginBean bean : list) {
			data[row][0] = bean.getUserName();
			data[row][1] = bean.getPassword();
			row++;
		}
		table = new JTable(data, headings);
		scroll.setViewportView(table);
		scroll.setBorder(BorderFactory.createTitledBorder(""));

		return scroll;
	}

}
