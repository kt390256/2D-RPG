package tiles;

import Graphics.Assets;

public class WaterTile extends Tile{
	
	public WaterTile(int id)
	{
		super(Assets.water, id);
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}
