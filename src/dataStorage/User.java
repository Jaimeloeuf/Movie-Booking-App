package dataStorage;

public class User {
	private int ID;
	private boolean staffAccess = false;
	private String name;
	private String userName;
	private String password;
	private BookingVector bookingVector;
	
	public User(){} // Throw error here when null constructor is used
	public User(int ID, String UserType, String name, String userName, String password) {
		this.ID = ID;
		this.staffAccess = (UserType.equals(new String("staff"))) ? true:false;
		this.name = name;
		this.userName = userName;
		this.password = password;
		bookingVector = new BookingVector(); // Create a new BookingVector object for this User instance
	}
	public int getID() {return ID;}
	public boolean getAccessLevel() {return staffAccess;}
	public String getName() {return name;}
	public String getUserName() {return userName;}
	
	public boolean validatePassword(String password){return password.equals(this.password);}
	public BookingVector getBookingVector(){return bookingVector;}
}