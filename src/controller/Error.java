package controller;

public class Error { // Class for error handling.
	public static int count = 0; // Error count for current session.
	
	private int errorID;
	
	public Error() { // Default null constructor
		this("general unspecified error");
	}
	public Error(String error_msg)
	{
//		this.errorID = date * 10 + ++count; // Move date all to the tens place before adding in the count. a date and a count makes up the UID
		// write the error to file before closing stream
	}
	
	
}