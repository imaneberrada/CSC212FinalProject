package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Tracks player's lives
 */
public class LivesTracker {

	public void draw(Graphics2D g) {
		for (int i=1; i <World.num_lives+1; i++) {
			g.setColor(Color.GREEN);
			g.fillRect(i*28+356, 20, 25, 8);
			g.fillRect(i*28+359, 18, 19, 8);
			g.fillRect(i*28+364, 15, 9, 8);
			g.fillRect(i*28+368, 12, 2, 4);
		}
	}
}
