
public class Door {

	private int lockValue;

	private Room from;
	private Room to;


	Door(Room f, Room t){

		from = f;
		to = t;

		lockValue = 0;

	}

	void lock(int value){
		lockValue = value;
	}

	int getLockValue(){
		return lockValue;
	}

	Room getNext(Room r){

		if(r == from){
			return to;
		}
		else if(r == to){
			return from;
		}

		return null;

	}

}