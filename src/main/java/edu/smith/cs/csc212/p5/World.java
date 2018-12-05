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
	
	static int points = 0;
	
	Player Player = new Player();
	
	List<Defense> bunkers = new ArrayList<Defense>();

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
				Alien1.y = ( row-1 )*50+35;
				aliens.add(Alien1);
				
			}
		}
		
		Defense Bunker1 = new Defense(1);
		Defense Bunker2 = new Defense(2);
		Defense Bunker3 = new Defense(3);
		Defense Bunker4 = new Defense(4);
		
		bunkers.add(Bunker1);
		bunkers.add(Bunker2);
		bunkers.add(Bunker3);
		bunkers.add(Bunker4);
		
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
					if (Alien1.row == 3 || Alien1.row == 4 ) {
						points+=10;
					}
					if (Alien1.row == 1 || Alien1.row == 2 ) {
						points +=20;
					}
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
		
		// points counter at top of screen
		g.setColor(Color.white);
		g.setFont( new Font("Arial", Font.BOLD, 20));
		g.drawString("SCORE:", 20, 28);
		g.drawString(Integer.toString( points ), 110, 28);
		
		
		for ( Defense bunker : bunkers ) {
			bunker.draw(g);
		}
		
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

