package healthApp.model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

public class UsersMessage implements Serializable{

	private static final long serialVersionUID = 4L;

	private ArrayList <String> message;
	private ArrayList <String> experience;
	private ArrayList <String> category;
	private ArrayList <String> date;
	private ArrayList <RadioButton> select;
	private ArrayList <String> name;
	private ArrayList <String> email;
	
	private String userID;
	
	private String messageTV;
	private String experienceTV;
	private String categoryTV;
	private String dateTV;
	private String nameTV;
	private String emailTV;
	
	private RadioButton selectTV;
	private CheckBox check;



	public ArrayList<String> getName() {
		return name;
	}

	public void setName(ArrayList<String> name) {
		this.name = name;
	}

	public ArrayList<String> getEmail() {
		return email;
	}

	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}

	public String getNameTV() {
		return nameTV;
	}

	public void setNameTV(String nameTV) {
		this.nameTV = nameTV;
	}

	public String getEmailTV() {
		return emailTV;
	}

	public void setEmailTV(String emailTV) {
		this.emailTV = emailTV;
	}

	public CheckBox getCheck() {
		return check;
	}

	public void setCheck(CheckBox check) {
		this.check = check;
	}

	public ArrayList<RadioButton> getSelect() {
		return select;
	}

	public void setSelect(ArrayList<RadioButton> select) {
		this.select = select;
	}

	public RadioButton getSelectTV() {
		return selectTV;
	}

	public void setSelectTV(RadioButton selectTV) {
		this.selectTV = selectTV;
	}

	public String getMessageTV() {
		return messageTV;
	}

	public void setMessageTV(String messageTV) {
		this.messageTV = messageTV;
	}

	public String getExperienceTV() {
		return experienceTV;
	}

	public void setExperienceTV(String experienceTV) {
		this.experienceTV = experienceTV;
	}

	public String getCategoryTV() {
		return categoryTV;
	}

	public void setCategoryTV(String categoryTV) {
		this.categoryTV = categoryTV;
	}
	
	public String getDateTV() {
		return dateTV;
	}

	public void setDateTV(String dateTV) {
		this.dateTV = dateTV;
	}

	public UsersMessage(){
		
	}
	//,ArrayList <RadioButton> select
	public UsersMessage(String userID,ArrayList<String> message,ArrayList <String> experinece, ArrayList <String> category,ArrayList <String> date){
		this.userID = userID;
		this.message = new <String> ArrayList();
		this.experience = new <String> ArrayList();
		this.category = new <String> ArrayList();
		this.date = new <String> ArrayList();
		//this.select = new <RadioButton> ArrayList();
		
	}
	
	//for treeview ONLY 		//,RadioButton selectTV
	public UsersMessage(String UserID,String experienceTV,String categoryTV,String messageTV,String dateTV) {
		this.userID = UserID;
		this.messageTV = messageTV;
		this.experienceTV = experienceTV;
		this.categoryTV = categoryTV;
		this.dateTV = dateTV;
		//this.selectTV = selectTV;
		
	}
	
	public UsersMessage(String UserID) {
		this.userID = UserID;
	}
	

	public ArrayList<String> getMessage() {
		return message;
	}

	public void setMessage(ArrayList<String> message) {
		this.message = message;
	}

	public ArrayList<String> getExperience() {
		return experience;
	}

	public void setExperience(ArrayList<String> experience) {
		this.experience = experience;
	}

	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	

	public ArrayList<String> getDate() {
		return date;
	}

	public void setDate(ArrayList<String> date) {
		this.date = date;
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userId) {
		this.userID = userId;
	}

	
	
	
}
