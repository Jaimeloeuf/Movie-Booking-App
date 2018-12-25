package dataStorage;

public class Booking{
	public static int totalNumOfBookings = 0;
	private int bookingID;
	private String bookingDate; private int year, month, day, time; // Time is stored in 24Hour clock format, 4 digits as one int
	private Movie movie;
	private TimeSlot timeSlot; // Stores the timeSlot the user selected
	private boolean paid = false; // Default value is always "not paid"
	
	private int number_of_seats = 0;
	
	public Booking(){} // Throw error when null constructor is called
	public Booking(String bookingDate, Movie movie, TimeSlot timeSlot){
		this.bookingID = ++totalNumOfBookings;
//		if(bookingDate after currentDate)
//			error
//		else
		this.bookingDate = bookingDate;
		this.movie = movie;
		this.timeSlot = timeSlot;
	}
	public int getBookingID(){return bookingID;}
	public String getBookingDate(){return bookingDate;}
	public Movie getMovie(){return movie;}
	public TimeSlot getTimeSlot(){return timeSlot;}
	public boolean getPaymentStatus(){return paid;}
	public void setPaymentStatus(boolean paid, User currentUser){
		if(currentUser.getAccessLevel()) // If the user is a staff
			this.paid = paid;
	}
	public String asJList_element(){
		return "ID: " + bookingID + " &nbsp;" + "Movie: &nbsp;" + movie.getTitle() +
				"<br>" + timeSlot.asJComboBox_element() + "<hr></html>";
	}
	
	public void set_seats(String seats) {
		
	}
	public void setNumOfSeats(int num){number_of_seats = num;}
	public int getNumOfSeats(){return number_of_seats;}
}