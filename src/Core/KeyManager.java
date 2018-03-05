package Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyManager implements KeyListener{

	
	
	public ArrayList<Integer> pressedKey ;
	
	
	
	public KeyManager() {
		
		pressedKey = new ArrayList<Integer>();
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		
		
		if(!pressedKey.contains(e.getKeyCode()))//if the current pressed key is not in the array list
		{                             //contains() will seeks for stuff that is inside the array list
			pressedKey.add(new Integer(e.getKeyCode()));//then add that new key(s) to the array list
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
		pressedKey.remove(new Integer(e.getKeyCode()));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	

}
