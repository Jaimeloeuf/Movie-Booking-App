package gui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import controller.Controller;

public class MainFrame extends JFrame {
	private CardLayout card = new CardLayout();
	private Controller cont; // Controller object for this MainFrame object.
	
	public MainFrame() { // Default Constructor
		cont = new Controller(this); // Create controller object to initialize all data and pass this MainFrame's reference to it too.
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){cont.userLogout();}
		});
		
		// Gui initialization
		this.setTitle("GW Movie Booking System");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(this.card); // Passing the card to the layout.
		this.setLocationRelativeTo(null); // Center the display screen.
		this.showWelcomeScreen(); // Display the welcome screen on startup.
		this.setVisible(true); // Setting visibility of the Application.
	}

	public Controller controller() {
		return this.cont; // getController function.
	}

	public void showStaffScreen() {
		StaffScreen ss = new StaffScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(ss, "StaffScreen");
		this.card.show(this.getContentPane(), "StaffScreen"); // Putting this card to the top to display.
	}
	public void showWelcomeScreen() {
		WelcomeScreen ws = new WelcomeScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(ws, "WelcomeScreen");
		this.card.show(this.getContentPane(), "WelcomeScreen"); // Putting this card to the top to display.
	}
	public void showLoginScreen() {
		LoginScreen lis = new LoginScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(lis, "LoginScreen");
		this.card.show(this.getContentPane(), "LoginScreen"); // Putting this card to the top to display.
	}
	public void showBookingDetailsScreen() {
		BookingDetailsScreen bs = new BookingDetailsScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(bs, "BookingScreen");
		this.card.show(this.getContentPane(), "BookingScreen"); // Putting this card to the top to display.
	}
	public void showExistingBookingsScreen() {
		ExistingBookingsScreen ebs = new ExistingBookingsScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(ebs, "ExistingBookingsScreen");
		this.card.show(this.getContentPane(), "ExistingBookingsScreen"); // Putting this card to the top to display.
	}
	public void showAvailMoviesScreen() {
		AvailMoviesScreen ams = new AvailMoviesScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(ams, "AvailMoviesScreen");
		this.card.show(this.getContentPane(), "AvailMoviesScreen"); // Putting this card to the top to display.
	}
	public void showHomeScreen() {
		HomeScreen hs = new HomeScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(hs, "HomeScreen");
		this.card.show(this.getContentPane(), "HomeScreen"); // Putting this card to the top to display.
	}
	public void showTimeSelectionScreen() {
		TimeSelectionScreen tss = new TimeSelectionScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(tss, "TimeSelectionScreen");
		this.card.show(this.getContentPane(), "TimeSelectionScreen"); // Putting this card to the top to display.
	}
	public void showMovieDetailsScreen() {
		MovieDetailsScreen mds = new MovieDetailsScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(mds, "MovieDetailsScreen");
		this.card.show(this.getContentPane(), "MovieDetailsScreen"); // Putting this card to the top to display.
	}
	public void showActivityDetailsScreen() {
		ActivityDetailsScreen ads = new ActivityDetailsScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(ads, "ActivityDetailsScreen");
		this.card.show(this.getContentPane(), "ActivityDetailsScreen"); // Putting this card to the top to display.
	}
	public void showLogoutScreen() {
		LogoutScreen los = new LogoutScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(los, "LogoutScreen");
		this.card.show(this.getContentPane(), "LogoutScreen"); // Putting this card to the top to display.
	}
	public void showExistingBookingDetailsScreen() {
		ExistingBookingDetailsScreen ebds = new ExistingBookingDetailsScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(ebds, "ExistingBookingDetailsScreen");
		this.card.show(this.getContentPane(), "ExistingBookingDetailsScreen"); // Putting this card to the top to display.
	}
	public void showStaffEditBookingScreen() {
		StaffEditBookingScreen sebs = new StaffEditBookingScreen(this); // Calling the constructor and passing in "this" Mainframe object
		this.add(sebs, "StaffEditBookingScreen");
		this.card.show(this.getContentPane(), "StaffEditBookingScreen"); // Putting this card to the top to display.
	}
	//	public void showScreen() {
	//	Screen  = new Screen(this); // Calling the constructor and passing in "this" Mainframe object
	//	this.add(, "Screen");
	//	this.card.show(this.getContentPane(), "Screen"); // Putting this card to the top to display.
	//}

	public static void main(String[] args){
		new MainFrame();
	}
}