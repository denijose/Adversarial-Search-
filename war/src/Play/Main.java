package Play;
import java.io.IOException;

import DataStructures.GameState;
import Logging.Log;


public class Main {

	/**
	 * @param args
	 */
	private static int TASK;
	private static int CUTOFFDEPTH;
	private static String MAPFILENAME = null;
	private static String INITFILENAME = null;	
	private static String OUTPUTPATH = null;
	private static String OUTPUTHLOG = null;

	private static void parseArgs(String[] args){
		//get the initfile, map file and other args from the command line and set the static variables here
		TASK = Integer.parseInt(args[1]);
		CUTOFFDEPTH = Integer.parseInt(args[3]);
		MAPFILENAME = args[5];
		INITFILENAME = args[7];
		OUTPUTPATH = args[9];
		OUTPUTHLOG = args[11];
	}
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		parseArgs(args);
		GameState s;
		try {
			s = new GameState(INITFILENAME, MAPFILENAME);
			Log log = new Log(TASK,CUTOFFDEPTH,s);
			Play.log =  log;
			Play.log.action(null,0,s);
			Play.MODE = TASK; // set the mode of play
			
			Play.play(CUTOFFDEPTH,s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//the end
	}

	
}
