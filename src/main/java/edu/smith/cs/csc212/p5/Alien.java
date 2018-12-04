package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;

import me.jjfoley.gfx.GFX;

public class Alien  {
	public int row;
	public int column;
	public int x;
	public int y;
	public boolean shot = false;
	
	public Alien( int row, int column ) {
		this.row = row;
		this.column = column;
		//this.x = x;
		//this.y = y;
		this.shot = shot;
		
	}

	public void draw(Graphics2D g) {
		if ( ( World.moveAliensRight == true ) ) { 
			x += 3;
		}
		if ( ( World.moveAliensLeft == true ) ) { 
			x -= 3;
		}
		
		if (( ( row == 1 ) || ( row == 2 ) )&& ( shot == false ) ) {
		    g.setColor(Color.white);
		    g.drawOval(x, y, 35, 25);
		    g.drawLine(x+5, y+22, x, y+29);
		    g.drawLine(x+30, y+22, x+35, y+29);
		    g.fillOval(x-5, y+28, 6, 4);
		    g.fillOval(x+35, y+28, 6, 4);
		    g.drawArc(x, y-3, 10, 5, 0, 150);
		    g.drawArc(x+25, y-3, 10, 5, 180, -150);
		    g.setColor(Color.pink.darker());
		    g.fillOval(x, y, 35, 25);
		    g.setColor(Color.black);
		    g.drawOval(x+5,y+6,7,7);
		    g.drawOval(x+22, y+6, 7, 7);
		    g.drawOval(x+12, y+12, 10, 10);
		    g.fillOval(x+12, y+12, 10, 10);
		    g.setColor(Color.white);
		    g.fillOval(x+5,y+6,7,7);
		    g.fillOval(x+22, y+6, 7, 7);
		    g.setColor(Color.black);
		    g.fillOval(x+3,y+6,7,7);
		    g.fillOval(x+20, y+6, 7, 7);
		}
		
		if (( ( row == 3 ) || ( row == 4 ) )&&(shot == false )) {
			g.setColor(Color.red.darker());
		    g.fillOval(x, y, 10,10);
		    g.fillOval(x+20, y, 10,10);
		    g.fillOval(x, y, 30,30);
		    g.fillRect(x, y+15, 30, 15);
		    g.fillOval(x-8, y+15, 18, 8);
		    g.fillOval(x+20, y+15, 18, 8);
		    g.fillOval(x, y+26, 8, 8);
		    g.fillOval(x+6, y+26, 8, 8);
		    g.fillOval(x+11, y+26, 8, 8);
		    g.fillOval(x+16, y+26, 8, 8);
		    g.fillOval(x+22, y+26, 8, 8);
		    
		    g.setColor(Color.white);
		    g.fillOval(x+5,y+5,20,20);
		    
		    g.setColor(Color.black);
		    g.fillOval(x+8,y+8,15,15);
		    //g.fillArc(b, b, width, height, startAngle, arcAngle);
		    g.fillArc(x+8, y+21, 15, 10, 180, 180);
		}
		
		
	}

}
