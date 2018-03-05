package Core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener{

	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	public MouseManager(Game game) {
		
	}
	
	//Getters
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	////////////////////////////////////////////
	

	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1)//left click
		{
			leftPressed = true;
		}
		else if(e.getButton() == MouseEvent.BUTTON3)//right click
		{
			rightPressed = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(e.getButton()==MouseEvent.BUTTON1)//left click
		{
			leftPressed = false;
		}
		else if(e.getButton() == MouseEvent.BUTTON3)//right click
		{
			rightPressed = false;
		}		
	}
	
	
	
	
	

}
