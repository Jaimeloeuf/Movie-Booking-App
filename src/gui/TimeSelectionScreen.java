package gui;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;

import dataStorage.Movie;
import dataStorage.TimeSlot;

// Displays all screening time and respective avail seats for the selected movie from MovieDetailsScreen 
public class TimeSelectionScreen extends JPanel {
	private static Movie movie;
	private static JComboBox timeSlotCB;
	private static MainFrame mainFrame;
	
	public TimeSelectionScreen(MainFrame mf) {
		this.setSize(400, 300);
		mainFrame = mf; // Setting the reference to the constructor field
		setLayout(null);  // Used for the event handler, need this to detect user selections
		mainFrame.setTitle("Time and Seats Selection");
		
		JLabel lblChooseAShow = new JLabel("Choose a Show time");
		lblChooseAShow.setBounds(34, 28, 187, 14);
		this.add(lblChooseAShow);
		
		movie = mainFrame.controller().getCurrentMovieSelection();
		
		timeSlotCB = new JComboBox();
		for(int i = 0; i<movie.getTimeSlotVector().size(); i++)
			timeSlotCB.addItem(movie.getTimeSlotVector().get(i).asJComboBox_element());
		timeSlotCB.setBounds(34, 53, 238, 20);
		timeSlotCB.addActionListener(new ComboBoxListener());
		this.add(timeSlotCB);
		TimeSlot.currentTimeSlot = movie.getTimeSlotVector().get(0); // Initialize currentTimeSlot as first JComboBox element
		
		JLabel lblChooseSeats = new JLabel("Choose Seat(s)");
		lblChooseSeats.setBounds(34, 84, 113, 14);
		this.add(lblChooseSeats);
		
		JLabel lblScreen = new JLabel("Screen");
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblScreen.setBounds(137, 102, 113, 14);
		this.add(lblScreen);
		
		JButton btnBookMovie = new JButton("Book Movie!");
		btnBookMovie.addActionListener(new ActionListener() { // Ask user to login before showing current bookings
			public void actionPerformed(ActionEvent arg0) {showBookingScreen();}
		});
		btnBookMovie.setBounds(238, 230, 110, 23);
		this.add(btnBookMovie);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { mainFrame.showMovieDetailsScreen(); }
		});
		btnBack.setBounds(34, 230, 113, 23);
		this.add(btnBack);
		
		JButton button = new JButton("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button.setBounds(34, 127, 66, 23);
		add(button);
		
		JButton button_1 = new JButton("2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_1.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_1.setBounds(118, 127, 66, 23);
		add(button_1);
		
		JButton button_2 = new JButton("3");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_2.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_2.setBounds(206, 127, 66, 23);
		add(button_2);
		
		JButton button_9 = new JButton("4");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_9.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_9.setBounds(282, 127, 66, 23);
		add(button_9);
		
		JButton button_3 = new JButton("5");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_3.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_3.setBounds(34, 160, 66, 23);
		add(button_3);
		
		JButton button_4 = new JButton("6");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_4.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_4.setBounds(118, 161, 66, 23);
		add(button_4);
		
		JButton button_5 = new JButton("7");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_5.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_5.setBounds(206, 161, 66, 23);
		add(button_5);
		
		JButton button_6 = new JButton("8");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_6.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_6.setBounds(282, 161, 66, 23);
		add(button_6);
		
		JButton button_7 = new JButton("9");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_7.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_7.setBounds(34, 194, 66, 23);
		add(button_7);
		
		JButton button_8 = new JButton("10");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_8.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_8.setBounds(118, 194, 66, 23);
		add(button_8);
		
		JButton button_10 = new JButton("11");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_10.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_10.setBounds(206, 194, 66, 23);
		add(button_10);
		
		JButton button_11 = new JButton("12");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				button_11.setText("x");
				mainFrame.controller().addSeats();
			}
		});
		button_11.setBounds(282, 194, 66, 23);
		add(button_11);
	}
	
	private void showBookingScreen() { // Private function for action listener inner class to access
		if(mainFrame.controller().getCurrentUser() == null) // User has yet to log in
			mainFrame.showLoginScreen();
		else
			mainFrame.showBookingDetailsScreen();
	}
	private class seatSelected implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			
		}
	}
	private class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			for(int i = 0; i<movie.getTimeSlotVector().size(); i++){
				if(timeSlotCB.getSelectedItem().equals(movie.getTimeSlotVector().get(i).asJComboBox_element())) {
					TimeSlot.currentTimeSlot = movie.getTimeSlotVector().get(i); // what the user selects
//					displaySeats(); // Call function to parse
					break; // Break out of this 'for' loop
				}
			}
		}
	}
//	private void displaySeats() {
//		TimeSlot.currentTimeSlot.getSeats(); // Use this boolean array for parsing
//	}
}
