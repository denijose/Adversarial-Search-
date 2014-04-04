package DataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import Logging.Log;


public class GameStateFunctions {	

	//Returns all nodes on which ParaTroop Drop is possible
	public static ArrayList<Node> paraTroopCheck(GameState s){
		ArrayList<Node> paraTroopNodes = new ArrayList<Node>();
		for(Node n: s.cities.values()){ 
			if(n.team == 0)
				paraTroopNodes.add(n);
		}

		return paraTroopNodes;
	}

	//Returns all nodes on which ForceMarch is possible
	public static ArrayList<Node> forceMarchCheck(GameState s, int player){
		ArrayList<Node> forceMarchNodes = new ArrayList<Node>(); 
		//printGameState(s);
		
		if(player == 1){
			for(Node n: s.cities.values()) {
				if(n.team == 1)
					//System.out.print("neighbors of " +n.name);
					for(Node d: n.neighbours){
						//System.out.print(n.name);
						Node n1 = s.cities.get(d.name);
						if(n1.team == 0 && !forceMarchNodes.contains(d)){
							forceMarchNodes.add(d);
							//System.out.print(d.name + " ");
						}
					}
			}
		}
		else if(player == -1){	
			for(Node n: s.cities.values()) {
				if(n.team == -1)
					for(Node d: n.neighbours){
						Node n1 = s.cities.get(d.name);
						if(n1.team == 0 && !forceMarchNodes.contains(d))
							forceMarchNodes.add(d);
					}
			}
		}
		
		return forceMarchNodes;
	}

	//returns true if the game is over else false
	public static boolean terminalState(GameState s){
		int flag = 0;
		for(Node n: s.cities.values()){
			if(n.team == 0)
				flag = 1;
		}
		if(flag == 1)
			return false;
		else
			return true;
	}


	
	public static ArrayList<Action> PossibleActions(GameState s, int player){
//		HashSet<Action> possibleActions = new HashSet<Action>();
//		HashSet<Node> paraNodes = new HashSet<Node>();
//		HashSet<Node> forceMarchUnion = new HashSet<Node>();
//		HashSet<Node> forceMarchConf = new HashSet<Node>();
//		
		ArrayList<Action> possibleActions = new ArrayList<Action>();
		ArrayList<Node> paraNodes = new ArrayList<Node>();
		ArrayList<Node> forceMarchUnion = new ArrayList<Node>();
		ArrayList<Node> forceMarchConf = new ArrayList<Node>();
		paraNodes = paraTroopCheck(s);
		paraNodes = order(paraNodes,s);
		
		if(player == 1){
			forceMarchUnion = forceMarchCheck(s,1);
			forceMarchUnion = order(forceMarchUnion,s);
		for(Node destination : forceMarchUnion)
			possibleActions.add(new Action("Force March",destination.name,1));
		for(Node destination : paraNodes){
			possibleActions.add(new Action("Paratroop Drop",destination.name,1));
		}
		}
		else if(player == -1){
			forceMarchConf = forceMarchCheck(s,-1);
			forceMarchConf =order(forceMarchConf,s);
		for(Node destination : forceMarchConf)
			possibleActions.add(new Action("Force March",destination.name,-1));
		for(Node destination : paraNodes){
			possibleActions.add(new Action("Paratroop Drop",destination.name,-1));
		}
		}
		//System.out.println("printing all possible actions");
		//printAllpossibleActions(possibleActions,s);
		
		return possibleActions; 
	} 

	//returns a game state object s given an action object a
	public static GameState Result(GameState s, Action a){
		GameState updatedGameState = s.copy(s);
//		GameState updatedGameState = s.copy(s);
	//	System.out.println("inside result function and the original game state =");
   		//printGameState(updatedGameState);
		//how to update the game state now??
		String cityName = a.destination;
		Node cityNode = updatedGameState.cities.get(cityName);
		//update the city with the new conquerors
		cityNode.team = a.player;

		
		double utilityUnion = 0.0;
		double utilityConfederacy = 0.0;
		if(a.player == 1 && a.action.equals("Force March")){				
				HashSet<Node> nei = updatedGameState.cities.get(a.destination).neighbours;
				//System.out.println("debug -" + updatedGameState.getSum(1));
				for(Node n : nei){					
					if(n.team == -1){
						n.team = 1;
						updatedGameState.updateCity(n);
					}
				}
			}
		//printGameState(updatedGameState);
		
		if(a.player == -1 && a.action.equals("Force March")){
			HashSet<Node> nei = updatedGameState.cities.get(a.destination).neighbours;
			for(Node n : nei){					
				if(n.team == 1){
					n.team = -1;
					//utilityUnion += n.val;
					updatedGameState.updateCity(n);
				}
			}
		}
	return updatedGameState; 
	}

	//returns the best action from a given list of utility objects
	public static Action findMaxUtility(HashMap<Action,Double> actionUtilityMap){
		double max = Double.NEGATIVE_INFINITY;
		Action bestAction = null;
		Action otherBestAction =  null;
		ArrayList<String> cityNamesToForceMarch = new ArrayList<String>();
		ArrayList<String> cityNamesToParaTrooop = new ArrayList<String>();
		for(Action a:actionUtilityMap.keySet()){
			System.out.println("the action and its utility" );
			printAction(a);
			System.out.println(actionUtilityMap.get(a));
			
			if(max<actionUtilityMap.get(a)){
				max = actionUtilityMap.get(a);
				bestAction	= a;	
				cityNamesToForceMarch.clear();
				cityNamesToParaTrooop.clear();
			}
			else if(max == actionUtilityMap.get(a)){
				if(a.action.equalsIgnoreCase("Force March"))
					cityNamesToForceMarch.add(a.destination);								
				else
					cityNamesToParaTrooop.add(a.destination);					
			}
			
		}
		if(bestAction.action.equalsIgnoreCase("Force March"))
			cityNamesToForceMarch.add(bestAction.destination);								
		else
			cityNamesToParaTrooop.add(bestAction.destination);	
		
	        Collections.sort(cityNamesToForceMarch);
	        Collections.sort(cityNamesToParaTrooop);
			if(cityNamesToForceMarch.size()>0){
				//bestAction = actionsListForceMarch.get(cityNamesToForceMarch.get(cityNamesToForceMarch.size()-1));
				bestAction = new Action("Force March",cityNamesToForceMarch.get(0), 0);
				
			}
			else if(cityNamesToParaTrooop.size()>0){
				//bestAction = actionsListParaTroop.get(cityNamesToParaTrooop.get(cityNamesToParaTrooop.size()-1));
				bestAction = new Action("Paratroop Drop",cityNamesToParaTrooop.get(0), 0);
			}
			
		return bestAction;
	}
	
	public static void printAction(Action a){
		System.out.println("printing action - "+ a.player + " " + a.action+ " " + a.destination);	
    }
	
	public static void printGameState(GameState s){		
		for(Node n:s.cities.values()){
			System.out.println(n.name + " " + n.team + " " + n.val);
		}
	}
	
	public static void printAllpossibleActions(ArrayList<Action> allPosibleActions, GameState s){
		for(Action a: allPosibleActions)
			System.out.println(a.player + " " + a.action+ " " + a.destination);
	}
	
	
	
	public static GameState Result2(GameState s, Action a){
		GameState updatedGameState = s.copy(s);
	//	System.out.println("inside result function and the original game state =");
   //		printGameState(s);
		//how to update the game state now??
		String cityName = a.destination;
		Node cityNode = updatedGameState.cities.get(cityName);
		//update the city with the new conquerors
		cityNode.team = a.player;
		//update the gamestate with the updated city
		
		double utilityUnion = 0.0;
		double utilityConfederacy = 0.0;
		if(a.player == 1 && a.action.equals("Force March")){
				
				HashSet<Node> nei = updatedGameState.cities.get(a.destination).neighbours;
			//	System.out.println("debug -" + updatedGameState.getSum(1));
				for(Node n : nei){					
					if(n.team == -1){
						n.team = 1;
						//utilityUnion += n.val;
					}
				}
			}
		return updatedGameState; 
	}
	
	public  static  ArrayList<Node> order(ArrayList<Node> list,GameState s){
		ArrayList<String> cityNames = new ArrayList<String>();
		for(Node n: list)
			cityNames.add(n.name);
		Collections.sort(cityNames);
		
		ArrayList<Node> newList = new ArrayList<Node>();
		for(String c : cityNames){
			newList.add(s.cities.get(c));
		}
		
		return newList;
	}
}
