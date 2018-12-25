package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//Get the year, month, day, hour, minute, second
import java.util.Calendar;
import java.util.Date;
public class GetDate {
	
	public static Calendar cal;
	private static int year, month, day, hour, minute, second;
	
	private static void setTime() {
		cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;      // 0 to 11 so convert to 1-12
		day = cal.get(Calendar.DAY_OF_MONTH);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);
		second = cal.get(Calendar.SECOND);
	}
	
	public static void main(String[] args) {
//		setTime();
//   		System.out.printf("%4d/%02d/%2d %02d:%02d:%02d\n", year, month, day, hour, minute, second); // Pad with zero
//   		
//   		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
//   		
//   		setTime();
//   		System.out.printf("%4d/%02d/%2d %02d:%02d:%02d\n", year, month, day, hour, minute, second); // Pad with zero
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String date = sdf.format(new Date()); 
		System.out.println(date);

		
		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "31-08-1982 10:20:56";
		Date date1 = null;
		try {
			date1 = sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date1); //Tue Aug 31 10:20:56 SGT 1982
		
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date1 = new Date();
		System.out.println(dateFormat.format(date1)); //2013/10/15 16:16:39
		
		
		
		Calendar calendar = Calendar.getInstance();
		date1 =  calendar.getTime();
		date = sdf.format(date1);
		System.out.println(date1);
	}
}