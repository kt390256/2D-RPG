package entity.GameObject;

import java.awt.Graphics;

import Core.Game;
import Graphics.Assets;
import State.GameState;
import entity.Entity;
import entity.MovingEntity.Player;
import tiles.Tile;

public class Tree extends Entity{
	
	public GameState gameState;
	
	public Tree(Game game, float x, float y, GameState gameState) {
		super(game, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2, gameState);
		
		
		
		hitBox.x = 10;
		hitBox.y = (int)(height / 1.5f);
		hitBox.width = width - 20;
		hitBox.height = (int)(height - height / 1.5f);
		
		
	}

	
	
	
	@Override
	public void tick() {
		

		
	}
	


	@Override
	public void render(Graphics g) {

		g.drawImage(Assets.tree, (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()), width, height, null);//have to cast it to int or else it doesn't let me use this function
		
	}



}
