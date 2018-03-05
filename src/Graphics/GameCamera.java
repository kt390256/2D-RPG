package Graphics;

import Core.Game;
import State.GameState;
import State.State;
import entity.Entity;
import tiles.Tile;

public class GameCamera {
	
	private Game game;
	private float xOffset, yOffset;
	private GameState gameState;
	//VERY IMPORTANT
	/*
	 * 
	 * if you want a class takes effect, in this case we are making an object of the Game class
	 * so it is very important that we have to make an object of the game class, and add that to
	 * this class's constructor
	 * THEN
	 * when you make an object of this class INSIDE THE game class
	 * pass the "this"(the game object) inside this class constructor
	 * then everything will work as expected 
	 * 
	 * its like a mutual relationship
	 * 
	 * you make an object of the Game class, then pass that into this class's constructor
	 * then you make an object of this class inside the game class, or the class you are going to use in main
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	public GameCamera(Game game, float xOffset, float yOffset, State gameState)
	{
		this.game = game;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.gameState = (GameState) gameState;
	}
	
	
public void checkBlankSpace() {
		
		if(xOffset < 0) {
			xOffset = 0;
			
		}else if(xOffset > gameState.getWorld().getWidth()*Tile.TILE_WIDTH - game.getWidth())
		{
			xOffset = gameState.getWorld().getWidth() * Tile.TILE_WIDTH - game.getWidth();
		}
		
		if(yOffset < 0) {
			yOffset = 0;
		}else if(yOffset > gameState.getWorld().getHeight() * Tile.TILE_HEIGHT - game.getHeight()) {
			yOffset = gameState.getWorld().getHeight() * Tile.TILE_HEIGHT - game.getHeight();
		}
		
		
	}
	
	
	
	public void centerOnEntity(Entity e)
	{
		//we are using the getWidth() and getHeight() from the game class, so just making an instance of game isn't enough, we have to do it inside the 
		//constructor here
		
		xOffset = e.getX() - game.getWidth() / 2 + e.getWidth()/2;//this line moves the camera in the x axis
		yOffset = e.getY() - game.getHeight() / 2 + e.getHeight()/2;//this line moves the camera in the y axis
		checkBlankSpace();
	}
	

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
