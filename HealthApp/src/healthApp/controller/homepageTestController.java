package healthApp.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class homepageTestController {

    @FXML
    private Button testBtn;

    @FXML
    private Button testBtn2;

    @FXML
    private Button testBtn3;

    @FXML
    private Button testBtn4;

    private Scene myScene;
    @FXML
    void goAdminInbox(ActionEvent event) throws IOException {

    	FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/AdminMessageInbox.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
    }

    @FXML
    void goNutritionTrack(ActionEvent event) throws IOException {
    	FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/NutritionTrackingHomepage.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
    }

    @FXML
    void goUserFeedback(ActionEvent event) throws IOException {
    	FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/UserFeedBack.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
    }

    @FXML
    void goUserInbox(ActionEvent event) throws IOException {
    	FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/UsersMessageInbox.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
    }

}
