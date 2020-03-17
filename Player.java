
public class Player {

	private Room currentRoom;
	private ItemList items;
	private Double maxWeight;

	public Player(Double maxW) {

		items = new ItemList();

		currentRoom = null;

		maxWeight = maxW;

	}

	/**
     * @return return the current room.
     */
	public Room getRoom(){
		return currentRoom;
	}

	/**
     * @param newRoom set the current room.
     */
	public void setRoom(Room newRoom){
		currentRoom = newRoom;
	}
	
	/**
     * @param index Index of the item to take in the room.
	 *
	 * @return true if succes, else if not.
     */
	public Boolean take(int index){

		Item addItem;

		if(items.getAllWeight() + currentRoom.getItemWeight(index) > maxWeight){
			return false;
		}

		addItem = currentRoom.getItemAndRemove(index);

		if(addItem == null) return false;

		items.addItem(addItem);
		
		return true;
	}


	/**
	 * Drop the item at the index 'index' of the items list in the current room.
	 * 
     * @param index the index of the item to return. 
	 * 
	 * @return true if succes, else if not.
     */
	public Boolean drop(int index){

		Item ret;

		ret = items.takeItem(index);

		if(ret == null) return false;

		currentRoom.addItem(ret);

		return true;

	}

	public Boolean useItem(int index){

		Item tempo;

		tempo = items.takeItem(index);

		if(tempo == null) return false;

		maxWeight += tempo.bonusWeight;

		return true;

	}


	    /**
    *   Return a description of items of the Player.
    *
    *   @return A description of the items of the Player.
    */
    public String getItemDescription(){

        StringBuilder toReturn = new StringBuilder("");

		for (int i = 0; i < items.getItems().size(); ++i) {
            toReturn.append(Integer.toString(i) + " : " + items.getItems().get(i).getDescription() + ".\n");
		}
		
		toReturn.append("Total weight : " + Double.toString(items.getAllWeight()) + ".\n");
        
        return toReturn.toString();

    }

}