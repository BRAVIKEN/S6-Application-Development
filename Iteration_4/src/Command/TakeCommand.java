
package Command;

import Base.Player;

public class TakeCommand extends Command {

    public TakeCommand(){}

    public boolean execute(Player player){

		if(getSecondWord() == null) {
			System.out.println("Take which ?");
            return false;
        }

		int index = Integer.parseInt(getSecondWord());

		if(!player.takeItem(index)){
			System.out.println("There is no item " + getSecondWord() + ".");
		}

		return false;

    }

}
