package Play;

import AdverserialSearch.MiniMax;
import DataStructures.GameState;

import DataStructures.*;

public class Play {

	//this is where the game runs in loops till no more moves are possible
	
	public static String MODE;
	public static GameState s;

	public static void play(){		
		if(MODE.equalsIgnoreCase("1"))
			;//call the greedy class			
		if(MODE.equalsIgnoreCase("2"))
			;//call the minimax class	
		if(MODE.equalsIgnoreCase("2"))	
			;//call the alpha beta pruning
		
		while(!GameStateFunctions.terminalState(s)){
			//oponents move - always greedy move, so call the greedy algo
			
			//my move - one of the three moves - greedy,minimax or alphabets, so call one of them
			MiniMax.MINIMAX_DECISION(s);
		}
	}
	

	

}
