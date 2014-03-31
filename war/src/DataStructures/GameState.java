package DataStructures;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;



public class GameState {

	public HashMap<String,Node> cities; // the set of cities(cities are nodes)
	 
	
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
	
	public double getUtility(){
		//doubtle utility = Resource_your_cities - Resource_opponent_cities
		return null; // for now
	}
	
}
