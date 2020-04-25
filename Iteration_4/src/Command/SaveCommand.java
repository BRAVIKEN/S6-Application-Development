package Command;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Base.Player;

public class SaveCommand extends Command {
    /**
     * Constructor for objects of class SaveCommand
     */
    public SaveCommand() {
    }

    /**
     * "Quit" was entered. Check the argument to see whether we really quit the
     * game. Return true, if we should quit, false otherwise.
     */
    public boolean execute(Player player) {

        if (hasSecondWord()) {

            String fileName = getSecondWord();
            this.save(fileName, player);
        } else {

            System.out.println("Which fileName ?");
        }
        return false;
    }

    public void save(String fileName, Player player) {

        FileWriter writer = null;

        try {
            writer = new FileWriter(fileName + ".save");
        } catch (IOException e) {

            e.printStackTrace();
        }


        //print seed
        try {
            writer.write(player.getSeed() + System.lineSeparator());
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        //print moves
        for(String str: player.getMoves()) {

            try {
                writer.write(str + System.lineSeparator());
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        }
        
        try {
            writer.close();
        } catch (IOException e) {
            
            e.printStackTrace();
        }

    }

}