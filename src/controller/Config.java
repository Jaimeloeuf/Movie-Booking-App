package controller;

public class Config { // Fully static class used for store configurations
	private static String movie_xml_file_location = "dataFiles/movies.xml";
	private static String genre_xml_file_location = "dataFiles/genre.xml";
	private static String user_xml_file_location = "dataFiles/user.xml";
	private static String booking_xml_folder = "dataFiles/Bookings";
	
	public static String getMovieFileLocation() {return movie_xml_file_location;}
	public static String getGenreFileLocation() {return genre_xml_file_location ;}
	public static String getUserFileLocation() {return user_xml_file_location; }
	public static String getBookingFolderLocation() {return booking_xml_folder;}
}