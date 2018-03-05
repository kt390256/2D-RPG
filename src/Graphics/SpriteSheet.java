package Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		
		this.sheet = sheet;
	
	}
	
	
	public BufferedImage crop(int x, int y, int width, int height) {
		
		
		//Few things
		//X and Y are the coordinate ON THE SPRITE SHEET
		//width and height ARE the width and height of the sub-image
		return sheet.getSubimage(x, y, width, height);//this will return a sub-image inside the sprite sheet
		
		
	}
	
	
	
	
	
}
