package dataStorage;

public class Genre { // Genre class is an abstract DataType, used as DataStructure.
	private static int num_of_genres = 0;
	private int genre_ID; // Read only via getID, protected data.
	private String name; // Read only via getName.
	private String description; // Read only via getDescription.
	
	public Genre(){} // Default null constructor, Throw exception here as Genre Attributes must be set!
	public Genre(int ID, String name, String description){
		this.genre_ID = ID;
		this.name = name;
		this.description = description;
	}
	public int getID() {return genre_ID;}
	public String getName() {return name;}
	public String getDescription() {return description;}
}