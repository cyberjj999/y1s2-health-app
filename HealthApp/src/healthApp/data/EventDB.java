package healthApp.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.Event;
import javafx.scene.control.Alert;

public class EventDB {
	private static final String Filename = "eventData/eventList.txt";
	private static final String Delimiter = ";";
	private File file;
	
	public static DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm");
	private ArrayList<Event> eventCCCList = new ArrayList<Event>();
	
	
	public EventDB() {
		Path dPath = FileSystems.getDefault().getPath(Filename);
		file = new File(dPath.toString());
	}
	
	public boolean storeEvent(Event e) {
		boolean status = false;
		ArrayList<Event> eList = getAllEvents();
		int index = eList.indexOf(e);
		if(index < 0) {
			eList.add(e);
			updateToEventDB(eList);
			status = true;
		}
		
		return status;
	}
	
	public ArrayList<Event> getAllEvents(){
		ArrayList<Event> eList = new ArrayList<Event>();
		try(Scanner sc = new Scanner(file)){
			while(sc.hasNextLine()) {
				String record = sc.nextLine();
				String [] fields = record.split(Delimiter);
				String category = fields[0];
				LocalDateTime eventDateTime = LocalDateTime.parse(fields[1]);
				String venue = fields[2];
				String name = fields[3];
				String description = fields[4];
				String imagePath = fields[5];
				
				Event e = new Event(category, eventDateTime, venue, name, description, imagePath);
				eList.add(e);
			}
		}
		catch(FileNotFoundException e) {
			Alert dbAlert = new Alert(Alert.AlertType.ERROR);
			dbAlert.setHeaderText("There is an error");
			dbAlert.setContentText("File not found");
			dbAlert.show();
		}
		catch(ArrayIndexOutOfBoundsException e) {
	
		}
		
		return eList;
	}
	
	public Event getEvent(String name) {
		ArrayList<Event> eList = getAllEvents();
		Event chosenEvent = new Event(name);
		for(Event e : eList) {
			if(e.getName().equals(name)) {
				chosenEvent = e;
			}
		}
		return chosenEvent;
	}
	
	public boolean deleteEvent(String name) {
		boolean status = false;
		ArrayList<Event> eList = getAllEvents();
		for(int i = 0; i < eList.size(); i++ ) {
			if(eList.get(i).getName().equals(name)) {
				eList.remove(eList.get(i));
			}
		}
		updateToEventDB(eList);
		status = true;
		return status;
	}
	
	public ArrayList<Event> getEventsByCC(String venue){
		ArrayList<Event> eList = getAllEvents();
		ArrayList<Event> eventCCList = new ArrayList<Event>();
		
		for(Event e : eList) {
			if(e.getVenue().equals(venue)) {
				eventCCList.add(e);
			}
		}
		
		return eventCCList;
	}
	
	public ArrayList<Event> getEventsByCategory(String category){
		ArrayList<Event> eList = getAllEvents();
		ArrayList<Event> eventCategoryList = new ArrayList<Event>();
		
		for(Event e : eList) {
			if(e.getCategory().equals(category)) {
				eventCategoryList.add(e);
			}
		}
		
		return eventCategoryList;
	}
	
	public ArrayList<Event> getEventsByCategoryCCs(String venue, String category){
		ArrayList<Event> eList = getEventsByCC(venue);
		
		for(Event e : eList) {
			if(e.getCategory().equals(category)) {
				eventCCCList.add(e);
			}
		}
		
		return eventCCCList;
	}
	
	public boolean updateToEventDB(ArrayList<Event> eList) {
		boolean status=false;
		try(PrintWriter writer = new PrintWriter(file)){
			for(Event e : eList) {
				writer.write(e.getCategory()+Delimiter+e.getEventDateTime().toString()+Delimiter+e.getVenue()+Delimiter+e.getName()+Delimiter+e.getDescription()+Delimiter+e.getImagePath()+"\n");
			}
			status = true;
		}
		catch(FileNotFoundException e) {
			Alert dbAlert = new Alert(Alert.AlertType.ERROR);
			dbAlert.setHeaderText("There is an error");
			dbAlert.setContentText("Unable to save!");
			dbAlert.show();
		}
		
		return status;
	}
}
