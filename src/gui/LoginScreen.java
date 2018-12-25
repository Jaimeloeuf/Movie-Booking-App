package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JOptionPane;

// Screen for user to log in
public class LoginScreen extends JPanel{
	private JTextField usernameField;
	private JPasswordField passwordField;
	private static MainFrame mainFrame;
	
	public LoginScreen(MainFrame mf) {
		mainFrame = mf; // Setting the reference to the constructor field
		setLayout(null);
		mainFrame.setTitle("Login Page");
		
		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setBounds(28, 39, 136, 14);
		this.add(lblLoginPage);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(28, 86, 73, 14);
		this.add(lblUsername);
		
		usernameField = new JTextField(); // Input field for username
		usernameField.setBounds(122, 83, 209, 20);
		this.add(usernameField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(28, 117, 73, 14);
		this.add(lblPassword);
		
		passwordField = new JPasswordField(); // Input field for password
		passwordField.setBounds(122, 114, 209, 20);
		this.add(passwordField);
		
		JButton btnCancel = new JButton("Cancel and Exit");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){mainFrame.controller().userLogout();}
		}); // User logout method from controller clears and resets all resources before showing Welcome Screen again.
		btnCancel.setBounds(28, 159, 145, 23);
		this.add(btnCancel);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { validateLogin(); }
		});
		btnLogin.setBounds(201, 159, 130, 23);
		this.add(btnLogin);
	}
	
	private void validateLogin() { // Private function for action listener inner class to access
		if(mainFrame.controller().userLogin(usernameField.getText(), passwordField.getPassword()))
		{
			if(mainFrame.controller().getCurrentMovieSelection() == null) { // If user logs in directly from welcome screen
				if(mainFrame.controller().getCurrentUser().getAccessLevel()) // If user is a staff
					mainFrame.showStaffScreen(); // Switch to Staff Screen after user verification.
				else // If the user is a customer
					mainFrame.showHomeScreen(); // Switch to the Home screen after user verification.
			}
			else { // If user logs in after selecting movie to book
				if(mainFrame.controller().getCurrentUser().getAccessLevel()) {// If User is a Staff but tries to book movie
					JOptionPane.showMessageDialog(null, "Error! Do not use Staff account to book movies!", "ERROR", JOptionPane.ERROR_MESSAGE);
					mainFrame.controller().resetCurrentUser(); // Reset the currentUser so User has to re-login
					mainFrame.showMovieDetailsScreen(); // Send the User back to the previous page
				}
				else
					mainFrame.showBookingDetailsScreen();
			}
		}
		else{ // Clear the fields and ask user to try again.
			usernameField.setText(null); // Clear username field.
			passwordField.setText(null); // Clear password field.
			JOptionPane.showMessageDialog(null, "Invalid User name. Please try again!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}