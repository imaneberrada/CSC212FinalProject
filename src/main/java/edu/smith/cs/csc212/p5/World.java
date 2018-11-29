package edu.smith.cs.csc212.p5;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.glass.events.MouseEvent;

import me.jjfoley.gfx.GFX;

public class World extends GFX {
	 
	public String fileName = "src/main/Images/SpaceInvaders.png";
	
	Player Player = new Player();
	
	//ImagePanel class
	public class ImagePanel extends JPanel {

		public BufferedImage image;

		public ImagePanel(String fileName) {
			try {
				image = ImageIO.read(new File(fileName));
			} catch (IOException e) {
				System.exit(-1);
			}

		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}

	}
	
	public static void main(String[] args) {
		// This is where our game will be displayed
		GFX app = new World();
		app.start();

	}

	@Override
	public void update(double secondsSinceLastUpdate) {
		boolean left = this.processKey(KeyEvent.VK_A) || this.processKey(KeyEvent.VK_LEFT);
		boolean right = this.processKey(KeyEvent.VK_D) || this.processKey(KeyEvent.VK_RIGHT);
		
		if (( Player.x >= 8 ) && (left) ) {
			Player.x -= 15;
		} else if (( Player.x <= 412 ) && ( right ) ) {
			Player.x += 15;
		}
		// TODO add mouse click here  
		boolean shoot = this.processKey(KeyEvent.VK_SPACE) || this.processKey(KeyEvent.VK_UP) ;//|| (this.processKey(MouseEvent.CLICK) );//&& this.processKey(KeyEvent.KEY_RELEASED));
		
		if ( shoot ) {
			System.out.println("shooting!!!!!");
		}
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		ImagePanel image = new ImagePanel(fileName);
		image.paintComponent(g);
		
		Player.draw(g);
		
	}

}
