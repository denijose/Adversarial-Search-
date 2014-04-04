package Logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import DataStructures.*;

public class Log {
	
	//private static BufferedWriter w1, w2;
	private static File resultFile,traversalFile;
	
	public Log(int task, int cutOffDepth,GameState s) throws IOException{
		String resulFileName = "C:\\Users\\Denis\\git\\Adversarial-Search-\\war\\src\\Logging" + task +"_cutoffdepth_" + cutOffDepth;
		String traversalFileName = "C:\\Users\\Denis\\git\\Adversarial-Search-\\war\\src\\Logging" + task +"_cutoffdepth_" + cutOffDepth;
		resultFile = new File(resulFileName);
		traversalFile = new File(traversalFileName);
		resultFile.createNewFile();
		traversalFile.createNewFile();
					
		action(null,0,s);
		
	}
	
	public static void action(Action a,int turn, GameState s) throws IOException{
		BufferedWriter w1;
		w1 = new BufferedWriter(new FileWriter(resultFile,true));
		String player;
		String action;
		String destination;
		if(a==null){
			player = "N/A";
			action = "N/A";
			destination = "N/A";
		}
		else{
			player = (a.player==1)? "Union" : "Confederacy";
			action = a.action;
			destination = a.destination;
		}
		
		w1.write("TURN = " + turn +"\n");		
		w1.write("Player = " + player+"\n");
		w1.write("Action = " + action+"\n");
		w1.write("Destination = " + destination+"\n");
		String unionCities = "Union,{";
		String ConfederacyCities = "Confederacy,{";

		
		for(Node city : s.cities.values()){
			if(city.team == 1){
				unionCities+=city.name+",";
			}
			if(city.team == -1){
				ConfederacyCities+=city.name+",";
			}
				
		}
		unionCities+="}," + s.getSum(1);
		ConfederacyCities+="}," + s.getSum(-1);
		
		w1.write(unionCities+"\n");
		w1.write(ConfederacyCities+"\n");
		w1.write("----------------------------------------------\n");
		w1.close();
		
		
	}
	
	public static void traversal(Action a,int depth, double value) throws IOException{
		BufferedWriter w2 = new BufferedWriter(new FileWriter(traversalFile,true));
		String player;
		String action;
		String destination;
		String depth_;
		String value_;
		if(a==null){
			player = "Player";
			action = "Actoion";
			destination = "Destination";
			depth_ = "Depth";
			value_ = "Value";
		}
		else{
			player = (a.player==1)? "Union" : "Confederacy";
			action = a.action;
			destination = a.destination;
			depth_ = String.valueOf(depth);
			value_ = String.valueOf(value);
		}
		
		w2.write("Player,Action,Destination,Depth,Value\n");
		String line =  player + "," + action + "," + destination + "," + depth_ + "," + value_;
		w2.write(line+"\n");
		w2.close();
	}
}
