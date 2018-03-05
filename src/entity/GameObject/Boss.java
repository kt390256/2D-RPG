package entity.GameObject;

import java.awt.Graphics;
import Graphics.Assets;

import Core.Game;
import State.GameState;
import entity.Entity;
import tiles.Tile;

public class Boss extends Entity{

	public Boss(Game game, float x, float y, GameState gameState) {
		super(game, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, gameState);

		this.health = 500;
		hitBox.x = 10;
		hitBox.y = (int)(height / 1.5f);
		hitBox.width = width - 20;
		hitBox.height = (int)(height - height / 1.5f);
	}

	float oldY = y;
	int increment = 0;
	
	@Override
	public void tick() {

		increment ++;
		upAndDown();
		

	}
	
public void upAndDown() {
		
		if(increment<100)
		{
			y++;
		}
		else if(increment>100)
		{
			y--;
			
			if(y==oldY)
			{
				increment = 0;//reset increment
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.boss, (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()), width, height, null);
		
		
	}



}
