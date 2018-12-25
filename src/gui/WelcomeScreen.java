package gui;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

// The first/front page of the application
public class WelcomeScreen extends JPanel{
	private static MainFrame mainFrame;
	
	public WelcomeScreen(MainFrame mf) { // Default null constructor
		mainFrame = mf; // Setting the reference to the constructor field
		setLayout(null);  // Used for the event handler, need this to detect user selections
		mainFrame.setTitle("Welcome");
		
		Label label = new Label("Welcome to GW Entertainment Movie Booking System!");
		label.setAlignment(Label.CENTER);
		label.setBounds(45, 55, 314, 44);
		this.add(label);
		
		JButton btnBrowseMovies = new JButton("Browse Movies");
		btnBrowseMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){mainFrame.showAvailMoviesScreen();}
		});
		btnBrowseMovies.setBounds(120, 127, 165, 37);
		this.add(btnBrowseMovies);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){mainFrame.showLoginScreen();}
		});
		btnLogin.setBounds(120, 175, 165, 37);
		this.add(btnLogin);
	}
}