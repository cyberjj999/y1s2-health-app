package healthApp.controller;


import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.regex.Pattern;

import healthApp.model.Reminder;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import healthApp.controller.AddReminderController;

public class EditReminderController implements Initializable{
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView backButton;
	@FXML
	private ScrollPane reminderContainer;
	@FXML
	private Pane addReminderButton;
	@FXML
	private GridPane content;
	@FXML
	private TextField searchBar;
	
	private DB db;
	private ConcurrentNavigableMap<String, Reminder> map;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		searchHelper("");

		//REMOVE THE DATA FROM TEMP FILE
		try {
			Path p = FileSystems.getDefault().getPath("data","temp.txt");
			File f = new File(p.toUri());
			FileWriter fw = new FileWriter(f,false);
			fw.write("");
			fw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void display(Collection<Reminder> collection) {
		//redisplays all the reminders in the scrollpane.
		//set pane to empty..
		content.getChildren().removeAll(content.getChildren());
		int counter = 0;
		for(Reminder r: collection) {
			showIndividualReminder(r, counter++);
		}
	}
	
	public void showIndividualReminder(Reminder r, int counter) {
		//creates a pane to display the info
		//counter is the row to add into (idk how to get the amt of rows in the gridpane...)
		Pane p = makeReminder(r);
		content.addRow(counter, p); //add p pane to content pane (inside scrollpane)
		
	}
	
	public Pane makeReminder(Reminder r) {
		//PANE STYLING
		Pane p = new Pane();
		int height = 100;
		p.setMinWidth(335);
		p.setMinHeight(height);
		p.setStyle("-fx-background-color: rgb(230,230,230)");
		p.setStyle("-fx-border-width : 3px");
		p.setStyle("-fx-border-color : black");
		p.setId(r.getName()); //sets pane's id to the reminder's name
		p.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				String key = ((Pane) e.getSource()).getId();
				Reminder r = map.get(key);
				String values = String.format("%s::%s::%s::%s::%s::%s",r.getName(), r.getDescription(), r.getRepetition(), r.getDate(), r.getImageURL(),r.getFormattedTime());
					//if i have time, put this somewhere else to make it more efficient ^^^^
				AddReminderController.saveTemp(values);
				goToAddReminder();
			}
			
		});
		
		//give today's reminmders a different color
		if(LocalDate.parse(r.getDate()).isEqual(LocalDate.now()))
			p.setStyle("-fx-background-color: rgb(237,255,104)");
		//IMAGE STYLING
		if(!r.getImageURL().equals("No Image")) {
			ImageView iv = new ImageView(r.getImageURL());
			iv.setFitWidth(50);
			iv.setFitHeight(50);
			iv.setLayoutY(30);
			iv.setLayoutX(265);
			p.getChildren().add(iv);
		}
		
		//LABELS STYLING
		Label title = makeLabel(r.getName(), 5, 0);
		title.setStyle("-fx-font-size: 25px");
		title.setPrefSize(150, 30);
		title.setTooltip(new Tooltip(r.getName()));
		//title.setStyle("-fx-border-width : 1px");
		//title.setStyle("-fx-border-color : black"); 
		
		Label description = makeLabel(r.getDescription(), 5, 60);
		description.setPrefSize(250, 40);
		description.setTooltip(new Tooltip(r.getDescription()));
		description.setStyle("-fx-border-width : 1px");
		description.setStyle("-fx-border-color : black"); 
		description.setAlignment(Pos.TOP_LEFT);
		
		Label time = makeLabel(r.getFormattedTime(), 5, height/3);
		time.setPrefSize(60, 20);
		
		Label date = makeLabel(r.getFormattedDate().toString(), 70, height/3);
		date.setPrefSize(100, 20);
		
		Label repetition = makeLabel(r.getRepetition(), 160, 5);
		repetition.setPrefSize(120, 30);
		repetition.setTooltip(new Tooltip(r.getRepetition()));
		
		//BUTTON
		Button delButton = new Button("Delete");
		delButton.setOnAction( e -> {
			deleteReminder(e);
		});
		delButton.setPrefWidth(70);
		delButton.setLayoutX(260);
		
		//ADDING TO PANE TO ADD TO CONTENT
		p.getChildren().add(title);//add title label to p pane
		p.getChildren().add(description);
		p.getChildren().add(date);
		p.getChildren().add(delButton); 
		p.getChildren().add(repetition);
		p.getChildren().add(time);
		
		return p;
		
	}
	
	public Label makeLabel(String title, int x, int y) {
		Label l = new Label(title);
		l.setLayoutX(x);
		l.setLayoutY(y);
		l.setStyle("-fx-font-size : 15px");
		return l;
	}
	
	public void deleteReminder(ActionEvent e) {
		//change button label to "Confirm?" before deleting for real
		String text = ((Button)e.getSource()).getText();
		if(text.equals("Delete")) {
			//change text to confirm again
			((Button)e.getSource()).setText("Confirm?");
		}
		else {
			//remove from arraylist and display again
			Node r = (((Button) e.getSource()).getParent());
			map.remove(r.getId());
			
			db.commit();
			display(map.values());
		}
		
		
	}
	
	@FXML
	public void search(KeyEvent event) {
		searchHelper(((TextField)event.getSource()).getText());
	}
	
	public void searchHelper(String regex) {
		//scrollpane can only have 1 node.
		reminderContainer.setContent(content);
		
		//search for and display existing reminders
		String DB_URL = "data/reminders.db";
		db = DBMaker.newFileDB(new File(DB_URL)).closeOnJvmShutdown().make();
		map = db.createTreeMap("reminders").makeOrGet();
		
		Pattern p = Pattern.compile(regex);
		ArrayList<Reminder> res = new ArrayList<Reminder>();
		for(Reminder r: map.values()) {
			if(p.matcher(r.getName()).find() || p.matcher(r.getFormattedDate()).find())
				res.add(r);
		}
		display(res);
	
	}
	public void goToReminder() {
		try {
			Parent newRoot = FXMLLoader.load(getClass().getResource("/healthApp/view/Reminder.fxml"));
			anchorPane.getScene().setRoot(newRoot);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goToAddReminder() {
		try {
			Parent newRoot = FXMLLoader.load(getClass().getResource("/healthApp/view/AddReminder.fxml"));
			anchorPane.getScene().setRoot(newRoot);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setSearchBar(String str) {
		searchBar.setText(str);
	}
}
