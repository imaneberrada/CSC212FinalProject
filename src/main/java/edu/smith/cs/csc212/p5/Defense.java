package edu.smith.cs.csc212.p5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class Defense {
	int order;
	int x;
	
	Map<Integer,Map<Integer,Integer>> piecesLeft = new HashMap<>();
	
	public Defense(int order) {
		this.order = order;
		
		this.x = order*120-90;
		
		int columnValue = 0;
		for (int i = x; i<x+80; i +=1 ) {
			Map<Integer, Integer> column = new HashMap<>();
			
			int squareValue = 0;
			for (int k = 0; k<50; k +=1 ) {
				column.put(squareValue, 1);
				squareValue +=1;
			}
			piecesLeft.put(columnValue, column);
			columnValue+=1;
		}
		
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		g.drawRect(x, 350, 80, 50);
		
		// Go through each column of the leaves using whereLeavesAre
				for (int i = 0; i<piecesLeft.size(); i +=1 ) {
					// Use the integer value as an index to get the appropriate column
					Map<Integer, Integer> column = piecesLeft.get(i);
					// Go through each square of the column using the first integer value as an index
					for (int k = 0; k < column.size(); k += 1 ) {
						// If there is no second integer value (the color index), do not draw a leaf
						if (piecesLeft.get(i).get(k) != 0 ) {
							// Otherwise, set the color appropriately using the index
							g.setColor( Color.blue );
							// Draw the leaf
							g.fillRect(x+i, 350+k, 1, 1);
						}
						
					}
				}
	}
}
