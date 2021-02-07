package healthApp.controller;

import javafx.scene.control.MenuItem;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import healthApp.model.Reminder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

public class AddReminderController implements Initializable {
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView backButton;
	@FXML
	private ImageView imagePane;
	@FXML
	private TextField titleField;
	@FXML
	private MenuButton repetitionButton;
	@FXML
	private JFXTimePicker timeField;
	@FXML
	private TextArea descriptionField;
	@FXML
	private JFXDatePicker dateField;
	
	private final String DEFAULT_IMAGE_URL = "resources/add.png";

	@FXML
	public void changeImage(MouseEvent event) {		
		//check if the
		
		//save current values into a temp file so data is not lost when reloaded
		saveTemp(simplify(getValues()));
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/healthApp/view/ChangeImage.fxml"));
			anchorPane.getScene().setRoot(root);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goToEditReminder() {
		try {
			Parent newRoot = FXMLLoader.load(getClass().getResource("/healthApp/view/EditReminder.fxml"));
			anchorPane.getScene().setRoot(newRoot);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveTemp(String values) {
		//this method just saves the values that are currently in the fields to a temp file, as there will be a root swap to a
		//new fxml file
		
		try {
			Path p = FileSystems.getDefault().getPath("data","temp.txt");
			File f = new File(p.toUri());
			FileWriter fw = new FileWriter(f,false);
			fw.write(values);
			fw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void confirmButton() {
		
		String[] values = getValues();
		
		if (values[4].contains(DEFAULT_IMAGE_URL)){
			//if user did not pick an image thus it returns the default image, check it to "No Image"
			values[4] = "No Image";
		}
		for(String value : values) {
			if(value.equals("")) { 
				//if it is empty, pop out an error and break;
				Alert alert = new Alert(AlertType.ERROR,"One or more fields are empty.");
				alert.showAndWait();
				return;
			}
		}
		
		//create reminder object
		Reminder r = new Reminder(values[0], values[1], values[2], values[3],values[4], values[5]);
		
		//add to mapdb
		String DB_URL = "data/reminders.DB"; 
		DB db = DBMaker.newFileDB(new File(DB_URL)).closeOnJvmShutdown().make();
		ConcurrentNavigableMap<String, Reminder> map = db.createTreeMap("reminders").makeOrGet();
		

		//if key name (reminder title) already exists, pop up tells user that it is not allowed
		if(map.containsKey(r.getName())) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Overwrite existing Reminder titled " + r.getName() + "?" , ButtonType.YES, ButtonType.NO);
			alert.showAndWait();
			if(alert.getResult() == ButtonType.NO)
				return;
		}		
				
		map.put(r.getName(), r); //key is the title of the reminder 
		db.commit();
		
		//remove data from temp file
		try {
			Path p = FileSystems.getDefault().getPath("data","temp.txt");
			File f = new File(p.toUri());
			FileWriter fw = new FileWriter(f,false);
			fw.write("");
			fw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		goToEditReminder();
	}
	
	public String[] getValues() {
		
		String title, repetition, description, imageURL, date, time;
		
		title = titleField.getText();
		repetition = repetitionButton.getText();
		description = descriptionField.getText();
		imageURL = imagePane.getImage().getUrl(); //should i be worried about this deprecation thing
		
		//		imageURL = imagePane.getImage().impl_getUrl(); //should i be worried about this deprecation thing

		date = dateField.getValue().toString();
		time = timeField.getValue().toString();
		
		String[] res = {title, description, repetition, date, imageURL, time};
		return res;
	}

	public String simplify(String[] strArr) {
		//simplifies the arguments to be ready for storing into database
		//{title::description::repetition::date::image::time}, like with the class
		
		return String.format("%s::%s::%s::%s::%s::%s",strArr[0], strArr[1], strArr[2], strArr[3], strArr[4],strArr[5]);
	}
	
	public void changeMenuText(ActionEvent event) {
		//idk why the menu button doesnt change to the menu item, so i made this
		String name = ((MenuItem)event.getSource()).getText();
		repetitionButton.setText(name);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		reinitialise();
		
		 Path dPath = FileSystems.getDefault().getPath("resources/logo/","add.png");
			File addImg = new File(dPath.toUri());
			Image image = new Image(addImg.toURI().toString());
			imagePane.setImage(image);

/*
		File f = new File("resources/logo/add.png");
		Image img = new Image(f.toURI().toString());
		imagePane.setImage(img);
		*/
			
		/*
		 * Path dPath = FileSystems.getDefault().getPath("foodImg",fileName);
		File foodImg = new File(dPath.toUri());
	
		Image image = new Image(foodImg.toURI().toString());
		foodImage.setImage(image);
		 */
	}
	
	public void reinitialise() {
		// read from temp file to initialise data	
		try {
			Path p = FileSystems.getDefault().getPath("data","temp.txt");
			File f = new File(p.toUri());
			Scanner sc = new Scanner(f);
			
			if(!sc.hasNextLine()) {
				//set the dateField to today's date by default.
				dateField.setValue(LocalDate.now());
				timeField.setValue(LocalTime.now());
				sc.close();
				return; //temp will only store 1 data set. if it has no line it is empty and has nothing to initialise
			}
			String[] values = sc.nextLine().split("::");
			titleField.setText(values[0]);
			descriptionField.setText(values[1]);
			repetitionButton.setText(values[2]);
			dateField.setValue(LocalDate.parse(values[3]));
			imagePane.setImage(new Image(values[4]));
			timeField.setValue(LocalTime.parse(values[5]));
			sc.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
