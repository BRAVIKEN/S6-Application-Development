
package Item;


import java.util.HashMap;


public class ItemKey extends Item {

	private int ID;
	
	public ItemKey(int ID_){

		super();
		
		ID = ID_;
	}

	public int getID(){

        return this.ID;
    }

    public void setID(int ID_){

        ID = ID_;
	}
	
	public boolean use(Player p){

        HashMap<String, Door> doors = p.getCurrentRoom().getDoors().getAllDoors();

		boolean oneOpen = false;

		for (HashMap.Entry<String, Door> pair: doors.entrySet()) { 
			
			if(pair.getValue().getLockValue() == this.ID){
				//value = 0;
				pair.getValue().lock(0);
				oneOpen = true;
            }

		}

		return oneOpen;
	}


}
