package healthApp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

public class Reminder implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name, description, repetition, imageURL;
	
	private LocalDate date;
	private LocalTime time;
	
	public Reminder(String name, String description, String repetition, String date, String imageURL, String time) {
		this.name = name;
		this.description = description;
		this.repetition = repetition;
		this.date = LocalDate.parse(date);
		this.imageURL = imageURL;
		this.time = LocalTime.parse(time);
	}

	public void alert() {
		//popup box to alert
	}
	
	
	public String[] simplify() {
		//return simplified of all fields to display in detailedPane for Reminder.fxml
		String[] fields = {name + description + repetition + date};
		
		return fields;
	}
	
	

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getRepetition() {
		return repetition;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	

	public String getDate() {
		return date.toString();
	}
	
	public String getTime() {
		return time.toString();
	}
	
	public String getFormattedTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		return time.format(dtf);
	}
	
	public String getFormattedDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(dtf);
	}

	@Override
	public String toString() {
		return "Reminder Object: " + name + description + repetition + date;
	}
}
