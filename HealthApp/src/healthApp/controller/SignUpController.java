package healthApp.controller;

import java.io.File;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mapdb.DB;
import org.mapdb.DBMaker;
//import com.sun.javafx.scene.control.behavior.PasswordFieldBehavior;
//import com.sun.javafx.scene.control.skin.TextFieldSkin;


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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
	
	@FXML
	private TextField uN;
	
	@FXML
	private TextField mailInput;

	@FXML
	private TextField toggleOff;

	@FXML
	private PasswordField toggleOn;
	
	@FXML
	private ComboBox location;
	
	@FXML
	private Button sU;

	@FXML
	private CheckBox tick;
	
	@FXML
	private ListView healthList;
	
	private final String CUSTOMER_DB = "data/users.db";
	private static final String USER_NAME = "users";
	private static DB db2;
	private static ConcurrentNavigableMap<String, Users> avg;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Path dPath = FileSystems.getDefault().getPath(CUSTOMER_DB);
		File file = new File(dPath.toString());

		db2 = DBMaker.newFileDB(file).closeOnJvmShutdown().make();

		// create a map in the db for persisting student data
		// map can only hold same type of data. Create separate map for separate
		// type data
		avg = db2.createTreeMap(USER_NAME).makeOrGet();

	    // To set ListView: healthList items
		ObservableList hi = FXCollections.observableArrayList();
		hi.add("Knee Pain");
		hi.add("Diabetes");
		hi.add("High Blood Pressure");
		healthList.setItems(hi);

      // To set multiple selection model
      healthList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      
	}
	
	private Scene theirScene;
	
	public void can(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/userProfile.fxml"));
		theirScene = (Scene) ((Node) e.getSource()).getScene();
		Stage stage = (Stage) (theirScene).getWindow();
		Parent nextNxt = loader.load();
		UserProfilePageController myA = loader.getController();
		myA.setEmailFromSignUp(mailInput.getText());
		stage.setScene(new Scene(nextNxt));
		stage.setTitle("Change My Picture");
		stage.show();
				}

	private Scene myScene;

// Write TOOLTIP METHOD.
//NOTE: FOR TOOLTIP TO SHOW STAY STIL FOR 2 SECS
	public void existingAccount(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/loginUpdate.fxml"));
		myScene = (Scene) ((Node) event.getSource()).getScene();
		Stage stage = (Stage) (myScene).getWindow();
		Parent nextView = loader.load();
		Login logIn = loader.getController();
		stage.setScene(new Scene(nextView));
		stage.setTitle("Login Page");
		stage.show();
	}

	public void signUp(ActionEvent epp) throws Exception {

		if (checkRecords()) {
			if (validatePassword() && validateEmail()) {
				System.out.println("Successful");
				can(epp);
			}
		} else {
			System.out.println("Error");
		}
	}

	public boolean checkRecords() {
		if ((avg.containsKey(mailInput.getText()))) {
			System.out.println("record exists");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Duplicate Email Account");
			alert.setHeaderText(null);
			alert.setContentText("Email Account already exists. Enter another Email Account or Sign Up now!");
			alert.showAndWait();
			mailInput.clear();
			return false;
		} else {
			System.out.println("creating");
			try {
				createRecords();
				printRecords();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	public void createRecords() throws ParseException {
		
		
		String myLocation = (String)location.getSelectionModel().getSelectedItem();
		String myEmail = mailInput.getText();
		String myPassword = toggleOn.getText();
		String myHealth = healthList.getSelectionModel().getSelectedItems().toString();
		
		// create a User object
		//Users activeUser = new Users(null, uN.getText(), myEmail, myPassword, myHealth, myLocation, null);
		Users activeUser = new Users(null, uN.getText(), myEmail, myPassword, myHealth, myLocation, null);
		avg.put(myEmail, activeUser);
		db2.commit();
	}

	public static void printRecords() {
		Set<Map.Entry<String, Users>> entries = avg.entrySet();
		for (Map.Entry<String, Users> u : entries) {
			System.out.println(u.getValue().getUserName());
			System.out.println(u.getValue().getEmail());
			System.out.println(u.getValue().getPassword());
			System.out.println(u.getValue().getHealth());
			System.out.println(u.getValue().getLocation());
		}
	}

	public void passwordInput(ActionEvent ex) throws Exception {
		class PasswordFieldSkin extends TextFieldSkin {
			public static final char BULLET = '\u2022';

			public PasswordFieldSkin(PasswordField toggleOn) {
				super(toggleOn);
			}

			@Override
			protected String maskText(String txt) {
				TextField toggleOff = getSkinnable();

				int n = toggleOff.getLength();
				StringBuilder passwordBuilder = new StringBuilder(n);
				for (int i = 0; i < n; i++) {
					passwordBuilder.append(BULLET);
				}

				return passwordBuilder.toString();
			}
		}
// text field to show password as unmasked
// Set initial state
		toggleOff.setManaged(false);
		toggleOff.setVisible(false);
	}

	public void ticked(ActionEvent epple) throws Exception {
// Actual password field
// PasswordField = toggleOn
// TextField = toggleOff

// Bind properties. Toggle textField and passwordField
// visibility and managability properties mutually when checkbox's state is
// changed.
// Because we want to display only one component (textField or passwordField)
// on the scene at a time.
		toggleOff.managedProperty().bind(tick.selectedProperty());
		toggleOff.visibleProperty().bind(tick.selectedProperty());

		toggleOn.managedProperty().bind(tick.selectedProperty().not());
		toggleOn.visibleProperty().bind(tick.selectedProperty().not());

// Bind the textField and passwordField text values bidirectionally.
		toggleOff.textProperty().bindBidirectional(toggleOn.textProperty());
	}

	private boolean validatePassword() {
		Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$");
		/*
		 * ^ # start-of-string (?=.*[0-9]) # a digit must occur at least once
		 * (?=.*[a-z]) # a lower case letter must occur at least once (?=.*[A-Z]) # an
		 * upper case letter must occur at least once (?=.*[@#$%^&+=]) # a special
		 * character must occur at least once (?=\S+$) # no whitespace allowed in the
		 * entire string .{8,} # anything, at least eight places though $ #
		 * end-of-string
		 */
		Matcher m = p.matcher(toggleOff.getText());
		Matcher m1 = p.matcher(toggleOn.getText());
		if (m.matches() || m1.matches()) {
			System.out.println("Password ok!");
			 return true;
		}
		else {
			System.out.println("Password fail!");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Password");
			alert.setHeaderText(null);
			alert.setContentText("Password must contain: At least 1 Lower Case Letter(a-z), "
					+ "At least 1 Upper Case Letter(A-Z), 1 number(0-9), 1 Special Character(@#$%^&+=) and "
					+ "length of 8 - 15");
			alert.showAndWait();
		return false;
		}
	}

	private boolean validateEmail() {
		Pattern p = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-z0-9]+([.][a-zA-z]+)+");
		Matcher m = p.matcher(mailInput.getText());
		if (m.find() && m.group().equals(mailInput.getText())) {
			System.out.println("Email valid!");
			return true;
		} else {
			System.out.println("Email fail!");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Email");
			alert.setHeaderText(null);
			alert.setContentText("Email must be valid and must contain: Any Numbers, Any Alphabets and Special Character'@'.");
			alert.showAndWait();
			return false;
		}
	}

	public void clearFields() {
		toggleOn.clear();
		mailInput.clear();
	}

}
