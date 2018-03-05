package State;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Core.Display;
import Core.Game;

import Graphics.Assets;

public class MenuState extends State{

	
	
	
	//private Rectangle playButton = new Rectangle(game.getWidth()+300, game.getHeight()/2 - 100, 100 , 50);
	//private Rectangle exit = new Rectangle(game.getWidth()+100, game.getHeight()/2 , 100 , 50);
	
	
	Image back = new ImageIcon(getClass().getResource("/texture/back.gif")).getImage();//this is how you import gif pic
	
	
	public MenuState(Game game) {
		
		super(game);
		
	}
	
	
	
	@Override
	public void tick() {
		
		//System.out.println(System.currentTimeMillis());
		
		if(game.getMouseManager().isLeftPressed())
		{
			State.setState(new GameState(game));
		}
		
		else if(game.getMouseManager().isRightPressed())
		{
			System.exit(1);
		}
		
		
		
		
	}

	@Override
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;
		
		
		g.drawImage(back, 0, 0, null);
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.GRAY);
		g.drawString("Apocalypse : The Broken World",  100, 280);
		
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g2d.setFont(fnt1);
		g.drawString("Single Player(Left Click)", 100, 435);
		//g2d.draw(playButton);
		
		g.drawString("Exit Game(Right Click)", 100, 535);
		//g2d.draw(exit);
		
		
	}

}
