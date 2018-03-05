package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//Static stuffs here
	public static Tile[] tiles = new Tile[20];//notices that the type of this array is a class, so this array is to put classes in there
	public static Tile grassTile = new GrassTile(0);//already generated
	public static Tile waterTile = new WaterTile(1);//already generated
	public static Tile rockTile = new RockTile(2);//already generated
	public static Tile lavaTile = new LavaTile(3);//already generated
	
	
	///////Class stuffs here////////////////
	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;//"this" means this current object, or this current constructor
		
		//System.out.println(this);
		
		//tiles[0] = this grass tile class
		//if you pass 0 inside the GrassTile constructor for example, then 0 will be passed inside the Tile constructor
		//but because right now the 0 is being passed inside the grass tile class, so inside the tiles array, the first 
		//element is going to be "this" grass tile class
	}
	
	public void tick() {
		
		
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void render(Graphics g, int x, int y) {
		
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
		
	}

	public int getId() {
		return id;
	}
	
	
}
