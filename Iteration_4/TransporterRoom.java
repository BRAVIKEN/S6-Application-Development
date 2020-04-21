
import java.util.ArrayList;

import java.util.Random;

public class TransporterRoom extends Room {

	private ArrayList<Room> allRooms;

	private Random rand; 


	TransporterRoom(String description, ArrayList<Room> allRooms_){
		super(description);

		allRooms = allRooms_;

		rand = new Random();

		rand.setSeed(567879);
	}


	public Room goIn(){

		int random = rand.nextInt(allRooms.size());

		return allRooms.get(random);

	}

}