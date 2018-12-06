package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Pixel {
	int i;
	int k;
	Rectangle2D rectangle;
	
	
	public Pixel(int i, int k) {
		this.i = i;
		this.k = k;
		this.rectangle = new Rectangle2D.Double(this.i, this.k, 4, 4);
	}
	
	public void draw(Graphics2D g) {
		Color gold = new Color(255,223,0);
		g.setColor(gold);
		g.fillRect(i,k,4,4);
	}

}
