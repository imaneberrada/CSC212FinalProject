package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.glass.events.MouseEvent;

import me.jjfoley.gfx.GFX;

public class World extends GFX {
	 
	public String fileName = "src/main/Images/SpaceInvaders.png";
	
	Player Player = new Player();
	
	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Bullet> removeBullets = new ArrayList<Bullet>();
	
	static List<Alien> aliens = new ArrayList<Alien>();
	List<Alien> removeAliens = new ArrayList<Alien>();
	
	BufferedImage backg;
	
	public static boolean moveAliensRight = true;
	public static boolean moveAliensLeft = false;

	public World() throws IOException {
		backg = ImageIO.read(new File(fileName));
		
		// create 5 columns and 5 rows of aliens!
		for ( int column = 0; column < 5; column++ ) {
			for ( int row = 1; row <5; row++ ) {
				Alien Alien1 = new Alien( row, column );
				Alien1.x = 10+ column*70;
				Alien1.y = ( row-1 )*50+20;
				aliens.add(Alien1);
				
			}
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		// This is where our game will be displayed
		GFX app = new World();
		app.start();
	}

	@Override
	public void update(double secondsSinceLastUpdate) {
		boolean left = this.isKeyDown(KeyEvent.VK_A) || this.isKeyDown(KeyEvent.VK_LEFT);
		boolean right = this.isKeyDown(KeyEvent.VK_D) || this.isKeyDown(KeyEvent.VK_RIGHT);
		
		if (( Player.x >= 35 ) && (left) ) {
			Player.x -= 15;
		} else if (( Player.x <= 412 ) && ( right ) ) {
			Player.x += 15;
		}
		// TODO add mouse click here  
		boolean shoot = this.processKey(KeyEvent.VK_SPACE) || this.processKey(KeyEvent.VK_UP) ;//|| (this.processKey(MouseEvent.CLICK) );//&& this.processKey(KeyEvent.KEY_RELEASED));
		
	
		if (shoot) {
			//System.out.println("shooting");
			Bullet Bullet1 = new Bullet(Player.x,"player");
			//System.out.println("new bullet");
			Bullet1.shot = shoot;
			bullets.add(Bullet1);
		}
		
		// look at all of the aliens. then, look at all bullets.
		// if a bullet gets in the rectangular area of an alien, plan to delete the bullet and alien.
		for ( Bullet bullet : bullets ) {
		for ( Alien Alien1 : aliens ) {
			 	if (Alien1.getRectangle().contains(bullet.x, bullet.y)) {
					Alien1.shot = true;
					removeBullets.add(bullet);
					removeAliens.add(Alien1);					
					
				}
				
			}	
		}

		// delete the bullets and aliens so they aren't drawn
		bullets.removeAll(removeBullets);
		removeBullets.removeAll(removeBullets);
		aliens.removeAll(removeAliens);
		removeAliens.removeAll(removeAliens);
				
		
		// if the aliens reach the left edge, stop moving left and start moving right
		// if they reach the right edge, start moving left
		// move down when an edge is reached!
		for( Alien Alien1 : aliens ) {
			if ( ( Alien1.x <= (10)) ) {
				moveAliensLeft = false;
				moveAliensRight = true;
				for( Alien Alien2 : aliens ) {
					Alien2.y+=10;
				}
				break;
			}
			if ( Alien1.x >= (450) ) {
				moveAliensRight = false;
				moveAliensLeft = true;
				for( Alien Alien2 : aliens ) {
					Alien2.y+=10;
				}
				break;
			}
		}
		
		
	
	}	
	
	@Override
	public void draw(Graphics2D g) {
		
		int centerX = (this.getWidth() - backg.getWidth()) / 2;
		g.drawImage(backg, centerX, 0, null);
		
		
		// draw player
		for ( Alien Alien1 : aliens ) {
			Alien1.draw(g);
		}
		
		Player.draw(g);
		
		// draw player bullets
		for ( Bullet Bullet1 :  bullets ) {

				Bullet1.draw(g);
				
				while(true) {
					
			    	Bullet1.y -= 10 ;
			    	
			    	if (Bullet1.y >=0 ) {
			    		break;
			    	}	
			    
			}
		}
	}
	
}

