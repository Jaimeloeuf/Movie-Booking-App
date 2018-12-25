package dataStorage;
import java.util.Vector;

public class ActivityVector { // Fully static class.
	private static Vector<Activity> activityVector = new Vector<Activity>(5,1);
	
	public static int size() {return activityVector.size();}
	public static void add(Activity a){activityVector.add(a);}
	public static Activity get(int index) {
		if(index < size())
			return activityVector.get(index);
		else
			return null; // throw exception
	}
	public static Activity getActivityWithJList_element(String s) {
		for(int i = 0; i<activityVector.size(); i++)
			if(get(i).asJList_element().equals(s))
				return get(i);
		return null; // Throw error/exception too
	}
	public static void resetVector(){
		activityVector.clear(); // Reset the vector
		Activity.resetCount(); // Reset the activity count static variable
	}
}