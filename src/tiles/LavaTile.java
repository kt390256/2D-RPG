package tiles;

import Graphics.Assets;

public class LavaTile extends Tile{
	
	public LavaTile(int id)
	{
		super(Assets.lava, id);
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}
