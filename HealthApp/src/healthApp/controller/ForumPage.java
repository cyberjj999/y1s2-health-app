package healthApp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.ForForums;
import healthApp.controller.Login;
import healthApp.controller.adminLoginController;
import healthApp.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ForumPage implements Initializable {
	
	@FXML
	private AnchorPane anchorPaneParent; 

	@FXML
	private Button submit;

	@FXML
	private Button fail;

	@FXML
	private ImageView back;

	@FXML
	private Button magical;
	
	@FXML
	private TextField applyTitle;
	
	@FXML
	private TextField applyItems;
	
	@FXML
	private Button tF;
	
	@FXML
	private TableView<ForForums> table;
	
	@FXML
	private CreatingForums CreatingForums;
	
	private final String FORUM__DB = "data/forum.db";
	private static final String FORUM_INFORMATION = "forum";
	private static DB db;
	private static ConcurrentNavigableMap<String, ForForums> map;
	
	private final String CUSTOMER_DB = "data/users.db";
	private static final String USER_NAME = "users";
	private static DB db2;
	private static ConcurrentNavigableMap<String, Users> avg;		
		
   @Override
   public void initialize(URL location, ResourceBundle resources) {
		Path dPath = FileSystems.getDefault().getPath(FORUM__DB);
		File file = new File(dPath.toString());
		db = DBMaker.newFileDB(file).closeOnJvmShutdown().make();
		map = db.createTreeMap(FORUM_INFORMATION).makeOrGet();
	
		Path dPath2 = FileSystems.getDefault().getPath(CUSTOMER_DB);
		File file2 = new File(dPath2.toString());
		db2 = DBMaker.newFileDB(file2).closeOnJvmShutdown().make();
		avg = db2.createTreeMap(USER_NAME).makeOrGet();	
}

	private Scene sadbackScene;
	public void goHomeToAdPage(MouseEvent eve) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/adminLoginFXML.fxml"));
		sadbackScene = (Scene) ((Node) eve.getSource()).getScene();
		Stage stage = (Stage) (sadbackScene).getWindow();
		Parent nextMe = loader.load();
		adminLoginController aLC = loader.getController();
		stage.setScene(new Scene(nextMe));
		stage.setTitle("See Forum");
		stage.show();
	}

	public void time(ActionEvent xe) throws Exception {
		createATable();
		System.out.println("Can you see this?");
	}
	
	// get all of user input to create a forum page, which are
	// value of item from combobox and title of topic
	private ObservableList<ForForums> getForForums() {
		ObservableList<ForForums> generateAForumPage = FXCollections.observableArrayList();

		//generateAForumPage.add(new ForForums(, titleOfTopic.getText()));
		
		//generateAForumPage.add(new ForForums("Laptop", "859.00", null, null, null));
		Set<Map.Entry<String, ForForums>> entries = map.entrySet();
		for (Map.Entry<String, ForForums> f : entries) {
			System.out.println("Printing information gathered in database....");
			String str = f.getValue().getComboItems();
			System.out.println(f.getValue().getComboItems());
			System.out.println(f.getValue().getTitleOfTopic());
			System.out.println(f.getValue().getForum_createdDateTime());
			generateAForumPage.add(new ForForums(f.getValue().getTitleOfTopic(), str, null, f.getValue().getForum_createdDateTime()));			
			}
		return generateAForumPage;
	}

	private void createATable() {

		// Column for selected item in comboBox
		TableColumn<ForForums, String> itemColumn = new TableColumn<>("Item Selected");
		itemColumn.setMinWidth(125);
		itemColumn.setCellValueFactory(new PropertyValueFactory<ForForums, String>("comboItems"));
		
		// For title of topic
		TableColumn<ForForums, String> titleOfTopicColumn = new TableColumn<ForForums, String>("Topic Name");
		titleOfTopicColumn.setMinWidth(125);
		titleOfTopicColumn.setCellValueFactory(new PropertyValueFactory<ForForums, String>("titleOfTopic"));
		
		// For timestamp of created forum
		TableColumn<ForForums, String> timeColumn = new TableColumn<ForForums, String>("Time of creation");
		timeColumn.setMinWidth(125);
		timeColumn.setCellValueFactory(new PropertyValueFactory<ForForums, String>("forum_createdDateTime"));

		int index = anchorPaneParent.getChildren().indexOf(table);
		//System.out.println(index); debug
		table = (TableView<ForForums>) anchorPaneParent.getChildren().get(index);
		table.setItems(getForForums());
		table.getColumns().setAll(itemColumn, titleOfTopicColumn, timeColumn);
	}
	
	
	public void add(ActionEvent eq) throws Exception {
		createUpdatedRecords();
		createATable();
		
		//Clear all fields after pressing add button
		 applyItems.clear();
		 applyTitle.clear();
	}
	
	public void delete(ActionEvent eawq) throws Exception {
		ObservableList<ForForums> rowSelected, allForumsCreated;
		allForumsCreated = table.getItems();
		rowSelected = table.getSelectionModel().getSelectedItems();
		
		rowSelected.forEach(allForumsCreated::remove);
		
		db.commit();
		
		//How to update database
	}

	public void createUpdatedRecords() throws ParseException {
		
		String manuallyAddedItem = applyItems.getText();
		String manuallyAddedTitle = applyTitle.getText();
				
		Date rightNow= new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		String currentTimeStamp = df.format(rightNow);
			
			Set<Map.Entry<String, ForForums>> entries = map.entrySet();

			ForForums b = map.get(manuallyAddedTitle);
			b.setTitleOfTopic(manuallyAddedTitle);
			b.setComboItems(manuallyAddedItem);
			b.setForum_createdDateTime(currentTimeStamp);

			map.replace(manuallyAddedTitle, b);
			db.commit();
			for (Map.Entry<String, ForForums> f : entries) {	
				// create a ForForums object
				System.out.println(f.getValue().getComboItems());
				System.out.println(f.getValue().getTitleOfTopic());
				System.out.println(f.getValue().getForum_createdDateTime());
			}
	}
	
}