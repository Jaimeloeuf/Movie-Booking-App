package dataStorage;

import java.util.Vector;

public class Movie {
	private int ID, ratings; // ratings in terms of percentage
	private String title, director, description; // Read only after Object instantiation
	private Vector<Integer> GenreIDVector = new Vector<Integer>();
	private Vector<TimeSlot> timeSlotVector = new Vector<TimeSlot>();
	
	/*	Movie class is and Abstract Data Type.
	 * 	Instance Fields:
	 * 	1. Movie ID
	 *  2. Title
	 *  3. Description
	 *  4. Director
	 *  5. ratings as a percentage
	 *  6. Vector<Integer> of genreIDs
	 *  7. Vector<TimeSlots>
	 * */
	
	public Movie(){this(0, null, null, null, 0);} // Default null constructor invoking overloaded constructor
	
	public Movie(int ID, String title, String director, String description, int ratings){
		this.ID = ID;
		this.title = title;
		this.director = director;
		this.description = description;
		this.ratings = ratings;
	}
	
	public int getID(){return ID;}
	public String getTitle(){return title;}
	public String getDirector() {return director;}
	public String getDescription(){return description;}
	public int getRatings(){return ratings;}

	public Vector<Integer> getGenreIDVector(){return GenreIDVector;}
	public void addGenreID(Integer genreID){GenreIDVector.add(genreID);}

	public Vector<TimeSlot> getTimeSlotVector(){return timeSlotVector;}
	public void addTimeSlot(TimeSlot timeSlot){timeSlotVector.add(timeSlot);}
}