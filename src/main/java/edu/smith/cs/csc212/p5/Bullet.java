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
public class Bullet  {
	public int x = 250;
	public int y = 423;
	
	//public int[] xPoints = {x+20,x+30,x+40,x+20};
	//public int[] yPoints = {y-20,y-40,y-20,y-20};
	
	boolean shot = false;
	public String shooter;
	
	
	public Bullet(int startingX, String shooter) {
		this.x = startingX;
		this.y = y;
		this.shot = shot;
		//this.xPoints = xPoints;
		//this.yPoints = yPoints;
		this.shooter = shooter; 
		
	}
    
	public void draw(Graphics2D g) {
		//line
		
		//g.setColor(Color.white);
		//g.drawLine(x+30, y-40, x+30, y-20);
		
		//triangle
		g.setColor(Color.green);
		//g.fillPolygon(xPoints, yPoints, 4);

		//arrow
		//Font stringFont = new Font( "SansSerif", Font.PLAIN, 24 );
		//g.setFont(stringFont);
		//g.drawString("↑", x+22, y-20);
		
		
		
		//dots
		if (this.shooter.equals("player")) {
		Font stringFont2 = new Font( "SansSerif", Font.PLAIN, 22 );
		g.setFont(stringFont2);
		g.drawString(".", x+27, y-23);
		g.drawString(".", x+27, y-30);
		g.drawString(".", x+27, y-37);
		}
		
		else if (this.shooter.equals("alien")) {
			Font stringFont = new Font( "SansSerif", Font.PLAIN, 24 );
			g.setFont(stringFont);
			g.drawString("↓", x+22, y-20);
		}
	}

	
}
