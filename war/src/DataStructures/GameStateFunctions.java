package DataStructures;

import java.util.HashMap;
import java.util.HashSet;

public class GameStateFunctions {	
	
	//returns true if the game is over else false
	public static boolean terminalState(GameState s){
		// code here.....
		return false;
	}
	
	//returns all possible action objects given a game state s
	public static HashSet<Action> Actions(GameState s){
		//code here..
		return null; // for now
	}
	
	//returns a game state object s given an action object a
	public static GameState Result(GameState s, Action a){
		//code here..
		return null; //for now
	}
	
	//returns the best action from a given list of utility objects
	public static Action findMaxUtility(HashMap<Action,Double> actionUtilityMap){
		double max = Double.NEGATIVE_INFINITY;
		Action bestAction = null;
		for(Action a:actionUtilityMap.keySet())
			if(max<actionUtilityMap.get(a)){
				max = actionUtilityMap.get(a);
				bestAction	= a;	
			}
		return bestAction;
	}
}
