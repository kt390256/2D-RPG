package entity.GameObject;

import java.awt.Graphics;
import Graphics.Assets;

import Core.Game;
import State.GameState;
import entity.Entity;
import tiles.Tile;

public class Boss2 extends Entity{

	public Boss2(Game game, float x, float y, GameState gameState) {
		super(game, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, gameState);
		this.health = 500;

		hitBox.x = 10;
		hitBox.y = (int)(height / 1.5f);
		hitBox.width = width - 20;
		hitBox.height = (int)(height - height / 1.5f);
	}

	float oldX = x;
	int increment = 0;
	
	@Override
	public void tick() {

		increment ++;
		backAndForth();
		
		//object patroling logic
				/*increment starts with value 0, then every time the tick() method is called
				 * then increment's value goes up by 1
				 * if increment is less than 100, keep adding, the object keeps moving
				 * 
				 * when increment reaches 100 and go beyond, object starts to move back and its x coordinate is decreasing
				 * when x has finally become the same as the oldX(the starting position)
				 * increment = 0, and the game loop calls the tick method again
				 * 
				 */
	}
	
public void backAndForth() {
		
		if(increment<100)
		{
			x++;
		}
		else if(increment>100)
		{
			x--;
			
			if(x==oldX)
			{
				increment = 0;//reset increment
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.boss2, (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()), width, height, null);
		
		
	}



}
