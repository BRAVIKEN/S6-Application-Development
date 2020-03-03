import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * This class creates all rooms, creates the parser and starts the game. It also
 * evaluates and executes the commands that the parser returns.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */
public class GameEngine {
    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine() {
        parser = new Parser();
        createRooms();
    }

    public void setGUI(UserInterface userInterface) {
        gui = userInterface;
        printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        gui.println("\n");
        gui.println("Welcome to Braviken Dungeon.");
        gui.println("You're lost here, try to advance as much as you can.");
        gui.println("Type 'help' if you need help.");
        gui.println("Also, try not to die.");
        gui.println("\n");
        gui.println(currentRoom.getLongDescription());
        gui.showImage(currentRoom.getImageName());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        // Room outside, theatre, pub, lab, office;

        ArrayList<Room> allRoom = new ArrayList<Room>();

        for (int i = 0; i < 15; i++) {
            allRoom.add(new Room(Integer.toString(i), "images/salle_vide.jpg"));
        }

        // HAUT DROITE BAS GAUCHE
        // public void setExit(String direction, Room neighbor) (Room north, Room east,
        // Room south, Room west

        // allRoom.get(0).setExit(allRoom.get(1), allRoom.get(2), allRoom.get(4), null);
        allRoom.get(0).setExit("north", allRoom.get(1));
        allRoom.get(0).setExit("east", allRoom.get(2));
        allRoom.get(0).setExit("south", allRoom.get(4));
        allRoom.get(0).setExit("west", null);
        allRoom.get(0).setExit("up", allRoom.get(14));

        // allRoom.get(1).setExit(null, null, allRoom.get(0), null);
        allRoom.get(1).setExit("north", null);
        allRoom.get(1).setExit("east", null);
        allRoom.get(1).setExit("south", allRoom.get(0));
        allRoom.get(1).setExit("west", null);

        // allRoom.get(2).setExit(allRoom.get(3), null, null, allRoom.get(0));
        allRoom.get(2).setExit("north", allRoom.get(3));
        allRoom.get(2).setExit("east", null);
        allRoom.get(2).setExit("south", null);
        allRoom.get(2).setExit("west", allRoom.get(0));

        // allRoom.get(3).setExit(null, null, allRoom.get(2), null);
        allRoom.get(3).setExit("north", null);
        allRoom.get(3).setExit("east", null);
        allRoom.get(3).setExit("south", allRoom.get(2));
        allRoom.get(3).setExit("west", null);

        // allRoom.get(4).setExit(allRoom.get(0), allRoom.get(5), allRoom.get(7), null);
        allRoom.get(4).setExit("north", allRoom.get(0));
        allRoom.get(4).setExit("east", allRoom.get(5));
        allRoom.get(4).setExit("south", allRoom.get(7));
        allRoom.get(4).setExit("west", null);

        // allRoom.get(5).setExit(null, allRoom.get(6), null, allRoom.get(4));
        allRoom.get(5).setExit("north", null);
        allRoom.get(5).setExit("east", allRoom.get(6));
        allRoom.get(5).setExit("south", null);
        allRoom.get(5).setExit("west", allRoom.get(4));

        // allRoom.get(6).setExit(null, null, null, allRoom.get(5));
        allRoom.get(6).setExit("north", null);
        allRoom.get(6).setExit("east", null);
        allRoom.get(6).setExit("south", null);
        allRoom.get(6).setExit("west", allRoom.get(5));

        // allRoom.get(7).setExit(allRoom.get(4), allRoom.get(11), allRoom.get(8),
        // null);
        allRoom.get(7).setExit("north", allRoom.get(4));
        allRoom.get(7).setExit("east", allRoom.get(11));
        allRoom.get(7).setExit("south", allRoom.get(8));
        allRoom.get(7).setExit("west", null);

        // allRoom.get(8).setExit(allRoom.get(7), null, allRoom.get(9), null);
        allRoom.get(8).setExit("north", allRoom.get(7));
        allRoom.get(8).setExit("east", null);
        allRoom.get(8).setExit("south", allRoom.get(9));
        allRoom.get(8).setExit("west", null);

        // allRoom.get(9).setExit(allRoom.get(8), null, allRoom.get(10), null);
        allRoom.get(9).setExit("north", allRoom.get(8));
        allRoom.get(9).setExit("east", null);
        allRoom.get(9).setExit("south", allRoom.get(10));
        allRoom.get(9).setExit("west", null);

        // allRoom.get(10).setExit(allRoom.get(9), null, null, null);
        allRoom.get(10).setExit("north", allRoom.get(9));
        allRoom.get(10).setExit("east", null);
        allRoom.get(10).setExit("south", null);
        allRoom.get(10).setExit("west", null);

        // allRoom.get(11).setExit(null, allRoom.get(12), null, allRoom.get(7));
        allRoom.get(11).setExit("north", null);
        allRoom.get(11).setExit("east", allRoom.get(12));
        allRoom.get(11).setExit("south", null);
        allRoom.get(11).setExit("west", allRoom.get(7));

        // allRoom.get(12).setExit(allRoom.get(13), allRoom.get(14), null,
        // allRoom.get(11));
        allRoom.get(12).setExit("north", allRoom.get(13));
        allRoom.get(12).setExit("east", allRoom.get(14));
        allRoom.get(12).setExit("south", null);
        allRoom.get(12).setExit("west", allRoom.get(11));

        // allRoom.get(13).setExit(null, null, allRoom.get(12), null);
        allRoom.get(13).setExit("north", null);
        allRoom.get(13).setExit("east", null);
        allRoom.get(13).setExit("south", allRoom.get(12));
        allRoom.get(13).setExit("west", null);

        allRoom.get(14).setExit("down", allRoom.get(0));

        // A ajouter jusqu'a 52

        // create the rooms
        // outside = new Room("outside the main entrance of the university");
        // theatre = new Room("in a lecture theatre");
        // pub = new Room("in the campus pub");
        // lab = new Room("in a computing lab");
        // office = new Room("in the computing admin office");

        // initialise room exits
        // outside.setExit(null, theatre, lab, pub);
        // theatre.setExit(null, null, null, outside);
        // pub.setExit(null, outside, null, null);
        // lab.setExit(outside, office, null, null);
        // office.setExit(null, null, null, lab);

        currentRoom = allRoom.get(0); // start game outside
    }

    /**
     * Given a command, process (that is: execute) the command. If this command ends
     * the game, true is returned, otherwise false is returned.
     */
    public void interpretCommand(String commandLine) {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if (command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit")) {
            if (command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
    }

    /**
     * Print out some help information. Here we print some stupid, cryptic message
     * and a list of the command words.
     */
    private void printHelp() {
        gui.println("You are lost. You are alone. You wander");
        gui.println("around at Monash Uni, Peninsula Campus." + "\n");
        gui.print("Your command words are: " + parser.showCommands());
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            gui.println("There is no door!");
        else {
            currentRoom = nextRoom;
            gui.println(currentRoom.getLongDescription());
            if (currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }

    private void endGame() {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
        // System.exit(0);
    }
}
