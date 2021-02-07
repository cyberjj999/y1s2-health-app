package healthApp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutNeckPain implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String HandClasped,NeckRetracted,SideNeck;
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

	/**
	 * @return the necklist
	 */
	public ArrayList<String> getNecklist() {
		return Necklist;
	}

	/**
	 * @param necklist the necklist to set
	 */
	public void setNecklist(ArrayList<String> necklist) {
		Necklist = necklist;
	}

	public ArrayList<String> Necklist=new ArrayList<String>();
	
	public String getHandClasped() {
		return HandClasped;
	}

	public void setHandClasped(String handClasped) {
		HandClasped = handClasped;
	}

	public String getNeckRetracted() {
		return NeckRetracted;
	}

	public void setNeckRetracted(String neckRetracted) {
		NeckRetracted = neckRetracted;
	}

	public String getSideNeck() {
		return SideNeck;
	}

	public void setSideNeck(String sideNeck) {
		SideNeck = sideNeck;
	}

	public WorkoutNeckPain(String handClasped, String neckRetracted, String sideNeck) {
		super();
		HandClasped = handClasped;
		NeckRetracted = neckRetracted;
		SideNeck = sideNeck;
	}
	
	public WorkoutNeckPain() {
		
	}
}
