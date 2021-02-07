package healthApp.data;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentNavigableMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.Food;

import healthApp.model.UsersMessage;
import javafx.scene.control.TextField;

public class UsersMessageDB {
	private static final String USER_MESSAGE_DB ="data/UsersMessage.db";
	private static final String MAP_NAME ="UsersMessageMap";
	private DB db;
	private ConcurrentNavigableMap<String, UsersMessage> map;
	
	public  UsersMessageDB() {
		db = DBMaker.newFileDB(new File(USER_MESSAGE_DB)).closeOnJvmShutdown().make();
		map = db.createTreeMap(MAP_NAME).makeOrGet();
	}

	public void addUsersMessage(UsersMessage uM) {
			map.put(uM.getUserID() ,uM);
			db.commit();
			
	}
	
	public UsersMessage getUsersMessage(String key) {
		UsersMessage uM = map.get(key);	
	 
		return uM;
    }
	
	
	
	public ConcurrentNavigableMap<String, UsersMessage> getMap(){
		
		return map;
		}
	
	
	public boolean updateArrayList(UsersMessage uM) {
		   boolean status = false;
		   if(uM != null) {
			   map.replace(uM.getUserID(), uM);
			   System.out.println("updated");
			   db.commit();
			   status = true;
		   }
		   else {
			   System.out.println("the user u parse in is null");
		   }
		   return status;
	   }
	
	

}
