package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTING
	final int originalTileSize=16; //16x16 tile
	final int scale = 3 ;
	
	public final int tileSize = originalTileSize * scale; //48*48
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;  //960pix
	public final int screenHeight = tileSize * maxScreenRow; //576pix
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	public final int maxMap = 10;
	public int currentMap = 0;
	
	//Fullscreen
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	
	//FPS
	int FPS=60;
	
	//GAME STATE
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int titleState = 3;
	
	
	//System
	Sound music = new Sound();
	Sound se = new Sound();
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	public UI ui = new UI(this);
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	
	//PLayers
	public Player player = new Player(this,keyH);	
	
	public GamePanel(){
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void setupGame() {
		
		playMusic(0);
		gameState = titleState;
		
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)tempScreen.getGraphics();
		
		setFullScreen();
	}
	
	public void setFullScreen() {
		
		//Get Local Screen Device
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		//GET FULL SCREEN WIDTH AND HEIGHT
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; //0.01666 
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update();
			 
			

			drawToTempScreen();
			drawToScreen();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void update() {
		
		if(gameState == playState) {
			player.update();
		}
		if(gameState == pauseState) {
			
		}
		
	}
	
	public void drawToTempScreen(){
		
		//Title SCreen
				if(gameState == titleState) {
					ui.draw(g2);
				}
				//Others
				else {
					//Tile
					tileM.draw(g2);
					
					//Player
					player.draw(g2);
					
					//UI
					ui.draw(g2);
				}
		
	}
	
	public void drawToScreen() {
		
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.Play();
		music.Loop();
	}
	
	public void stopMusic() {
		music.Stop();
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.Play();
	}
	
}




