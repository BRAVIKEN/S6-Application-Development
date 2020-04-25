
package Door;


import Room.Room;

public class Door {

	private int lockValue;

	private Room from;
	private Room to;


	public Door(Room f, Room t){

		from = f;
		to = t;

		lockValue = 0;

	}

	public void lock(int value){
		lockValue = value;
	}

	public int getLockValue(){
		return lockValue;
	}

	public Room getNext(Room r){

		if(r == from){
			return to;
		}
		else if(r == to){
			return from;
		}

		return null;

	}

}