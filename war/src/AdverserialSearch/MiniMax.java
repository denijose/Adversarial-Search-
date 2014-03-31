package AdverserialSearch;


import java.util.HashMap;
import java.util.HashSet;

import DataStructures.*;

import DataStructures.Action;
import DataStructures.GameState;

public class MiniMax {

	public static Action MINIMAX_DECISION(GameState s){
		HashSet<Action> possibleActions = GameStateFunctions.Actions(s);
		HashMap<Action,Double> actionUtilityMap = new HashMap<Action,Double>(); 
		for(Action a: possibleActions)
			actionUtilityMap.put(a,MIN_VALUE(GameStateFunctions.Result(s,a)));
		Action bestAction = GameStateFunctions.findMaxUtility(actionUtilityMap);
		return bestAction;
	}
	
	private static double MIN_VALUE(GameState s){
		if(GameStateFunctions.terminalState(s)) // this function and isGameOver might be the same
			return s.getUtility();
		double v = Double.NEGATIVE_INFINITY;
		HashSet<Action> possibleActions = GameStateFunctions.Actions(s);
		for(Action a:possibleActions){
			double temp = MAX_VALUE(GameStateFunctions.Result(s,a));
			if(v>temp)
				v=temp; //getting the max utility value;
		}
		return v;
	}
	
	private static double MAX_VALUE(GameState s){
		if(GameStateFunctions.terminalState(s)) 
			return s.getUtility();
		double v = Double.NEGATIVE_INFINITY;
		HashSet<Action> possibleActions = GameStateFunctions.Actions(s);
		for(Action a:possibleActions){
			double temp = MIN_VALUE(GameStateFunctions.Result(s,a));
			if(v<temp)
				v=temp; //getting the max utility value;
		}
		return v;
		
			
	}
}
