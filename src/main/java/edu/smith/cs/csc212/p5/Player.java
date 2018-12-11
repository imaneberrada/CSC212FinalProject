package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.Rectangle2D;

public class Player extends WorldObject  {


	public int x = 250;

	public int y = 435;

	public boolean isShot;
	Arc2D arc;

	public Player() {
		this.x = x;
		this.y = y;
		this.isShot = false;
	}

	public Arc2D getArc() {
		return new Arc2D.Double(this.x, this.y, 60, 60, 0, 180, 0 );

	}

	/**
	 * first stage in the death animation - sad face and 90% opacity
	 * @param g
	 */
	public void transparent1(Graphics2D g) {
		if(World.removePlayer == false) {
			Color sadGreen = new Color(0f,1f,0f,.9f);

			g.setColor(Color.white);
			g.drawLine(x+30, y, x+30, y-20);
			g.fillOval(x+25,y-20,10,10);
			g.drawArc(x, y, 60, 60, 0, 180);
			g.drawOval(x+15, y+10, 30, 35);
			g.setColor(sadGreen);
			g.fillOval(x+15, y+10, 30, 35);
			g.setColor(Color.black);
			g.fillOval(x+21, y+15, 7, 7 );
			g.fillOval(x+32, y+15, 7, 7 );
			g.drawArc(x+25, y+23, 10, 5, 0, 180);


			g.setColor(Color.gray);
			g.fillOval(x-17, y+27, 95, 30);

			g.setColor(Color.yellow);
			g.fillOval(x-5, y+37, 10, 10);
			g.fillOval(x+15, y+37, 10, 10);
			g.fillOval(x+35, y+37, 10, 10);
			g.fillOval(x+55, y+37, 10, 10);

		}
	}

	/**
	 * second stage in death animation - sad face and 60% opacity
	 * @param g
	 */
	public void transparent2(Graphics2D g) {
		if(World.removePlayer == false) {
			Color sadGreen = new Color(0f,1f,0f,.6f);

			g.setColor(Color.white);
			g.drawLine(x+30, y, x+30, y-20);
			g.fillOval(x+25,y-20,10,10);
			g.drawArc(x, y, 60, 60, 0, 180);
			g.drawOval(x+15, y+10, 30, 35);
			g.setColor(sadGreen);
			g.fillOval(x+15, y+10, 30, 35);
			g.setColor(Color.black);
			g.fillOval(x+21, y+15, 7, 7 );
			g.fillOval(x+32, y+15, 7, 7 );
			g.drawArc(x+25, y+23, 10, 5, 0, 180);


			g.setColor(Color.gray);
			g.fillOval(x-17, y+27, 95, 30);

			g.setColor(Color.yellow);
			g.fillOval(x-5, y+37, 10, 10);
			g.fillOval(x+15, y+37, 10, 10);
			g.fillOval(x+35, y+37, 10, 10);
			g.fillOval(x+55, y+37, 10, 10);

		}
	}

	/**
	 * third stage in death animation - sad face and 30% opacity
	 * @param g
	 */
	public void transparent3(Graphics2D g) {
		if(World.removePlayer == false) {
			Color sadGreen = new Color(0f,1f,0f,.3f);

			g.setColor(Color.white);
			g.drawLine(x+30, y, x+30, y-20);
			g.fillOval(x+25,y-20,10,10);
			g.drawArc(x, y, 60, 60, 0, 180);
			g.drawOval(x+15, y+10, 30, 35);
			g.setColor(sadGreen);
			g.fillOval(x+15, y+10, 30, 35);
			g.setColor(Color.black);
			g.fillOval(x+21, y+15, 7, 7 );
			g.fillOval(x+32, y+15, 7, 7 );
			g.drawArc(x+25, y+23, 10, 5, 0, 180);


			g.setColor(Color.gray);
			g.fillOval(x-17, y+27, 95, 30);

			g.setColor(Color.yellow);
			g.fillOval(x-5, y+37, 10, 10);
			g.fillOval(x+15, y+37, 10, 10);
			g.fillOval(x+35, y+37, 10, 10);
			g.fillOval(x+55, y+37, 10, 10);

		}
	}

	/**
	 * Only draw the spaceship with no player inside 
	 * @param g
	 */
	public void noPlayer (Graphics2D g) {
		if(World.removePlayer == false) {			
			g.setColor(Color.white);
			g.drawLine(x+30, y, x+30, y-20);
			g.fillOval(x+25,y-20,10,10);
			g.drawArc(x, y, 60, 60, 0, 180);		

			g.setColor(Color.gray);
			g.fillOval(x-17, y+27, 95, 30);

			g.setColor(Color.yellow);
			g.fillOval(x-5, y+37, 10, 10);
			g.fillOval(x+15, y+37, 10, 10);
			g.fillOval(x+35, y+37, 10, 10);
			g.fillOval(x+55, y+37, 10, 10);

		}
	}

	/**
	 * Draw the player like normal inside the spaceship
	 * @param g
	 */
	public void draw(Graphics2D g) {
		if(World.removePlayer == false) {
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

}
