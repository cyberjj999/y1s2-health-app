package healthApp.data;


import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.AdminMessage;
import healthApp.model.UsersFoodConsumption;


public class AdminMessageDB {

	
	private static final String ADMIN_MESSAGE_DB="data/AdminMessage.db";
	private static final String MAP_NAME="AdminMap";
	

	private DB db;
	private ConcurrentNavigableMap<String, AdminMessage> map;
	
	public AdminMessageDB() {
		db = DBMaker.newFileDB(new File(ADMIN_MESSAGE_DB)).closeOnJvmShutdown().make();
		map = db.createTreeMap(MAP_NAME).makeOrGet();
	}
	
	public void addAdminMessage(AdminMessage aM) {
		map.put(aM.getUserID(),aM);		
		db.commit();
		
}
	
	
	public AdminMessage getAdminMessage(String key) {
		AdminMessage aM= map.get(key);	
	 
		return aM;
    }
	
	
	public ConcurrentNavigableMap<String, AdminMessage> getMap(){
		return map;
		}
	
	public boolean updateMap(AdminMessage aM) {
		   boolean status = false;
		   if(aM != null) {
			   map.replace(aM.getUserID(), aM);
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
