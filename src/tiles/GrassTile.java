package tiles;


import Graphics.Assets;

public class GrassTile extends Tile{

	public GrassTile(int id) {
		super(Assets.grass, id);
		
	}

	@Override
	public boolean isSolid() {
		return false;
	}
	
}
