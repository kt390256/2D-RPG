package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import Core.Game;
import State.GameState;
import State.State;
import entity.MovingEntity.Player;
import worlds.World;

public class EntityManager { //this class will manager all the entity

	private Game game;
	private Player player;
	private GameState gameState;
	
	
	private int gameScore;
	private int gameLevel = 1 ;
	private int entityCounter = 0;

	
	private ArrayList<Entity> entities;//this Array is going to hold a bunch of Entity objects
	
	
	
	
	public EntityManager(Game game, GameState gameState) {
		
		this.gameState = gameState;
		this.game = game;
		entities = new ArrayList<Entity>();
		
		
	}
	
	////////////////////////////////////////call the tick() from a certain Entity////////////////////////
	public void tick() {
		
	
		
		for(int i = 0; i < entities.size(); i ++)
		{
			Entity e = entities.get(i);
			e.tick();
			if(e.isActive()==false)//check to see if the status of an entity is false
			{
				entities.remove(e);
				
				
				 if(e.equals(gameState.getWorld().getP1()) || e.equals(gameState.getWorld().getP2()) || e.equals(gameState.getWorld().getP3()) || e.equals(gameState.getWorld().getP4()) || e.equals(gameState.getWorld().getP5()))
					{
					 	
						game.level++;
						gameLevel = game.level;
						
					}
				
				else {
					entityCounter++;
					game.score++;
					gameScore = game.score;
					
				}
				
				 if(entityCounter%2 != 0)//level up when at odd level
					{

						game.level++;
						gameLevel = game.level;
					}
				
				
			}
		}
		
		 if(gameScore==10)
		 {
			 State.setState(game.getYouWon());
		 }
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void render(Graphics g) {
		
		for(Entity e : entities)
		{
			e.render(g);
		}
		
	}

	public void addEntity(Entity e) {
		entities.add(e);//add an entity object into the ArrayList 
	}
	
	public Game getGame() {
		return game;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public int getGameScore() {
		return gameScore;
	}

	public int getGameLevel() {
		return gameLevel;
	}
	
}
