package healthApp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutAnklePain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public ArrayList<String> Anklelist=new ArrayList<String>();
	
	private String BallRoll,CalfStretch,HeelSitBack,UniqueAnkle,ID;
	
	
	/**
	 * @return the uniqueAnkle
	 */
	
	
	public WorkoutAnklePain() {
		
	}
	
	/**
	 * @return the counter
	 */


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
	 * @param counter the counter to set
	 */


	public String getUniqueAnkle() {
		return UniqueAnkle;
	}

	/**
	 * @param uniqueAnkle the uniqueAnkle to set
	 */
	public void setUniqueAnkle(String uniqueAnkle) {
		this.UniqueAnkle = uniqueAnkle;
	}

	public String getBallRoll() {
		return BallRoll;
	}

	public void setBallRoll(String ballRoll) {
		BallRoll = ballRoll;
	}

	public String getCalfStretch() {
		return CalfStretch;
	}

	public void setCalfStretch(String calfStretch) {
		CalfStretch = calfStretch;
	}

	public String getHeelSitBack() {
		return HeelSitBack;
	}

	public void setHeelSitBack(String heelSitBack) {
		HeelSitBack = heelSitBack;
	}

	public WorkoutAnklePain(ArrayList<String> Anklelist) {
		this.Anklelist = Anklelist;
	}

	/**
	 * @return the anklelist
	 */
	public ArrayList<String> getAnklelist() {
		return Anklelist;
	}

	/**
	 * @param anklelist the anklelist to set
	 */
	public void setAnklelist(ArrayList<String> anklelist) {
		Anklelist = anklelist;
	}
	
}
