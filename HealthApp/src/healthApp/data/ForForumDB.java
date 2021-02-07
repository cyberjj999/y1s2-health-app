package healthApp.data;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.ForForums;
import javafx.scene.control.TextField;

public class ForForumDB {
	private final String FORUM__DB = "forum_info/forum.db";
	private static final String FORUM_INFORMATION = "forum";
	private static DB db;
	private static ConcurrentNavigableMap<String, ForForums> map;
	
	public ForForumDB() {
		db = DBMaker.newFileDB(new File(FORUM__DB)).closeOnJvmShutdown().make();
		map = db.createTreeMap(FORUM_INFORMATION).makeOrGet();
	}

	public boolean addForForums(ForForums f) {
			map.put(f.getTitleOfTopic(), f);		
			db.commit();
			return true;
	}
	
	public ForForums getForForums(String key) {
		ForForums f = map.get(key);	
	 
		return f;
    }

}
