package gui;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

// Payment page for the staff?? If not needed can delete le
// Show the staff how much he needs to collect from the user

public class PaymentPage extends JPanel {
	private MainFrame mainFrame;
	
	public PaymentPage(MainFrame mainFrame) {
		this.mainFrame = mainFrame; // Setting the reference to the constructor field
		setLayout(null);  // Used for the event handler, need this to detect user selections
		mainFrame.setTitle("GW Movie Booking System  " + "(Payments Page)");
		
		Label label = new Label("Something here");
		label.setAlignment(Label.CENTER);
		label.setBounds(119, 20, 146, 37); // lblName.setBounds(89, 11, 148, 14);
		this.add(label);
		
		JButton btnBrowseMovies = new JButton("Browse Movies");
		btnBrowseMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { }
		});
		btnBrowseMovies.setBounds(106, 105, 165, 37);
		this.add(btnBrowseMovies);
		
		JButton btnBackToHome = new JButton("Back to HOME");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { showHomeScreen(); }
		});
		btnBackToHome.setBounds(106, 169, 165, 37);
		this.add(btnBackToHome);
	}
	
	private void showHomeScreen() { // Private function for action listener inner class to access
		this.mainFrame.showHomeScreen();
	}
}
