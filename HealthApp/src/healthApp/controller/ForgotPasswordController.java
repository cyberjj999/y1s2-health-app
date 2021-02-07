package healthApp.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
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
//import forumModel.ForForums;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.stage.Stage;

public class ForgotPasswordController implements Initializable {
 
   @FXML
   private Button genCap;
   
   @FXML
   private TextArea genWords;
   
   @FXML
   private Button bTL;
   
   @FXML
   private TextField eAdd;
   
   @FXML
   private TextField replaceOff;
   
   @FXML
   private PasswordField replaceOn;
   
   @FXML
   private TextField reEveOff;
   
   @FXML
   private PasswordField reEveOn;
   
   @FXML
   private CheckBox upG;
   
   @FXML
   private CheckBox rIP;
   
   @FXML
   private CheckBox reEve;
   
    private final String CUSTOMER_DB = "data/Users.db";
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
   }
   
   private Scene hiScene;
   
   @FXML
   private void back(ActionEvent event) throws Exception {
	   	  FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(getClass().getResource("/healthApp/view/loginUpdate.fxml"));
		  hiScene = (Scene)((Node)event.getSource()).getScene();
		  Stage stage = (Stage)(hiScene).getWindow();
		  Parent nextMyView = loader.load();
		  Login logUp= loader.getController();
		  stage.setScene(new Scene(nextMyView));
		  stage.setTitle("Testing");
		  stage.show();
   }
   
   private Scene hmScene;
   public void generateWords(ActionEvent event) throws Exception {
	   if(checkRecords()) {
		   Alert alert = new Alert(AlertType.INFORMATION);
		   alert.setTitle("Success!");
		   alert.setHeaderText(null);
		   alert.setContentText("Password has been successfully changed! Login to your account now!");
		   alert.showAndWait();
		   FXMLLoader loader = new FXMLLoader();
		   loader.setLocation(getClass().getResource("/healthApp/viewloginUpdate.fxml"));
		   hmScene = (Scene)((Node)event.getSource()).getScene();
		   Stage stage = (Stage)(hmScene).getWindow();
		   Parent ok = loader.load();
		   Login logUp= loader.getController();
		   stage.setScene(new Scene(ok));
		   stage.show();
	   }else {
		   Alert alert = new Alert(AlertType.ERROR);
		   alert.setTitle("Error!");
		   alert.setHeaderText("Password Incorrect");
		   alert.setContentText("Password in one of the fields has been entered incorrectly! Please Re-Enter and Try Again");
		   alert.showAndWait();
		   replaceOn.clear();
		   reEveOn.clear();
	   }
	 
    } 
   
	//FOR NEW PASSWORD
	public void passwordInputN(ActionEvent ex) throws Exception {
		class PasswordFieldSkin extends TextFieldSkin {
			public static final char BULLET = '\u2022';

			public PasswordFieldSkin(PasswordField replaceOn) {
				super(replaceOn);
			}

			@Override
			protected String maskText(String txt) {
				TextField replaceOff = getSkinnable();

				int n = replaceOff.getLength();
				StringBuilder passwordBuilder = new StringBuilder(n);
				for (int i = 0; i < n; i++) {
					passwordBuilder.append(BULLET);
				}

				return passwordBuilder.toString();
			}
		}
		// text field to show password as unmasked
		// Set initial state
		replaceOff.setManaged(false);
		replaceOff.setVisible(false);
	}

	//FOR NEW PASSWORD
	public void nPT(ActionEvent exop) throws Exception {
		// Actual password field
		// PasswordField = replaceOff
		// TextField = replaceOn

		// Bind properties. Toggle textField and passwordField
		// visibility and managability properties mutually when checkbox's state is
		// changed.
		// Because we want to display only one component (textField or passwordField)
		// on the scene at a time.
		replaceOff.managedProperty().bind(rIP.selectedProperty());
		replaceOff.visibleProperty().bind(rIP.selectedProperty());

		replaceOn.managedProperty().bind(rIP.selectedProperty().not());
		replaceOn.visibleProperty().bind(rIP.selectedProperty().not());

		// Bind the textField and passwordField text values bidirectionally.
		replaceOff.textProperty().bindBidirectional(replaceOn.textProperty());
	}
	
	//RE-ENTER NEW PASSWORD 
	public void passwordInputRe(ActionEvent ex) throws Exception {
		class PasswordFieldSkin extends TextFieldSkin {
			public static final char BULLET = '\u2022';

			public PasswordFieldSkin(PasswordField reEveOn ) {
				super(reEveOn);
			}

			@Override
			protected String maskText(String txt) {
				TextField reEveOff = getSkinnable();

				int n = reEveOff.getLength();
				StringBuilder passwordBuilder = new StringBuilder(n);
				for (int i = 0; i < n; i++) {
					passwordBuilder.append(BULLET);
				}

				return passwordBuilder.toString();
			}
		}
		// text field to show password as unmasked
		// Set initial state
		reEveOff.setManaged(false);
		reEveOff.setVisible(false);
	}

	//RE-ENTER FOR NEW PASSWORD
	public void rENPT(ActionEvent exops) throws Exception {
		// Actual password field
		// PasswordField = reEveOff
		// TextField = reEveOn

		// Bind properties. Toggle textField and passwordField
		// visibility and managability properties mutually when checkbox's state is
		// changed.
		// Because we want to display only one component (textField or passwordField)
		// on the scene at a time.
		reEveOff.managedProperty().bind(reEve.selectedProperty());
		reEveOff.visibleProperty().bind(reEve.selectedProperty());

		reEveOn.managedProperty().bind(reEve.selectedProperty().not());
		reEveOn.visibleProperty().bind(reEve.selectedProperty().not());

		// Bind the textField and passwordField text values bidirectionally.
		reEveOff.textProperty().bindBidirectional(reEveOn.textProperty());
	}
   
	public boolean checkRecords() {
		Set<Map.Entry<String, Users>> entries = avg.entrySet();
		String myReplacedPasswordOff = replaceOff.getText();
		String myReplacedPasswordOn = replaceOn.getText();
		for (Map.Entry<String, Users> u : entries) {
			System.out.println("Email before change: " + u.getValue().getEmail());
			System.out.println("Password before change: " + u.getValue().getPassword());
			if(avg.get(eAdd.getText()).getEmail().equals(eAdd.getText())) {
				if(myReplacedPasswordOn != u.getValue().getPassword()) {
					Users str = new Users(u.getValue().getName(), u.getValue().getUserName(), u.getValue().getEmail(), myReplacedPasswordOn, u.getValue().getHealth(), u.getValue().getLocation(), u.getValue().getImageType());
					avg.put(u.getValue().getEmail(), str);
					db2.commit();
					System.out.println("Password after change: " + u.getValue().getPassword());
					System.out.println("Email after change: " + u.getValue().getEmail());
				
				}else if(myReplacedPasswordOff != u.getValue().getPassword()) {
					Users str = new Users(u.getValue().getName(), u.getValue().getUserName(), u.getValue().getEmail(), myReplacedPasswordOff, u.getValue().getHealth(), u.getValue().getLocation(), u.getValue().getImageType());
					avg.put(u.getValue().getEmail(), str);
					db2.commit();
					System.out.println("Password after change: " + u.getValue().getPassword());
					System.out.println("Email after change: " + u.getValue().getEmail());
			}	
		}
	}
		return true;
}
	
}
   
