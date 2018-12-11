package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Pixel {
	int i;
	int k;
	Rectangle2D rectangle;
	
	/**
	 * The pixel contains two coordinates and creates a square with width 4
	 * @param i - x coordinate
	 * @param k - y coordinate
	 */
	public Pixel(int i, int k) {
		this.i = i;
		this.k = k;
		this.rectangle = new Rectangle2D.Double(this.i, this.k, 4, 4);
	}
	
	/** 
	 * Draw all pixels as little golden rectangles in the designated coordinates
	 * @param g
	 */
	public void draw(Graphics2D g) {
		Color gold = new Color(255,223,0);
		g.setColor(gold);
		g.fillRect(i,k,4,4);
	}

}
