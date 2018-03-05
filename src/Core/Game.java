package Core;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import Graphics.Assets;
import Graphics.GameCamera;
import State.GameOver;
import State.GameState;
import State.MenuState;
import State.State;
import State.YouWon;
import audio.AudioManager;
import worlds.World;

//the main class of the game, it will run everything and close everything
public class Game implements Runnable{ //Runnable has to be implemented, and it has to do with thread
	

	private Display display;//Game HAS-A display, making an object means LAUNCHING everything inside the display constructor
	private int width, height;
	private String title;
	
	//create a thread here so this Game class will run separately from the rest of the Classes/Codes
	private Thread thread;
	private boolean running = false;
	
	
	private BufferStrategy bs;
	private Graphics g;
	
	
	//Score
	public int score = 0;
	public int level = 1;
	
	//States
	private State gameState;
	private State menuState;
	private State gameOver;
	private State youWon;
	
	//Key manager
	private KeyManager keyManager;
	
	
	//game camera
	private GameCamera gameCamera;
	
	//mouse
	private MouseManager mouseManager;
	
	//audio
	private AudioManager audioManager;
	
	public Game(String title, int width, int height) {
	
		this.width = width;
		this.height =height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager(this);
	}

	
	
	///////////////////init() method explaination////////////////////////////////////////////////////////////////////
	
	//When we run the init() method, it first makes a "display", then add "keylistener" to the display
	//then add images to the display
	//then add camera control to the display
	//then add a state to the display
	
	    //Game state(whatever inside the constructor will take effect)
		//add a world(), which has the map, like all the map drawing stuffs
		//add a player, and its initial spawn position
	
	
		//Menu state
		//add a menu(bunch of text)
	
	//setState determines which state the game is in, and execute the current state's render() and tick() methods
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////"this" explain///////////////////////////////////////////////////////////////
	/*class itself is an object, lile right now we are inside, this is actaully an object itself
	 * inside a class, if you use "this", it refers to this object
	 * or this class
	 * not the constructor, not the function, but THIS CLASS(THIS OBJECT)
	 * 
	 * 
	 * 
	 */

	
	private void init() {//this function is gonna initialize all the graphcis, and only run once
		
		//GameState world = GameState.getWorld();
		
		display = new Display(title, width, height);//constructor
		display.getFrame().addKeyListener(keyManager);//keybaord input activated
		
		display.getFrame().addMouseListener(mouseManager);//mouse input activated inside JFrame
		//display.getFrame().addMouseMotionListener(mouseManager);
		
		display.getCanvas().addMouseListener(mouseManager);//mouse input activated inside Canvas
		//display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();//load all sprite images
		
		audioManager = new AudioManager("/music/background.wav");
	   
	 
		gameState = new GameState(this);//because the State constructor takes in a Game object, so we can justt use this
		menuState = new MenuState(this);//both are gonna call the game constrcutor, which really just means to execute what's inside the constructor
		gameOver = new GameOver(this);
		youWon = new YouWon(this);
		State.setState(menuState);//beginning state of the game
		 
		
		 gameCamera = new GameCamera(this, 0, 0, gameState);//take the Game object/constructor because we WANT the GameCamera to be part of this GAME object so when you launch it in main, the CAMERA class works together
			//the numbers here have no effect on the camera
		
		//To load sprite sheet
		//1.You have to pass the entire sprite sheet picture inside the picture loader class
		//2.You have to pass the buffered image object insdie the sprite sheet parameter
		//test = ImageLoader.loadImage("/texture/spriteSheet.png");
		//sheet = new SpriteSheet(test);
	}
	


	
	private void tick() {//or you can name it to update, and this will update everything the game for us
	
		//keyManager.tick();
		
		if(State.getState()!= null)
		{
			State.getState().tick();//depending on which state the game is in, calls the tick() function from that class
									//ex:the game right now is set to game state, so getState().tick() will call the tick() inside the game state class
		}
	}
	
	private void render() {//this will render everything for our game
		

		//////////////////The way we gonna render our images///////////////////////////////////////////
		bs = display.getCanvas().getBufferStrategy();//Buffer Strategy = a canvas object buffer strategy
		
		if(bs == null) {
			
			display.getCanvas().createBufferStrategy(3);//increase loading image speed
			
		return;
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////
		g = bs.getDrawGraphics();//graphics object g allows us to draw everything to the Canvas
		
		System.out.println("printing asd");
		
		if(State.getState()!= null)
		{
			State.getState().render(g);
		}

		
		bs.show();//draw all bufferes and shits and images to the screen
		g.dispose();//disposes of this graphics context and releases any system resources that it is using
	}
	
	public State getGameState() {
		return gameState;
	}



	public State getGameOver() {
		return gameOver;
	}



	public State getMenuState() {
		return menuState;
	}



	public State getYouWon() {
		return youWon;
	}



	public void run() {//called by thread.start()
		
		init();
		
		///////////////////////////FPS calculation Preperation///////////////////////////////////////////////////
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer =  System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		while(running) {
			
			/////////////////////////FPS Calculation///////////////////////////
			
			now = System.nanoTime();
		
			delta += (now - lastTime)/ timePerTick;//(now - lastTime) get the amount of time passes, divide by the maximum amount of time
			lastTime = now;							//allowed to call these tick() and render() method
			
			//////////////////////////////////////////////////////////////////
			
			if(delta >= 1) {//the purpose of doing this, is everything will be kept at 60fps, so moving speed doesn't go crazy
				tick();//or this can be named updated()
				render();
				updates++;
				delta--;
			}
			frames++;
			
			
			////////////////////////////////Displaying FPS//////////////////////////
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(updates + " Ticks, " + frames);
				updates = 0;
				frames = 0;
			}
			
			////////////////////////////////////////////////////////////////////////
			
			
		}
		
		
		stop();//stop our thread
	}
	
	
	public KeyManager getKeyManager() {
		
		return keyManager;
	}

	public GameCamera getGameCamera() {
		
		return gameCamera;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getScore() {
		return score;
	}


	//////////////////////////////////////////////Thread stuff/////////////////////////////////////////////////////////////
	
	


	public synchronized void start()//use synchronized whenever we are working with thread directly 
									//whenever you create a start() or a stop() you must use synchronized os nothing gets in the way
	{
		if(running == true)//if the game is already running, then we want to exit out of this function
			return;
		
		running = true;
		thread = new Thread(this);//Thread constructor takes in what class you want to run, in this case, we can do the "Game" class
								  //This is run the Game class on the thread
		thread.start();			  //this .start() function will actually call the run() function above
		
	}
	
	public synchronized void stop() {
		
		if(running==false)//or !running
			return;
		
		running = false;
		
	try {			  //join method allows one thread to wait for the completion of another
		thread.join();//causes the current thread to pause execution until this thread's terminate
	}			      //in there, we can interpret this as the join() to stop the game
	catch(InterruptedException e) {
		e.printStackTrace();
	}

	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}