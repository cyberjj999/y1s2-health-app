package healthApp.model;

import java.io.IOException;

import healthApp.controller.AnklePainController1;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class healthApp extends Application {
	
	private static Stage primaryStage;
	private static Parent root;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		
		//showHomeView();
		showHomeView();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	//UnderPrograms.fxml
	
	// These are all methods to just run the loading to next scene
	public static void showHomeView() throws IOException{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(healthApp.class.getResource("/healthApp/view/HomeView.fxml"));
		root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Home");
		
	}
	
	@FXML
	public void goPrograms() throws IOException {
		// TODO Autogenerated
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(healthApp.class.getResource("/healthApp/view/UnderPrograms.fxml"));
		root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Home");
	}
	
	@FXML
	public void goPhysio() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(healthApp.class.getResource("/healthApp/view/ConditionSelection.fxml"));
		root = loader.load();
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Select condition");
	}
	
	@FXML
	public void goCustomisedWorkout() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(healthApp.class.getResource("/healthApp/view/CustConditionSelection.fxml"));
		root = loader.load();
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Select condition");
	}
	@FXML
	public void goBackPain() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(healthApp.class.getResource("/healthApp/view/BackPain.fxml"));
		root = loader.load();
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Exercise");
	}
	@FXML
	public void goQuadPain() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(healthApp.class.getResource("/healthApp/view/QuadPain.fxml"));
		root = loader.load();
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Exercise");
	}
	@FXML
	public void goAnklePain() throws IOException {
		
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/AnklePain.fxml"));
		Parent root=loader.load();
		Scene scene = new Scene(root);
		Stage stage=(Stage)(scene).getWindow();
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void goNeckPain() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(healthApp.class.getResource("/healthApp/view/NeckPain.fxml"));
		root = loader.load();
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Exercise");
	}
	@FXML
	public void goExerciseList() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(healthApp.class.getResource("/healthApp/view/ExerciseList.fxml"));
		root = loader.load();
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Exercise");
	}

	public void goWorkoutHistory() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(healthApp.class.getResource("/healthApp/view/WorkoutHistory.fxml"));
		root = loader.load();
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Exercise");
	}

}
