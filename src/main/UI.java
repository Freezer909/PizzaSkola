package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import entity.Player;

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
			g2.drawString("NAUDA = "+ Player.Nauda+"€", 30, 50);
			if(gp.keyH.checkDrawTime == true) {
	            g2.setFont(arial_40.deriveFont(20F));
	            g2.setColor(Color.white);
	            
	            int x = 10;
	            int y = 400;
	            int lineHeight = 25;

	            g2.drawString("WorldX: " + gp.player.worldX, x, y);
	            g2.drawString("WorldY: " + gp.player.worldY, x, y + lineHeight);
	            g2.drawString("Col: " + (gp.player.worldX / gp.tileSize), x, y + lineHeight * 2);
	            g2.drawString("Row: " + (gp.player.worldY / gp.tileSize), x, y + lineHeight * 3);
	            
	            
	        }
			
		}
		//PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		//OptionsPanel
		if(gp.gameState == gp.optionsState) {
	        drawOptionsPanel();
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

	public void drawOptionsPanel() {
	    

	    int x = gp.tileSize * 2;
	    int y = gp.tileSize * 6;
	    int width = gp.screenWidth - (gp.tileSize * 4);
	    int height = gp.tileSize * 5;
	    
	    // Draw the background 
	    drawSubWindow(x, y, width, height);
	    

	    g2.setColor(Color.white);
	    g2.setFont(g2.getFont().deriveFont(32F));

	    // Title
	    String text = "Izvēlies lomu:";
	    g2.drawString(text, x + gp.tileSize, y + gp.tileSize);


	    text = "Darbinieks";
	    int textX = x + gp.tileSize * 2;
	    int textY = (int) (y + gp.tileSize * 2.5);
	    g2.drawString(text, textX, textY);
	    if(commandNum == 0) {
	        g2.drawString(">", textX - 25, textY);
	    }


	    text = "Klients";
	    textY += gp.tileSize;
	    g2.drawString(text, textX, textY);
	    if(commandNum == 1) {
	        g2.drawString(">", textX - 25, textY);
	    }
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
	    
	    Color c = new Color(0, 0, 0, 210);
	    g2.setColor(c);
	    g2.fillRoundRect(x, y, width, height, 35, 35);
	    

	    g2.setColor(Color.white);
	    g2.setStroke(new java.awt.BasicStroke(5));
	    g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
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
