package healthApp.controller;

import java.io.File;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DisplayAllUsers implements Initializable {

	@FXML
	private AnchorPane aPParent;

	@FXML
	private Button generateTable;

	@FXML
	private TableView<Users> inova;

	@FXML
	private CreatingForums CreatingForums;

	private final String CUSTOMER_DB = "data/users.db";
	private static final String USER_NAME = "users";
	private static DB db2;
	private static ConcurrentNavigableMap<String, Users> avg;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Path dPath = FileSystems.getDefault().getPath(CUSTOMER_DB);
		File file = new File(dPath.toString());

		db2 = DBMaker.newFileDB(file).closeOnJvmShutdown().make();

		// create a map in the db for persisting student data
		// map can only hold same type of data. Create separate map for separate
		// type data
		avg = db2.createTreeMap(USER_NAME).makeOrGet();

	}

	public void ableToSee(ActionEvent epaxe) throws Exception {
		createATable();
		System.out.println("Can you see this?");

	}

	// get all of user input to create a forum page, which are
	// value of item from combobox and title of topic
	private ObservableList<Users> getUsers() {
		ObservableList<Users> generateAUsersPage = FXCollections.observableArrayList();

		// generateAForumPage.add(new ForForums(, titleOfTopic.getText()));

		// generateAForumPage.add(new ForForums("Laptop", "859.00", null, null, null));
		Set<Map.Entry<String, Users>> entries = avg.entrySet();
		for (Map.Entry<String, Users> u : entries) {
			System.out.println("Printing information gathered in database....");
			System.out.println(u.getValue().getName());
			System.out.println(u.getValue().getUserName());
			System.out.println(u.getValue().getEmail());
			System.out.println(u.getValue().getPassword());
			System.out.println(u.getValue().getHealth());
			System.out.println(u.getValue().getLocation());
			generateAUsersPage.add(new Users(u.getValue().getName(), u.getValue().getUserName(), u.getValue().getEmail(),u.getValue().getPassword(), u.getValue().getHealth(), u.getValue().getLocation(), u.getValue().getImageType()));
		}

		return generateAUsersPage;

	}

	private void createATable() {
		
		// Column for user's full name
		TableColumn<Users, String> fullNameColumn = new TableColumn<>("Full Name");
		fullNameColumn.setMinWidth(110);
		fullNameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));

		// For user's username
		TableColumn<Users, String> usernameColumn = new TableColumn<Users, String>("Username");
		usernameColumn.setMinWidth(110);
		usernameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("userName"));

		// For user's email
		TableColumn<Users, String> emailColumn = new TableColumn<Users, String>("Email Address");
		emailColumn.setMinWidth(110);
		emailColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));

		// For user's password
		TableColumn<Users, String> passWordColumn = new TableColumn<Users, String>("Password");
		passWordColumn.setMinWidth(110);
		passWordColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("password"));

		// For user's health history
		TableColumn<Users, String> myHealthColumn = new TableColumn<Users, String>("Known Health Troubles");
		myHealthColumn.setMinWidth(110);
		myHealthColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("health"));

		// For user's living location
		TableColumn<Users, String> myLocationColumn = new TableColumn<Users, String>("Live near to");
		myLocationColumn.setMinWidth(110);
		myLocationColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("location"));

		// int index = aPParent.getChildren().indexOf(inova);
		// System.out.println(index); debug
		// inova = (TableView<Users>) aPParent.getChildren().get(index);
		inova.setItems(getUsers());
		inova.getColumns().setAll(fullNameColumn, usernameColumn, emailColumn, passWordColumn,
				myHealthColumn, myLocationColumn);
	}
	
	private Scene backScene;
	public void backToAdminHomePage(MouseEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/adminView/adminLoginFXML.fxml"));
		backScene = (Scene) ((Node) e.getSource()).getScene();
		Stage stage = (Stage) (backScene).getWindow();
		Parent nextNxt = loader.load();
		adminLoginController aLC = loader.getController();
		stage.setScene(new Scene(nextNxt));
		stage.setTitle(null);
		stage.show();
	}

}
