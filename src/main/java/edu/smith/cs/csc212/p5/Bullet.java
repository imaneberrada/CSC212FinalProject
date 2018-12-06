package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import me.jjfoley.gfx.GFX;
/*
 * 4 kinds of bullets:
 * arrow
 * squiggle
 * dots (different y's)
 * triangle
 */
public class Bullet {
	public int x;
	public int y = 423;
	
	//public int[] xPoints = {x+20,x+30,x+40,x+20};
	//public int[] yPoints = {y-20,y-40,y-20,y-20};
	
	boolean shot = false;
	public WorldObject shooter;
	
	
	public Bullet(int startingX, WorldObject shooter) {
		this.x = startingX + 30;
		this.y = y;
		this.shot = shot;
		this.shooter = shooter;
		
		/*if (this.shooter.getClass().equals(Alien.class) ) {
			this.y = shooter.y;
		}*/
		
	}

	public void draw(Graphics2D g) {
		
		//arrow
		//Font stringFont = new Font( "SansSerif", Font.PLAIN, 24 );
		//g.setFont(stringFont);
		//g.drawString("↑", x+22, y-20);
		
		//dots
		//g.drawString(".", x+27, y-23);
		//g.drawString(".", x+27, y-30);
		//g.drawString(".", x+27, y-37);
		
		//move this to LivesTracker --before the game starts.
		
		if (this.shooter.getClass().equals(Alien.class)) {
			Color purple = new Color(206, 128, 255);
			g.setColor(purple);
			g.fillRect(x-15,y+15,2,10);
			
			/*Font stringFont2 = new Font("SansSerif", Font.PLAIN, 22);
			g.setFont(stringFont2);
			g.drawString("↯", x, y - 20);*/
		}
		
		else if (this.shooter.getClass().equals(Player.class)) {
			//TODO: make an invisible rectangle that goes from the top of the bullet, h=speed
			//intersect instead of contains 
			
			//Player bullets
	 		g.setColor(Color.green);
			g.drawLine(x, y-36, x, y-23);
			g.drawLine(x, y-36, x+4, y-31);
			g.drawLine(x, y-36, x-4, y-31);
			g.drawLine(x, y-34, x+4, y-29);
			g.drawLine(x, y-34, x-4, y-29);
			
			/*Font stringFont = new Font( "SansSerif", Font.PLAIN, 24 );
			g.setFont(stringFont);
			g.drawString("↟", x, y-20);*/
			
		}
	}

	
}
