package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Admin extends Application {

	@Override
	public void start(Stage primaryStage) {
		//load viewFriend.fxml
		
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/healthApp/view/adminLoginFXML.fxml"));
			
			//use the loader to get root control: (AnchorPane)
			AnchorPane root = loader.load();
			//Region root = loader.load();
			//Parent root = loader.load();
			
			//create a scene and add the root control to the scene
			Scene scene = new Scene(root, 900, 600);
			
			scene.getStylesheets().addAll("application/signUp.css", "application/loginUpdate.css", "application/wrongPassword.css");
			
			//attach the scene to the stage by setting the scene to the stage
			primaryStage.setScene(scene);
			
			//give a stage name using setTitle()
			primaryStage.setTitle("Login Page");
			
			//and finally show the stage
			primaryStage.show();
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
