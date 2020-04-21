
import java.util.HashMap;


class DoorList {


	private HashMap<String, Door> doors;
	

	DoorList(){
		
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