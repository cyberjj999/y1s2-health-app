package healthApp.data;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.WorkoutNeckPain;

public class WorkoutNeckPainDB {
	
	private static final String NECK_DB="data/WorkoutNeck.db";
	private static final String MAP_NAME="WorkoutNeck";
	

	private DB db;
	private ConcurrentNavigableMap<String, WorkoutNeckPain> map;
	
	public WorkoutNeckPainDB() {
		db = DBMaker.newFileDB(new File(NECK_DB)).closeOnJvmShutdown().make();
		map = db.createTreeMap(MAP_NAME).makeOrGet();
	}
	
	public WorkoutNeckPain getNeckExercise(String key) {
		WorkoutNeckPain c = map.get(key);
		return c;
	}
	
	
	
	
	public ArrayList<WorkoutNeckPain> getAllNeckExercises(){
		
		ArrayList<WorkoutNeckPain> NeckList = new ArrayList<>(map.values());
		
		return NeckList;
	}

	
	public void addNeckExerciseX(WorkoutNeckPain wNP) {
		map.put(wNP.getID(),wNP);		
		db.commit();
		
}
}
