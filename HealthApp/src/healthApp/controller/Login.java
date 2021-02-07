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
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;

public class Login implements Initializable {


private static String loginCheck;
	
    public String getLoginCheck() {
		return loginCheck;
	}

	public void setLoginCheck(String loginCheck) {
		this.loginCheck = loginCheck;
	}
	
	@FXML
	private Hyperlink redirectToSignUpPage;

	@FXML
	private Hyperlink changePassword;

	@FXML
	private Hyperlink uM;
	
	@FXML
	private Hyperlink guess;

	@FXML
	private Button lI;
	
	@FXML
	private Button signOut;
	
	@FXML
	private Button authorized;

	@FXML
	private TextField mailInput;

	@FXML
	private TextField toggleOff;

	@FXML
	private PasswordField toggleOn;
	
	private String emailFromSignUp;

	@FXML
	private CheckBox tick;

	private Scene myScene;

	private final String CUSTOMER_DB = "data/users.db";
	private static final String USER_NAME = "users";
	private static DB db2;
	private static ConcurrentNavigableMap<String, Users> avg;

	// When user click on HyperLink
	// redirects users to Sign Up Page will be called.
	// NOTE: FOR TOOLTIP TO SHOW STAY STIL FOR 2 SECS

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Path dPath = FileSystems.getDefault().getPath(CUSTOMER_DB);
		File file = new File(dPath.toString());

		db2 = DBMaker.newFileDB(file).closeOnJvmShutdown().make();

		// create a map in the db for persisting student data
		// map can only hold same type of data. Create separate map for separate
		// type data
		avg = db2.createTreeMap(USER_NAME).makeOrGet();
		
		signOut.setVisible(false);
		uM.setVisible(false);

	}

	public void signingUpPage(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/signUp.fxml"));
		myScene = (Scene) ((Node) event.getSource()).getScene();
		Stage stage = (Stage) (myScene).getWindow();
		Parent nextView = loader.load();
		SignUpController aC = loader.getController();
		stage.setScene(new Scene(nextView));
		stage.setTitle("Sign up Page");
		stage.show();
	}

	private Scene urScene;

	public void forgotPassword(ActionEvent myEvent) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/wrongPassword.fxml"));
		urScene = (Scene) ((Node) myEvent.getSource()).getScene();
		Stage stage = (Stage) (urScene).getWindow();
		Parent nextNextView = loader.load();
		ForgotPasswordController pR = loader.getController();
		stage.setScene(new Scene(nextNextView));
		stage.setTitle("Testing");
		stage.show();

	}

	private Scene oneScene;

	public void guessing(ActionEvent exp) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/StartingAForum.fxml"));
		oneScene = (Scene) ((Node) exp.getSource()).getScene();
		Stage stage = (Stage) (oneScene).getWindow();
		Parent critical = loader.load();
		CreatingForums cF = loader.getController();
		stage.setScene(new Scene(critical));
		stage.setTitle("FORUM TIME");
		stage.show();
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
	
public void logIn(ActionEvent epp) throws Exception {
	
		if (checkRecords()) {
				System.out.println("Email is correctly entered");
				signOut.setVisible(true);
				
				setLoginCheck(mailInput.getText());
				System.out.println(emailFromSignUp);
				System.out.println(getLoginCheck());
				
				selectedFoodController sFC = new selectedFoodController();
				sFC.setLoggedInUser(mailInput.getText());
				AdminMessageInboxController aMIC = new AdminMessageInboxController();
				aMIC.setLoggedInUser(mailInput.getText());
				AdminMessageInboxDetailsController aMIDC = new AdminMessageInboxDetailsController();
				aMIDC.setLoggedInUser(mailInput.getText());
				ConsumedFoodInfoController cFIC = new ConsumedFoodInfoController();
				cFIC.setLoggedInUser(mailInput.getText());
				UserFeedBackController uFB = new UserFeedBackController();
				uFB.setLoggedInUser(mailInput.getText());
				UsersMessageInboxController uMIC = new UsersMessageInboxController();
				uMIC.setLoggedInUser(mailInput.getText());
				UsersMessageInboxDetailsController uMIDC = new UsersMessageInboxDetailsController();
				uMIDC.setLoggedInUser(mailInput.getText());
				ViewFoodConsumedController vFCC = new ViewFoodConsumedController();
				vFCC.setLoggedInUser(mailInput.getText());
				
				UsersMapDB userDB = new UsersMapDB();
				userDB.setLoggedInUser(emailFromSignUp);

				
				FXMLLoader loader=new FXMLLoader();
				loader.setLocation((getClass().getResource("/healthApp/view/HomepageView.fxml")));
				 myScene=(Scene)((Node)epp.getSource()).getScene();
				 Stage stage=(Stage)(myScene).getWindow();
				 Parent nextView=loader.load();
				 stage.setScene(new Scene(nextView));
				 stage.show();
			
				 
				
		}
		else {
			System.out.println("Error! Wrong email account");
	}
	
}

	public boolean checkRecords() {
		String hide = toggleOn.getText(); //PasswordField
		String show = toggleOff.getText(); //TextField
	
			if(avg.get(mailInput.getText()).getPassword().equals(hide) || avg.get(mailInput.getText()).getPassword().equals(show)) {
				System.out.println("Password is correct!");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Successful Login");
				alert.setHeaderText(null);
					String username = avg.get(mailInput.getText()).getUserName();
					alert.setContentText("Congratulations! You have been successfully logged in!." + " Welcome back " + username + " !");
					uM.setVisible(true);
				alert.showAndWait();
				return true;
			}else {
				System.out.println("Wrong Password");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid Password");
				alert.setHeaderText(null);
				alert.setContentText("OOPS! Wrong Password has been entered! Please check and Re-Enter the Correct Password.");
				alert.showAndWait();
				toggleOn.clear();
				return false;
			}
	}

	public static void printRecords() {
		Set<Map.Entry<String, Users>> entries = avg.entrySet();
		for (Map.Entry<String, Users> u : entries) {
			System.out.println(u.getValue().getEmail());
			System.out.println(u.getValue().getPassword());
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

	
	public void clearFields() {
		toggleOn.clear();
		mailInput.clear();
	}
	
	private Scene canScene;
	public void logOut(ActionEvent eaas) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/loginUpdate.fxml"));
		canScene = (Scene) ((Node) eaas.getSource()).getScene();
		Stage stage = (Stage) (canScene).getWindow();
		Parent nextPls = loader.load();
		Login llG = loader.getController();
		stage.setScene(new Scene(nextPls));
		stage.show();
		uM.setVisible(false);
	}
	
	public void setEmailFromSignUp(String emailFromSignUp) {
		this.emailFromSignUp = mailInput.getText();
	}
	

}
