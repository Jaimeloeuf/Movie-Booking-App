package controller;

import java.util.Vector;

import dataStorage.Booking;
import dataStorage.DetailedBooking;
import dataStorage.User;
import dataStorage.UserVector;

//Booking controller class controls all the bookingvectors of all users

public class BookingController { // Fully static class

	public static void init(){
		// At start up, delete all bookings that have passed show time
	}
	
	public static User getUserWithBookingID(int bookingID){
		for(int i = 0; i<UserVector.size(); i++)
			for(int j = 0; j<UserVector.get(i).getBookingVector().size(); j++)
				if(bookingID == UserVector.get(i).getBookingVector().get(j).getBookingID())
					return UserVector.get(i);
		return null; // If booking ID is invalid
	}
	public static User deleteBooking(int bookingID){
		for(int i = 0; i<UserVector.size(); i++)
			for(int j = 0; j<UserVector.get(i).getBookingVector().size(); j++)
				if(bookingID == UserVector.get(i).getBookingVector().get(j).getBookingID())
					UserVector.get(i).getBookingVector().deleteBooking(j);
		return null; // If booking ID is invalid
	}
	public static int totalBookings() {
		int total = 0;
		for(int i = 0; i<UserVector.size(); i++)
			total += UserVector.get(i).getBookingVector().size();
		return total;
	}
	public static int totalPaidBookings() {
		int total = 0;
		for(int i = 0; i<UserVector.size(); i++)
			for(int j = 0; j<UserVector.get(i).getBookingVector().size(); j++)
				if(UserVector.get(i).getBookingVector().get(j).getPaymentStatus())
					++total;
		return total;
	}
	public static int totalUnpaidBookings() {
		int total = 0;
		for(int i = 0; i<UserVector.size(); i++)
			for(int j = 0; j<UserVector.get(i).getBookingVector().size(); j++)
				if(!UserVector.get(i).getBookingVector().get(j).getPaymentStatus())
					++total;
		return total;
	}
	
	public static Vector<DetailedBooking> bookings = new Vector<DetailedBooking>(5, 2);
	
	public static Vector<DetailedBooking> getAllBookings(){
		bookings.clear(); // Reset the vector before using!
		for(int i = 0; i<UserVector.size(); i++)
			for(int j = 0; j<UserVector.get(i).getBookingVector().size(); j++)
				bookings.add(new DetailedBooking(UserVector.get(i).getBookingVector().get(j), UserVector.get(i)));
		return bookings;
	}
	public static Vector<DetailedBooking> getPaidBookings(){
		bookings.clear(); // Reset the vector before using!
		for(int i = 0; i<UserVector.size(); i++)
			for(int j = 0; j<UserVector.get(i).getBookingVector().size(); j++)
				if(UserVector.get(i).getBookingVector().get(j).getPaymentStatus())
					bookings.add(new DetailedBooking(UserVector.get(i).getBookingVector().get(j), UserVector.get(i)));
		return bookings;
	}
	public static Vector<DetailedBooking> getUnpaidBookings(){
		bookings.clear(); // Reset the vector before using!
		for(int i = 0; i<UserVector.size(); i++)
			for(int j = 0; j<UserVector.get(i).getBookingVector().size(); j++)
				if(!UserVector.get(i).getBookingVector().get(j).getPaymentStatus())
					bookings.add(new DetailedBooking(UserVector.get(i).getBookingVector().get(j), UserVector.get(i)));
		return bookings;
	}
	
	public static Booking getBookingWithID(int ID) {
		for(int i = 0; i<UserVector.size(); i++)
			for(int j = 0; j<UserVector.get(i).getBookingVector().size(); j++)
				if(ID == UserVector.get(i).getBookingVector().get(j).getBookingID())
					return UserVector.get(i).getBookingVector().get(j);
		return null; // Return null if Booking ID is invalid
	}
}