/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

import java.util.ArrayList;


public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        //Room outside, theatre, pub, lab, office;
        

        ArrayList<Room> allRoom = new ArrayList<Room>();


        for (int i = 0; i < 15; i++) {
            System.out.println(i);
            allRoom.add(new Room(Integer.toString(i)));
        }

        //HAUT DROITE BAS GAUCHE

        allRoom.get(0).setExits(allRoom.get(1), allRoom.get(2), allRoom.get(4), null);
        allRoom.get(1).setExits(null, null, allRoom.get(0), null);
        allRoom.get(2).setExits(allRoom.get(3), null, null, allRoom.get(0));
        allRoom.get(3).setExits(null, null, allRoom.get(2), null);
        allRoom.get(4).setExits(allRoom.get(0), allRoom.get(5), allRoom.get(7), null);
        allRoom.get(5).setExits(null, allRoom.get(6), null, allRoom.get(4));
        allRoom.get(6).setExits(null, null, null, allRoom.get(5));
        allRoom.get(7).setExits(allRoom.get(4), allRoom.get(11), allRoom.get(8), null);
        allRoom.get(8).setExits(allRoom.get(7), null, allRoom.get(9), null);
        allRoom.get(9).setExits(allRoom.get(8), null, allRoom.get(10), null);
        allRoom.get(10).setExits(allRoom.get(9), null, null, null);
        allRoom.get(11).setExits(null, allRoom.get(12), null, allRoom.get(7));
        allRoom.get(12).setExits(allRoom.get(13), allRoom.get(14), null, allRoom.get(11));
        allRoom.get(13).setExits(null, null, allRoom.get(12), null);
        
        //A ajouter jusqu'a 52
        allRoom.get(14).setExits(null, null, null, null);


        // create the rooms
        // outside = new Room("outside the main entrance of the university");
        // theatre = new Room("in a lecture theatre");
        // pub = new Room("in the campus pub");
        // lab = new Room("in a computing lab");
        // office = new Room("in the computing admin office");
        
        // initialise room exits
        // outside.setExits(null, theatre, lab, pub);
        // theatre.setExits(null, null, null, outside);
        // pub.setExits(null, outside, null, null);
        // lab.setExits(outside, office, null, null);
        // office.setExits(null, null, null, lab);

        currentRoom = allRoom.get(0);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Function we had for display to avoid inconsistency
     */
    private void printLocationInfo(){
		System.out.println("You are " + currentRoom.getDescription());
		System.out.print("Exits: ");

		//7.7
		System.out.print(currentRoom.getExitString());

		System.out.println();
	}

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        //modif
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            //modif
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
