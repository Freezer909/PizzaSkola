package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/Sounds/trashgame_menuNEW.wav");
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e){
			
		}
		
	}
	
	public void Play() {
		
		clip.start();
		
	}
	
	public void Loop() {
		
		clip.loop(clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void Stop() {
		
		clip.stop();
		
	}
	
}
