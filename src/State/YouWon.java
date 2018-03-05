package State;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import Core.Game;

public class YouWon extends State{

	private Game game;
	
	Image back = new ImageIcon(getClass().getResource("/texture/won.gif")).getImage();//this is how you import gif pic
	
	
	public YouWon(Game game) {
		
		super(game);
		this.game = game;
		
		
	}

	@Override
	public void tick() {


		
		
		if(game.getMouseManager().isLeftPressed() )
		{
			
			game.score = 0;
			game.level = 1;
			State.setState(new GameState(game));
			
		}
		else if( game.getMouseManager().isRightPressed())
		{
			System.exit(1);
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(back, 0, 0, null);
		
		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.BLACK);
		g.drawString("The darkness had faded, and you have survived.",  100, 280);
		g.drawString("Left click to start over, or Right click to exit the Game", 100, 380);
		g.drawString("You final score is : " + GameState.GScore, 80, 50);
		
		
	}

}
