
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


import java.util.ArrayList;
import java.util.Stack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GameEngine {
	private Parser parser;
	
	private Player player;

    private UserInterface gui;
    private ArrayList<Room> allRoom;
    private Stack<Room> oldVisitedRooms;

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine() {
        parser = new Parser();
        
        allRoom = new ArrayList<Room>();
		oldVisitedRooms = new Stack<Room>();
		
		player = new Player(1.0);

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
        gui.println(player.getRoom().getLongDescription());
        gui.showImage(player.getRoom().getImageName());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        // Room outside, theatre, pub, lab, office;

        allRoom.add(new Room("0", "images/salle_coffre.jpg"));
        for (int i = 1; i < 15; i++) {
            allRoom.add(new Room(Integer.toString(i), "images/salle_vide.jpg"));
        }

        allRoom.get(1).addItem(new Item("awesome item", 2.0, 5.25));
		allRoom.get(1).addItem(new Item("awesome item number 2", 1.0, 4.25));
		
		Item tempo = new Item("magic cookie", 0.0, 10.25);
		tempo.addEffect(100.0);
		allRoom.get(0).addItem(tempo);

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
		
		player.setRoom(allRoom.get(0));

	}

    /**
     * Given a command, process (that is: execute) the command. If this command ends
     * the game, true is returned, otherwise false is returned.
     *
     * @param commandLine A String containing the commande written by the user.
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
		else if (commandWord.equals("items")){
			if (command.hasSecondWord())
				gui.println("items what?");
			else{
				gui.println(player.getItemDescription());
			}
		}
		else if (commandWord.equals("use")){
			if (!command.hasSecondWord())
				gui.println("use which?");
			else{
				if(!player.useItem(Integer.parseInt(command.getSecondWord()))){
					gui.println("Could not use this item.");
				}
			}
		}
		else if(commandWord.equals("take")){
			if (!command.hasSecondWord())
                gui.println("take which?");
            else
                if(!player.take(Integer.parseInt(command.getSecondWord()))){
					gui.println("The room don't have this item number.");
				}
		}
		else if(commandWord.equals("drop")){
			if (!command.hasSecondWord())
                gui.println("drop which?");
            else
                if(!player.drop(Integer.parseInt(command.getSecondWord()))){
					gui.println("Invalid item index.");
				}
		}
        else if (commandWord.equals("quit")) {
            if (command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
        else if(commandWord.equals("back")){
            if (command.hasSecondWord())
                gui.println("Back what?");
            else
                goBack();
		}
		else if(commandWord.equals("test")){
            testFile(command);
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
	
	private void testFile(Command command){
		if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Test what?");
            return;
		}

		String filePath = command.getSecondWord();
		
		BufferedReader readBuffer = null;
		String line;

		try{
			readBuffer = new BufferedReader(new FileReader(filePath));
		}
		catch(FileNotFoundException exc){
			gui.println("Erreur d'ouverture");
		}
		
		while(true){

			try{
				line = readBuffer.readLine();
			}
			catch(IOException e){
				gui.println("Catched.");
				line = null;
			}

			if(line == null) break;

			interpretCommand(line);

		}
		

		
		try{
			readBuffer.close();
		}
		catch(IOException e){
			gui.println("Erreur fermeture du fichier.");
		}
		
		

	}

    /**
     * Try to go to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     *
     * @param command The command that contain go and the second word which should be the direction 
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

		
        // Try to leave current room.
        Room nextRoom = player.getRoom().getExit(direction);

        if (nextRoom == null)
            gui.println("There is no door!");
        else {
            oldVisitedRooms.push(player.getRoom());
            player.setRoom(nextRoom);
            gui.println(player.getRoom().getLongDescription());

            if (player.getRoom().getImageName() != null)
                gui.showImage(player.getRoom().getImageName());
        }
    }

    /**
     * Go back to the last room visited.
     *
     */  
    private void goBack(){
        if(oldVisitedRooms.isEmpty()){
            gui.println("You're at your starting point.");
        }
        else{

            gui.println("you retrace your steps.");

            player.setRoom(oldVisitedRooms.pop());
            gui.println(player.getRoom().getLongDescription());

            if (player.getRoom().getImageName() != null)
                gui.showImage(player.getRoom().getImageName());

        }

    }


    /**
     * End the game.
     */  
    private void endGame() {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
        // System.exit(0);
    }
}
