package healthApp.model;

import java.io.Serializable;

public class adminAccess implements Serializable {
	private static final long serialVersionUID = 3L;
	private String adminID;
	private String adminPassword;
	
	public adminAccess(String adminID, String adminPassword) {
		this.adminID = adminID;
		this.adminPassword = adminPassword;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
}
