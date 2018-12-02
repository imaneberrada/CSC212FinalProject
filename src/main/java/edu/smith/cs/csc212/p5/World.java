package edu.smith.cs.csc212.p5;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.glass.events.MouseEvent;

import me.jjfoley.gfx.GFX;

public class World extends GFX {
	 
	public String fileName = "src/main/Images/SpaceInvaders.png";
	
	Player Player = new Player();
	//Bullet Bullet = new Bullet();
	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Bullet> deletedBullets = new ArrayList<Bullet>();
	
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
		
		if (( Player.x >= 35 ) && (left) ) {
			Player.x -= 15;
		} else if (( Player.x <= 412 ) && ( right ) ) {
			Player.x += 15;
		}
		// TODO add mouse click here  
		boolean shoot = this.processKey(KeyEvent.VK_SPACE) || this.processKey(KeyEvent.VK_UP) ;//|| (this.processKey(MouseEvent.CLICK) );//&& this.processKey(KeyEvent.KEY_RELEASED));
		
		
		
		//Bullet.shot = shoot;
		
		//bullets.add(Bullet);
		if (shoot) {
			System.out.println("shooting");
			Bullet Bullet1 = new Bullet();
			System.out.println("new bullet");
			Bullet1.time = 0;
			Bullet1.shot = shoot;
			bullets.add(Bullet1);
		}
		
		for (Bullet Bullet1: bullets) {
			
			if (Bullet1.time == 0) {
				Bullet1.x = Player.x;
			}
			System.out.println("time is: " + Bullet1.time);
			Bullet1.time++;
			
		}
	}

	
	
	@Override
	public void draw(Graphics2D g) {
		ImagePanel image = new ImagePanel(fileName);
		image.paintComponent(g);
		
		Player.draw(g);
		
		for (Bullet Bullet1: bullets) {
			System.out.println("time: " + Bullet1.time);
			if (Bullet1.y <= 0) {
				deletedBullets.add(Bullet1);
			}
			Bullet1.draw(g);
		}
		
		for(Bullet Bullet1: deletedBullets) {
			bullets.remove(Bullet1);
		}
		
		while (true) {
			if (deletedBullets.size() ==0) {
				break; 	
			}
			Bullet bullet = deletedBullets.get(0);
			deletedBullets.remove(bullet);
		
		
	}

}
}
