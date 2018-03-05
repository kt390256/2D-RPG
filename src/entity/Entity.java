package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import Core.Game;
import State.GameState;
import entity.MovingEntity.MovingEntity;

public abstract class Entity {

	protected Game game;
	protected GameState gameState;
	
	//coo
	protected float x, y;//using float for smoother experience
	protected int width, height;
	
	protected double health;
	public static final double DEFAULT_HEALTH = 50;//setting to final so nothing can change it
	
	
	protected boolean active = true;//this is for the state of an entity, if it is no longer exists, active is set to false
	
	
	
	protected Rectangle hitBox;
	
	
	public Entity(Game game, float x, float y, int width, int height, GameState gameState)
	{
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gameState = gameState;
		this.health = MovingEntity.DEFAULT_HEALTH;
		
		hitBox = new Rectangle(0, 0, width, height);
	}
	
	/////////////////collision dection for entity/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		
		for(Entity e : gameState.getWorld().getEntityManager().getEntities())//for every single object inside this Entity class
		{
			
			if(e.equals(this))//doing this so it doesn't check collsion with itself
			{
				continue;
			}
			
			if(e.getCollisionHitBox(0f, 0f).intersects(getCollisionHitBox(xOffset, yOffset)))//check if the hit-box of the entity collide with the hitbox of the player
				return true;																 //if it does then return true;
			
		}
		
		return false;
	}
	

	public Rectangle getCollisionHitBox(float xOffset, float yOffset) { //return the hit-box of an entity
		
		
		return new Rectangle((int)(x + hitBox.x + xOffset), (int)(y + hitBox.y + yOffset), hitBox.width, hitBox.height);
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public void hurt(double amt) {
		health -= amt;
		
		
		if(health <= 0 )
		{
			active = false;
			
		}
	}
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}


	public abstract void tick();//function to update entity
	
	public abstract void render(Graphics g);//function to draw all entity
}
