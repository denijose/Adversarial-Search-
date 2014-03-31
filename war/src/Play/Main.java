package Play;
import DataStructures.GameState;


public class Main {

	/**
	 * @param args
	 */
	private static String TASK;
	private static String CUTOFFDEPTH;
	private static String MAPFILENAME;
	private static String INITFILENAME;	
	private static String OUTPUTPATH;
	private static String OUTPUTHLOG;

	
	
	public static void main(String[] args) {
		parseArgs(args);
		
		GameState s = new GameState(INITFILENAME, MAPFILENAME);
		Play.MODE = TASK; // set the mode of play
		Play.s = s;
		Play.play();
		//the end
	}

	private static parseArgs(String[] args){
		//get the initfile, map file and other args from the command line and set the static variables here
		
		
	}
}
