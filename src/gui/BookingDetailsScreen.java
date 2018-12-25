package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dataStorage.Activity;
import dataStorage.ActivityVector;
import dataStorage.Booking;
import dataStorage.TimeSlot;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Font;

// Shows details of the booking that user is about to save/make.
public class BookingDetailsScreen extends JPanel{
	private static MainFrame mainFrame;
	
	public BookingDetailsScreen(MainFrame mf) {
		mainFrame = mf;
		setLayout(null);
		mainFrame.setTitle("Booking Details");
		
		// To fix below, keep the name of the obj as "booking" for compatibility with other code
		Booking booking = new Booking("2015/7/10/1300", mainFrame.controller().getCurrentMovieSelection(), TimeSlot.currentTimeSlot);
		// Replace date with current date / time
		
		JLabel lblBookingId = new JLabel("Booking ID:  " + booking.getBookingID());
		lblBookingId.setBounds(21, 29, 102, 14);
		this.add(lblBookingId);
		
		JLabel lblMovieId = new JLabel("Movie ID:  " + booking.getMovie().getID());
		lblMovieId.setBounds(21, 54, 67, 14);
		this.add(lblMovieId);
		
		JLabel lblMovieName = new JLabel("Movie Name:  " + booking.getMovie().getTitle());
		lblMovieName.setBounds(98, 54, 177, 14);
		this.add(lblMovieName);
		
		JLabel lblTime = new JLabel(booking.getTimeSlot().asJComboBox_element());
		lblTime.setBounds(21, 79, 250, 40);
		lblTime.setBorder(BorderFactory.createTitledBorder("TimeSlot Choosen:"));
		this.add(lblTime);
		
		JLabel lblSeats = new JLabel("Seat(s):");
		lblSeats.setBounds(21, 125, 55, 14);
		this.add(lblSeats);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 142, 102, 59);
		this.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setColumnHeaderView(list);
		
		JLabel lblPrice = new JLabel("Price: $" + String.valueOf(mainFrame.controller().getSeats()*13));
		lblPrice.setBounds(21, 212, 85, 14);
		this.add(lblPrice);
		
		JLabel lblStatus = new JLabel((booking.getPaymentStatus())?"Paid":"Unpaid");
		lblStatus.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(146, 120, 125, 47);
		lblStatus.setBorder(BorderFactory.createTitledBorder("Payment Status:"));
		this.add(lblStatus);
		
		JButton btnSave = new JButton("Save Booking!");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.controller().resetSeats();
				booking.setNumOfSeats(mainFrame.controller().getSeats());
				mainFrame.controller().getCurrentUser().getBookingVector().add(booking);
				JOptionPane.showMessageDialog(null, "Booking Saved!\nPlease pay at the counter with\nBooking ID to confirm your booking!",
						"GW Entertainment", JOptionPane.WARNING_MESSAGE);
				ActivityVector.add(new Activity(mainFrame, "book", booking)); // Record user activity/action
				mainFrame.showHomeScreen();
			}
		});
		btnSave.setBounds(146, 208, 125, 23);
		this.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.controller().resetSeats();
				mainFrame.showTimeSelectionScreen();
			}
		});
		btnBack.setBounds(146, 178, 125, 23);
		this.add(btnBack);
	}
}