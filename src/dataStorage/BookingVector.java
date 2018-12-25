package dataStorage;

import java.io.File;
import java.io.FileWriter;
import java.util.Vector;
import com.thoughtworks.xstream.XStream;
import controller.Config;

public class BookingVector {
//	private static XStream xstream = new XStream();
	private Vector<Booking> bookingVector = new Vector<Booking>(5,1);
	
	public BookingVector() {}
	
	public int size(){return bookingVector.size();} 
	public void add(Booking booking){bookingVector.add(booking);}
	public Booking get(int index) {
		if(index < size())
			return bookingVector.get(index);
		else
			return null; // Throw exception here
	}
	public void deleteBooking(int index) {
		if(index < size())
			bookingVector.removeElementAt(index);
		else {} // Throw exception
	}
}