package healthApp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutBackPain implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String BirdDog,Bridging,LowerBackStretch;
	public String ID;
	
	
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<String> BackPainList=new ArrayList<String>();
	
	public ArrayList<String> getBacklist() {
		return BackPainList;
	}
	/**
	 * @return the backPainList
	 */
	public ArrayList<String> getBackPainList() {
		return BackPainList;
	}

	/**
	 * @param backPainList the backPainList to set
	 */
	public void setBackPainList(ArrayList<String> backPainList) {
		BackPainList = backPainList;
	}

	
	
	
	
	
	private String getBirdDog() {
		return BirdDog;
	}

	public void setBirdDog(String birdDog) {
		BirdDog = birdDog;
	}

	private String getBridging() {
		return Bridging;
	}

	public void setBridging(String bridging) {
		Bridging = bridging;
	}

	private String getLowerBackStretch() {
		return LowerBackStretch;
	}

	public void setLowerBackStretch(String lowerBackStretch) {
		LowerBackStretch = lowerBackStretch;
	}
	
	public WorkoutBackPain (String BirdDog,String Bridging, String LowerBackStretch) {
		
		this.BirdDog = BirdDog;
		this.Bridging = Bridging;
		this.LowerBackStretch = LowerBackStretch;
		
	}

	public WorkoutBackPain() {
		// TODO Auto-generated constructor stub
	}
}
