package healthApp.data;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.WorkoutHistory;

public class WorkoutHistoryDB {
	private static final String HISTORY_DB="data/History.db";
	private static final String MAP_NAME="History";
	

	private DB db;
	private ConcurrentNavigableMap<String, WorkoutHistory> map;
	
	public WorkoutHistoryDB() {
		db = DBMaker.newFileDB(new File(HISTORY_DB)).closeOnJvmShutdown().make();
		map = db.createTreeMap(MAP_NAME).makeOrGet();
	}
	
	
	
	public WorkoutHistory getWorkoutHistory(String key) {
		WorkoutHistory d = map.get(key);
		return d;
	}

	
	public void addHistory(WorkoutHistory WH) {
		map.put(WH.getID(),WH);		
		db.commit();
		
}
}
