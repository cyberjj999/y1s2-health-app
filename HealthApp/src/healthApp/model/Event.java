package healthApp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Serializable{
	
	public static DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
	private static final long serialVersionUID = 1L;
	
	private String category;
	private String venue;
	private LocalDateTime eventDateTime;
	private String name;
	private String description;
	private String imagePath;
	private String key;
	
	public Event(String name) {
		super();
		this.name = name;
	}
	
	public Event(String category, LocalDateTime eventDateTime, String venue, String name, String description, String imagePath) {
		super();
		this.category = category;
		this.eventDateTime = eventDateTime;
		this.venue = venue;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
	}
	
	private String generateKey() {
		String key = eventDateTime.format(customFormatter) + venue;
		return key;
	}
	
	public String generateKey(LocalDateTime eventDateTime, String venue) {
		String key =  eventDateTime.format(customFormatter)+ venue;
		return key;
	}
	
	public String getKey() {
		return key;
	}
	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(LocalDateTime eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
