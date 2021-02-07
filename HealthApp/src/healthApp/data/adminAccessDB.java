package healthApp.data;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.adminAccess;


public class adminAccessDB {
	
	private final String ADMIN_DB = "data/adminUsers.db";
	private static final String ADMIN_STORE = "admin";
	private static DB db3;
	private static ConcurrentNavigableMap<String, adminAccess> aas;
	
	public adminAccessDB() {
		db3 = DBMaker.newFileDB(new File(ADMIN_DB)).closeOnJvmShutdown().make();
		aas = db3.createTreeMap(ADMIN_STORE).makeOrGet();
	}

	public boolean addAdminAccess(adminAccess a) {
			aas.put(a.getAdminID(), a);		
			db3.commit();
			return true;
	}
	
	public adminAccess getAdminAccess(String key) {
		adminAccess a = aas.get(key);	
	 
		return a;
    }

}

