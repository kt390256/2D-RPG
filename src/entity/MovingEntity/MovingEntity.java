package entity.MovingEntity;

import Core.Game;
import State.GameState;
import entity.Entity;
import entity.EntityManager;
import tiles.Tile;

public abstract class MovingEntity extends Entity{
	
	

	public GameState world;
	public EntityManager entityManager;
	//public Game asd;
	
	
	public static final float DEFAULT_SPEED = 3.0f; //3.0 is double, so i have to add a f behind the number
	
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;

	
	protected float speed;
	protected float xMove, yMove;
	
	
	
	//VERY IMPAORTANT
	/*
	 * so i was making a world object from the GameState class and noticed it has a null value
	 * the reason why, is because the GameState class is not really "inside" this class
	 * To fix it
	 * you will have to go to the class which has the function you want, like the getWorld() from the GameState class, because
	 * the World class has been initialized, so i can create a getter class in there
	 * Then
	 * I noticed the "game object"(down below) from the Game class has already been initialized
	 * I was like...how?
	 * that's because the Game object has been passed into this class in the Game class(go check now) - the GameState
	 * 
	 * 
	 * 
	 * You either have to initialize it like Game game = new Game();
	 * or, you can initialize it inside the current class constructor by doing, this.world = world, and don't forget to add that inside the parenthesis as a parameter
	 * 
	 * typically when you make an object of a class, but this object is null, that means this current class don't have another class's data.
	 * like Game asd; asd here is null
	 * what you want to do is
	 * 1.add asd as the constructor parameter, like public Creature (Game asd)	<-------------------------------------
	 * 2.then set the local asd equals to the new asd, which means, this.asd = asd;									 |
	 * 3.Go to your Game class and initialize the Creature class, dont forget your getter for this object,cuz you need the functions inside that class
	 * 4.Then pass "this" into the parameter, like new Creature(this)												 |
	 * 5.now we are back at the creature class																		 |
	 * 6.Through passing the Game class's data into the Creature construtor, now the asd FROM INSIDE the parenthesis-- this one, has the Game class's information
	 * 7.and now you can use the asd to reference anything inside the Game class, because asd isn't null anymore
	 * 8.so if you want this "additional" class to have effect on the Game class, or just any core class in general, jsut making an instance isn't enough
	 * 9.you have to either make an instance of this "new class" inside the core class's constructor, and make a getter of this instance, like, keyManager inside the Game constructor, and a getKeyManager()
	 * 10.or inside this new class's constructor, add "this(inside that core class)", as a parameter, like player = new Player(game. this) inside the GameState class
	 * 
	 * 
	 * *1.make an object of the new class and initialize it in the core class(like the game class here)
	 * *2.make a getter function for this object, because I need my new functions to HAVE EFFECT ON MY CORE CLASS(IMPORTANT!!!!!!)
	 * *3.ONLY pass the core game data into the new class if you want to use any functions from the core game class, otherwise you don't need it. Look at the keyboard manager class, Tile class, and Imageloader class
	 * 
	 * OOP shit
	 * Just make a func class, make an instance of this class inside the core class, if you need to use any functions from the core class
	 * make sure you make a getter for the class you just made an instance of
	 * make sure you pass the core class data inside the func class construcotr new func(this)
	 * so when you need to use any methods from this core class, you are not using a null variable
	 * 
	 * 
	 * 
	 * Let say you have 3 classes, Main class and A and B class
	 * You want your B classes function have effect on the Main class but you also want to use functions from the A class inside the B class
	 * You can first, make an instance of A class inside Main, give some value to the A object construcor
	 * then make a getter method for the A class object, then PASS THE Main object into the B object, like B b = new B(this);
	 * then inside the B class you can use functions from A
	 * 
	 * 
	 */
	public MovingEntity(Game game, float x, float y, int width, int height, GameState world) {
		
		super(game, x, y, width, height, world);//must match super class constructor
		this.game = game;
		
		this.speed = MovingEntity.DEFAULT_SPEED;
		this.world = world;
		
	}
	
	/////////////////////player movement///////////////////////////////
	public void move() {
		//System.out.println("123");
		
		if(!checkEntityCollisions(xMove, 0f))//if collision is false
		{
		
			moveX();//move
		}
		
		//moving collision with object, if player hits an object then player's health reduce
		if(checkEntityCollisions(xMove, 0f))
		{
			this.hurt(0.1);
			//System.out.println(this.health);
		}
		
		
		if(!checkEntityCollisions(0f, yMove))
		moveY();
		
		
		if(checkEntityCollisions(0f, yMove))
		{
			this.hurt(0.1);
			
		}
		
	}

	public void moveX() {
		
		//////Definition//
		/* x is the current x coordinate of the hitbox, the upperleft hand corner
		 * y is teh current y coordinate of the hitbox, the upperleft hand corner 
		 * xMove is the speed at which the hitBox is moving at 
		 * yMove same as above 
		 * hitBox.x is the coordinate of the hitbox RELATIVE TO THE MOVING SCREEN 
		 * hitBox.y is the same thing
		 */
		//////////////////////////////////////Right side collision with Tile////////////////////////////////////////////
		if(xMove > 0) {//moving right 
			
			int tx = (int)(x + hitBox.x + hitBox.width) / Tile.TILE_WIDTH;//This is the x coordinate of the hitbox 
																 //adding hitBox.x so that the entire hitbox will be outside of the rock, instead of half way into the rock
																					//we are diving the Tile width because we want the unit in Tile

			if(collisionWithTile(tx, (int)(y + hitBox.y)/Tile.TILE_HEIGHT) == false	 //this one checks for the upper right corner(right side)
													&&
				collisionWithTile(tx, (int)(y+hitBox.y+hitBox.height)/Tile.TILE_HEIGHT)	== false //this one checks the lower right corner
					) {
																				//check to see if the condition is "not true", if its not true, run the x+= xMove
				x += xMove;										              //but if the condition inside the if-statement is true, then dont run x += xMove, which mean the character stay the same place
			}
			else {
				
				x = tx * Tile.TILE_WIDTH - hitBox.x - hitBox.width ;
			}
	
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////////////Left side collision/////////////////////////////////////////////
		else if(xMove < 0) {//moving left

			int tx = (int)( x +hitBox.x ) / Tile.TILE_WIDTH;

			if(collisionWithTile(tx, (int)(y + hitBox.y)/Tile.TILE_HEIGHT) == false	 //this one checks for the upper right corner(left side)
													&&
				collisionWithTile(tx, (int)(y+hitBox.y+hitBox.height)/Tile.TILE_HEIGHT)	== false 
					) {
																			
				x += xMove;										             
		}
			else
			{
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - hitBox.x;
			}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	x += xMove;
		//System.out.println(asd);
		
	}
	
	public void moveY() {
		
	if(yMove < 0) {
		
		int ty = (int)(y + yMove + hitBox.y) / Tile.TILE_HEIGHT;
		
		if(collisionWithTile((int)(x + hitBox.x) / Tile.TILE_WIDTH, ty)  == false // left corner(top side)
				           &&
		   	collisionWithTile((int)(x + hitBox.x + hitBox.width) / Tile.TILE_WIDTH, ty) == false)   //check right corner (top side)                                   
			{
			
						y += yMove;
			}

	}
	
	else if(yMove > 0) {
		
			int ty = (int)(y + yMove + hitBox.y + hitBox.height) / Tile.TILE_HEIGHT;
		
			if(collisionWithTile((int)(x + hitBox.x) / Tile.TILE_WIDTH, ty)  == false // left corner(top side)
				           &&
		   	collisionWithTile((int)(x + hitBox.x + hitBox.width) / Tile.TILE_WIDTH, ty) == false)   //check right corner (top side)                                   
			{
			
						y += yMove;
			}
		
	}
	
		
	}
	
	
	protected boolean collisionWithTile(int x, int y)
	{
		
		
		return world.getWorld().getTile(x, y).isSolid();//return the isSolid() function from a tile class, x y here should be the coordinate of the tile
	}
	
	
	
	
	/////////////////////////////////////////////////Getters and Setters///////////////////////////////////////////
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}


	public double getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}


	public void setSpeed(float speed) {
		this.speed = speed;
	}





}
