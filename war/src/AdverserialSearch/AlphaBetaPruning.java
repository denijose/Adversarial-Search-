package AdverserialSearch;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import DataStructures.*;

import DataStructures.Action;
import DataStructures.GameState;
import DataStructures.GameStateFunctions;

public class AlphaBetaPruning {

	public static int cutOfDepth;
	private static int depthCheck;
	
	public static Action ALPHABETA_DECISION(GameState s){
		depthCheck = 0;
		GameState temp = s.copy(s);		
		double v = MAX_VALUE(temp,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
		ArrayList<Action> possibleActions = GameStateFunctions.PossibleActions(s,1); 
		Action bestAction = null;
		for(Action a: possibleActions){
			GameState tempState = s.copy(s);
			Action tempAction = new Action(a.action,a.destination,a.player);
			if(GameStateFunctions.Result(tempState, tempAction).evaluationFunction(1) == v){
				bestAction = a;
				//return a;
			}	
		}
			
		return bestAction;
	}
	
	private static double MIN_VALUE(GameState s, double alpha, double beta){
		if(GameStateFunctions.terminalState(s)) // this function and isGameOver might be the same
			return s.evaluationFunction(-1);
//		if(depthCheck>=cutOfDepth)
//			return s.evaluationFunction(-1);
		depthCheck++;
		double v = Double.NEGATIVE_INFINITY;
		ArrayList<Action> possibleActions = GameStateFunctions.PossibleActions(s,-1);
		for(Action a:possibleActions){
			Action tempAction = new Action(a.action,a.destination,a.player);
			GameState tempState = s.copy(s);		
			v = Math.min(v, MAX_VALUE(GameStateFunctions.Result(tempState,tempAction),alpha,beta));
			if(v<=alpha)
				return v;
			beta=Math.min(beta,v);
		}
		return v;
	}
	
	private static double MAX_VALUE(GameState s,double alpha, double beta){
		if(GameStateFunctions.terminalState(s)) 
			return s.evaluationFunction(1);
//		if(depthCheck>=cutOfDepth)
//			return s.evaluationFunction(1);
		depthCheck++;
		double v = Double.NEGATIVE_INFINITY;
		ArrayList<Action> possibleActions = GameStateFunctions.PossibleActions(s,1);
		for(Action a:possibleActions){
			Action tempAction = new Action(a.action,a.destination,a.player);
			GameState tempState = s.copy(s);	
			v = Math.max(v, MIN_VALUE(GameStateFunctions.Result(tempState,tempAction),alpha,beta));
			if(v>=beta)
				return v;					
			alpha=Math.max(alpha,v);
		}	
		return v;
	} 
}
