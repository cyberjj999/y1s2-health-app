package healthApp.data;


import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import healthApp.model.Food;

public class FoodDB {

	
	private static final String FOOD_DB="data/FoodData.db";
	private static final String MAP_NAME="FoodMap";
	

	private DB db;
	private ConcurrentNavigableMap<String, Food> map;
	
	public FoodDB() {
		db = DBMaker.newFileDB(new File(FOOD_DB)).closeOnJvmShutdown().make();
		map = db.createTreeMap(MAP_NAME).makeOrGet();
	}
	
	public void addFood(Food f) {
		map.put(f.getName(),f);		
		db.commit();
		
}
	
	
	public Food getFood(String key) {
		Food f= map.get(key);	
	 
		return f;
    }
	
	
	public ConcurrentNavigableMap<String, Food> getMap(){
		return map;
		}
	

}
