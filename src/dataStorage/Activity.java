package dataStorage;

import gui.MainFrame;

public class Activity {
	private static int activity_count = 0;
	private int ID; // Read only
	private String action = null; // Read only, Set only using constructor
	private Booking booking; // Read only, Set only using constructor
	private static MainFrame mainFrame;
	
	public Activity(MainFrame mf, String action, Booking booking) { // Overloaded constructor
		mainFrame = mf;
		this.ID = ++activity_count;
		
		if(mainFrame.controller().getCurrentUser().getAccessLevel() && (action.equals("update") || action.equals("delete")))
				this.action = action; // If Staff activity type is correct
		else if(action.equals("book")) // If Customer activity type is correct
			this.action = action;
		else
			System.out.println("Error. Invalid activity type");
		
		this.booking = booking;
	}
	
	public int getID(){return ID;}
	public String getAction(){return action;}
	public Booking getBooking(){return booking;}
	
	public String asJList_element() {
		return new String("<html>Activity " + String.valueOf(this.ID) + ": &nbsp;&nbsp;Action type: '" + this.action + "'<br>" +  
				"BookingID: &nbsp;" + String.valueOf(booking.getBookingID()) + "<br>" +
				"Movie: &nbsp;" + booking.getMovie().getTitle() + "<br>" +
				booking.getTimeSlot().asJComboBox_element() + "<hr></html>");
	}
	public static void resetCount(){activity_count = 0;}
}