package entity.MovingEntity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import State.State;

import Core.Game;
import Core.KeyManager;
import Graphics.Animation;
import Graphics.Assets;
import State.GameState;
import entity.Entity;

public class Player extends MovingEntity{

	//useless code//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private KeyManager k = new KeyManager();;//can't do this because the Keyboard class object is null, we have to access it from the game object
						//because the keyBoard class has been initialized
						//now that it is initialized, no more errors but still can't control, i think thats because the key only
						//goes back to the KeyManager class, but not to the Game class, I think, I have to come back to this
						//and figure this out, it seems important.
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////player animation stuffs///////////////////////
	protected boolean attackUp = false;
	protected boolean attackDown = false;
	protected boolean attackLeft = false;
	protected boolean attackRight = false;
	private Animation down, up, left, right, playerAttack;
	private BufferedImage curPos;
	////////////////////////////////////////////////////////////////////
	private int dmg = 2;
	
	///////////////Attack Timer//////////////////////////////////////////////////////
	private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;//1000ms = 1s, so basically you have to wait 800 ms to attack again
	////////////////////////////////////////////////////////////////////////////////////
	
	public Rectangle AttackHitBox = new Rectangle();
	
	
	public Player(Game game, float x, float y, GameState world) {//passing the game object here meaning when you make a game object in main, this class will have an effect
												//like, this class is part of the game class
												//when you make a game object, every class that has a game object will takes effect too
		
		super( game, x, y, MovingEntity.DEFAULT_CREATURE_WIDTH, MovingEntity.DEFAULT_CREATURE_HEIGHT, world);
		
		/////Manuelly setting the size of the rectangle, or the collision box//////////////
		hitBox.x = 16;
		hitBox.y = 32;
		hitBox.width = 32;
		hitBox.height = 32;
		
		
		//preparing image animation array
		up = new Animation(50, Assets.player_up);
		
		down = new Animation(50, Assets.player_down);
		
		left = new Animation(50, Assets.player_left);
		
		right = new Animation(50, Assets.player_right);
		
		playerAttack = new Animation(100, Assets.attAnimation);
		
	}

	@Override
	public void tick() {
		
		//Animations
		down.tick();
		up.tick();
		left.tick();
		right.tick();
		////////////
		
		if(attackUp || attackDown || attackLeft || attackRight)
		{
			playerAttack.tick();
		}
		
		getInput();//get keyboard movement
		move();//changing x and y 
		game.getGameCamera().centerOnEntity(this);
		
		
		
		AttacksAnimation();//drawing attack hitBox
		
		//System.out.println(this.health);
		
		if(this.health<0)
		{
			
			State.setState(game.getGameOver());
		}
		
		
	}
	
	private void AttacksAnimation() {
		
		//this is how much you have to wait before the next attack can occur
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		
		
		if(attackTimer < attackCooldown)
		return;
		
		
		
		Rectangle PlayerHitbox = getCollisionHitBox(0, 0);//PlayerHitbox is the HitBox of an entity
		
		
		int AttackHitBoxSize = 20;//size of the attack rectangle
		AttackHitBox.width = AttackHitBoxSize;
		AttackHitBox.height = AttackHitBoxSize;
		
		
		
		if(game.getKeyManager().pressedKey.contains(KeyEvent.VK_UP)) {
			AttackHitBox.x = PlayerHitbox.x + PlayerHitbox.width /2 - AttackHitBoxSize / 2;
			AttackHitBox.y = PlayerHitbox.y - AttackHitBoxSize;
			attackUp = true;
			
		}
		
		else if(game.getKeyManager().pressedKey.contains(KeyEvent.VK_DOWN)) {
			AttackHitBox.x = PlayerHitbox.x + PlayerHitbox.width /2 - AttackHitBoxSize / 2;
			AttackHitBox.y = PlayerHitbox.y + PlayerHitbox.height;
			attackDown = true;
		}
		
		else if(game.getKeyManager().pressedKey.contains(KeyEvent.VK_LEFT)) {
			AttackHitBox.x = PlayerHitbox.x - AttackHitBoxSize;
			AttackHitBox.y = PlayerHitbox.y + PlayerHitbox.height / 2 - AttackHitBoxSize / 2;
			attackLeft = true;
		}
		
		else if(game.getKeyManager().pressedKey.contains(KeyEvent.VK_RIGHT)) {
			AttackHitBox.x = PlayerHitbox.x + PlayerHitbox.width;
			AttackHitBox.y = PlayerHitbox.y + PlayerHitbox.height / 2 - AttackHitBoxSize / 2;
			attackRight = true;
		}
		
		else {
			return;//if any of these keys are not pressed, then get out of this method
		}
		
		attackTimer = 0;

		for(Entity e : gameState.getWorld().getEntityManager().getEntities()) {
			
		//for each entity in the gameWorld
			
			if(e.getCollisionHitBox(0, 0).intersects(AttackHitBox))//attack hitbox collision with every Entity hitBox
			{
				e.hurt(dmg);
				
			}
		}
		
	}

	
	public void getInput() {
		
		xMove = 0;//without these two the image will start drifting
		yMove = 0;
		/*
		 * VERY IMPORTANT
		 * 
		 * You can't use a class just like this directly, because it has no effect on the Game class, bacause this keyboard class is not called in the GAME class, which inside main!!!!
		 * 1. making an instance of that class in the Game class, or the class that will be called in the Main
		 * 2.then somehow combine that class's object with the Game class, like putting it in the Game constructor, or put it in one of the methods inside the Game class
		 * BUT MAKE SURE YOU HAVE TO CALL THIS METHOD DIRECTLY IN THE MAIN, like game.start(), and keyboard manager is inside the start()
		 * 2.1.by combining that class's object and the Game class will now enable the Game class to regonize this new class
		 * 3.make a getter for that class, in this case the keyboardManager
		 * 4.then you can use the methods inside that class by accessing the getter from the Game class like here
		 * 
		if(k.pressedKey.contains(KeyEvent.VK_A))//when pressing the key does get added to the array but it is not changing
		{										//the coordinate of the player image
			System.out.println("pressing A");
			x-= 3;
		}
		*/
		
		if(game.getKeyManager().pressedKey.contains(KeyEvent.VK_W))
		{
			yMove = -speed;
			
		}
		if(game.getKeyManager().pressedKey.contains(KeyEvent.VK_S))
		{
			yMove = speed;
		
		}
		
		if(game.getKeyManager().pressedKey.contains(KeyEvent.VK_A))
		{
			xMove = -speed;
			
		}
		
		if(game.getKeyManager().pressedKey.contains(KeyEvent.VK_D))
		{
			xMove = speed;
		
		}
		
		if(game.getKeyManager().pressedKey.contains(KeyEvent.VK_ESCAPE))
		{
			System.exit(1);
		}
	}
	

	@Override
	public void render(Graphics g) {

		
		//draw healthBar
		if(this.health > 40)
		{
			g.setColor(Color.green);
			g.fillRect(100, 100, 100, 30);
			g.fillRect((int)(x - game.getGameCamera().getxOffset())-20, (int)(y - game.getGameCamera().getyOffset())-10, 100, 10);
			
		}
		else if(this.health > 30)
		{
			g.clearRect(100, 100, 100, 30);
			g.setColor(Color.green);
			g.fillRect(100, 100, 80, 30);
			g.fillRect((int)(x - game.getGameCamera().getxOffset())-20, (int)(y - game.getGameCamera().getyOffset())-10, 80, 10);
		}
		else if(this.health > 20)
		{
			g.clearRect(100, 100, 100, 30);
			g.setColor(Color.YELLOW);
			g.fillRect(100, 100, 60, 30);
			g.fillRect((int)(x - game.getGameCamera().getxOffset())-20, (int)(y - game.getGameCamera().getyOffset())-10, 60, 10);
		}
		else if(this.health > 10)
		{
			g.clearRect(100, 100, 100, 30);
			g.setColor(Color.YELLOW);
			g.fillRect(100, 100, 40, 30);
			g.fillRect((int)(x - game.getGameCamera().getxOffset())-20, (int)(y - game.getGameCamera().getyOffset())-10, 40, 10);
		}
		else if(this.health > 0)
		{
			g.clearRect(100, 100, 100, 30);
			g.setColor(Color.RED);
			g.fillRect(100, 100, 20, 30);
			g.fillRect((int)(x - game.getGameCamera().getxOffset())-20, (int)(y - game.getGameCamera().getyOffset())-10, 20, 10);
		}
		else if(this.health < 0)
		{
			g.clearRect(100, 100, 100, 30);
			
		}
		
		
		////draw player///////////////////////////////////////////////////
		g.drawImage(getCurrentAnimationFrame(), (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()), width, height, null);//drawImage() takes in integer, not float
														//the .drawImage() method will draw the image at (x,y) coordinate specified
														//this x and y are from the Entity class
		
		
		//draw attack animation
		if(attackUp == true)
		{
			g.drawImage(getAttackFrame(), (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()-40), width, height, null);
			
			if(playerAttack.getCurrentFrame()==Assets.attAnimation[4])
			{
				attackUp = false;
				playerAttack.setIndex(0);
			}
		}
		
		else if(attackDown == true)
		{
			
			g.drawImage(getAttackFrame(), (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()+40), width, height, null);
			
			if(playerAttack.getCurrentFrame()==Assets.attAnimation[4])
			{
			
				attackDown = false;
				playerAttack.setIndex(0);
			}
		}
		
		else if(attackLeft == true)
		{
			g.drawImage(getAttackFrame(), (int)(x - game.getGameCamera().getxOffset()-40), (int)(y - game.getGameCamera().getyOffset()), width, height, null);
			
			if(playerAttack.getCurrentFrame()==Assets.attAnimation[4])
			{
				attackLeft = false;
				playerAttack.setIndex(0);
			}
		}
		
		else if(attackRight == true)
		{
			g.drawImage(getAttackFrame(), (int)(x - game.getGameCamera().getxOffset()+40), (int)(y - game.getGameCamera().getyOffset()), width, height, null);
			
			if(playerAttack.getCurrentFrame()==Assets.attAnimation[4])
			{
				attackRight = false;
				playerAttack.setIndex(0);
			}
		}

		/*
		g.setColor(Color.red);
		g.fillRect((int)(x + hitBox.x - game.getGameCamera().getxOffset()), //have to add x here to make the hit-box on top of my player, subtracting is to get the proper position on the screen
				   (int)(y + hitBox.y - game.getGameCamera().getyOffset()),//have to add y here to make the hit-box on top of my player
				    hitBox.width, 
				    hitBox.height
				    );
		*/
	}
	
	//////////////////return attack animation////////////////////////
	private BufferedImage getAttackFrame() {
		if(attackUp || attackDown || attackLeft || attackRight)
		{
			return playerAttack.getCurrentFrame();
		}
		else
			return null;
	}
	///////////////////////////////////////////////////////////////////
	
	
	////////////////////////Return wallking Animation/////////////////
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			curPos = Assets.player_left[0];
			return left.getCurrentFrame();
		}
		 if(xMove > 0)
		{
			 curPos = Assets.player_right[0];
			return right.getCurrentFrame();
		}
		 if(yMove < 0) {
			 curPos = Assets.player_up[0];
			return up.getCurrentFrame();
		}
		 if(yMove > 0) {
			 curPos = Assets.player_down[0];
			 return down.getCurrentFrame();
		 }
		else {
			return curPos;
		}
	}
////////////////////////////////////////////////////////////////////////////
	


	public Rectangle getAr() {
		return AttackHitBox;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public int getDmg() {
		return dmg;
	}
	
	
	
	
	

}
