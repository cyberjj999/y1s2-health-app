package healthApp.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.controller.Login;
import healthApp.model.Users;
import javafx.scene.control.TextField;


public class UsersMapDB {
	private static final String CUSTOMER_DB ="data/Users.db";
	private static final String USER_DB ="users";
	private DB db2;
	private ConcurrentNavigableMap<String, Users> avg;
	
	private static String loggedInUser;
	
	
	public static String getLoggedInUser() {
		return loggedInUser;
	}

	public static void setLoggedInUser(String loggedInUserX) {
		loggedInUser = loggedInUserX;
	}

	
	public UsersMapDB() {
		//db = DBMaker.newFileDB(new File(USER_DB)).closeOnJvmShutdown().make();
		//map = db.createTreeMap(USER_DB).makeOrGet();
		
		db2 = DBMaker.newFileDB(new File(CUSTOMER_DB)).closeOnJvmShutdown().make();
		avg = db2.createTreeMap(USER_DB).makeOrGet();
	}

	public boolean addUsers(Users u) {
		avg.put(u.getEmail(), u);
		db2.commit();
			return true;
	}
	
	public Users getUsers(String key) {
		//Users u = map.get(key);	
	 Users u = avg.get(key);
		return u;
    }
	
	
	public Users checkLoggedIn(String mail) {
	
		
		UsersMapDB userDB = new UsersMapDB();
		//System.out.println(userDB.getMap().values());
		Login loginClass = new Login();
		Users loggedInUser = null;
		ArrayList <Users> allUsers = new ArrayList<Users>(userDB.getMap().values());
		
		System.out.print(allUsers);
		for(Users user : allUsers) {
			if(mail.equals(user.getEmail()))
			
			{
				System.out.println("Yes it is logged in");
				loggedInUser = user;
			}
			
		}
		
		return loggedInUser;
		
	}
	

	
	
		
		
	public ConcurrentNavigableMap<String, Users> getMap(){
		return avg;
		}
	
	

}
