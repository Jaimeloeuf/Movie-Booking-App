package dataStorage;

import java.io.File;
import java.io.FileWriter;
import java.util.Vector;
import com.thoughtworks.xstream.XStream;
import controller.Config;

public class MovieVector { // Fully static class.
	private static XStream xstream = new XStream();
	private static Vector<Movie> movieVector = new Vector<Movie>(5,1);
	
	public static void init() { // populate with XML
		try{
			Movie[] mArr = (Movie[]) xstream.fromXML(new File(Config.getMovieFileLocation()));
			for(int i = 0; i<mArr.length; i++)
				add(mArr[i]);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int size() {return movieVector.size();}
	public static void add(Movie movie){movieVector.add(movie);}
	public static Movie get(int index) {
		if(index < movieVector.size())
			return movieVector.get(index);
		else
			return null; // throw exception
	}
	public static Movie getMovieWithTitle(String title) {
		for(int i = 0; i<size(); i++)
			if(title.equals(get(i).getTitle()))
				return get(i);
		return null;
	}
	public static void saveToFile(){
		Movie[] mArr = new Movie[size()];
		movieVector.toArray(mArr);
		String xml = xstream.toXML(mArr);
		try{
			FileWriter fw = new FileWriter(Config.getMovieFileLocation());
			fw.write(xml);
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}