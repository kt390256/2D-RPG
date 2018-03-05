package State;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import Core.Game;

public class GameOver extends State{

	
	
	Image back = new ImageIcon(getClass().getResource("/texture/lose.gif")).getImage();//this is how you import gif pic
	
	
	public GameOver(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {


		if(game.getMouseManager().isLeftPressed() )
		{
			game.getGameState().game.score = 0;
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
		g.setColor(Color.WHITE);
		g.drawString("You Died",  100, 280);
		g.drawString("Left click to start over, or Right click to exit the Game", 100, 380);
		
		
	}

}
