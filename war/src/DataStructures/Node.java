package DataStructures;

import java.util.ArrayList;
import java.util.HashSet;


public class Node {

	public String name;
	public int val;
	public int team;
	HashSet<Node> neighbours = new HashSet<Node>();
	
	public Node() {
		// TODO Auto-generated constructor stub
	}
	public Node(String name, int val, int team){
		this.name = name;
		this.val = val;
		this.team = team;
		
	}

	public void getNodeInfo(){
		System.out.println("Name: "+name+" Value: "+val+" Team: "+team);
		System.out.println("Neighbours: ");
		for(Node n: neighbours){
			System.out.println(n.name);
		}
		
	}


}
