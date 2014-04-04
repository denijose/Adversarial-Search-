package DataStructures;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;



public class GameState implements Cloneable{

	public HashMap<String,Node> cities; // the set of cities(cities are nodes)
	
	 public Object clone() throws CloneNotSupportedException {
		 return super.clone();
		 }
	
	
	public GameState(){ //the default constructor
		
	}
	//copy constructor
	public GameState(GameState copy){
		
		this.cities  = new HashMap<String, Node>(copy.cities);		
		for(Node node:copy.cities.values()){
			Node newCity = new Node();
			newCity.name =  node.name;
			newCity.neighbours = new HashSet<Node>();
			for(Node neighbor:node.neighbours)
				newCity.neighbours.add(neighbor);
			newCity.team = node.team;
			newCity.val = node.val;	
			this.cities.put(node.name, newCity);
		}
		
	}
	
	public GameState copy(GameState copy){
		GameState newState = new GameState();		
		HashMap<String, Node> cities  = new HashMap<String, Node>();		
		for(Node node:copy.cities.values()){
			Node newCity = new Node();
			newCity.name =  node.name;
			newCity.neighbours = new HashSet<Node>();
			newCity.team = node.team;
			newCity.val = node.val;	
			cities.put(node.name, newCity);
		}
		for(Node node : cities.values()){
			node.neighbours = new HashSet<Node>();
			for(Node n:copy.cities.values()){
				if(n.name.equalsIgnoreCase(node.name)){
					for(Node n2:n.neighbours){
						node.neighbours.add(cities.get(n2.name));
					}
				}
			}
		}

		newState.cities = cities;
		return newState;
	}
	
	public GameState copy2(GameState copy){
		GameState newState = new GameState();		
		HashMap<String, Node> cities  = new HashMap<String, Node>(copy.cities);		
		for(Node node:copy.cities.values()){
			Node newCity = new Node();
			newCity.name =  node.name;
			newCity.neighbours = new HashSet<Node>();
			for(Node neighbor:node.neighbours)
				newCity.neighbours.add(neighbor);
			newCity.team = node.team;
			newCity.val = node.val;	
			this.cities.put(node.name, newCity);
		}
		newState.cities = cities;
		return newState;
	}
	
	
	//read the init file and map file to create the gameState
	public GameState(String initFileName, String mapFileName) throws IOException{
		cities = new HashMap<String,Node>();
		File initFile = new File(initFileName);
		File mapFile = new File(mapFileName);
		
		BufferedReader br = new BufferedReader(new FileReader(initFile));
		String line;
		while( (line = br.readLine()) != null){
			String tokens[] = line.split(",");
			Node city = new Node(tokens[0],Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
			cities.put(tokens[0], city);
			
		}
		br.close();
		br = new BufferedReader(new FileReader(mapFile));
		while( (line = br.readLine()) != null){
			String tokens[] = line.split(",");
			Node firstCity = cities.get(tokens[0]);
			Node secondCity = cities.get(tokens[1]);
			firstCity.neighbours.add(secondCity);
			secondCity.neighbours.add(firstCity);
		}		
		br.close();
		
	}
	
	public double evaluationFunction(int team){
		//double utility = Resource_your_cities - Resource_opponent_cities
		double utility = 0.0;
		double resourceUnion = 0.0;
		double resourceConfederacy = 0.0;
			for(Node n: cities.values()){
				if(n.team == 1){
					resourceUnion+=n.val;
				}
				else if(n.team == -1){
					resourceConfederacy+=n.val;
				}
			}
			
			//System.out.println("resourceUnion: "+resourceUnion);
			//System.out.println("resourceConfederacy: "+resourceConfederacy);
			
			if(team == 1){
				utility = resourceUnion - resourceConfederacy;
				return utility;
			}
			else{ 
				utility = resourceConfederacy - resourceUnion;
				return utility;
			}
			

	}	
	
	public double getSum(int team){
		//double utility = Resource_your_cities - Resource_opponent_cities
		double utility = 0.0;
		double resourceUnion = 0.0;
		double resourceConfederacy = 0.0;
			for(Node n: cities.values()){
				if(n.team == 1){
					resourceUnion+=n.val;
				}
				else if(n.team == -1){
					resourceConfederacy+=n.val;
				}
			}
			
			
			if(team == 1)
				return resourceUnion;
			else 
			  return resourceConfederacy;
	}	
	
	
	public void updateCity(Node n){
		cities.put(n.name, n);
	}
}
