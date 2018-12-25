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
public class ExistingBookingDetailsScreen extends JPanel{
	private static MainFrame mainFrame;
	
	public ExistingBookingDetailsScreen(MainFrame mf) {
		mainFrame = mf;
		setLayout(null);
		mainFrame.setTitle("Booking Details");
		
		Booking booking = mainFrame.controller().getCurrentBookingSelection().getBooking();
		
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
		lblTime.setBounds(21, 79, 254, 40);
		lblTime.setBorder(BorderFactory.createTitledBorder("TimeSlot Booked:"));
		this.add(lblTime);
		
		JLabel lblSeats = new JLabel("Seat(s):");
		lblSeats.setBounds(21, 125, 55, 14);
		this.add(lblSeats);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 142, 102, 59);
		this.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setColumnHeaderView(list);
		
		JLabel lblPrice = new JLabel("Price: $" + String.valueOf(booking.getNumOfSeats()*13));
		lblPrice.setBounds(21, 212, 85, 14);
		this.add(lblPrice);
		
		JLabel lblStatus = new JLabel((booking.getPaymentStatus())?"Paid":"Unpaid");
		lblStatus.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(150, 142, 125, 47);
		lblStatus.setBorder(BorderFactory.createTitledBorder("Payment Status:"));
		this.add(lblStatus);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { mainFrame.showExistingBookingsScreen(); }
		});
		btnBack.setBounds(150, 208, 125, 23);
		this.add(btnBack);
	}
}