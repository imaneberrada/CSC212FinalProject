package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;

import me.jjfoley.gfx.GFX;

public class Player  {
	public int x = 250;
	public int y = 435;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
 
	public void draw(Graphics2D g) {
		// TODO When shooting from player, start at x+30, y-20
		g.setColor(Color.white);
		g.drawLine(x+30, y, x+30, y-20);
		g.fillOval(x+25,y-20,10,10);
		g.drawArc(x, y, 60, 60, 0, 180);
		g.drawOval(x+15, y+10, 30, 35);
		g.setColor(Color.green);
		g.fillOval(x+15, y+10, 30, 35);
		g.setColor(Color.black);
		g.fillOval(x+21, y+18, 7, 7 );
		g.fillOval(x+32, y+18, 7, 7 );
		
		g.setColor(Color.gray);
		g.fillOval(x-17, y+27, 95, 30);
		
		g.setColor(Color.yellow);
		g.fillOval(x-5, y+37, 10, 10);
		g.fillOval(x+15, y+37, 10, 10);
		g.fillOval(x+35, y+37, 10, 10);
		g.fillOval(x+55, y+37, 10, 10);
		
	}

}
