package healthApp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.ForForums;
import healthApp.controller.Login;
import healthApp.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class CreatingForums implements Initializable{
	@FXML
	private Button seeForum;

	@FXML
	private Button newForum;

	@FXML
	private Button retrace;

	@FXML
	private Button checking;

	@FXML
	private ComboBox myCombobox;

	@FXML
	private String usersName;

	@FXML
	private TextField titleOfTopic;

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

		// create a map in the db for persisting student data
		// map can only hold same type of data. Create separate map for separate
		// type data
		map = db.createTreeMap(FORUM_INFORMATION).makeOrGet();

		Path dPath2 = FileSystems.getDefault().getPath(CUSTOMER_DB);
		File file2 = new File(dPath2.toString());

		db2 = DBMaker.newFileDB(file2).closeOnJvmShutdown().make();

		// create a map in the db for persisting student data
		// map can only hold same type of data. Create separate map for separate
		// type data
		avg = db2.createTreeMap(USER_NAME).makeOrGet();

	}

	@FXML
	private Scene myScene;

	public void seFo(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/FMPg.fxml"));
		myScene = (Scene) ((Node) event.getSource()).getScene();
		Stage stage = (Stage) (myScene).getWindow();
		Parent nextWeView = loader.load();
		FMPg myB = loader.getController();
		myB.setTopicTitleFromCreatingForum(titleOfTopic.getText());
		stage.setScene(new Scene(nextWeView));
		stage.setTitle("thhis");
		stage.show();
	}

	@FXML
	private Scene byeScene;

	public void neFo(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/StartingAForum.fxml"));
		byeScene = (Scene) ((Node) e.getSource()).getScene();
		Stage stage = (Stage) (byeScene).getWindow();
		Parent nextInView = loader.load();
		CreatingForums cF = loader.getController();
		stage.setScene(new Scene(nextInView));
		stage.setTitle("Redirected");
		stage.show();
	}

	private Scene myByeScene;

	public void retraceBack(ActionEvent eve) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/loginUpdate.fxml"));
		myByeScene = (Scene) ((Node) eve.getSource()).getScene();
		Stage stage = (Stage) (myByeScene).getWindow();
		Parent nextMeView = loader.load();
		Login lGin = loader.getController();
		stage.setScene(new Scene(nextMeView));
		stage.setTitle("Redirected");
		stage.show();
	}

	public void checkingDone(ActionEvent esper) throws Exception {
		if (validateFields()) {
			createRecords();
			printRecords();
			System.out.println("Forum will be created shortly");
			Set<Map.Entry<String, ForForums>> entries = map.entrySet();
			for (Map.Entry<String, ForForums> f : entries) {
				System.out.println(f.getValue().getComboItems());
				System.out.println(f.getValue().getTitleOfTopic());
				// System.out.println(f.getValue().getForum_content());
				System.out.println(f.getValue().getForum_createdDateTime());
				// System.out.println(f.getValue().getForum_creator());
			}
			//titleOfTopic.clear(); //<-- here
		} else {
			System.out.println("OOPS! Something went wrong, check if ALL fields has been entered");
		}
	}

	private boolean validateFields() {
		if (myCombobox.getSelectionModel().isEmpty() || titleOfTopic.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate fields");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter into the Fields");
			alert.showAndWait();
			return false;
		} else {
			System.out.println("Everything has been checked!");
			return true;
		}
	}

	public void createRecords() throws ParseException {

		String topic = titleOfTopic.getText();
		String items = myCombobox.getSelectionModel().getSelectedItem().toString();

		Date now = new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy H:mm:ss");
		String dateTimeString = df.format(now);

		// create a ForForums object
		ForForums generateForum = new ForForums(topic, items, null, dateTimeString);
		map.put(topic, generateForum);
		db.commit();
	}

	public void printRecords() {
		Set<Map.Entry<String, ForForums>> entries = map.entrySet();
		for (Map.Entry<String, ForForums> f : entries) {
			System.out.println(f.getValue().getComboItems());
			System.out.println(f.getValue().getTitleOfTopic());
			// System.out.println(f.getValue().getForum_content());
			System.out.println(f.getValue().getForum_createdDateTime());
		}
	}
}