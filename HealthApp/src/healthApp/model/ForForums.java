package healthApp.model;

import java.io.Serializable;

public class ForForums implements Serializable {
	private static final long serialVersionUID = 2L;
	private String titleOfTopic;
	private String comboItems;
	private String forum_content;
	private String forum_createdDateTime;
	private String forum_creator;
	
	public ForForums(String titleOfTopic, String comboItems, String forum_content, String forum_createdDateTime) {
		this.titleOfTopic = titleOfTopic;
		this.comboItems = comboItems;
		this.forum_content = forum_content;
		this.forum_createdDateTime = forum_createdDateTime;
	}
	
	public String getTitleOfTopic() {
		return titleOfTopic;
	}

	public void setTitleOfTopic(String titleOfTopic) {
		this.titleOfTopic = titleOfTopic;
	}

	public String getComboItems() {
		return comboItems;
	}

	public void setComboItems(String comboItems) {
		this.comboItems = comboItems;
	}
	
	public String getForum_content() {
		return forum_content;
	}

	public void setForum_content(String forum_content) {
		this.forum_content = forum_content;
	}

	public String getForum_createdDateTime() {
		return forum_createdDateTime;
	}

	public void setForum_createdDateTime(String forum_createdDateTime) {
		this.forum_createdDateTime = forum_createdDateTime;
	}
}
