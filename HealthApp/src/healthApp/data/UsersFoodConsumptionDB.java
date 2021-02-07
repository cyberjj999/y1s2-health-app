package healthApp.data;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentNavigableMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.Food;

import healthApp.model.UsersFoodConsumption;
import javafx.scene.control.TextField;

public class UsersFoodConsumptionDB {
	private static final String USER_FOOD_CONSUMPTION_DB ="data/UsersFoodConsumption.db";
	private static final String MAP_NAME ="UsersFoodMap";
	private DB db;
	private ConcurrentNavigableMap<String, UsersFoodConsumption> map;
	
	public  UsersFoodConsumptionDB() {
		db = DBMaker.newFileDB(new File(USER_FOOD_CONSUMPTION_DB)).closeOnJvmShutdown().make();
		map = db.createTreeMap(MAP_NAME).makeOrGet();
	}

	public void addUsersFood(UsersFoodConsumption uFC) {
			map.put(uFC.getUserID() ,uFC);
			db.commit();
			
	}
	
	public UsersFoodConsumption getUsers(String key) {
		UsersFoodConsumption uFC = map.get(key);	
	 
		return uFC;
    }
	
	
	
	public ConcurrentNavigableMap<String, UsersFoodConsumption> getMap(){
		
		return map;
		}
	
	
	public boolean updateArrayList(UsersFoodConsumption uFC) {
		   boolean status = false;
		   if(uFC != null) {
			   map.replace(uFC.getUserID(), uFC);
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
