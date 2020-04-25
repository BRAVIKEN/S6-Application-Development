

package Room;


import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;


import Door.*;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

import Item.ItemList;
import Item.Item;

import Door.DoorList;


public class Room {

    private String description;
	private DoorList exits;        // stores exits of this room.
    private ItemList items;
    

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court 
     * yard".
     */
    public Room(String description) 
    {
        this.description = description;
		exits = new DoorList();
		items = new ItemList();
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Door theDoor) {
		exits.addDoor(direction, theDoor);
	}

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getItemString() + "\n" + getExitString();
    }

	private String getItemString(){
		String returnString = "Items:";
		ArrayList<Item> allItems = items.getItems();
		
		returnString += Integer.toString(allItems.size());

        // for(Iterator iter = keys.iterator(); iter.hasNext(); )
        //     returnString += " " + iter.next();
        return returnString;

	}

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.getAllDoors().keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
	}
	
	public Room goIn(){
		return this;
	}

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Door getDoor(String direction) {
        return exits.getDoor(direction);
	}
	
	public void addItem(Item item){
		items.addItem(item);
	}

	public Item getItemAndRemove(int index) {

		return items.takeItem(index);

	}

    public DoorList getDoors(){

        return exits;
	}

}

