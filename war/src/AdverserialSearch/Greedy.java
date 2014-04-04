package AdverserialSearch;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import DataStructures.*;

import DataStructures.Action;
import DataStructures.GameState;
import DataStructures.Node;
import Logging.Log;

public class Greedy {

	public static int cutOfDepth;
	public static Log log;
	
	public static Action GREEDY_DECISION(GameState s, int player) throws IOException, CloneNotSupportedException{
	//	System.out.println("printing game state before calling greedy -");
		
		GameState temp = s.copy(s);
		
		ArrayList<Action> possibleActions = GameStateFunctions.PossibleActions(temp, player);
		HashMap<Action,Double> actionUtilityMap = new HashMap<Action,Double>();
		System.out.println("printing all possible actions = ");
		for(Action a: possibleActions){
			printAction(a);
			GameState temp3 = s.copy(s);
			Action tempAction = new Action(a.action,a.destination,a.player);
			GameState sTemp = GameStateFunctions.Result(temp3,tempAction);
			double value = sTemp.evaluationFunction(player);			
			log.traversal(tempAction,(int)1,value);
			actionUtilityMap.put(tempAction,value);
		}
		Action bestAction = GameStateFunctions.findMaxUtility(actionUtilityMap);
		bestAction.player = player;
		printAction(bestAction);
		return bestAction;
	}
	
	public static void printGameState(GameState s){	
		for(Node n:s.cities.values()){
			System.out.println(n.name + " " + n.team + " " + n.val);
		}
	}
	
	public static void printAction(Action a){
		System.out.println("printing action - "+ a.player + " " + a.action+ " " + a.destination);	
    }
	
	public static double evaluate(GameState s, int player){
		return 0;
	}
	
}
