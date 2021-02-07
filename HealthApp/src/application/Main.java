package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;


public class Main extends Application {
	
	public static Stage primaryStage;
	public static Parent root;

	@Override
	public void start(Stage primaryStage) throws IOException{
		this.primaryStage = primaryStage;
		show("/healthApp/view/HomeView.fxml");
		
		
	}


	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void show(String location) throws IOException{
		
		FXMLLoader loader = new FXMLLoader();	
		loader.setLocation(Main.class.getResource(location));
		root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
