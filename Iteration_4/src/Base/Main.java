
package Base;

public class Main {

	static public Game theGame;

	/**
	 * Main function...
	 * 
	 * @param args program parameters
	 */
	public static void main(String[] args) {
		theGame = new Game();
		theGame.play();
	}

}