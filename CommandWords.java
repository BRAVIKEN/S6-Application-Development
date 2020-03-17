import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognise commands as they are typed in.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class CommandWords {
    // a constant array that holds all valid command words
    // private static final String[] validCommands = { "go", "items", "use", "take", "drop", "quit", "help", "ping", "back", "test" };

	private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {

		validCommands = new HashMap<String, CommandWord>();
		
		validCommands.put("go", CommandWord.GO);
		validCommands.put("objets", CommandWord.ITEMS);
		validCommands.put("utilise", CommandWord.USE);
		validCommands.put("prend", CommandWord.TAKE);
		validCommands.put("laisse", CommandWord.DROP);
		validCommands.put("regarde", CommandWord.LOOK);
		validCommands.put("quitter", CommandWord.QUIT);
		validCommands.put("aide", CommandWord.HELP);
		validCommands.put("ping", CommandWord.PING);
		validCommands.put("retour", CommandWord.BACK);
		validCommands.put("test", CommandWord.TEST);

    }

    /**
     * Check whether a given String is a valid command word.
     * 
     * @param aString string to check
     * @return true if a given string is a valid command, false if it isn't.
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
	}
	
	/**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
	public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
		}
		
    }

    /**
     * Print all valid commands to System.out.
     *
     * @return A string containing all the commands.
     */
    public String getCommandList() {

        StringBuilder toReturn = new StringBuilder();

        for (String command : validCommands.keySet()) {
            toReturn.append(command);
            toReturn.append(" ");
        }

        return toReturn.toString();

    }
}
