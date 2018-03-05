package State;

import java.awt.Graphics;

import Core.Game;

public abstract class State {

	//Every state has a render and tick function
	
	
	//Game state manager, has nothing to do with the game state class
	///////////////////////////////setting current state///////////////////
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	////////////////////////////////////////////////////////////////////////
	
	//Class
	protected Game game;
	
	//I think adding the game object here meaning that, when you call the game constructor, you will all call the State 
	//as well as the Entity class
	//It is like, "The game has all these stuffs in it"
	public State(Game game)//pass the game constructor to the State constrcutor, so when you call the State class, then 
	{				       //you are actaully calling the game constrcutor
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	
	
}
