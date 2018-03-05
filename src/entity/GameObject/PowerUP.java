package entity.GameObject;

import java.awt.Graphics;

import Core.Game;
import Graphics.Assets;
import State.GameState;
import entity.Entity;
import entity.MovingEntity.Player;
import tiles.Tile;

public class PowerUP extends Entity {
	
	public GameState gameState;
	private Player player;
	private boolean attackUP = true;
	
	
	public PowerUP(Game game, float x, float y, GameState gameState, Player player) {
		super(game, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT , gameState);
		this.gameState = gameState;
		this.player = player;
		
		hitBox.x = 10;
		hitBox.y = (int)(height / 1.5f);
		hitBox.width = width - 20;
		hitBox.height = (int)(height - height / 1.5f);
		
		
	}

	

	
	@Override
	public void tick() {
		
		
		if(attackUP == true)
		{
			power();
		}
		
		this.setHealth(1);
		
		
	}
	
	
	public void power() {
		
		if(this.getCollisionHitBox(0, 0).intersects(gameState.getPlayer().getAr()))
		{
			
			player.setDmg(player.getDmg()+2);
			attackUP = false;
		}
	
		
	}
	
	
	

	@Override
	public void render(Graphics g) {

		g.drawImage(Assets.powerUP, (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()), width, height, null);//have to cast it to int or else it doesn't let me use this function
		
	}



}
