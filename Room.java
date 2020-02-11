/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

import java.util.HashMap;

public class Room 
{
    public String description;
    //7.8
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Return an exit in function of the string name argument
     */
    public Room getExit(String direction){
		return exits.get(direction);
	}

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExit(String direction, Room neighbor){
		exits.put(direction, neighbor);
	}

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
	* Return a description of the room's exits,
	* for example, "Exits: north west".
	* @return A description of the available exits.
	*/
	//7.7
	public String getExitString(){
		StringBuilder toReturn = new StringBuilder();
		toReturn.append("Exits: ");
		for(HashMap.Entry<String, Room> entry : exits.entrySet()) {
			if(entry.getValue() != null) {
				toReturn.append(entry.getKey() + " ");
			}
		}
		return toReturn.toString();
	}

	/**
	* Return a long description of this room, of the form:
	* You are in the kitchen.
	* Exits: north west
	* @return A description of the room, including exits.
	*/
	public String getLongDescription(){
		return "You are " + description + ".\n" + getExitString();
	}
}
