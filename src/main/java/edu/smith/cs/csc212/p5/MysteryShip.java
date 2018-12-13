package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class MysteryShip {
	
	// starting coordinates for the ship
	public int x = -80;
	public int y = 42;

	// how long it's been since the ship has passed through
	public static int timer = 0;
	
	// check if the ship has been shot by the player
	public static boolean shot = false;
	
	// custom colors for the ship
	private Color purple = new Color(221,160,221);
	private Color newBlue = new Color(70,130,180);
	
	/**
	 * Find out the rectangular area of the ship
	 * @return - a rectangle showing the area of the ship to help figure out when it has been shot
	 */
	public Rectangle2D getRectangle() {
		return new Rectangle2D.Double(x-10, y+5, 70, 30);
	}
	
	/**
	 * Draw the player like normal inside the spaceship
	 * @param g
	 */
	public void draw(Graphics2D g) { 
		if(World.removePlayer == false) {
		// Move to the right when drawing 
		x += 3;

		g.setColor(newBlue);
		g.fillRect(x+24, y-10, 3, 10);
		g.fillRect(x+5, y, 40, 20);

		g.setColor(Color.black);
		g.drawRect(x+5, y, 40, 20);
		g.drawOval(x-10, y+5, 70, 30);

		g.setColor(newBlue);
		g.drawLine(x+2, y+30, x-15, y+35);
		g.drawLine(x+48, y+30, x+65, y+35);
		g.fillOval(x-10, y+5, 70, 30);

		g.setColor(Color.white);
		g.fillRect(x+2, y+11, 47, 16);

		g.setColor(purple);
		g.fillArc(x+3, y+14, 20, 26, 0, 180);
		g.fillArc(x+27, y+14, 20, 26, 0, 180);

		g.setColor(Color.black);
		g.drawRect(x+2, y+11, 47, 16);
		g.drawArc(x+3, y+14, 20, 26, 0, 180);
		g.drawArc(x+27, y+14, 20, 26, 0, 180);

		g.fillOval(x+7, y+18, 5, 5);
		g.fillOval(x+14, y+18, 5, 5);
		g.fillOval(x+31, y+18, 5, 5);
		g.fillOval(x+38, y+18, 5, 5);

		g.drawArc(x+9, y+23, 7, 3, 0, -180);
		g.drawArc(x+33, y+23, 7, 3, 0, -180);
		
		}

	}
}
