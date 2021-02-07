package healthApp.data;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.controller.EditReminderController;
import healthApp.controller.ReminderController;
import healthApp.model.Reminder;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class showDates {
	public static GregorianCalendar displayDate = new GregorianCalendar(); //returns the calendar object to use generally
	public static String[] today = simplifyMonth(displayDate); //the simplified stuff
	public static int monthCount =monthStringToInt(today[1]);
	public static final String[] MONTHS = {"January", "Feburary","March", "April", "May","June","July","August","September","October","November","December"};
	
	public static String[] simplifyMonth(GregorianCalendar gc){
		//This function finds and returns the day, month, and year of the current time.
		String day, month, year;
		
		//get the date and days from calendar. Prints out as string (Wed Jan 02 14:10:35 GMT+08:00 2019)
		Date time = gc.getTime();
		
		
		//Splits the time string into an array
		String[] values = time.toString().split(" ");
		day = values[2];
		month = values[1];
		year = values[5];
		
		String[] res = {day, month, year};
		return res;
	}
	
	
	public static int monthStringToInt(String month) {
		switch(month) {
		case "Jan":
			return 0;
		case "Feb":
			return 1;
		case "Mar":
			return 2;
		case "Apr":
			return 3;
		case "May":
			return 4;
		case "Jun":
			return 5;
		case "Jul":
			return 6;
		case "Aug":
			return 7;
		case "Sep":
			return 8;
		case "Oct":
			return 9;
		case "Nov":
			return 10;
		case "Dec":
			return 11;
		default:
			System.out.println("Error, invalid");
			return 0;
		}
	}
	public static void showMonth(int month, int year, GridPane calendar) {
		//This function displays the dates corresponding with the months.

		GregorianCalendar cal = new GregorianCalendar(year, month, 1); 
		//get first day of month.
		String firstDayOfMonth = cal.getTime().toString().split(" ")[0]; //returns mon, tues, wed etc..
				
		//get last day of month
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
	
		int lastDayOfMonth = Integer.parseInt(cal.getTime().toString().split(" ")[2]); //returns 30,31, etc...
		
		int dayOfWeek;
		int week = 1;
		
		switch(firstDayOfMonth) {
		case "Mon":
			dayOfWeek = 1;
			break;
		case "Tue":
			dayOfWeek = 2;
			break;
		case "Wed":
			dayOfWeek = 3;
			break;
		case "Thu":
			dayOfWeek = 4;
			break;
		case "Fri":
			dayOfWeek = 5;
			break;
		case "Sat":
			dayOfWeek = 6;
			break;
		default:
			dayOfWeek = 0;
		}
		
		ArrayList<Reminder> thisMonthReminders = getReminders(month); //month is showing the previous month (for label) [january has feburary's number] 
		
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		
		//DISPLAYING ONTO CALENDAR
		calendar.getChildren().remove(8, calendar.getChildren().toArray().length);
		for(int i=1; i<=lastDayOfMonth; i++) {
			cal.set(GregorianCalendar.DAY_OF_MONTH, i);

			
			//CREATING PANE
			StackPane p = new StackPane();
			
			//CREATING LABEL
			Label lb = new Label(String.valueOf(i)); //styling the stuff		
			lb.setLayoutX(0);
			lb.setLayoutY(0);
			
			Pane imageP = new Pane(); 
			//FINDING WHICH REMINDERS TO WHICH DATES
			for(Reminder r : thisMonthReminders) {
			if(!r.getImageURL().equals("No Image")) {	
				String[] values = r.getDate().split("-");
				int reminderYear = Integer.parseInt(values[0]);
				int reminderMonth = Integer.parseInt(values[1]);
				int reminderDay = Integer.parseInt(values[2]);
				
				LocalDate reminderDate = LocalDate.parse(r.getDate());
				String repetition = r.getRepetition();
				
				//if there is repetition and the reminder has happened past the day already,
				Date d = cal.getTime();
		
				ArrayList<Integer> reminderValues = new ArrayList<>(Arrays.asList(reminderYear, reminderMonth, reminderDay));
				ArrayList<Integer> dateValues = new ArrayList<>(Arrays.asList(cal.get(GregorianCalendar.YEAR), cal.get(GregorianCalendar.MONTH)+1, i));

				//if year is past this year, confirm valid. if year == this year, check month. if year > this year, confirm no.
				//if there is repetition and past the day
				if(!(repetition.equals("No Repeat")) && checkIfPastDate(reminderValues, dateValues)) {
					//if it is weekly, compare the day of week ( sunday etc)
					if(repetition.equals("Weekly") && reminderDate.getDayOfWeek().getValue() == cal.get(GregorianCalendar.DAY_OF_WEEK)-1){
						ImageView iv = makeImageView(r.getImageURL());
						imageP.getChildren().add(iv);
						iv.setLayoutX(((imageP.getChildren().size()-1) * 20) % 40);
						iv.setLayoutY((((imageP.getChildren().size()-1) * 20) / 40) * 20);
						continue;
					}
					
					//if monthly, compare just the day.
					if(repetition.equals("Monthly") && reminderDate.getDayOfMonth() == cal.get(GregorianCalendar.DAY_OF_MONTH)) {
						ImageView iv = makeImageView(r.getImageURL());
						imageP.getChildren().add(iv);
						iv.setLayoutX(((imageP.getChildren().size()-1) * 20) % 40);
						iv.setLayoutY((((imageP.getChildren().size()-1) * 20) / 40) * 20);
						continue;
					}
					
					//if yearly, compare the day and month (day of year)
					if(repetition.equals("Yearly") && reminderDate.getDayOfYear() == cal.get(GregorianCalendar.DAY_OF_YEAR)) {
						ImageView iv = makeImageView(r.getImageURL());
						imageP.getChildren().add(iv);
						iv.setLayoutX(((imageP.getChildren().size()-1) * 20) % 40);
						iv.setLayoutY((((imageP.getChildren().size()-1) * 20) / 40) * 20);
						continue;
					}
				}
				//if no repetition and the day does not match, go to next reminder in loop
				if(repetition.equals("No Repeat") && (i == reminderDay && reminderYear == cal.get(GregorianCalendar.YEAR))) {
					ImageView iv = makeImageView(r.getImageURL());
					imageP.getChildren().add(iv);
					iv.setLayoutX(((imageP.getChildren().size()-1) * 20) % 40);
					iv.setLayoutY((((imageP.getChildren().size()-1) * 20) / 40) * 20);
					continue;
				}
			}
			}
			
			if(imageP.getChildren().size() > 0) {
				p.getChildren().add(imageP);
			}
			
			//STYLING STACKPANE
			StackPane.setAlignment(lb, Pos.BOTTOM_RIGHT);
			for(Node n : p.getChildren()) {
				StackPane.setMargin(n, new Insets(10,10,10,10));
			}
			
			//ADDING CHILDREN TO PARENTS
			p.getChildren().add(lb);
			calendar.add(p, dayOfWeek++, week);
			
			LocalDate now = LocalDate.now();
			if(month+1 == now.getMonthValue() && i == now.getDayOfMonth() && cal.get(GregorianCalendar.YEAR) == now.getYear()) {
				p.setStyle("-fx-background-color:rgb(237,255,104)");
				GridPane.setMargin(p, new Insets(1,1,1,1)); //makes it prettier 
			}
			
			if(dayOfWeek > 6) {
				dayOfWeek = 0; week++;
			}
		}
	}
	
	public static boolean checkIfPastDate(ArrayList<Integer> reminderValues, ArrayList<Integer> date) {
		if(reminderValues.get(0) < date.get(0)) //if rYear < year, valid. if rMonth < month, valid...
			return true;
		if(reminderValues.get(0) > date.get(0)) //if rYear > year, not valid. If rMonth > month, not valid...
			return false;
		if(reminderValues.get(0).equals(date.get(0)) && reminderValues.size()>1) { 
			//if rYear == year, check with rMonth and month, until left with day. if it is not more or less, it is equal. valid.
			reminderValues.remove(0);
			date.remove(0);
			return checkIfPastDate(reminderValues, date);
		}
		
		return true;
	}
	public static ImageView makeImageView(String url) {
		ImageView iv = new ImageView(url);
		iv.setFitWidth(20);
		iv.setFitHeight(20);
		return iv;
	}
	
	
	public static ArrayList<Reminder> getReminders(int month){
		//looks through the reminders database and gets all reminders in that specific month. 
		String DB_URL = "data/reminders.DB"; 
		DB db = DBMaker.newFileDB(new File(DB_URL)).closeOnJvmShutdown().make();
		ConcurrentNavigableMap<String, Reminder> map = db.createTreeMap("reminders").makeOrGet();
		
		ArrayList<Reminder> arrlist = new ArrayList<Reminder>();
		
		for(Reminder r : map.values()) {
			int reminderYear = Integer.parseInt(r.getDate().split("-")[0]); //checks if its the right year
			int year = calYear();
			
			int reminderMonth = Integer.parseInt(r.getDate().split("-")[1]) - 1; 
			//Reminder.getDate returns january as 1, but calendar returns january as 0.
			
			//if the reminder has a repetition 
			if(!(r.getRepetition().equals("No Repeat"))) {
				arrlist.add(r);
			}
			else if(reminderMonth == month && reminderYear <= year)
				arrlist.add(r);
		}
		
		return arrlist;
	}

	
	public static ArrayList<Reminder> get2DaysReminders(){
		//returns an arraylist with reminders for today and tomorrow.
		String DB_URL = "data/reminders.DB"; 
		DB db = DBMaker.newFileDB(new File(DB_URL)).closeOnJvmShutdown().make();
		ConcurrentNavigableMap<String, Reminder> map = db.createTreeMap("reminders").makeOrGet();
		
		ArrayList<Reminder> todayReminders = new ArrayList<Reminder>();
		LocalDate date = LocalDate.now();
		for(Reminder r : map.values()) {
			LocalDate rDate = LocalDate.parse(r.getDate());
			if (rDate.isEqual(date) || rDate.plusDays(1).isEqual(date)) 
				todayReminders.add(r);
		}
		return todayReminders;
	}
	
	public static int calYear() {
		double yearOffset =  Math.floor(((double)monthCount) /12);
		return Integer.parseInt(today[2]) + (int) yearOffset; 
	}
	
	public static void prevMonth(GridPane calendar) {
		--monthCount;
		showMonth(monthCount % 12,calYear() , calendar);
	}
	
	public static void nextMonth(GridPane calendar) {
		showMonth(++monthCount % 12,calYear(), calendar);
	}
}
