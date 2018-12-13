package edu.smith.cs.csc212.p5;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;


public class Defense {
	int order;
	int x;
	Rectangle2D rectangle;
	
	// A list of pixels in the bunker and pixels that need to be removed
	public List<Pixel> pixels = new ArrayList<Pixel>();
	public static List<Pixel> removePixels = new ArrayList<Pixel>();
	
	/**
	 * Add pixels to the bunker set up to be drawn
	 * @param order - which bunker this is on the screen (from the left to right)
	 */
	public Defense(int order) {
		this.order = order;
		this.x = order*150-90;
		// A rectangle that surrounds the bunker - can be checked for bullets more easily
		this.rectangle = new Rectangle2D.Double(this.x, 350, 80, 50);
		
		for (int i = x+5; i<x+75; i +=4 ) {
			for (int k = 355; k<385; k +=4 ) {
				if ( (k>=375) && (i<=x+55) && (i>= x+25)) {
					continue;
				}
				Pixel pixel1 = new Pixel(i,k);
				pixels.add(pixel1);
			}
		}
		
		
	}
	
	/**
	 * Draw all of the pixels inside the bunker
	 * @param g
	 */
	public void draw(Graphics2D g) {

		for (Pixel pixel1 : pixels) {
			pixel1.draw(g);
		}
	}
}
