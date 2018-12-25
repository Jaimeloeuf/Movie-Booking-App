package gui;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

// User Home screen after logging in
public class HomeScreen extends JPanel{
	private static MainFrame mainFrame;
	
	public HomeScreen(MainFrame mf) { // Default null constructor
		mainFrame = mf;
		setLayout(null);
		mainFrame.setTitle("Home Screen");
		
		Label label = new Label("Welcome back "+ mainFrame.controller().getCurrentUser().getName() + "!");
		label.setAlignment(Label.CENTER);
		label.setBounds(69, 37, 230, 29);
		this.add(label);

		JButton btnBrowseMovies = new JButton("Browse Movies");
		btnBrowseMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){mainFrame.showAvailMoviesScreen();}
		});
		btnBrowseMovies.setBounds(106, 72, 165, 37);
		this.add(btnBrowseMovies);

		JButton btnSeeBookings = new JButton("Existing Bookings");
		btnSeeBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){mainFrame.showExistingBookingsScreen();}
		});
		btnSeeBookings.setBounds(106, 120, 165, 37);
		this.add(btnSeeBookings);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){mainFrame.showLogoutScreen();}
		});
		btnLogout.setBounds(106, 168, 165, 37);
		this.add(btnLogout);
	}
}