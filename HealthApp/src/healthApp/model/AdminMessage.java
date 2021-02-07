package healthApp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AdminMessage implements Serializable {

	private static final long serialVersionUID = 5L;

	private String UserID;
	private String message;
	private String date;
	private ArrayList <String> dateAL;
	private ArrayList <String> messageAL;
	
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public ArrayList<String> getDateAL() {
		return dateAL;
	}
	public void setDateAL(ArrayList<String> dateAL) {
		this.dateAL = dateAL;
	}
	public ArrayList<String> getMessageAL() {
		return messageAL;
	}
	public void setMessageAL(ArrayList<String> messageAL) {
		this.messageAL = messageAL;
	}
	public AdminMessage(){
		
	}
	

	
	/*
	 * 	public UsersMessage(String userID,ArrayList<String> message,ArrayList <String> experinece, ArrayList <String> category,ArrayList <String> date){
		this.userID = userID;
		this.message = new <String> ArrayList();
		this.experience = new <String> ArrayList();
		this.category = new <String> ArrayList();
		this.date = new <String> ArrayList();
		//this.select = new <RadioButton> ArrayList();
		
	}
	 */
	
	public AdminMessage(String UserID, String message, String date) {
	
		this.UserID = UserID;
		this.message = message;
		this.date = date;
		System.out.println("You created a new AdminMSG objects");
		
	}
	
	public AdminMessage(String userID,ArrayList<String> messageAL,ArrayList <String> dateAL) {
		
		this.UserID = userID;
		this.messageAL = new <String> ArrayList();
		this.dateAL = new <String> ArrayList();
		System.out.println("You created a new AdminMSG objects");

	}
	
	
	
}
