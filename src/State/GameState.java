package State;

import java.awt.Font;
import java.awt.Graphics;

import Core.Game;
import Graphics.Assets;
import entity.GameObject.Tree;
import entity.MovingEntity.Player;
import tiles.Tile;
import worlds.World;
import State.State;

public class GameState extends State{

	private Player player;
	private World world;
	
	
	public static String GScore;
	public static String GLevel;
	
	//VERY IMPORTANT
	/*
	 * 
	 * If you want to use a function from a class, MAKE SURE YOU MAKE AN OBJECT OF IT BEFORE YOU USE IT
	 * like, World world = new World(); dont jsut do
	 * World world, world.whatever(); this aint goona work ok?
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public GameState(Game game) {
		
		super(game);//private State gameState = new GameState(this); will first, add the "Game" constructor
		//then create a player constructor that includes the player position
		
		
		
		player  = new Player(game, 260, 260, this);//x and y here are the starting spawn position of my player 
		
		//player = new Player(game, 2280, 2890, this);
		
		world = new World(game, "res/worlds/GameWorld.txt", this, player);
		//the Tree here is the same as the player, if you want to draw a tree, well you have to make it here first
		//tree = new Tree(game,100, 200 );
		
	
		
	}
	

	
	public World getWorld() {
		return world;
	}

	@Override
	public void tick() {
		
		//world stuff
		player.tick();
		
		
		//player changing stuff
		world.tick();
	
		
		//HUD
		 GScore = Integer.toString(this.getWorld().getEntityManager().getGameScore());
		GLevel = Integer.toString(this.getWorld().getEntityManager().getGameLevel());
		
		 
		 
	}

	@Override
	public void render(Graphics g) {
		
		
		world.render(g);
		//g.drawImage(Assets.player, 0, 0, null);
		player.render(g);
		//Tile.tiles[0].render(g, 0, 0);//this render method is from the tile class
		//tree.render(g);
		
		Font fnt0 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt0);
		g.drawString("Score : " + GScore, 1380, 50);
		g.drawString("Lv. " + GLevel, 100, 90);
		
		
		
		
	}



	public Player getPlayer() {
		return player;
	}
	
	

}
