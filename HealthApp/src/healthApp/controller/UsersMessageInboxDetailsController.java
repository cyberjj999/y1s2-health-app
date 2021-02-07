package healthApp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

public class UsersMessageInboxDetailsController implements Initializable {
	
	private static String loggedInUser;
	
	public String getLoggedInUser() {
		return loggedInUser;
	}



	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	@FXML
	private TextField experienceTextField;
	@FXML
	private TextField categoryTextField;
	@FXML
	private TextArea feedbackTextArea;
	@FXML
	private Label userDateLabel;
	@FXML
	private TextArea adminResponseTextArea;
	@FXML
	private Label adminDateLabel;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public TextField getExperienceTextField() {
		return experienceTextField;
	}
	public void setExperienceTextField(String experienceTextField) {
		this.experienceTextField.setText(experienceTextField);
	}
	public TextField getCategoryTextField() {
		return categoryTextField;
	}
	public void setCategoryTextField(String categoryTextField) {
		this.categoryTextField.setText(categoryTextField);
	}
	public TextArea getFeedbackTextArea() {
		return feedbackTextArea;
	}
	public void setFeedbackTextArea(String feedbackTextArea) {
		this.feedbackTextArea.setText(feedbackTextArea);
	}
	public Label getUserDateLabel() {
		return userDateLabel;
	}
	public void setUserDateLabel(String userDateLabel) {
		this.userDateLabel.setText(userDateLabel);
	}
	public TextArea getAdminResponseTextArea() {
		return adminResponseTextArea;
	}
	public void setAdminResponseTextArea(String adminResponseTextArea) {
		this.adminResponseTextArea.setText(adminResponseTextArea);
	}
	public Label getAdminDateLabel() {
		return adminDateLabel;
	}
	public void setAdminDateLabel(String adminDateLabel) {
		this.adminDateLabel.setText(adminDateLabel);
	}
	
	
	

}
