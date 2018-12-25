package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import dataStorage.Activity;
import dataStorage.ActivityVector;
import javax.swing.JOptionPane;

// Logout screen shown to user when "logout" btn is pressed. Shows all user activites
public class LogoutScreen extends JPanel{
	private MainFrame mainFrame;

	public LogoutScreen(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame; // Setting the reference to the constructor field
		setLayout(null);
		mainFrame.setTitle(((mainFrame.controller().getCurrentUser().getAccessLevel()) ? "Staff":"Customer") + " Session Summary");

		JLabel lblLoginPage = new JLabel("User Activities for current session:");
		lblLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginPage.setBounds(101, 11, 223, 14);
		this.add(lblLoginPage);

		// Show user activity summary before logging out of current session.
		JScrollPane scrollPane = new JScrollPane(); // Create ScrollPane	
		scrollPane.setBounds(101, 27, 223, 188);
		this.add(scrollPane);

		// Create and Add JList to scrollPane
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		DefaultListCellRenderer r = (DefaultListCellRenderer) list.getCellRenderer();
		r.setHorizontalAlignment(SwingConstants.CENTER);

		// Populating the JList
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i<ActivityVector.size() ; i++)
			model.addElement(ActivityVector.get(i).asJList_element());
		list.setModel(model);

		// Adding action listener to JList
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Activity a = ActivityVector.getActivityWithJList_element(list.getSelectedValue().toString()); 
				mainFrame.controller().setCurrentActivitySelection(a); // Set the selected movie
				mainFrame.showActivityDetailsScreen(); // Call ActivityDetailsScreen after setting current activity from the JList
			}
		});
		
		JLabel lblNumberOfActivities = new JLabel("<html>Number of activities: " + ActivityVector.size() + "</html>");
		lblNumberOfActivities.setBounds(11, 82, 80, 61);
		this.add(lblNumberOfActivities);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){logout();}
		});
		btnLogout.setBounds(174, 226, 80, 23);
		this.add(btnLogout);
	}
	private void logout(){
		JOptionPane.showMessageDialog(null, "Thank you for using this app! Have a nice day!", "GW Entertainment", JOptionPane.PLAIN_MESSAGE);
		mainFrame.controller().userLogout(); // Logout function from controller
	}
}