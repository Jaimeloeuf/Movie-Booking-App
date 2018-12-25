package dataStorage;

// This class is a enhanced version of Booking class for Staff/BookingController to use
// The difference between the 2 Booking classes is that this class contains a User Object too
public class DetailedBooking {
	private Booking booking;
	private User user;
	
	public DetailedBooking(Booking booking, Object user){
		this.booking = booking;
		if(user != null)
			this.user = (User) user;
	}
	public Booking getBooking(){return booking;}
	public User getUser(){return user;}
	public String asJList_element(){
		return ("<html>User: " + this.user.getUserName() + "<br>" + booking.asJList_element()); 
	}
}