package healthApp.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.mapdb.DB;
import org.mapdb.DBMaker;
//import com.sun.javafx.scene.control.behavior.PasswordFieldBehavior;
//import com.sun.javafx.scene.control.skin.TextFieldSkin;


import healthApp.data.UsersMapDB;
import healthApp.model.Users;
import healthApp.model.adminAccess;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;

public class adminLoginController implements Initializable {

	@FXML
	private Button lI;

	@FXML
	private Button signOut;

	@FXML
	private Button authorized;

	@FXML
	private CheckBox mark;

	@FXML
	private Button wAA;
	
	@FXML
	private TextField adminIdentity;
	
	@FXML
	private TextField seeOff;
	
	@FXML
	private PasswordField seeOn;

	@FXML
	private Button userData;

	private final String ADMIN_DB = "data/adminUsers.db";
	private static final String ADMIN_STORE = "admin";
	private static DB db3;
	private static ConcurrentNavigableMap<String, adminAccess> aas;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Path dPath = FileSystems.getDefault().getPath(ADMIN_DB);
		File file = new File(dPath.toString());

		db3 = DBMaker.newFileDB(file).closeOnJvmShutdown().make();

		// create a map in the db for persisting student data
		// map can only hold same type of data. Create separate map for separate
		// type data
		aas = db3.createTreeMap(ADMIN_STORE).makeOrGet();

		signOut.setVisible(false);

	}

	public void accessAdminPage(ActionEvent epp) throws Exception {

		if (checkRecords()) {
			System.out.println("Email is correctly entered");
			signOut.setVisible(true);
			Set<Map.Entry<String, adminAccess>> entries = aas.entrySet();
			for (Map.Entry<String, adminAccess> a : entries) {
				a.getValue().getAdminID();
				a.getValue().getAdminPassword();
			}
			
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation((getClass().getResource("/healthApp/view/AdminHomePage.fxml")));
			 Scene myScene=(Scene)((Node)epp.getSource()).getScene();
			 Stage stage=(Stage)(myScene).getWindow();
			 Parent nextView=loader.load();
			 stage.setScene(new Scene(nextView));
			 stage.show();
			
			
		}
		
		
		
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText(null);
			alert.setContentText("One or Both of the fields has been entered wrongly! Please re-enter the fields.");
			alert.showAndWait();
			seeOn.clear();
			adminIdentity.clear();
		}
	}
	
	public boolean checkRecords() {
		String hidePassword = seeOn.getText(); // PasswordField
		String showPassword = seeOff.getText(); // TextField
		
		
		if(aas.get(adminIdentity.getText())== null) {
			System.out.println("Error in email");
		}else if(aas.get(adminIdentity.getText()).getAdminPassword() != showPassword || aas.get(adminIdentity.getText()).getAdminPassword() != hidePassword) {
			System.out.println("Error in password");
		}else if (aas.get(adminIdentity.getText()).getAdminPassword().equals(showPassword) || aas.get(adminIdentity.getText()).getAdminPassword().equals(hidePassword)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Successful Login");
			alert.setHeaderText(null);
			alert.setContentText("Admin Login Successful! Welcome back Admin User: " + aas.get(adminIdentity.getText()).getAdminID());
			alert.showAndWait();
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Password");
			alert.setHeaderText(null);
			alert.setContentText("OOPS! Wrong Password has been entered! Please check and Re-Enter the Correct Password.");
			alert.showAndWait();
			seeOn.clear();
			return false;	
		}
		return false;
	}

	public void adminPasswordInput(ActionEvent ex) throws Exception {
		class PasswordFieldSkin extends TextFieldSkin {
			public static final char BULLET = '\u2022';

			public PasswordFieldSkin(PasswordField seeOn) {
				super(seeOn);
			}

			@Override
			protected String maskText(String t) {
				TextField seeOff = getSkinnable();

				int n = seeOff.getLength();
				StringBuilder passwordBuilder = new StringBuilder(n);
				for (int i = 0; i < n; i++) {
					passwordBuilder.append(BULLET);
				}

				return passwordBuilder.toString();
			}
		}
		// text field to show password as unmasked
		// Set initial state
		seeOff.setManaged(false);
		seeOff.setVisible(false);
	}

	public void marked(ActionEvent epple) throws Exception {
		// Actual password field
		// PasswordField = seeOn
		// TextField = seeOff

		// Bind properties. Toggle textField and passwordField
		// visibility and managability properties mutually when checkbox's state is
		// changed.
		// Because we want to display only one component (textField or passwordField)
		// on the scene at a time.
		seeOff.managedProperty().bind(mark.selectedProperty());
		seeOff.visibleProperty().bind(mark.selectedProperty());

		seeOn.managedProperty().bind(mark.selectedProperty().not());
		seeOn.visibleProperty().bind(mark.selectedProperty().not());

		// Bind the textField and passwordField text values bidirectionally.
		seeOff.textProperty().bindBidirectional(seeOn.textProperty());
	}

	public void clearFields() {
		seeOn.clear();
		adminIdentity.clear();
	}

	private Scene canScene;
	public void usersDataBase(ActionEvent eli) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/AllUsers.fxml"));
		canScene = (Scene) ((Node) eli.getSource()).getScene();
		Stage stage = (Stage) (canScene).getWindow();
		Parent nextPls = loader.load();
		DisplayAllUsers dAS = loader.getController();
		stage.setScene(new Scene(nextPls));
		stage.show();
	}
	
	private Scene pScene;
	public void connectToDbB(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/Forum.fxml"));
		pScene = (Scene) ((Node) e.getSource()).getScene();
		Stage stage = (Stage) (pScene).getWindow();
		Parent nextPls = loader.load();
		DisplayAllUsers dAS = loader.getController();
		stage.setScene(new Scene(nextPls));
		stage.show();
	}


private Scene noScene;
	public void logOut(ActionEvent eaas) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/adminLoginFXML.fxml"));
		noScene = (Scene) ((Node) eaas.getSource()).getScene();
		Stage stage = (Stage) (noScene).getWindow();
		Parent nextPls = loader.load();
		adminLoginController aL = loader.getController();
		stage.setScene(new Scene(nextPls));
		stage.show();
	}

	private Scene popScene;

	public void wantAnAccount(ActionEvent a) throws Exception {
		Dialog<String> dialog = new Dialog<>();
		dialog.setHeaderText("Admin Access Sign Up");
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		Label l = new Label("Admin ID:");
		TextField t = new TextField();
		t.setPromptText("Please enter Admin ID here ");
		t.setMinHeight(30);
		t.setMinWidth(50);
		
		Label b = new Label("Admin Password");
		PasswordField f = new PasswordField();
		f.setPromptText("Please enter Password here");
		f.setMinHeight(30);
		f.setMinWidth(50);
		
		dialogPane.setMinHeight(200);
		dialogPane.setMinWidth(300);
		dialogPane.setContent(new VBox(8, l, t, b, f));
		dialog.setResultConverter((ButtonType button) -> {
			if (button == ButtonType.OK) {
				
				String aID = t.getText();
				String aPword = f.getText();
				adminAccess creation = new adminAccess(aID, aPword);
				aas.put(aID, creation);
				db3.commit();
				
				Set<Map.Entry<String, adminAccess>> entries = aas.entrySet();
				for (Map.Entry<String, adminAccess> ab : entries) {
					System.out.println(ab.getValue().getAdminID());
					System.out.println(ab.getValue().getAdminPassword());
				}
			}
			return null;
		});
		Optional<String> option = dialog.showAndWait();
		if (option.isPresent()) {

		}
	}
}
