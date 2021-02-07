package healthApp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import healthApp.data.UsersFoodConsumptionDB;
import healthApp.model.Food;
import healthApp.model.UsersFoodConsumption;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomepageViewController implements Initializable {
	@FXML
	private ImageView feedbackBtn;
	@FXML
	private ImageView locationBtn;
	@FXML
	private ImageView reminderBtn;
	@FXML
	private ImageView exerciseBtn;
	@FXML
	private ImageView homeBtn;
	@FXML
	private ImageView foodBtn;
	@FXML
	private ImageView socialBtn;
	@FXML
	private ImageView userMessageInboxBtn;
	@FXML
	private Button admin;
    @FXML
    private Button editProfBtn;

    @FXML
    private Button viewFoodConsumedBtn;

	private Scene myScene;
    @FXML
    private Button logoutBtn;
    @FXML
    void logOut(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/loginUpdate.fxml"));
		Scene canScene = (Scene) ((Node) event.getSource()).getScene();
		Stage stage = (Stage) (canScene).getWindow();
		Parent nextPls = loader.load();
		Login llG = loader.getController();
		stage.setScene(new Scene(nextPls));
		stage.show();
		//uM.setVisible(false);
    }
    @FXML
    void editProfile(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/healthApp/view/userProfile.fxml"));
		Scene theirScene = (Scene) ((Node) event.getSource()).getScene();
		Stage stage = (Stage) (theirScene).getWindow();
		Parent nextNxt = loader.load();
		UserProfilePageController myA = loader.getController();
		stage.setScene(new Scene(nextNxt));
		stage.setTitle("Change My Picture");
		stage.show();
    }
    
	// Event Listener on ImageView[#feedbackBtn].onMouseClicked
	@FXML
	public void goFeedback(MouseEvent event) throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/UserFeedBack.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();	
		 }
	// Event Listener on ImageView[#locationBtn].onMouseClicked
	@FXML
	public void goLocation(MouseEvent event) throws IOException {

		FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/NutritionTrackingHomepage.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
	}
	// Event Listener on ImageView[#reminderBtn].onMouseClicked
	@FXML
	public void goReminder(MouseEvent event) throws IOException {
		// TODO Autogenerated
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/Reminder.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
	}
	// Event Listener on ImageView[#exerciseBtn].onMouseClicked
	@FXML
	public void goExercise(MouseEvent event) throws IOException {
		// TODO Autogenerated
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/HomeView.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
	}
	// Event Listener on ImageView[#homeBtn].onMouseClicked
	@FXML
	public void goHome(MouseEvent event) throws IOException {
		// TODO Autogenerated
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/HomepageView.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
	}
	// Event Listener on ImageView[#foodBtn].onMouseClicked
	@FXML
	public void goFood(MouseEvent event) throws IOException {
		// TODO Autogenerated
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/NutritionTrackingHomepage.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
	}
	// Event Listener on ImageView[#socialBtn].onMouseClicked
	@FXML
	public void goSocial(MouseEvent event) throws IOException {
		// TODO Autogenerated
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/SocialFunctionView.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
	}
	// Event Listener on ImageView[#userMessageInboxBtn].onMouseClicked
	@FXML
	public void goUserMessageInbox(MouseEvent event) throws IOException {
		// TODO Autogenerated
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/UsersMessageInbox.fxml")));
		 myScene=(Scene)((Node)event.getSource()).getScene();
		 Stage stage=(Stage)(myScene).getWindow();
		 Parent nextView=loader.load();
		 stage.setScene(new Scene(nextView));
		 stage.show();
	}
	// Event Listener on Button[#admin].onAction
	@FXML
	public void toAdmin(ActionEvent event) {
		// TODO Autogenerated
	}
	
	

    @FXML
    void viewConsumedFood(ActionEvent event) throws IOException {
    	FXMLLoader loader=new FXMLLoader();
		loader.setLocation((getClass().getResource("/healthApp/view/ViewFoodConsumed.fxml")));
		myScene=(Scene)((Node)event.getSource()).getScene();
		Stage stage=(Stage)(myScene).getWindow();
		Parent nextView=loader.load();
		stage.setScene(new Scene(nextView));
		stage.show();		
		
		ViewFoodConsumedController vFCC = loader.getController();
		UsersFoodConsumptionDB UsersFoodDB = new UsersFoodConsumptionDB();
		
		if(UsersFoodDB.getUsers("User1")==null) {
			System.out.println("No such user");
			
			vFCC.addToVbox(new ArrayList <String>());
		}
		
		else {
		UsersFoodConsumption testUser = UsersFoodDB.getUsers("User1");
		ArrayList <Food> FoodArrayList = testUser.getFoodArrayList();
		ArrayList <String> foodList = new ArrayList <String>();
		
		double totalCalories = 0;
		double totalProtein = 0;
		double totalCarbs = 0;
		double totalFats = 0;
		double totalCholesterol = 0;
		double totalSugar = 0;
		double totalSodium = 0;
		
		for (Food foodItems : FoodArrayList) {
			foodList.add(foodItems.getName());
			totalCalories+=foodItems.getCalories();
			totalProtein+=foodItems.getProtein();
			totalCarbs+=foodItems.getCarbs();
			totalFats+=foodItems.getFats();
			totalCholesterol+=foodItems.getCholesterol();
			totalSugar+=foodItems.getSugar();
			totalSodium+=foodItems.getSodium();
			
		}
		
		vFCC.setCalories(totalCalories);
		vFCC.setProtein(totalProtein);
		vFCC.setCarbs(totalCarbs);
		vFCC.setFats(totalFats);
		vFCC.setCholesterol(totalCholesterol);
		vFCC.setSugar(totalSugar);
		vFCC.setSodium(totalSodium);
		
		vFCC.addToVbox(foodList);
		System.out.println(testUser.getFoodArrayList());
		}
		
	
    	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		viewFoodConsumedBtn.setStyle("-fx-border-color: #17202A; -fx-border-width: 3px;-fx-background-color:pink;");
		
		
	}
}