
package Item;

import Base.Player;

public abstract class Item {

    public Item(){}

    /*true if the object is correctly used else false*/
    public abstract boolean use(Player p);

}