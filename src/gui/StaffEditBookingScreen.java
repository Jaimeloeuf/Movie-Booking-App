package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controller.BookingController;
import dataStorage.Activity;
import dataStorage.ActivityVector;
import dataStorage.Booking;
import dataStorage.TimeSlot;
import dataStorage.User;

public class StaffEditBookingScreen extends JPanel{
	private static MainFrame mainFrame;
	
	public StaffEditBookingScreen(MainFrame mf) {
		this.setSize(400, 300);
		mainFrame = mf;
		setLayout(null);
		mainFrame.setTitle("Booking Details");
		
		Booking booking = mainFrame.controller().getCurrentBookingSelection().getBooking();
		User bookingUser = mainFrame.controller().getCurrentBookingSelection().getUser();
		
		JLabel lblBookingId = new JLabel("<html>ID: " + booking.getBookingID() + " &nbsp;&nbsp;Booking made by: " +
										bookingUser.getName() + "<br>UserID: " + bookingUser.getID() +
										" &nbsp;&nbsp;UserName: " + bookingUser.getUserName() + "</html>");
		lblBookingId.setBounds(21, 11, 256, 52);
		lblBookingId.setBorder(BorderFactory.createTitledBorder("Booking:"));
		this.add(lblBookingId);
		
		JLabel lblMovieId = new JLabel("Movie ID:  " + booking.getMovie().getID());
		lblMovieId.setBounds(21, 62, 67, 14);
		this.add(lblMovieId);
		
		JLabel lblMovieName = new JLabel("Movie Name:  " + booking.getMovie().getTitle());
		lblMovieName.setBounds(100, 62, 177, 14);
		this.add(lblMovieName);
		
		JLabel lblTime = new JLabel(booking.getTimeSlot().asJComboBox_element());
		lblTime.setBounds(21, 84, 250, 40);
		lblTime.setBorder(BorderFactory.createTitledBorder("TimeSlot Booked:"));
		this.add(lblTime);
		
		JLabel lblSeats = new JLabel("Seat(s):");
		lblSeats.setBounds(21, 135, 55, 14);
		this.add(lblSeats);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 154, 102, 58);
		this.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setColumnHeaderView(list);
		
		JLabel lblPrice = new JLabel("Price: $" + String.valueOf(booking.getNumOfSeats()*13));
		lblPrice.setBounds(21, 217, 85, 14);
		this.add(lblPrice);
		
		JLabel lblStatus = new JLabel((booking.getPaymentStatus())?"Paid":"Unpaid");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(152, 123, 125, 40);
		lblStatus.setBorder(BorderFactory.createTitledBorder("Payment Status:"));
		this.add(lblStatus);
		
		JButton btnPaymentCollected = new JButton("Payment Collected");
		btnPaymentCollected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				booking.setPaymentStatus(true, mainFrame.controller().getCurrentUser());
				lblStatus.setText((booking.getPaymentStatus())?"Paid":"Unpaid");
				ActivityVector.add(new Activity(mainFrame, "update", booking)); // Record user activity/action
				remove(btnPaymentCollected); // Remove this button after being pressed once
				mainFrame.showStaffEditBookingScreen(); // Repaint
			}
		});
		btnPaymentCollected.setBounds(133, 169, 144, 28);
		this.add(btnPaymentCollected);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				mainFrame.controller().setCurrentBookingSelection(null);
				mainFrame.showStaffScreen();
			}
		});
		btnBack.setBounds(146, 198, 125, 23);
		this.add(btnBack);
		
		JButton btnDelete = new JButton("Delete Booking");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				BookingController.deleteBooking(booking.getBookingID());
				JOptionPane.showMessageDialog(null, "Booking Deleted!", "Staff action", JOptionPane.WARNING_MESSAGE);
				ActivityVector.add(new Activity(mainFrame, "delete", booking)); // Record user activity/action
				mainFrame.showStaffScreen(); // Go back to staff screen after deleting bookings
			}
		});
		btnDelete.setBounds(146, 222, 125, 23);
		this.add(btnDelete);
	}
}