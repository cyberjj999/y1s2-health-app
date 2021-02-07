package healthApp.controller;

import javafx.fxml.FXML;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import javax.imageio.ImageIO;
import javax.swing.colorchooser.ColorSelectionModel;

import org.mapdb.DB;
import org.mapdb.DBMaker;
//import com.sun.xml.internal.ws.message.EmptyMessageImpl;

import healthApp.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class UserProfilePageController implements Initializable {

	@FXML
	private Button goBackHome;

	@FXML
	private ImageView picsChange;

	@FXML
	private BufferedImage image;

	@FXML
	private TextField username;

	@FXML
	private TextField fullname;

	@FXML
	private TextField email;

	@FXML
	private ComboBox ownLocation;
	
	@FXML
	private ListView myHealthList;

	@FXML
	private Button up;
	
	private String emailFromSignUp;

	private final String CUSTOMER_DB = "data/users.db";
	private static final String USER_NAME = "users";
	private static DB db2;
	private static ConcurrentNavigableMap<String, Users> avg;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Path dPath = FileSystems.getDefault().getPath(CUSTOMER_DB);
		File file = new File(dPath.toString());
		db2 = DBMaker.newFileDB(file).closeOnJvmShutdown().make();
		avg = db2.createTreeMap(USER_NAME).makeOrGet();
		
		ObservableList hiList = FXCollections.observableArrayList();
		hiList.add("Knee Pain");
		hiList.add("Diabetes");
		hiList.add("High Blood Pressure");

		Set<Map.Entry<String, Users>> entries = avg.entrySet();
		for (Map.Entry<String, Users> u : entries) {
			this.username.setText(u.getValue().getUserName());
			this.myHealthList.setItems(hiList);
			this.myHealthList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			this.ownLocation.setPromptText(u.getValue().getLocation());
		}
		email.setDisable(true);
	}

	public void pictureChange() throws Exception {
		// Changing the profile image for users

		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open resource file");
		// use existing window here, don't create a new one
		File file = fileChooser.showOpenDialog(picsChange.getScene().getWindow());
		if (file != null) {
			// openFile(file);
			// delete old image in ImageView
			// NOT WORKING
			picsChange.setImage(null);
			System.gc();
			// set new image users choose in ImageView
			picsChange.setImage(new Image(file.toURI().toString()));
			File outputFile = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator
					+ "itp192" + File.separator + "pic.png");
			System.out.println(outputFile.getAbsolutePath());
			if (!outputFile.getParentFile().exists())
				outputFile.getParentFile().mkdir();
			BufferedImage image = SwingFXUtils.fromFXImage(picsChange.getImage(), null);
			System.out.println(image.toString());

			try {
				ImageIO.write(image, "png", outputFile);

			} catch (IOException e) {
				e.printStackTrace();
			}

			Set<Map.Entry<String, Users>> entries = avg.entrySet();
			for (Map.Entry<String, Users> u : entries) {
				Users toDate = new Users(u.getValue().getName(), u.getValue().getUserName(), u.getValue().getEmail(),
						u.getValue().getPassword(), u.getValue().getHealth(), u.getValue().getLocation(),
						u.getValue().getImageType());
				avg.put(email.getText(), toDate);
				db2.commit();

			}
		}

	}

	private Scene byeScene;

	public void backToHome(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/loginUpdate.fxml"));
		byeScene = (Scene) ((Node) e.getSource()).getScene();
		Stage stage = (Stage) (byeScene).getWindow();
		Parent nextMyView = loader.load();
		Login logUp = loader.getController();
		stage.setScene(new Scene(nextMyView));
		stage.setTitle("Redirected");
		stage.show();
	}

	public void clickMe(ActionEvent esoup) {
		String changeUserName = avg.get(email.getText()).getUserName();
		String changeFullName = avg.get(email.getText()).getName();
		String changeMyHealth = myHealthList.getSelectionModel().getSelectedItems().toString();
		String changeMyLocation = (String)ownLocation.getSelectionModel().getSelectedItem();

		Set<Map.Entry<String, Users>> entries = avg.entrySet();
		
		Users a = avg.get(email.getText());
		a.setUserName(changeUserName);
		
		a.setName(changeFullName);
		
		a.setHealth(changeMyHealth);
		
		a.setLocation(changeMyLocation);
		avg.replace(email.getText(), a);
		db2.commit();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Update Successful!");
		alert.setHeaderText(null);
		alert.setContentText("Your information has been successfully updated!");
		System.out.println("Information ok");
	}
	
	public void setEmailFromSignUp(String emailFromSignUp) {
		this.emailFromSignUp = emailFromSignUp;
		this.email.setText(emailFromSignUp);
	}

}
