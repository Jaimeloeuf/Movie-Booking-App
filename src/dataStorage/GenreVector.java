package dataStorage;

import java.io.File;
import java.io.FileWriter;
import java.util.Vector;
import com.thoughtworks.xstream.XStream;
import controller.Config;

public class GenreVector { // Fully static class
	private static XStream xstream = new XStream();
	private static Vector<Genre> genreVector = new Vector<Genre>(5,1);
	
	public static void init() { // populate with XML
		try{
			Genre[] gArr = (Genre[]) xstream.fromXML(new File(Config.getGenreFileLocation()));
			for(int i = 0; i<gArr.length; i++)
				add(gArr[i]);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Genre get(int index) {
		if(index < genreVector.size())
			return genreVector.get(index);
		else
			return null; // Throw exception here
	}
	public static void add(Genre genre) {
		if(genre != null && genre.getName() != null)
			genreVector.add(genre);
		else
			System.out.println("exception"); // Throw exception here
	}
	public static void saveToFile(){
		Genre[] gArr = new Genre[genreVector.size()];
		genreVector.toArray(gArr);
		String xml = xstream.toXML(gArr);
		try{
			FileWriter fw = new FileWriter(Config.getGenreFileLocation());
			fw.write(xml);
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}