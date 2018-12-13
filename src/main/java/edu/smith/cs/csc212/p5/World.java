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
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import com.sun.glass.events.MouseEvent;

import me.jjfoley.gfx.GFX;

public class World extends GFX {

	//Background image file
	public String fileName = "src/main/Images/SpaceInvaders.png";
	//Background image
	BufferedImage backg;
		
	//Points: used to track scores
	static int points = 0;
	
	//Player gets 3 lives that they can lose if they get shot by an alien
	static int num_lives = 3;

	//Lives: used to track lives
	LivesTracker lives = new LivesTracker();
	
	//t: used for the "game over" animation
	int t = 0;

	//timeSinceDeath: used in "player gets shot" animation
	static int timeSinceDeath = -1;

	//our player!
	Player Player = new Player();

	//mystery ship that passes through every once in a while
	MysteryShip mysteryShip = new MysteryShip();
	
	//Arraylist of bunkers
	List<Defense> bunkers = new ArrayList<Defense>();

	//Arraylist of player bullets
	List<Bullet> bullets = new ArrayList<Bullet>();
	//Arraylist of player bullets to be removed
	List<Bullet> removeBullets = new ArrayList<Bullet>();
		
	//Arraylist of alien bullets
	List<Bullet> shots = new ArrayList<Bullet>();
	//Arraylist of alien bullets to be removed
	List<Bullet> removeShots = new ArrayList<Bullet>();

	//bullet speed
	public static final int SPEED = 10;
		
	//Arraylist of aliens
	static List<Alien> aliens = new ArrayList<Alien>();
	//Arraylist of aliens to be removed
	List<Alien> removeAliens = new ArrayList<Alien>();
	
	//shoot delay
	int shootDelay;

	//Setting for alien movements
	public static boolean moveAliensRight = true;
	public static boolean moveAliensLeft = false;

	//Initialize state to NormalPlay
	State state = State.NormalPlay;
	
	//Boolean to draw/undraw player
	public static boolean removePlayer = false;
	
	public World() throws IOException {
		// Make background image in window
		backg = ImageIO.read(new File(fileName));

		// Create 5 columns and 5 rows of aliens, add to the list of aliens
		for ( int column = 0; column < 5; column++ ) {
			for ( int row = 1; row <5; row++ ) {
				Alien Alien1 = new Alien( row, column);
				Alien1.x = 10+ column*70;
				Alien1.y = ( row-1 )*50+35;
				aliens.add(Alien1);

			}
		}

		// Create 3 bunkers and add to the list of bunkers 
		Defense Bunker1 = new Defense(1);
		Defense Bunker2 = new Defense(2);
		Defense Bunker3 = new Defense(3);

		bunkers.add(Bunker1);
		bunkers.add(Bunker2);
		bunkers.add(Bunker3);

	}
	
	/**
	 * Cycle through each alien in the list of aliens. 
	 * When the number of lives decreases, aliens become more likely to shoot at the player.
	 * Move all aliens left or right until they reach an edge, in which case move down and switch directions. 
	 */
	public void aliensShootAndMove() {
		
		for (Alien Alien1 : aliens) {			
			Random rand = new Random();
			// The number of possibilities for this random number decreases when the player loses lives
			// This means when there are fewer lives, the alien is more likely to shoot
			int t = rand.nextInt(9000/(4-num_lives));
			if (t <= 10 ) {
				Bullet shot1 = new Bullet(Alien1.x, Alien1);
				shot1.y = Alien1.y;
				shots.add(shot1);
			}

			// If any of the aliens reach the left side of the window, switch directions and move down by 10
			if ( ( Alien1.x <= (10)) ) {
				moveAliensLeft = false;
				moveAliensRight = true;
				for( Alien Alien2 : aliens ) {
					Alien2.y+=10;
				}
				break;
			}

			// If any of the aliens reach the right side of the window, switch directions and move down by 10
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

	/**
	 * This method tracks player bullets. If a bullet hits anything, it must be deleted from the list of bullets.
	 * If a bullet hits an alien, then the alien must be removed. 
	 * If a bullet hits part of a bunker, it must remove the closest pixel. 
	 */
	public void shootAliensAndBunkers() {
		
		
		// Look at all of the bullets, then look at all the aliens. Then, look at all of the bunkers.
		for ( Bullet bullet : bullets ) {
			// See if the bullet hit the rectangular area of the mystery ship
			if( mysteryShip.getRectangle().contains(bullet.x, bullet.y)) {
				mysteryShip.shot = true;
				removeBullets.add(bullet);
				points+=50;
			}
				
			// If a bullet gets in the rectangular area of an alien, plan to delete the bullet and alien.
			for ( Alien Alien1 : aliens ) {
				if (Alien1.getRectangle().contains(bullet.x, bullet.y)) {
					Alien1.shot = true;
					removeBullets.add(bullet);
					removeAliens.add(Alien1);
					// If the player hits an alien from the bottom two rows, they get 10 points.
					if (Alien1.row == 3 || Alien1.row == 4 ) {
						points+=10;
					}
					// If the player hits an alien from the top two rows, they get 20 points.
					if (Alien1.row == 1 || Alien1.row == 2 ) {
						points +=20;
					}
				}
			}	

			//If the bullet enters the rectangular area of a bunker, check all of the pixels in the bunker.
			for ( Defense bunker : bunkers ) {
				if ( bunker.rectangle.contains(bullet.x,bullet.y-36)) {  
					// Make a rectangle for the bullet that predicts where the bullet will go so it won't skip over any pixels because of speed.
					Rectangle2D bulletR = new Rectangle2D.Double(bullet.x, bullet.y-36, 2, SPEED+20);
					// Look through the bunker pixels backwards so the lowest pixel that the bullet touches gets removed.
					for( int i = bunker.pixels.size()-1; i>= 0; i-- ) {
						if( bulletR.intersects(bunker.pixels.get(i).rectangle)) {
							Defense.removePixels.add(bunker.pixels.get(i));
							removeBullets.add(bullet);
							break; 
						}
					}
				}
				//Remove the hit bunker pixels
				bunker.pixels.removeAll(Defense.removePixels);
			}
		}

		// Delete the hit bullets and aliens so they aren't drawn again
		bullets.removeAll(removeBullets);
		removeBullets.removeAll(removeBullets);
		aliens.removeAll(removeAliens);
		removeAliens.removeAll(removeAliens);
	}



	public static void main(String[] args) throws IOException {
		// This is where our game will be displayed
		GFX app = new World();
		app.start();

	}

	@Override
	public void update(double secondsSinceLastUpdate) {

		// Clicking the "A" and left key will move the player to the left. Clicking the "D" and right key will move the player to the right.
		boolean left = this.isKeyDown(KeyEvent.VK_A) || this.isKeyDown(KeyEvent.VK_LEFT);
		boolean right = this.isKeyDown(KeyEvent.VK_D) || this.isKeyDown(KeyEvent.VK_RIGHT);

		// Player moves according to the keys pressed if it hasn't reached the edge of the screen
		if (( Player.x >= 35 ) && (left) ) {
			Player.x -= 10;
		} else if (( Player.x <= 412 ) && ( right ) ) {
			Player.x += 10;
		}

		// Call the method that makes the aliens shoot randomly and move side to side
		aliensShootAndMove();

		// Clicking the space bar or up key will make the player shoot upwards.
		boolean shoot = this.processKey(KeyEvent.VK_SPACE) || this.processKey(KeyEvent.VK_UP) ;

		// Establish a shoot delay so the player can't spam the window with bullets.
		// Only shoot when the shoot delay has finished and the player hasn't been shot recently
		shootDelay -= 1;
		if ( timeSinceDeath< 0 && shoot && shootDelay <= 0) {
			Bullet Bullet1 = new Bullet(Player.x, Player);
			Bullet1.shot = shoot;
			bullets.add(Bullet1);
			shootDelay = 20;
		}
		
		// Call the method that checks what the player's bullets hit
		shootAliensAndBunkers();

		// Check what the alien shots hit
		for( Bullet shot : shots ) {
			// If the alien shot enters the rectangle surrounding a bunker, 
			// Look through all of the bunker pixels and remove the one that the shot has hit.
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
			// If the shot enters the player's spaceship and the player hasn't been shot recently, decrease the number of remaining lives
			// finalState becomes true, so the game endings happen if needed
			// If the player has no lives left, remove it entirely
			if (timeSinceDeath<0 && Player.getArc().contains(shot.x-15,shot.y+25)) {
				num_lives -=1;
				if (num_lives <=0) {
					removePlayer = true;
				}

				removeShots.add(shot);
				state = State.PlayerLost;
			}
		}

		// Remove all of the bullets and shots necessary so they aren't drawn again
		Defense.removePixels.clear();
		bullets.removeAll(removeBullets);
		shots.removeAll(removeShots);
		removeBullets.removeAll(removeBullets);
		removeShots.removeAll(removeShots);

	}	

	@Override
	public void draw(Graphics2D g) {
		// Find the center of the window, and then draw the selected background image every time
		int centerX = (this.getWidth() - backg.getWidth()) / 2;
		g.drawImage(backg, centerX, 0, null);

		// Set a points counter at top of screen
		g.setColor(Color.white);
		g.setFont( new Font("Arial", Font.BOLD, 20));
		g.drawString("SCORE:", 20, 28);
		g.drawString(Integer.toString( points ), 110, 28);

		// Draw the lives tracker
		g.drawString("LIVES:", 310, 28);
		lives.draw(g);

		// Draw all of the bunkers
		for ( Defense bunker : bunkers ) {
			bunker.draw(g);
		}

		// Draw all of the aliens. 
		// If an alien has gotten to the bottom portion of the screen, aliensWon is true, which will cause one of the game endings
		for ( Alien Alien1 : aliens ) {
			Alien1.draw(g);
			if( Alien1.y >= 400 ) {
				state = State.AliensWon;
			}
		}
		
		// If enough time has passed, draw the mystery ship until it is shot or goes off the screen
		if ( MysteryShip.timer <= 600 ) {
			MysteryShip.timer++;
		} else if ( MysteryShip.timer > 600 && MysteryShip.shot == true ) {
			MysteryShip.timer = 0;
			mysteryShip.x = -80;
			MysteryShip.shot = false;
		}
		else if ( MysteryShip.timer > 600 && MysteryShip.shot != true ) {
			if ( mysteryShip.x <= 500 ) {
				mysteryShip.draw(g);
			} else {
				MysteryShip.timer = 0;
				mysteryShip.x = -80;
			}
			
		}
		
		// Draw the player depending on if it is in the animation sequence after getting shot.
		// If the player hasn't been shot, just draw like normal
		if( timeSinceDeath < 0 ) {
			Player.draw(g);
		} 
		// If the player has been shot, check the amount of time that has passed since the shot.
		// Then, draw a more transparent version of the player when more time has passed.
		else if( timeSinceDeath >= 50 ) {
			Player.noPlayer(g);
			timeSinceDeath++;
		} else if( timeSinceDeath >= 40 ) {
			Player.transparent3(g);
			timeSinceDeath++;
		} else if( timeSinceDeath >= 20 ) {
			Player.transparent2(g);
			timeSinceDeath++;
		} else if( timeSinceDeath >= 0 ) {
			Player.transparent1(g);
			timeSinceDeath++;
		}


		// Draw player bullets and make them move upwards with speed. 
		if( num_lives > 0 ) {
			for ( Bullet Bullet1 :  bullets ) {

				Bullet1.draw(g);

				while(true) {

					Bullet1.y -= SPEED ;
					if ( Bullet1.y<0 ) {
						removeBullets.add(Bullet1);
						break;
					}
					if (Bullet1.y >=0 ) {
						break;
					}

				}
			}

			// If a bullet has gone past the window, don't draw it next time
			bullets.removeAll(removeBullets);
			removeBullets.clear();

			// Make sure all alien shots that need to be removed aren't drawn
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



		}
		// Remove shots that are no longer on the screen
		shots.removeAll(removeShots);
		removeShots.removeAll(removeShots);

		// Displayed message depending on states: 
		
		switch(this.state) {
		case PlayerWon: //Player has won!
			g.setColor(Color.green);
			g.drawString("YOU WON", 210, 250);
			break;

		case PlayerLost: //Player was killed (ran out of lives)
			if (num_lives <= 0 && aliens.isEmpty()==false ) {
				
				if (t%50 >= 25) {
					g.setColor(Color.red);
					g.drawString("GAME OVER", 210, 250);
					t++;
				}
				else {
				
				g.setColor(Color.black);
				g.drawString("GAME OVER", 210, 250);
				t++;
				}
			}
			
			break;
		
		case AliensWon: //Aliens have gotten to the bottom of the window
			
			if (t%50 >= 25) {
				g.setColor(Color.red);
				g.drawString("GAME OVER", 210, 250);
				t++;
			}
			else {
			
			g.setColor(Color.black);
			g.drawString("GAME OVER", 210, 250);
			t++;
			}
		
			break;
			
		case PlayAgain:
			g.setColor(Color.white);
			g.fillRect(205, 220, 120, 40);
			g.setColor(Color.black);
			g.drawString("Play again?", 210, 250);
			break;
			

		default:
			break; 
			
		}
		
		// If the player has killed all of the aliens, they won the game! 
		if (aliens.isEmpty() ) {
			state = State.PlayerWon;

		}

		else if ( timeSinceDeath<=0 && num_lives > 0 && (state==State.PlayerLost) && aliens.isEmpty()==false) {
			timeSinceDeath = 0;
			

		} 
		else if ( (state==State.PlayerLost) && timeSinceDeath>=100 && num_lives > 0 ) {
			
			state = State.NormalPlay;
			timeSinceDeath = -1;
			

		} 
		else if ( (state==State.PlayerLost) && num_lives <= 0 && aliens.isEmpty()==false ) {
			removePlayer = true;
			

		} 
		else if ( state == State.AliensWon ){
			removePlayer = true;
			
		}
	}



}



