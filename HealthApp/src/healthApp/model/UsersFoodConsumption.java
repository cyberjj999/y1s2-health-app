package healthApp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UsersFoodConsumption implements Serializable {
	
	private static final long serialVersionUID = 3L;

	
	ArrayList <Food> FoodArrayList = new <Food> ArrayList();
	ArrayList <Integer> PortionArrayList = new <Integer> ArrayList ();
	ArrayList <String> DateArrayList = new <String> ArrayList();
	
	public ArrayList<String> getDateArrayList() {
		return DateArrayList;
	}

	public void setDateArrayList(ArrayList<String> dateArrayList) {
		DateArrayList = dateArrayList;
	}

	public ArrayList<Integer> getPortionArrayList() {
		return PortionArrayList;
	}

	public void setPortionArrayList(ArrayList<Integer> PortionArrayList) {
		this.PortionArrayList = PortionArrayList;
	}
	private String UserID;
	
	public UsersFoodConsumption(String UserID){
		this.UserID = UserID;
		
	}
	
	public UsersFoodConsumption(String UserID,ArrayList<Food>FoodArrayList,ArrayList<Integer>PortionArrayList,ArrayList<String> DateArrayList){
		
		this.UserID = UserID;
		this.FoodArrayList = FoodArrayList;
		this.PortionArrayList = PortionArrayList;
		this.DateArrayList = DateArrayList;
		
	
	}
	
	public ArrayList<Food> getFoodArrayList() {
		return FoodArrayList;
	}
	public void setFoodArrayList(ArrayList<Food> foodArrayList) {
		FoodArrayList = foodArrayList;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	
	
	
	
	
	
}
