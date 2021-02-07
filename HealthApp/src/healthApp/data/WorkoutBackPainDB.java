package healthApp.data;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.WorkoutBackPain;

public class WorkoutBackPainDB {
	private static final String BACK_DB="data/WorkoutBackPain.db";
	private static final String MAP_NAME="WorkoutBack";
	

	private DB db;
	private ConcurrentNavigableMap<String, WorkoutBackPain> map;
	
	public WorkoutBackPainDB() {
		db = DBMaker.newFileDB(new File(BACK_DB)).closeOnJvmShutdown().make();
		map = db.createTreeMap(MAP_NAME).makeOrGet();
	}
	
	public WorkoutBackPain getBackExercise(String key) {
		WorkoutBackPain b = map.get(key);
		return b;
	}
	
	public ArrayList<WorkoutBackPain> getAllBackExercises(){
		
		ArrayList<WorkoutBackPain> BackList = new ArrayList<>(map.values());
		
		return BackList;
	}

	
	public void addBackExerciseX(WorkoutBackPain wBP) {
		map.put(wBP.getID(),wBP);		
		db.commit();
		
}
}
