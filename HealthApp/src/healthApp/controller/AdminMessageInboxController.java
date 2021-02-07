package healthApp.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import application.Main;
import healthApp.data.UsersFoodConsumptionDB;
import healthApp.data.UsersMapDB;
import healthApp.data.UsersMessageDB;
import healthApp.model.Food;
import healthApp.model.Users;
import healthApp.model.UsersFoodConsumption;
import healthApp.model.UsersMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;

public class AdminMessageInboxController implements Initializable {
	
	private static String loggedInUser;
	
	public String getLoggedInUser() {
		return loggedInUser;
	}



	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	final ToggleGroup group = new ToggleGroup();

	@FXML
	private Pane pane1;
	
    @FXML
    private Button viewMsgBtn;

	TableView<UsersMessage> table;
	private RadioButton selectTV;
	private CheckBox check;

	
	public TableView getTable() {
		return table;
	}

	public void setTable(TableView table) {
		this.table = table;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		/*
		UsersMessageDB UsersMsgDB = new UsersMessageDB();
		UsersMessage UserMessage1 = UsersMsgDB.getUsersMessage("User1");
		ArrayList <String> message = UserMessage1.getMessage();
		ArrayList <String> experience = UserMessage1.getExperience();
		ArrayList <String> categoryAL = UserMessage1.getCategory();
		*/
		
		/*
		 * messageColumn.addEventHandler(MouseEvent.MOUSE_CLICKED,e-> {
			Stage primaryStage = new Stage();
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource("healthApp/view/UserProfile.fxml"));		
			Parent root;
			try {
				root = loader.load();
				Scene scene=new Scene(root);
				primaryStage.initModality(Modality.APPLICATION_MODAL);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Nutrition tracking home page");
				primaryStage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 */
		
		try {
			createTableView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//check verification here
		/*
		UsersMessageDB uMDB = new UsersMessageDB();
		for(int j=0;j<getUsersMessage().size();j++) {
		if(uMDB.getUsersMessage("User1")!=null) {
			if(uMDB.getUsersMessage("User1").getIsChecked().get(j).contains("Checked")) {
			System.out.println(uMDB.getUsersMessage("User1").getIsChecked());	
			table.getItems().get(j).getCheck().setSelected(true);
		
				}
			}
		}
		*/
		
	}
	
	private ObservableList<UsersMessage> getUsersMessage() {
		//UsersMessageDB UsersMsgDB = new UsersMessageDB();
		ObservableList<UsersMessage> userMessages = FXCollections.observableArrayList();
		
		/*
		ArrayList <UsersMessage> allUsersMessage = new ArrayList<UsersMessage>(UsersMsgDB.getMap().values());
	
		for (UsersMessage allUser : allUsersMessage) {
			userMessages.add(allUser);
	
		
			}
			*/

		for(UsersMessage users : createNewObjects()) {
			userMessages.add(users);
		
		}
		//System.out.println(createNewObjects());
		
	return userMessages;

	}
	
	private void createTableView() throws IOException {
		TableView <UsersMessage> table;
		
		
		TableColumn<UsersMessage, String> nameColumn = new TableColumn<>("Username");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameTV"));
		nameColumn.setMinWidth(150);
		
		TableColumn<UsersMessage, String> experienceColumn = new TableColumn<>("Experience");
		experienceColumn.setCellValueFactory(new PropertyValueFactory<>("experienceTV"));
		experienceColumn.setMinWidth(150);
		
		TableColumn<UsersMessage, String> categoryColumn = new TableColumn<>("Category");
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryTV"));
		categoryColumn.setMinWidth(150);
		
		TableColumn<UsersMessage, String> messageColumn = new TableColumn<>("Message");
		//edit here if u wan the msg column to be wider
		messageColumn.setCellValueFactory(new PropertyValueFactory<>("messageTV"));
		messageColumn.resizableProperty();
		
		TableColumn<UsersMessage, String> dateColumn = new TableColumn<>("Date");
		//edit here if u wan the msg column to be wider
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateTV"));
		
		TableColumn<UsersMessage,String> selectColumn = new TableColumn<>("Select");
		selectColumn.setCellValueFactory(new PropertyValueFactory<>("selectTV"));
		
		TableColumn<UsersMessage,String> checkColumn = new TableColumn<>("Replied");
		checkColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
		
		table = new TableView();
		table.setItems(getUsersMessage());	//,selectColumn
		table.getColumns().addAll(nameColumn,experienceColumn,categoryColumn,messageColumn,dateColumn,selectColumn,checkColumn);
		
		table.prefHeightProperty().bind(pane1.heightProperty());
		table.prefWidthProperty().bind(pane1.widthProperty());
		pane1.getChildren().add(table);
		setTable(table);
		
	}
		
	
	public ArrayList <UsersMessage> createNewObjects() {
		
		//	public UsersMessage(String UserID,String experienceTV,String categoryTV,String messageTV,String dateTV) {
		//public UsersMessage(String userID,ArrayList<String> message,ArrayList <String> experinece, ArrayList <String> category,ArrayList <String> date){

		/*here
		 * UsersFoodConsumptionDB UsersFoodDB = new UsersFoodConsumptionDB();
		 *	Users loggedInUser = UsersFoodDB.checkLoggedIn;
		 * if(UsersFoodDB.getUsers(loggedInUser.getEmail()==null){
		 * new UsersFoodConsumption(loggedInUser.getEmail(),new ArrayList <Food>(),new ArrayList <Integer>(),new ArrayList<String>())
		 * }
		 * 
		 * to here
		 */
		
		/*		if(UsersMsgDB.getUsersMessage(loggedInUser.getEmail())==null)
			UsersMsgDB.addUsersMessage(new UsersMessage(loggedInUser.getEmail(),new ArrayList <String>(),new ArrayList <String>(),new ArrayList <String>(),new ArrayList <String>()));

		 * 		UsersMessage UserMessage1 = UsersMsgDB.getUsersMessage(loggedInUser.getEmail());

		 * 
		 */
		UsersMapDB usersDB = new UsersMapDB();
		ArrayList <Users> allUsers = new ArrayList<Users>(usersDB.getMap().values());

		//Users loggedInUser = usersDB.checkLoggedIn(getLoggedInUser());
		UsersMessageDB UsersMsgDB = new UsersMessageDB();

		//System.out.println("The logged in User is " + loggedInUser.getName());
	//	System.out.println("The email of the logged in User is : " + getLoggedInUser());
		
		ArrayList <UsersMessage> userMessageAL = new ArrayList <UsersMessage>();

		for(Users u : allUsers) {
			//check if users has a message - inside usersMsgDB
			//if they never send , means dh , so no need to create
			if(UsersMsgDB.getUsersMessage(u.getEmail()) == null){
				
			}
			
			//that particular user has a msg , or two or more
			//here alr selected that FIRST user or SECOND user
			//now its just to retrieve it based on its email
			else {
				//for(UsersMessage uMsg : UsersMsgDB.getUsersMessage(u.getEmail()).getMessage())  {
				//UsersMessage UserMessageAL = new UsersMessage();
					ArrayList <String> message = UsersMsgDB.getUsersMessage(u.getEmail()).getMessage();
					ArrayList <String> experience = UsersMsgDB.getUsersMessage(u.getEmail()).getExperience();
					ArrayList <String> categoryAL = UsersMsgDB.getUsersMessage(u.getEmail()).getCategory();
					ArrayList <String> date = UsersMsgDB.getUsersMessage(u.getEmail()).getDate();
					
					for (int i=0;i<message.size();i++) {	
						
						UsersMessage newUser = new UsersMessage(u.getEmail(),experience.get(i),categoryAL.get(i),message.get(i),date.get(i));
						newUser.setSelectTV(new RadioButton());
						newUser.setCheck(new CheckBox());
						newUser.getCheck().setDisable(true);
						newUser.setNameTV(u.getUserName());
					
						
						newUser.setEmailTV(u.getEmail());
						
						
	
						
						userMessageAL.add(newUser);
						
						newUser.getSelectTV().setToggleGroup(group);
					}
					
			}
			
		}
		return userMessageAL;

	}
			/*
		if(UsersMsgDB.getUsersMessage(loggedInUser.getEmail())==null)
			UsersMsgDB.addUsersMessage(new UsersMessage(loggedInUser.getEmail(),new ArrayList <String>(),new ArrayList <String>(),new ArrayList <String>(),new ArrayList <String>()));
		
		
		UsersMessage UserMessage1 = UsersMsgDB.getUsersMessage(loggedInUser.getEmail());
		ArrayList <String> message = UserMessage1.getMessage();
		ArrayList <String> experience = UserMessage1.getExperience();
		ArrayList <String> categoryAL = UserMessage1.getCategory();
		ArrayList <String> date = UserMessage1.getDate();
		//ArrayList <RadioButton> select = UserMessage1.getSelect();
		//for actual implementation
		//for EACH user , you must run this code
		//then get the SAME user arraylist and store it in
		
		for (int i=0;i<message.size();i++) {	//,select.get(i)
			
			//sub "User1" with loggedInUser.getEmail()
			UsersMessage newUser = new UsersMessage(loggedInUser.getEmail(),experience.get(i),categoryAL.get(i),message.get(i),date.get(i));
			newUser.setSelectTV(new RadioButton());
			newUser.setCheck(new CheckBox());
			newUser.getCheck().setDisable(true);
			
			userMessageAL.add(newUser);
			
			newUser.getSelectTV().setToggleGroup(group);
		}
	
		*/
	//	return userMessageAL;

		//	}

	
	
	

	 @FXML
	    void viewMsg(ActionEvent event) throws IOException {
		 System.out.println("You clicked the view msg button");
		 
			for(int j=0;j<getUsersMessage().size();j++) {
			if(table.getItems().get(j).getSelectTV().isSelected()) {
				
					
					//FXMLLoader loader=new FXMLLoader();
					//loader.setLocation((getClass().getResource("/healthApp/view/UserProfile.fxml")));
					table.getItems().get(j).getCheck().setDisable(true);

					Stage primaryStage = new Stage();
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource("/healthApp/view/AdminMessageInboxDetails.fxml"));		
					Parent root=loader.load();
					
					Scene scene=new Scene(root);
					primaryStage.initModality(Modality.APPLICATION_MODAL);
					primaryStage.setScene(scene);
					primaryStage.show();
					System.out.println(getUsersMessage().get(j).getMessageTV());
					
					UsersMessage newUser = table.getItems().get(j);
					
					//get all the TV - treeviews element
					String newUserID = newUser.getUserID();
					String newUserExp = newUser.getExperienceTV();
					String newUserCategory = newUser.getCategoryTV();
					String newUserMsg = newUser.getMessageTV();
					String newUserDate = newUser.getDateTV();
					String newUserName = newUser.getNameTV();
					String newUserEmail = newUser.getEmailTV();
					
					AdminMessageInboxDetailsController aMIDC = loader.getController();
						aMIDC.setCategoryTextField(newUserCategory);
						aMIDC.setExperienceTextField(newUserExp);
						aMIDC.setFeedbackTextArea(newUserMsg);
						aMIDC.setDateLabel(newUserDate);
						aMIDC.setEmailTextField(newUserEmail);
						aMIDC.setNameTextField(newUserName);
						aMIDC.setImage(loadImage(newUserExp));
					
						
						//set the check in the next controller
						aMIDC.setCheckThing(table.getItems().get(j).getCheck());
						if(aMIDC.getCheckThing().isSelected()) {
							table.getItems().get(j).getCheck().setSelected(true);
						}
					
				}
			
	 		}
	
	
	 }
	 
	 
		
		public Image loadImage(String experience) {

				String fileName;
				switch (experience) {
				
				case "Very sad" :  fileName = "very_sad.png";
				break;
				
				case "Sad" : fileName = "sad.png";
				break;
				
				case "Neutral" : fileName = "confused.png";
				break;
				
				case "Happy" : fileName = "happy.png";
				break;
				
				case "Very happy" :fileName = "very_happy.png";
				break;
				
				default: fileName = "happy.png";
				break;


}


Path dPath = FileSystems.getDefault().getPath("feedback",fileName);
File expImg = new File(dPath.toUri());

Image image = new Image(expImg.toURI().toString());
return image;
}
		 
	 
	
	
}

//Null issue solved