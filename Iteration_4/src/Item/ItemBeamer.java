
package Item;

import Base.Player;
import Room.Room;

public class ItemBeamer extends Item {

	private Room saved;
	

	public ItemBeamer(){

		super();
		
		saved = null;

	}

	public boolean use(Player p){

		if(saved == null){
			
			System.out.println("Salle sauvegardé dans le téléporteur.");

			saved = p.getCurrentRoom();

		} else if(saved == p.getCurrentRoom()){
			
			System.out.println("Cette salle est déjà sauvegardé.");

			return false;

		} else {
			
			System.out.println("Téléportation effectué.");
			
			p.setCurrentRoom(saved);
			
			System.out.println(p.getCurrentRoom().getLongDescription());

			saved = null;

		}

		return true;
	}

}