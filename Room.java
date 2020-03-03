
import java.util.HashMap;
import java.util.ArrayList;


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
public class Room {
    public String description;
    // 7.8
    private HashMap<String, Room> exits;
    private String imageName;
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     * 
     * @param description The room's description.
     * @param image The path to the image.
     */
    public Room(String description, String image) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.imageName = image;

        items = new ArrayList<Item>();
    }   


    /**
     * Return an exit in function of the string name argument
     * 
     * @param direction desired direction
     * @return exit room
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Set a room at a specific direction.
     * 
     * @param direction The direction were we want to set the room.
     * @param neighbor  The room we want to set at the direction.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return a description of the room's exits, for example, "Exits: north west".
     * 
     * @return A description of the available exits.
     */
    // 7.7
    public String getExitString() {
        StringBuilder toReturn = new StringBuilder("Exits: ");
        for (HashMap.Entry<String, Room> entry : exits.entrySet()) {
            if (entry.getValue() != null) {
                toReturn.append(entry.getKey() + " ");
            }
        }
        return toReturn.toString();
    }

    /**
    *  Add an item in the array of items
    *
    *  @param itemToAdd The item you want to add.
    */
    public void addItem(Item itemToAdd){
        items.add(itemToAdd);
    }

    /**
    *   Return a description of items in the room.
    *
    *   @return A description of the items in the room.
    */
    public String getItemDescription(){

        StringBuilder toReturn = new StringBuilder("");

        items.forEach((item) -> {
            toReturn.append("There is an " + item.getDescription() + " on the ground.\n");
        });
        
        return toReturn.toString();

    }

    /**
     * Return a long description of this room, of the form: You are in the kitchen.
     * Exits: north west
     * 
     * @return A description of the room, including exits.
     */
    public String getLongDescription() {
        StringBuilder toReturn = new StringBuilder("You are " + description + ".\n");

        if(items != null){
            toReturn.append(getItemDescription() + "\n");
        }

        toReturn.append(getExitString());

        return toReturn.toString();
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @return the items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @param index the index of the item to return. 
     *
     * @return return one item
     */
    public Item getItem(int index) {
        
        if(index < 0 || index >= items.size()) return null;

        return items.get(index);
    }
}
