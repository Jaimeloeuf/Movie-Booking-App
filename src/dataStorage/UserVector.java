package dataStorage;

import java.io.File;
import java.io.FileWriter;
import java.util.Vector;
import com.thoughtworks.xstream.XStream;
import controller.Config;

public class UserVector{
	private static XStream xstream = new XStream();
	private static Vector<User> userVector = new Vector<User>(1,1);
	
	public static void init() { // populate with XML
		try{
			User[] uArr = (User[]) xstream.fromXML(new File(Config.getUserFileLocation()));
			for(int i = 0; i<uArr.length; i++)
				add(uArr[i]);
		}catch(Exception e){
			e.printStackTrace();
		}

//		user = new User(4, "staff", "", "Amos", "Lim"); // Staff for Mr Lee to do testing
//		add(user);
	}
	
	public static void add(User user){userVector.add(user);}
	public static int size(){return userVector.size();}
	public static User get(int index) {
		if(index < size())
			return userVector.get(index);
		else
			return null; // throw exception
	}
	public static User getUser(String username) { // Returns a User object if a user with "username" exists
		for(int i = 0; i<userVector.size(); i++) {
			if(username.equals(get(i).getUserName())) {
				return get(i); // Return User object with the given "username"
			}
		}
		return null; // Return null if User with "username" does not exist.
	}
	public static void saveToFile(){
		User[] uArr = new User[size()];
		userVector.toArray(uArr);
		String xml = xstream.toXML(uArr);
		try{
			FileWriter fw = new FileWriter(Config.getUserFileLocation());
			fw.write(xml);
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}