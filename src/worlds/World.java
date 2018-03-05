package worlds;

import java.awt.Graphics;

import Core.Game;
import Graphics.GameCamera;
import State.GameState;
import entity.EntityManager;
import entity.GameObject.Altar;
import entity.GameObject.Boss;
import entity.GameObject.Boss2;
import entity.GameObject.Monster;
import entity.GameObject.Monster2;
import entity.GameObject.Monster3;
import entity.GameObject.PowerUP;
import entity.GameObject.Tree;
import entity.MovingEntity.Player;
import mapLoader.ParseMap;
import tiles.Tile;

public class World {

	
	private Game game;
	private Player player;
	private int width, height;
	private int[][] tiles;//this will hold bunch of IDs saying what tile will be displayed
	
	
	//Entites manager
	private EntityManager entityManager;
	private PowerUP p1;
	private PowerUP p2;
	private PowerUP p3;
	private PowerUP p4;
	private PowerUP p5;
	
	public World(Game game, String filePath, GameState gameState, Player player) {
		this.game = game;
		this.player = player;
		
		//adding Entities to the game
		entityManager = new EntityManager(game, gameState);//passing the Game in here so it has all the data from the Game class
		
		
		//trees
		entityManager.addEntity(new Tree(game, 1000, 280, gameState));
		
		
		entityManager.addEntity(new Tree(game, 1500, 280, gameState));
		entityManager.addEntity(new Tree(game, 1700, 280, gameState));
		entityManager.addEntity(new Tree(game, 1950, 280, gameState));
		entityManager.addEntity(new Tree(game, 2300, 280, gameState));
		entityManager.addEntity(new Tree(game, 2600, 280, gameState));
		
		entityManager.addEntity(new Tree(game, 1720, 740, gameState));
		entityManager.addEntity(new Tree(game, 1920, 740, gameState));
		entityManager.addEntity(new Tree(game, 2120, 740, gameState));
		entityManager.addEntity(new Tree(game, 2320, 740, gameState));
		entityManager.addEntity(new Tree(game, 2520, 740, gameState));
		entityManager.addEntity(new Tree(game, 2700, 740, gameState));
		
		
		//Altars
		entityManager.addEntity(new Altar(game, 140, 280, gameState, this.player));
		entityManager.addEntity(new Altar(game, 2900, 2650, gameState, this.player));//altar lower right
		entityManager.addEntity(new Altar(game, 2350, 1270, gameState, this.player));//middle
		entityManager.addEntity(new Altar(game, 2350, 2890, gameState, this.player));
		
		//Enhancement
		entityManager.addEntity(p1 = new PowerUP(game, 310, 1300, gameState, this.player));
		entityManager.addEntity(p2 = new PowerUP(game, 300, 2700, gameState, this.player));
		entityManager.addEntity(p3 = new PowerUP(game, 2340, 160, gameState, this.player));
		entityManager.addEntity(p4 = new PowerUP(game, 2850, 2580, gameState, this.player));
		entityManager.addEntity(p5 = new PowerUP(game, 2350, 2800, gameState, this.player));
		
		
		entityManager.addEntity(new Monster(game, 882, 277, gameState));
		entityManager.addEntity(new Monster(game, 1467, 290, gameState));
		entityManager.addEntity(new Monster(game, 1890, 311, gameState));
		entityManager.addEntity(new Monster(game, 1310, 411, gameState));
		
		entityManager.addEntity(new Monster2(game, 2100, 300, gameState));
		entityManager.addEntity(new Monster2(game, 2456, 300, gameState));
		entityManager.addEntity(new Monster2(game, 2399, 200, gameState));
		
		//Guadian
		entityManager.addEntity(new Monster3(game, 240, 1250, gameState));
		entityManager.addEntity(new Monster3(game, 360, 1250, gameState));
		entityManager.addEntity(new Monster(game, 250, 1240, gameState));
		entityManager.addEntity(new Monster(game, 250, 1350, gameState));
		
		
		//Boss
		//for altar lower right
		entityManager.addEntity(new Boss2(game, 2850, 2580, gameState));//for altar lower right
		entityManager.addEntity(new Boss(game, 2850, 2580, gameState));
		entityManager.addEntity(new Boss(game, 2930, 2580, gameState));
		
		//for middle
		entityManager.addEntity(new Boss(game, 2300, 1150, gameState));
		entityManager.addEntity(new Boss2(game, 2300, 1220, gameState));
		
		
		entityManager.addEntity(new Boss(game, 2270, 2830, gameState));
		
		entityManager.addEntity(new Boss2(game, 2270, 2800, gameState));
		
		
		loadWorld(filePath);
		
	}
	

	public void tick() {
		
		entityManager.tick();//responsible for all the entities' tick() method
		
	}
	
	public void render(Graphics g) {
		
		
		
		
		for(int y = 0; y < height; y++) {
			
			for(int x = 0; x <width; x++) {
				
			getTile(x, y).render(g, (int)(x * Tile.TILE_WIDTH - game.getGameCamera().getxOffset() ), //this is very important, because we are calling the game object in Main, EVERY class must have/take a GAME object, in order to take effect
																				   //which means, first, you have to go to the GAME class to make a GameCamera instance, then make the GC instance take in a game object
																				   //Second, use a getting to return it
																				   //Third, we can use it here
																				   //This is very very important
					                (int)(y * Tile.TILE_HEIGHT - game.getGameCamera().getyOffset()) );
	
			
			}
		}
		
		entityManager.render(g);//drawing all entities
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	//1.Step one, you must have the layout first, "how big is the map"?
	//2.then, we need to make a new 2D array to start the map [width][height]
	//3.use a double for loop to "decide" or "pick" what texture/image we are using to represent the ground
	
	private void loadWorld(String path) {//responsible for deciding what type of tile we will be using
		

		
		String file = ParseMap.loadFileAsString(path);
		
		String[] tokens = file.split("\\s+");//this will parse then entire file, using whitespace as a delimiter, and put all the parsed number into the 
											 //String array
											 // \\s matches single whitespace character
										     // \\s+ matches one more more whitespace
		width = ParseMap.parseInt(tokens[0]);
		height =ParseMap.parseInt(tokens[1]);
		
			
			
		tiles = new int[width][height];
		
		for(int y = 0; y < height; y++) {
			
			for(int x = 0; x <width; x++) {
				
				tiles[x][y] = ParseMap.parseInt(tokens[(x + y * width) + 2]);
				
			}
		}
		
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//1.Step 2, you have to return a tile object first in order to process to the next stage.
	public Tile getTile(int x, int y) {//using "Tile" because we need a tile object
		
		////////////collision dection area////////////////////////
		if(x < 0 || y < 0 || x >= width || y >= height)
		{
			return Tile.grassTile;
		}
		//////------------------making sure if we are standing outside of the map, we are just "standing" on a grass tile, so the program doesnt give us error"
	
		
		Tile t = Tile.tiles[tiles[x][y]];//tiles[x][y] returns a number, Tile.tiles[some number]returns an object
																				
		if(t==null) {
		
			return Tile.waterTile;
		}		
		
		return t;//this returns a tile object
	}

	
	
	//////getters 
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public PowerUP getP2() {
		return p2;
	}


	public PowerUP getP3() {
		return p3;
	}


	public PowerUP getP4() {
		return p4;
	}


	public PowerUP getP5() {
		return p5;
	}


	public PowerUP getP1() {
		return p1;
	}

	
	
	

	
}
