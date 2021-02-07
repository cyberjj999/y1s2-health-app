package healthApp.data;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.WorkoutAnklePain;

public class WorkoutAnklePainDB {
	private static final String ANKLE_DB="data/WorkoutAnkle.db";
	private static final String MAP_NAME="WorkoutAnkle";
	

	private DB db;
	private ConcurrentNavigableMap<String, WorkoutAnklePain> map;
	
	public WorkoutAnklePainDB() {
		db = DBMaker.newFileDB(new File(ANKLE_DB)).closeOnJvmShutdown().make();
		map = db.createTreeMap(MAP_NAME).makeOrGet();
	}

/*	
	public boolean addAnkleExercise(WorkoutAnklePain UniqueAnkle) {
		
		boolean status = false;
		if(UniqueAnkle != null) {
			map.put(UniqueAnkle.getUniqueAnkle(),UniqueAnkle);
			System.out.print(UniqueAnkle.getUniqueAnkle());
			status = true;
			db.commit();
		}
		else {
			System.out.println("No Exercise");
		}
		return status;
	}
	*/
	
	public WorkoutAnklePain getAnkleExercise(String key) {
		WorkoutAnklePain a = map.get(key);
		return a;
	}
	
	public ArrayList<WorkoutAnklePain> getAllAnkleExercises(){
		
		ArrayList<WorkoutAnklePain> AnkleList = new ArrayList<>(map.values());
		
		return AnkleList;
	}

	
	public void addAnkleExerciseX(WorkoutAnklePain wAP) {
		map.put(wAP.getID(),wAP);		
		db.commit();
		
}
}
