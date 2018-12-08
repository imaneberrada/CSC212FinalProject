package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.glass.events.MouseEvent;

import me.jjfoley.gfx.GFX;

public class World extends GFX {
	 
	public String fileName = "src/main/Images/SpaceInvaders.png";
	
	static int points = 0;
	static int num_lives = 3;
	
	//Boolean for player drawing/removal
	public static boolean removePlayer = false;
	
	public static final int SPEED = 10;
	Player Player = new Player();
	
	List<Defense> bunkers = new ArrayList<Defense>();

	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Bullet> removeBullets = new ArrayList<Bullet>();
	
	List<Bullet> shots = new ArrayList<Bullet>();
	List<Bullet> removeShots = new ArrayList<Bullet>();
		
	static List<Alien> aliens = new ArrayList<Alien>();
	List<Alien> removeAliens = new ArrayList<Alien>();
	
	BufferedImage backg;
	int shootDelay;
	
	public static boolean moveAliensRight = true;
	public static boolean moveAliensLeft = false;

	public static boolean finalState = false; 
	
	public World() throws IOException {
		backg = ImageIO.read(new File(fileName));
		
		// create 5 columns and 5 rows of aliens!
		for ( int column = 0; column < 5; column++ ) {
			for ( int row = 1; row <5; row++ ) {
				Alien Alien1 = new Alien( row, column);
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
		
		//Player moves 
		if (( Player.x >= 35 ) && (left) ) {
			Player.x -= 10;
		} else if (( Player.x <= 412 ) && ( right ) ) {
			Player.x += 10;
		}
		
		//aliens shoot with the SAME probability if size of aliens is greater than 5
		for (Alien Alien1 : aliens) {
			
				Random rand = new Random();
				int t = rand.nextInt(5000);
				if (t <= 10 ) {
				Bullet shot1 = new Bullet(Alien1.x, Alien1);
				shot1.y = Alien1.y;
				shots.add(shot1);
				}
		}
		
		// TODO add mouse click here  
		boolean shoot = this.processKey(KeyEvent.VK_SPACE) || this.processKey(KeyEvent.VK_UP) ;//|| (this.processKey(MouseEvent.CLICK) );//&& this.processKey(KeyEvent.KEY_RELEASED));
		
		shootDelay -= 1;
		if (shoot && shootDelay <= 0) {
			//System.out.println("shooting");
			Bullet Bullet1 = new Bullet(Player.x, Player);
			//System.out.println("new bullet");
			Bullet1.shot = shoot;
			bullets.add(Bullet1);
			shootDelay = 20;
		}
		
		//QUESTION: SWITCH OR NOT?
		// look at all of the bullets. then, look at all the aliens.
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
		
		for( Bullet bullet : bullets ) {
			for ( Defense bunker : bunkers ) {
				if ( bunker.rectangle.contains(bullet.x,bullet.y-36)) {  
					Rectangle2D bulletR = new Rectangle2D.Double(bullet.x, bullet.y-36, 2, SPEED+20);
					for( int i = bunker.pixels.size()-1; i>= 0; i-- ) {
						if( bulletR.intersects(bunker.pixels.get(i).rectangle)) {
							Defense.removePixels.add(bunker.pixels.get(i));
							removeBullets.add(bullet);
							break; 
						}
					}
				}
				bunker.pixels.removeAll(Defense.removePixels);
			}
		}
		
		for( Bullet shot : shots ) {
			for ( Defense bunker : bunkers ) {
				if ( bunker.rectangle.contains(shot.x-15,shot.y+25)) {
					for ( Pixel pixel1: bunker.pixels ) {
						if( pixel1.rectangle.contains(shot.x-15,shot.y+25)) {
							Defense.removePixels.add(pixel1);
							removeShots.add(shot);
						}
					}
				}
				bunker.pixels.removeAll(Defense.removePixels);
			}
			//Player disappears when shot by an alien
			if (Player.getArc().contains(shot.x-15,shot.y+25)) {
				removePlayer = true;
				removeShots.add(shot);
				finalState = true;
			}
		}
		
		Defense.removePixels.clear();
		bullets.removeAll(removeBullets);
		shots.removeAll(removeShots);
		removeBullets.removeAll(removeBullets);
		removeShots.removeAll(removeShots);
		
		
		
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
					
			    	Bullet1.y -= SPEED ;
			    	
			    	if (Bullet1.y >=0 ) {
			    		break;
			    	}	
			    
			}
		}
		
		shots.removeAll(removeShots);
		removeShots.removeAll(removeShots);
		
		
		// draw alien's bullets
		for (Bullet shot: shots) {			
			while (true) {
				shot.y += 3;
				shot.draw(g);
				if (shot.y >= 500) {
					removeShots.add(shot);
					
				}
					break;
				
			}
			
		}
		//removes shots that are no longer on the screen
		//shots.removeAll(removeShots);
		//removeShots.removeAll(removeShots);
		
		if (aliens.isEmpty() ) {
			g.setColor(Color.green);
			g.drawString("YOU WON", 210, 250);
			
			
		}
		else if ( finalState && aliens.isEmpty()==false) {
			g.setColor(Color.red);
			g.drawString("YOU LOST", 210, 250);
			
		} 
		else {
			//ADD CASE: all aliens disappeared from the screen/ got to the bunkers
		}
	}

	
}

