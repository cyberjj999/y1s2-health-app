package healthApp.model;


import java.io.Serializable;
import java.util.ArrayList;

public class Food implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private double calories;
	private double protein;
	private double carbs;
	private double fats;
	private double sugar;
	private double sodium;
	private double cholesterol;
	private double servingSize;
	private String servingSizeUnit;
	private String description;
	
	
	private String category;
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	/*
	private ArrayList <String> category;
	
	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	 */
	
	public Food() {
		//category = new ArrayList<String>();
	}

	public Food(String name,double calories) {
		this.name = name;
		this.calories = calories;
		//category = new ArrayList<String>();
	}
	
	public Food(String name, double calories, double protein, double carbs, double fats, double sugar, double sodium,
			double cholesterol,double servingSize,String servingSizeUnit) {
		super();
		this.name = name;
		this.calories = calories;
		this.protein = protein;
		this.carbs = carbs;
		this.fats = fats;
		this.sugar = sugar;
		this.sodium = sodium;
		this.cholesterol = cholesterol;
		this.servingSize = servingSize;
		this.servingSizeUnit = servingSizeUnit;
		//category = new ArrayList<String>();
	}
	
	public String getServingSizeUnit() {
		return servingSizeUnit;
	}

	public void setServingSizeUnit(String servingSizeUnit) {
		this.servingSizeUnit = servingSizeUnit;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getCarbs() {
		return carbs;
	}
	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}
	public double getFats() {
		return fats;
	}
	public void setFats(double fats) {
		this.fats = fats;
	}
	public double getSugar() {
		return sugar;
	}
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}
	public double getSodium() {
		return sodium;
	}
	public void setSodium(double sodium) {
		this.sodium = sodium;
	}
	public double getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}
	public double getServingSize() {
		return servingSize;
	}

	public void setServingSize(double servingSize) {
		this.servingSize = servingSize;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
