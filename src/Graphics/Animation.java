package Graphics;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed , index;
	private BufferedImage[] frames;
	private long lastTime, timer;
	
	
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
	}
	
	public void tick() {
		
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		
		if(timer > speed) {//if timer is greater than speed
			index++;       //then index goes up by 1
			timer = 0;     //and timer is now back to 0
			
			//reset
			if(index >= frames.length) 
				index = 0;
		}
		
	}
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public BufferedImage getCurrentFrame() {
		
		return frames[index];
		
	}
	
	
	
}
