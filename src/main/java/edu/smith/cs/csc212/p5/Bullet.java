package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Bullet {
	public int x;
	public int y = 423;
	public Rectangle2D playerRectangle;

	boolean shot = false;
	public WorldObject shooter;


	public Bullet(int startingX, WorldObject shooter) {
		this.x = startingX + 30;
		this.y = y;
		this.shot = shot;
		this.shooter = shooter;

	}

	public void draw(Graphics2D g) {

		if (this.shooter.getClass().equals(Alien.class)) {
			// Alien bullets
			Color purple = new Color(206, 128, 255);
			g.setColor(purple);
			g.fillRect(x-15,y+15,2,10);

		}

		else if (this.shooter.getClass().equals(Player.class)) {
			// Player bullets
			g.setColor(Color.green);
			g.drawLine(x, y-36, x, y-23);
			g.drawLine(x, y-36, x+4, y-31);
			g.drawLine(x, y-36, x-4, y-31);
			g.drawLine(x, y-34, x+4, y-29);
			g.drawLine(x, y-34, x-4, y-29);

		}
	}
}
