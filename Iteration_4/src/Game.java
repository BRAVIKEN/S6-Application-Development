
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
 *  rooms, creates the parser and starts the game.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.1 (December 2002)
 */

import java.util.ArrayList;

import Room.*;
import Item.*;
import Door.Door;
import Command.Command;
import Base.*;

public class Game {

    private Parser parser;
	private Player player;
	private int commandLimit;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
		parser = new Parser();
		
		commandLimit = 10;

        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
		office = new Room("in the computing admin office");
		
		ArrayList<Room> xd = new ArrayList<Room>();

		xd.add(outside);
		xd.add(theatre);
		xd.add(pub);
		xd.add(lab);
		xd.add(office);

		TransporterRoom tpRoom = new TransporterRoom("This is a fucking tp room.", xd, player.getSeed());
		
		ItemBeamer beamer = new ItemBeamer();

		outside.addItem(beamer);

		// initialise room exits
		
		Door ot = new Door(outside, theatre);
		Door ol = new Door(outside, lab);
		Door op = new Door(outside, pub);
		

        outside.setExit("east", ot);
        outside.setExit("south", ol);
        outside.setExit("west", op);

		//theatre.setExit("west", outside);
		Door tp = new Door(theatre, pub);
		theatre.setExit("south", tp);

        pub.setExit("east", op);

		lab.setExit("north", ol);
		
		Door lof = new Door(lab, office);
		lof.lock(1);

        lab.setExit("east", lof);

		office.setExit("west", lof);
		
		Door outToTp = new Door(outside, tpRoom);
        outside.setExit("troll", outToTp);
        // the player starts the game outside
		player.setCurrentRoom(outside);

	}

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {   
		int commandCount = 0;
		long startingTime;
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
		
		startingTime = System.currentTimeMillis();

        boolean finished = false;
        while(! finished) {
			Command command = parser.getCommand();
			if((System.currentTimeMillis() - startingTime) > 100000){
				System.out.println("Tu as dépassé 10 secondes.");
				break;
			}
            if(command == null) {
                System.out.println("I don't understand...");
			}
			else {
                
				++commandCount;
				finished = command.execute(player);
                
				if(commandCount > commandLimit){
					System.out.println("You've been eaten by a wolf.");
					break;
				}
                
                player.pushMove(command);
                System.out.println(command.getSecondWord());
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to The World of Zuul!");
        System.out.println("The World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
}
