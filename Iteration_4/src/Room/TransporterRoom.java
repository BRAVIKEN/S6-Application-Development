

package Room;


import java.util.ArrayList;

import java.util.Random;

public class TransporterRoom extends Room {

	private ArrayList<Room> allRooms;

	private Random rand; 


	public TransporterRoom(String description, ArrayList<Room> allRooms_, int seed){
		super(description);

		allRooms = allRooms_;

		rand = new Random();

		rand.setSeed(seed);
	}


	public Room goIn(){

		int random = rand.nextInt(allRooms.size());

		return allRooms.get(random);

	}

}