package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.BookingController;
import dataStorage.Activity;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// This screen shows the details of the selected Activity in the JList on the logout screen
public class ActivityDetailsScreen extends JPanel{
	private static MainFrame mainFrame;
	
	public ActivityDetailsScreen(MainFrame mf) {
		mainFrame = mf;
		setLayout(null);
		mainFrame.setTitle("Activity Details");
		Activity a = mainFrame.controller().getCurrentActivitySelection();
		
		JLabel lblActivityID = new JLabel("Activity ID: " + a.getID());
		lblActivityID.setBounds(10, 34, 79, 14);
		this.add(lblActivityID);
		
		JLabel lblActionType = new JLabel("Action Type: " + a.getAction());
		lblActionType.setHorizontalAlignment(SwingConstants.CENTER);
		lblActionType.setBounds(192, 34, 127, 14);
		this.add(lblActionType);
		
		JLabel lblBookingId = new JLabel("Booking ID: " + a.getBooking().getBookingID());
		lblBookingId.setBounds(10, 52, 79, 14);
		this.add(lblBookingId);
		
		JLabel lblDescription = new JLabel("<html>Movie Title: &nbsp;" + a.getBooking().getMovie().getTitle() + "<hr>" +
				"TimeSlot booked: &nbsp;" + a.getBooking().getTimeSlot().asJComboBox_element() + "</html>");
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescription.setBounds(10, 117, 295, 71);
		lblDescription.setBorder(BorderFactory.createTitledBorder("Movie Description:"));
		this.add(lblDescription);
		
		JLabel lblStatus = new JLabel((a.getBooking().getPaymentStatus())?"Paid":"Unpaid");
		lblStatus.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(204, 59, 101, 47);
		lblStatus.setBorder(BorderFactory.createTitledBorder("Payment Status:"));
		this.add(lblStatus);
		
		String userWhoMadeBooking;
		if(mainFrame.controller().getCurrentUser().getAccessLevel()) // If user is a staff
			userWhoMadeBooking = BookingController.getUserWithBookingID(a.getBooking().getBookingID()).getName();
		else
			userWhoMadeBooking = mainFrame.controller().getCurrentUser().getName();
		JLabel lblBookingBy = new JLabel(userWhoMadeBooking);
		lblBookingBy.setBounds(10, 74, 173, 37);
		lblBookingBy.setBorder(BorderFactory.createTitledBorder("Booking made by:"));
		this.add(lblBookingBy);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { mainFrame.showLogoutScreen(); }
		});
		btnBack.setBounds(10, 199, 127, 23);
		this.add(btnBack);
		
		JButton btnLogout = new JButton("Logout"); // Exact same logout button as the logoutScreen
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Thank you for using this app! Have a nice day!", "GW Entertainment", JOptionPane.PLAIN_MESSAGE);
				mainFrame.controller().userLogout(); // Logout function from controller
			}
		});
		btnLogout.setBounds(163, 199, 127, 23);
		this.add(btnLogout);
	}
}