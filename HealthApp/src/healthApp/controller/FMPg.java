package healthApp.controller;

import java.io.File;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FMPg implements Initializable {
	@FXML
	private Button btnSendMe;

	@FXML
	private ImageView writeSomething;

	@FXML
	private Button changeF;

	@FXML
	private Button sPg;

	@FXML
	private ListView<Pane> vape;

	private String topicTitleFromCreatingForum;

	private final String FORUM__DB = "data/forum.db";
	private static final String FORUM_INFORMATION = "forum";
	private static DB db;
	private static ConcurrentNavigableMap<String, ForForums> map;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Path dPath = FileSystems.getDefault().getPath(FORUM__DB);
		File file = new File(dPath.toString());
		db = DBMaker.newFileDB(file).closeOnJvmShutdown().make();
		map = db.createTreeMap(FORUM_INFORMATION).makeOrGet();
	}

	public void owYou() {
		GridPane gp = new GridPane();
		Set<Map.Entry<String, ForForums>> entries = map.entrySet();
		gp.add(new Label(map.get(topicTitleFromCreatingForum).getTitleOfTopic()), 1, 1);
		gp.add(new Label(map.get(topicTitleFromCreatingForum).getForum_content()), 9, 0);
		gp.add(new Label(map.get(topicTitleFromCreatingForum).getComboItems()), 1, 2);
		gp.add(new Label(map.get(topicTitleFromCreatingForum).getForum_createdDateTime()), 1, 5);
		vape.setStyle("-fx-padding: 1px;");
		gp.getChildren().get(0).setStyle("-fx-padding:10;");
		gp.setStyle("-fx-background-color: #F5F5F5; -fx-border-radius: 50px;");

		gp.setEffect(new DropShadow(10, Color.PURPLE));
		gp.getChildren().forEach(System.out::println);
		vape.getItems().add(gp);
		System.out.println();
	}

	public void letMeCreate(MouseEvent lMC) throws Exception {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Dialog Test");
		dialog.setHeaderText("Please specify…");
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		TextArea t = new TextArea();
		t.setPromptText("Please type something");
		dialogPane.setContent(new VBox(t));
		dialog.setResultConverter((ButtonType button) -> {
			if (button == ButtonType.OK) {
				String message = t.getText();
				Date now = new Date();
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
				String timeStampNow = df.format(now);
				Set<Map.Entry<String, ForForums>> entries = map.entrySet();
				for (Map.Entry<String, ForForums> f : entries) {
					ForForums recording = new ForForums(f.getValue().getTitleOfTopic(), f.getValue().getComboItems(),
							message, timeStampNow);
					map.put(f.getValue().getTitleOfTopic(), recording);
					db.commit();
					System.out.println(f.getValue().getComboItems());
					System.out.println(f.getValue().getTitleOfTopic());
					System.out.println(f.getValue().getForum_content());
					System.out.println(f.getValue().getForum_createdDateTime());
				}
				owYou();
			}
			return null;
		});
		Optional<String> option = dialog.showAndWait();
		if (option.isPresent()) {

		}
	}

	private Scene byeScene;

	public void bringMeBack(ActionEvent espps) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/loginUpdate.fxml"));
		byeScene = (Scene) ((Node) espps.getSource()).getScene();
		Stage stage = (Stage) (byeScene).getWindow();
		Parent nextMyView = loader.load();
		Login lll = loader.getController();
		stage.setScene(new Scene(nextMyView));
		stage.setTitle("See Forum");
		stage.show();
	}

	private Scene badScene;

	public void changeForum(ActionEvent espps) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/FMPg.fxml"));
		badScene = (Scene) ((Node) espps.getSource()).getScene();
		Stage stage = (Stage) (badScene).getWindow();
		Parent nextView = loader.load();
		FMPg fmp = loader.getController();
		stage.setScene(new Scene(nextView));
		stage.setTitle("See Forum");
		stage.show();
	}

	private Scene goodScene;

	public void createAForum(ActionEvent espps) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/StartingAForum.fxml"));
		goodScene = (Scene) ((Node) espps.getSource()).getScene();
		Stage stage = (Stage) (goodScene).getWindow();
		Parent ne = loader.load();
		CreatingForums cFF = loader.getController();
		stage.setScene(new Scene(ne));
		stage.setTitle("See Forum");
		stage.show();
	}

	public String getTopicTitleFromCreatingForum() {
		return topicTitleFromCreatingForum;
	}

	public void setTopicTitleFromCreatingForum(String topicTitleFromCreatingForum) {
		System.out.println(String.valueOf(map == null) + " asdfq123");
		this.topicTitleFromCreatingForum = map.get(topicTitleFromCreatingForum).getTitleOfTopic();
		if (topicTitleFromCreatingForum != null) {
			owYou();
		}
	}

}