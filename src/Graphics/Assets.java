package Graphics;

import java.awt.image.BufferedImage;

public class Assets {

	
	private static final int width = 32, height = 32;
	private static final int pmWidth = 64, pmHeight = 64;
	
	public static BufferedImage player, water, grass, stone, lava;
	public static BufferedImage tree, monster, monster2, monster3, monster4, monster5, boss, boss2, altar, powerUP;
	
	
	//public static BufferedImage attAnimation;
	
	
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] attAnimation;
	//this function will only be loaded once, and it will load everything
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/spritesheets.png"));
		SpriteSheet playerMovement = new SpriteSheet(ImageLoader.loadImage("/texture/playerMovement.png"));
		
		
		//Player
		//player = sheet.crop(width*1, height*32, width, height);//X and Y coordinates are on the sprite sheet
		
		//attack animation
		attAnimation = new BufferedImage[5];
		attAnimation[0] = sheet.crop(width*38, height*23, width, height); 
		attAnimation[1] = sheet.crop(width*39, height*23, width, height);
		attAnimation[2] = sheet.crop(width*40, height*23, width, height);
		attAnimation[3] = sheet.crop(width*41, height*23, width, height);
		attAnimation[4] = sheet.crop(width*42, height*23, width, height);
		
		
		
		
		//player movement animation//////////////////////////////////////////////////////////////////
		player_down = new BufferedImage[9];//my walking down has 9  so 9 there
		player_down[0] = playerMovement.crop(0        , pmHeight*2, pmWidth, pmHeight);
		player_down[1] = playerMovement.crop(pmWidth*1, pmHeight*2, pmWidth, pmHeight);
		player_down[2] = playerMovement.crop(pmWidth*2, pmHeight*2, pmWidth, pmHeight);
		player_down[3] = playerMovement.crop(pmWidth*3, pmHeight*2, pmWidth, pmHeight);
		player_down[4] = playerMovement.crop(pmWidth*4, pmHeight*2, pmWidth, pmHeight);
		player_down[5] = playerMovement.crop(pmWidth*5, pmHeight*2, pmWidth, pmHeight);
		player_down[6] = playerMovement.crop(pmWidth*6, pmHeight*2, pmWidth, pmHeight);
		player_down[7] = playerMovement.crop(pmWidth*7, pmHeight*2, pmWidth, pmHeight);
		player_down[8] = playerMovement.crop(pmWidth*8, pmHeight*2, pmWidth, pmHeight);
		
		
		player_up = new BufferedImage[9];//my walking down has 9  so 9 there
		player_up[0] = playerMovement.crop(0 ,        0, pmWidth, pmHeight);
		player_up[1] = playerMovement.crop(pmWidth*1, 0, pmWidth, pmHeight);
		player_up[2] = playerMovement.crop(pmWidth*2, 0, pmWidth, pmHeight);
		player_up[3] = playerMovement.crop(pmWidth*3, 0, pmWidth, pmHeight);
		player_up[4] = playerMovement.crop(pmWidth*4, 0, pmWidth, pmHeight);
		player_up[5] = playerMovement.crop(pmWidth*5, 0, pmWidth, pmHeight);
		player_up[6] = playerMovement.crop(pmWidth*6, 0, pmWidth, pmHeight);
		player_up[7] = playerMovement.crop(pmWidth*7, 0, pmWidth, pmHeight);
		player_up[8] = playerMovement.crop(pmWidth*8, 0, pmWidth, pmHeight);
		
		
		player_left = new BufferedImage[9];//my walking down has 9  so 9 there
		player_left[0] = playerMovement.crop(0        , pmHeight*1, pmWidth, pmHeight);
		player_left[1] = playerMovement.crop(pmWidth*1, pmHeight*1, pmWidth, pmHeight);
		player_left[2] = playerMovement.crop(pmWidth*2, pmHeight*1, pmWidth, pmHeight);
		player_left[3] = playerMovement.crop(pmWidth*3, pmHeight*1, pmWidth, pmHeight);
		player_left[4] = playerMovement.crop(pmWidth*4, pmHeight*1, pmWidth, pmHeight);
		player_left[5] = playerMovement.crop(pmWidth*5, pmHeight*1, pmWidth, pmHeight);
		player_left[6] = playerMovement.crop(pmWidth*6, pmHeight*1, pmWidth, pmHeight);
		player_left[7] = playerMovement.crop(pmWidth*7, pmHeight*1, pmWidth, pmHeight);
		player_left[8] = playerMovement.crop(pmWidth*8, pmHeight*1, pmWidth, pmHeight);
		
		
		player_right = new BufferedImage[9];//my walking down has 9  so 9 there
		player_right[0] = playerMovement.crop(0        , pmHeight*3, pmWidth, pmHeight);
		player_right[1] = playerMovement.crop(pmWidth*1, pmHeight*3, pmWidth, pmHeight);
		player_right[2] = playerMovement.crop(pmWidth*2, pmHeight*3, pmWidth, pmHeight);
		player_right[3] = playerMovement.crop(pmWidth*3, pmHeight*3, pmWidth, pmHeight);
		player_right[4] = playerMovement.crop(pmWidth*4, pmHeight*3, pmWidth, pmHeight);
		player_right[5] = playerMovement.crop(pmWidth*5, pmHeight*3, pmWidth, pmHeight);
		player_right[6] = playerMovement.crop(pmWidth*6, pmHeight*3, pmWidth, pmHeight);
		player_right[7] = playerMovement.crop(pmWidth*7, pmHeight*3, pmWidth, pmHeight);
		player_right[8] = playerMovement.crop(pmWidth*8, pmHeight*3, pmWidth, pmHeight);
		///////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		//Tiles
		water = sheet.crop(width*1, height*23, width, height);//lava
		grass = sheet.crop(width*0, height*5, width, height);//id 0
		stone = sheet.crop(width*3, height*14, width, height);
		lava = sheet.crop(width*0, height*6, width, height);
		
		
		//Entity
		altar = sheet.crop(width * 3, height * 0, width, height);
		tree = sheet.crop(width * 13, height * 13, width, height);
		powerUP = sheet.crop(width*58, height * 27, width, height);
		
		
		//monster
		monster = sheet.crop(width * 2, height * 1, width, height);
		monster2 = sheet.crop(width*5, height*1, width, height);
		monster3 = sheet.crop(width*1, height*32, width, height);
		boss = sheet.crop(width*1, height*27, width, height);
		boss2 = sheet.crop(width*3, height*27, width, height);
		//attack
		//attAnimation = sheet.crop(width, height*24, width, height);
		//attAnimation = attack.crop(0, 0, width, height);
		
	}
	
	
	
}
