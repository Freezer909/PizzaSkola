package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40;
	BufferedImage titleImage;
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		try {
	        // Make sure the path matches where you put the file in your project (e.g., res folder)
	        titleImage = ImageIO.read(getClass().getResourceAsStream("/Images/TitleScreenPlaceHolder.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//PLAy STATE
		if(gp.gameState == gp.playState) {
			//YEs yes play state
		}
		//PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
	}
	
	private void drawTitleScreen() {
		
		if (titleImage != null) {
	   
	        g2.drawImage(titleImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
	    }
		
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
	    String text = "Picērijas simulātors (ifykyk)";
	    int x = getXforCenteredText(text);
	    int y = gp.tileSize * 2;

	    // Shadow for better readability (Optional)
	    g2.setColor(Color.black);
	    g2.drawString(text, x + 5, y + 5);

	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);
	    
	    //MENU
	    text = "Sākt spēli";
	    x = getXforCenteredText(text);
	    y = gp.tileSize * 7;
	    g2.setColor(Color.black);
	    g2.drawString(text, x + 5, y + 5);
	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);
	    if(commandNum == 0) {
	    	g2.drawString(">", x - gp.tileSize, y);
	    }
	    
	    text = "Iestatijumi";
	    x = getXforCenteredText(text);
	    y = (int) (gp.tileSize * 8.5);
	    g2.setColor(Color.black);
	    g2.drawString(text, x + 5, y + 5);
	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);
	    if(commandNum == 1) {
	    	g2.drawString(">", x - gp.tileSize, y);
	    }
	    
	    text = "Beigt";
	    x = getXforCenteredText(text);
	    y = gp.tileSize * 10;
	    g2.setColor(Color.black);
	    g2.drawString(text, x + 5, y + 5);
	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);
	    if(commandNum == 2) {
	    	g2.drawString(">", x - gp.tileSize, y);
	    }
	}

	public void drawPauseScreen() {
		
		String text = "NOPAUZĒTS";
		int y = gp.screenHeight/2;
		int x = getXforCenteredText(text);
		
		g2.drawString(text, x, y);
	}
	
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
				
	}
	
}
