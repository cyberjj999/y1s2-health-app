package healthApp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;

import healthApp.data.EventDB;
import healthApp.model.Event;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

public class AdminPageController implements Initializable{
	@FXML
	private TextField eventName;
	@FXML
	private TextField dYear;
	@FXML
	private TextField dMonth;
	@FXML
	private TextField dDay;
	@FXML
	private TextField dHour;
	@FXML
	private TextField dMinute;
	@FXML
	private DatePicker eventDatePicker;
	@FXML
	private TextArea eventDescription;
	@FXML
	private TextField eventVenue;
	@FXML
	private ImageView eventImage;
	@FXML
	private ListView eventVenueList;
	@FXML
	private ChoiceBox<String> eventCategory;
	@FXML
	private Button enterEvent;
	@FXML
	private Button toHome;
	@FXML
	private Button editEvents;
	
	private ArrayList<String> locationArray = new ArrayList<String>();
	private String eventImagePath;
	
	Path dPath = FileSystems.getDefault().getPath("locationData/CCLocation.txt");
	File file = new File(dPath.toUri());
	// Event Listener on Button[#enterEvent].onAction
	
	public void getEventImage(MouseEvent event) throws MalformedURLException {
		String imagepath="";
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {
            imagepath =  file.toURI().toURL().toString();
            System.out.println("file:"+imagepath);
            Image image = new Image(imagepath);
            eventImage.setImage(image);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
            alert.setContentText("You didn't select a file!");
            alert.show();
        }

       eventImagePath = imagepath;
    }
	
	@FXML
	public void addEvent(ActionEvent event) {
		EventDB db = new EventDB();
		String name = eventName.getText();
		
		String hour = dHour.getText();
		String minute = dMinute.getText();
		
		String description = eventDescription.getText();
		String venue = eventVenue.getText();
		String category = (String) eventCategory.getSelectionModel().getSelectedItem();
		
		String date = eventDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String [] fields = date.split("-");
		String year = fields[0];
		String month = fields[1];
		String day = fields[2];

		if(!(hour.matches("^[0-9]{2}")) || Integer.parseInt(hour) > 23 || hour.equals(null)) {
				Alert eAlert = new Alert(Alert.AlertType.ERROR);
				eAlert.setHeaderText("There is an error");
				eAlert.setContentText("Please ensure all inputs are valid4");
				eAlert.show();
		} 
		else if(!(minute.matches("^[0-9]{2}")) || Integer.parseInt(minute) > 59 || minute.equals(null)) {
				Alert eAlert = new Alert(Alert.AlertType.ERROR);
				eAlert.setHeaderText("There is an error");
				eAlert.setContentText("Please ensure all inputs are valid5");
				eAlert.show();
		}
		else if(venue.equals(null)) {
			Alert eAlert = new Alert(Alert.AlertType.ERROR);
			eAlert.setHeaderText("There is an error");
			eAlert.setContentText("Please ensure all inputs are valid");
			eAlert.show();
		}
		else if(name.equals(null)) {
			Alert eAlert = new Alert(Alert.AlertType.ERROR);
			eAlert.setHeaderText("There is an error");
			eAlert.setContentText("Please ensure all inputs are valid");
			eAlert.show();
		}
		else if(category.equals(null)) {
			Alert eAlert = new Alert(Alert.AlertType.ERROR);
			eAlert.setHeaderText("There is an error");
			eAlert.setContentText("Please ensure all inputs are valid");
			eAlert.show();
		}
		else {
			String eIP = eventImagePath;
			System.out.println(eIP);
			
			int checkedYear = Integer.parseInt(year);
			int checkedMonth = Integer.parseInt(month);
			int checkedDay = Integer.parseInt(day);
		
			int checkedHour = Integer.parseInt(hour);
			int checkedMinute = Integer.parseInt(minute);
			
			LocalDateTime eDay = LocalDateTime.of(checkedYear, checkedMonth, checkedDay, checkedHour, checkedMinute);
			Event newEvent = new Event(category, eDay, venue, name, description, eIP);
			db.storeEvent(newEvent);
			
			Alert eAlert = new Alert(Alert.AlertType.CONFIRMATION);
			eAlert.setHeaderText("Event Confirmation");
			eAlert.setContentText("Event has been successfully added!");
			eAlert.show();
			
			dHour.clear();
			dMinute.clear();
			eventName.clear();
			eventDescription.clear();
			eventVenue.clear();
		}
	}
	
	@FXML
	public void toHomepage(ActionEvent event) {
		try {
			Parent homePageLoader = FXMLLoader.load(getClass().getResource("/healthApp/view/HomepageView.fxml"));
			Scene homePageScene = new Scene(homePageLoader);
		
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(homePageScene);
			window.show();
		}
		
		catch(IOException e) {
		}
	}
	
	public void editEvent(ActionEvent event2) {
		try {
			Parent editAdminPageLoader = FXMLLoader.load(getClass().getResource("/healthApp/view/EditEventsAdminPage.fxml"));
			Scene editAdminPageScene = new Scene(editAdminPageLoader);
		
			Stage window = (Stage)((Node)event2.getSource()).getScene().getWindow();
			window.setScene(editAdminPageScene);
			window.show();
		}
		
		catch(IOException e) {
		}
	}
	
	public void handleSearchByKey(String oldVal, String newVal) {
	    if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
	    	eventVenueList.getItems().clear();
			for(int i = 0; i < locationArray.size(); i++) {
				ObservableList<String> locations = FXCollections.observableArrayList(locationArray.get(i).toString());
				eventVenueList.getItems().addAll(locations);

			}	
	    }
	     
	    String[] parts = newVal.toUpperCase().split(" ");
	 
	    ObservableList<String> subentries = FXCollections.observableArrayList();
	    for ( Object entry: eventVenueList.getItems() ) {
	        boolean match = true;
	        String entryText = (String)entry;
	        for ( String part: parts ) {
	            if ( ! entryText.toUpperCase().contains(part) ) {
	                match = false;
	                break;
	            }
	        }
	 
	        if ( match ) {
	            subentries.add(entryText);
	        }
	    }
	    eventVenueList.setItems(subentries);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//TODO Auto-generated method stub
		eventDescription.setWrapText(true);
		
		eventCategory.getItems().add("Music");
		eventCategory.getItems().add("Art");
		eventCategory.getItems().add("Community Work");
		eventCategory.getItems().add("Health");
		eventCategory.getItems().add("Information & Technology");
		eventCategory.getItems().add("Social");
		eventCategory.getItems().add("Exercise");
		
		try(Scanner in = new Scanner(file)){
			while(in.hasNextLine()) {
				locationArray.add(in.nextLine());
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("There is an error!");
		}
		
		Collections.sort(locationArray);
		
		for(int i = 0; i < locationArray.size(); i++) {
			ObservableList<String> locations = FXCollections.observableArrayList(locationArray.get(i).toString());
			eventVenueList.getItems().addAll(locations);
			
			eventVenueList.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) { 
						eventVenue.setText(eventVenueList.getSelectionModel().getSelectedItem().toString());
				}
			});

		}
		
	    eventVenue.textProperty().addListener(
	            new ChangeListener() {
	                public void changed(ObservableValue observable, Object oldVal, Object newVal) {
	                    handleSearchByKey((String)oldVal, (String)newVal);
	                }
	    });
	}
}
