
package Door;


import java.util.HashMap;


public class DoorList {


	private HashMap<String, Door> doors;
	

	public DoorList(){
		
		doors = new HashMap<String, Door>();

	}


	public void addDoor(String path, Door d){

		doors.put(path, d);

	}

	public Door getDoor(String path){
		return doors.get(path);
	}

	public HashMap<String, Door> getAllDoors(){
		return doors;
	}
	

    
}