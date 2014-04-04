package Play;

import java.io.IOException;

import AdverserialSearch.AlphaBetaPruning;
import AdverserialSearch.Greedy;
import AdverserialSearch.MiniMax;
import DataStructures.GameState;

import DataStructures.*;
import Logging.Log;

public class Play {

	//this is where the game runs in loops till no more moves are possible
	
	public static int MODE;
	public GameState s;
	public static Log log;
	private static int TURN = 0;

	public static void play(int cutOfDepth,GameState s) throws IOException, CloneNotSupportedException{
		MiniMax.log = log;
		MiniMax.cutOfDepth = cutOfDepth;
		Greedy.log = log;
		AlphaBetaPruning.cutOfDepth = cutOfDepth;
		if(MODE == 1){	
			int turn1=1;
			while(!GameStateFunctions.terminalState(s)){	
			//my move - one of the three moves - greedy,minimax or alpha beta, so call one of them
			GameState temp=s.copy(s);
			Action myAction = Greedy.GREEDY_DECISION(temp, 1);
			//System.out.println("I play");
			//printGameState(s);
			s = GameStateFunctions.Result(s, myAction);
			log.action(myAction, ++TURN, s);	
			//System.out.println(turn1++ +"the action i took");
		//	printAction(myAction);
			//System.out.println("after the action i took");
			//printGameState(s);
			
			
			if(!GameStateFunctions.terminalState(s)){
			//opponents move - always greedy move, so call the greedy algo
				GameState tempG=s.copy(s);
			Action oppAction = Greedy.GREEDY_DECISION(tempG, -1);			
			s = GameStateFunctions.Result(s, oppAction);	
			System.out.println("opponent plays");
			System.out.println(turn1++ +"the action it took");
			printAction(oppAction);
			System.out.println("after the action it took");
			printGameState(s);
			log.action(oppAction, ++TURN, s);
			}
		}
		}
			
		if(MODE == 2){
			while(!GameStateFunctions.terminalState(s)){				
				//my move - one of the three moves - greedy,minimax or alpha beta, so call one of them
				GameState temp=s.copy(s);
				Action myAction = MiniMax.MINIMAX_DECISION(temp);
				s = GameStateFunctions.Result(s, myAction);
				log.action(myAction, ++TURN, s);	
				//System.out.println("the action i took");
				//printAction(myAction);
				//System.exit(0);
				if(!GameStateFunctions.terminalState(s)){
					GameState temp1=s.copy(s);
					Action oppAction = Greedy.GREEDY_DECISION(temp1, -1);			
					s = GameStateFunctions.Result(s, oppAction);	
					log.action(oppAction, ++TURN, s);
				}
			}
			
		}
			
		if(MODE == 3){	
			while(!GameStateFunctions.terminalState(s)){				
				//my move - one of the three moves - greedy,minimax or alpha beta, so call one of them
				Action myAction = AlphaBetaPruning.ALPHABETA_DECISION(s);
				s = GameStateFunctions.Result(s, myAction);
				log.action(myAction, ++TURN, s);	
				System.out.println("the action i took");
				printAction(myAction);
				
				if(!GameStateFunctions.terminalState(s)){
					Action oppAction = Greedy.GREEDY_DECISION(s, -1);			
					s = GameStateFunctions.Result(s, oppAction);	
					log.action(oppAction, ++TURN, s);
				}
			}
		}
	//	MiniMax.cutOfDepth = cutOfDepth;

	}
	
public static void printGameState(GameState s){	
	for(Node n:s.cities.values()){
		System.out.println(n.name + " " + n.team + " " + n.val);
	}
}
	

public static void printAction(Action a){
		System.out.println("printing action - "+ a.player + " " + a.action+ " " + a.destination);	
}	
}
