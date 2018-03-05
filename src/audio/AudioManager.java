package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager {
	
	
	private AudioInputStream soundStream;
	private Clip clip;
	
	
	
	public AudioManager( String filePath) {
		
		
		try {
			soundStream = AudioSystem.getAudioInputStream(AudioManager.class.getResource(filePath));//loading file into memory 
			clip = AudioSystem.getClip();
			clip.open(soundStream);//using the Clip object to open the laoded file
			
		}catch(Exception e) {
			
			System.out.println("No audio music found");
			
		}
		
		
		
			Runnable r = new Runnable() {

				public void run() {
					while(true) {
						clip.start();//cue the music
						clip.loop(clip.LOOP_CONTINUOUSLY);
					}
				}
			};
			Thread thread = new Thread(r);
			thread.start();//cal the run()
		//end of if
		
	}
	
	
	public void play() {
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}

}
