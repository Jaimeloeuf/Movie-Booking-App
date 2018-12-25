package controller;

import dataStorage.Activity;
import dataStorage.ActivityVector;
import dataStorage.BookingVector;
import dataStorage.DetailedBooking;
import dataStorage.GenreVector;
import dataStorage.Movie;
import dataStorage.MovieVector;
import dataStorage.User;
import dataStorage.UserVector;
import gui.MainFrame;

public class Controller {
	private static User currentUser = null; // Only has a valid reference after Login.
	private static Movie currentMovieSelection = null;
	private static DetailedBooking currentBookingSelection = null;
	private static Activity currentActivitySelection = null;
	private static MainFrame mainFrame; // MainFrame of this controller.
	private static int seats;
	
	public Controller(MainFrame mf) { // Default null constructor
		mainFrame = mf;
		// Load all the data of the movies and all from dataStorage first
		GenreVector.init();
		MovieVector.init();
		UserVector.init();
	}
	
	public User getCurrentUser(){return currentUser;}
	public Movie getCurrentMovieSelection(){return currentMovieSelection;}
	public void setCurrentMovieSelection(Movie m){currentMovieSelection = m;}
	public Activity getCurrentActivitySelection(){return currentActivitySelection;}
	public void setCurrentActivitySelection(Activity a){currentActivitySelection = a;}
	public DetailedBooking getCurrentBookingSelection(){return currentBookingSelection;}
	public void setCurrentBookingSelection(DetailedBooking b){currentBookingSelection = b;}
	public void addSeats(){++seats;}
	public int getSeats(){return seats;}
	public void resetSeats(){seats=0;}
	
	public boolean userLogin(String username, char[] cs_password) { // User verification function for GUI to call.
		// returns a boolean value to indicate validation outcome.
		// If verification fails, more details can be found in error logs.
		currentUser = UserVector.getUser(username); // Find user using the username first.
		if(currentUser == null) { // no such user exists in userVector.
			// call error log and record error So gui can read find out what went wrong
			return false;
		}
		else if(currentUser.validatePassword(new String(cs_password))) {
			return true; // return true to the gui.
		}
		else{ // User exist but password is wrong!
			currentUser = null; // currentUser reference is resetted to null if password is invalid.
			return false; // gui can use JOptionPane to display error and ask if user wants to try again.
		}
	}
	public void resetCurrentUser(){currentUser = null;}
	public void userLogout() {
		// Save all vectors to file!
		GenreVector.saveToFile();
		MovieVector.saveToFile();
		UserVector.saveToFile();
		
		// Reset everything before showing welcome screen again
		setCurrentMovieSelection(null); // Reset current movie selection during logout.
		setCurrentBookingSelection(null); // Reset current booking selection during logout.
		ActivityVector.resetVector(); // Clear activity vector of current user session
		resetCurrentUser(); // Reset currentUser reference during logout.
		mainFrame.showWelcomeScreen(); // Use mainframe to move back to the welcome page.
	}
}