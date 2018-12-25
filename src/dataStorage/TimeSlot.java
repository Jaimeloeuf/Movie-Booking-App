package dataStorage;

// TimeSlot stores time of show and seat availability 
public final class TimeSlot { // Abstract Data Structure that cannot be extended
	private String movieTimeSlot; private int year, month, day, time; // Time is stored in 24Hour clock format, 4 digits as one int
	private String[] token; // Token array of timeSlot for this object

	// Static variables used for parsing
	private static String[] static_token;
	public static TimeSlot currentTimeSlot;
	
	public TimeSlot() {this(null);} // Throw exception here
	public TimeSlot(String s) {parseTimeSlot(s);}
	
	public static String[] getToken(){return static_token;}
	public static void parse(String s){static_token = s.split("/");}
	
	private void parseTimeSlot(String s) {
		this.movieTimeSlot = s;
		parse(s);
		this.token = static_token;
		this.year = Integer.parseInt(token[0]);
		this.month = Integer.parseInt(token[1]);
		this.day = Integer.parseInt(token[2]);
		this.time = Integer.parseInt(token[3]);
	}
//	public String getMovieTime(){return movieTimeSlot;} // Currently unused
	public String asJComboBox_element() {
		TimeSlot.parse(movieTimeSlot);
		return "Date: " + TimeSlot.getToken()[0] + "/" + TimeSlot.getToken()[1] + "/" + TimeSlot.getToken()[2] + "  Time: " + TimeSlot.getToken()[3];
	}
	
	
	
	private static int numOfSeatsForCurrentBooking = 0;
	private String[] selectedSeats; // Seats are strings with the format "Row Alphabet" by "Column Number"
	public void selectNewSeats(String seats) {
		if(seats.length() == 2 && Character.isLetter(seats.charAt(0)) && Character.isDigit(seats.charAt(1))) // Error checking
			selectedSeats[++numOfSeatsForCurrentBooking] = seats;
		else
			System.out.println("Error with seat selected"); // Throw error
	}
}