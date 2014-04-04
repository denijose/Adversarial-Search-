package DataStructures;

import java.util.HashSet;

public class Action {

	public String action;
	public String destination;
	public int player;
	
	public Action(String action, String destination, int player){
		this.action = action;
		//this.destination = destination;
		this.destination = destination;
		this.player = player;
	}
	
//	public Action copy(Action copyOb){
//		Node temp = new Node(copyOb.destination.name,copyOb.destination.val,copyOb.destination.team);
//		HashSet<Node> neighbours = new HashSet<Node>();
//		for(Node nei:copyOb.destination.neighbours){
//			Node temp2 = new Node(nei.name,nei.val,nei.team);
//			neighbours.add(temp2);
//		}
//		temp.neighbours = neighbours;
//		return new Action(copyOb.action,temp,copyOb.player);
//	}
	
	
}
