package DataStructures;

public class Utility {
	public Action action;
	public int value;
	public GameState newState;
	
	public Utility(Action action, GameState newState){
		this.action = action;
		this.newState = newState;
		evaluationFuntion();
	}
	
	public void evaluationFuntion(){
		// calculate the Resource_your_cities - Resource_opponent_cities and set the value
	}

}
