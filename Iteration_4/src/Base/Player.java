
package Base;

import Room.*;
import Item.*;
import Door.Door;
import Command.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents players in the game. Each player has 
 * a current location.
 * 
 * @author Michael Kolling
 * @version 1.0 (December 2002)
 */
public class Player {

    private ArrayList<Command> moves;
    private Room currentRoom;
    private ItemList items;
    private Integer seed;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
		currentRoom = null;
		items = new ItemList();
        moves = new ArrayList<Command>();
        
        Random rand = new Random();
		seed = rand.nextInt(9999999);
    }

    /**
     * Return the current room for this player.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Set the current room for this player.
     */
    public void setCurrentRoom(Room room){

		Room toGoIn = room.goIn();

        currentRoom = toGoIn;
    }
    
    /**
     * Try to walk in a given direction. If there is a door
     * this will change the player's location.
     */
    public void walk(String direction)
    {
        // Try to leave current room.
        Door nextDoor = currentRoom.getDoor(direction);

        if (nextDoor == null)
            System.out.println("There is no door!");
        else {

			if(nextDoor.getLockValue() > 0){
				System.out.println("The door is locked.");
			}
			else{
				setCurrentRoom(nextDoor.getNext(currentRoom));
				System.out.println(currentRoom.getLongDescription());	
			}

        }
    }

    public boolean takeItem(int index){

		Item it = currentRoom.getItemAndRemove(index);
		
		if(it == null) return false;

		items.addItem(it);

		return true;

	}
	
	public boolean useItem(int index){

		Item temp = items.getItem(index);

		if(temp == null) return false;

		return temp.use(this);

    }
    
    public ArrayList<Command> getMoves(){

        return moves;
    }
    
    public void pushMove(Command command){

        moves.add(command);
    }

    public int getSeed(){

        return seed;
    }

}
