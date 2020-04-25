
package Command;

import Base.Player;

public class UseCommand extends Command {

    public UseCommand() {}

    public boolean execute(Player player){

		if(getSecondWord() == null) {
			System.out.println("Use which ?");
            return false;
        }

		int index = Integer.parseInt(getSecondWord());

		if(!player.useItem(index)){
			System.out.println("Can't use it right now.");
		}

		return false;

    }

}
