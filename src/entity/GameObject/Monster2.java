package entity.GameObject;

import java.awt.Graphics;
import Graphics.Assets;

import Core.Game;
import State.GameState;
import entity.Entity;
import tiles.Tile;

public class Monster2 extends Entity{

	public Monster2(Game game, float x, float y, GameState gameState) {
		super(game, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, gameState);


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
	
public void upAndDown() {
		
		if(increment<130)
		{
			y++;
		}
		else if(increment>130)
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
		
		g.drawImage(Assets.monster2, (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()), width, height, null);
		
		
	}



}
