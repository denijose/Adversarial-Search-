package AdverserialSearch;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import DataStructures.*;

import DataStructures.Action;
import DataStructures.GameState;
import DataStructures.Node;
import Logging.Log;

public class MiniMax {

	public static int cutOfDepth;
	//private int depthCheck;
	public static Log log;
    	
	public static Action MINIMAX_DECISION(GameState s){
		int depthCheck = 0;
		
		ArrayList<Action> possibleActions = GameStateFunctions.PossibleActions(s,1);
		HashMap<Action,Double> actionUtilityMap = new HashMap<Action,Double>(); 
		
		for(Action a: possibleActions){
			//printAction(a);
			GameState temp = s.copy(s);
			Action tempAction = new Action(a.action,a.destination,a.player);
			double tempVal = MIN_VALUE(GameStateFunctions.Result(temp,tempAction),1);
			//System.out.println(tempVal);
			actionUtilityMap.put(a,tempVal);
			System.out.println(a.destination+" "+tempVal+" "+a.action);
		}
		Action bestAction = GameStateFunctions.findMaxUtility(actionUtilityMap);
		//logging
		bestAction.player = 1;
		return bestAction;
	}
	
	private static double MIN_VALUE(GameState s,int depthCheck){
		
		//printGameState(s);
		if(GameStateFunctions.terminalState(s)) // this function and isGameOver might be the same
			return s.evaluationFunction(1);
		if(depthCheck>=cutOfDepth){
			//System.out.println("depth at MIN = " + depthCheck);
			return s.evaluationFunction(1);}
		depthCheck++;
		//System.out.println("before calling max-" +depthCheck);
		
		//printGameState(obj);
		double v = Double.POSITIVE_INFINITY;
		ArrayList<Action> possibleActions = GameStateFunctions.PossibleActions(s,-1);
		for(Action a:possibleActions){	
			GameState obj = s.copy(s);
			//System.out.println("AT "+depthCheck);
			//printGameState(obj);
			Action tempAction = new Action(a.action,a.destination,a.player);
			double temp = MAX_VALUE(GameStateFunctions.Result(obj,tempAction),depthCheck);
			//System.out.println("after calling max-" +depthCheck);
			
			if(v>temp)
				v=temp; //getting the max utility value;
			
		//	v = Math.min(v, MAX_VALUE(GameStateFunctions.Result(obj,tempAction),++depthCheck));
		}
		
		return v;
	}
	
	private static double MAX_VALUE(GameState s,int depthCheck){
		
		//printGameState(s);
		if(GameStateFunctions.terminalState(s)) 
			return s.evaluationFunction(1);
		if(depthCheck>=cutOfDepth){
			//System.out.println("depth at MAX = " + depthCheck);
				return s.evaluationFunction(1);}
		depthCheck++;
		
		double v = Double.NEGATIVE_INFINITY;
		ArrayList<Action> possibleActions = GameStateFunctions.PossibleActions(s,1);
		ArrayList<GameState> copies = new ArrayList<GameState>();
		for(Action a:possibleActions){	
			GameState obj = s.copy(s);
			Action tempAction = new Action(a.action,a.destination,a.player);
		    double temp = MIN_VALUE(GameStateFunctions.Result(obj,tempAction),depthCheck);
			if(v<temp)
				v=temp; //getting the max utility value;
		//	v = Math.max(v, MIN_VALUE(GameStateFunctions.Result(obj,tempAction),++depthCheck));
		}
		return v;
	}
	
	public static void printAction(Action a){
		System.out.println("printing action - "+ a.player + " " + a.action+ " " + a.destination);	
}
	public static void printGameState(GameState s){	
		for(Node n:s.cities.values()){
			System.out.println(n.name + " " + n.team + " " + n.val);
		}
	}	
	
}
