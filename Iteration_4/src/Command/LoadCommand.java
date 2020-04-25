package Command;

public class LoadCommand {

}

/*	private void testFile(Command command){
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
		
		
*/