package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	
	public static BufferedImage loadImage(String filePath) {
		
		try {
			return ImageIO.read(ImageLoader.class.getResource(filePath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);//if iamges dont print then it would be no point to run the game, so might as well to just exit the game
		}
		
		return null;
		
	}
	
	

}
