package edu.smith.cs.csc212.p5;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import me.jjfoley.gfx.GFX;

public class World extends GFX {
	
	public String fileName = "src/main/Images/SpaceInvaders.png";
	//ImagePanel class
	public class ImagePanel extends JPanel {

		public BufferedImage image;

		public ImagePanel(String fileName) {
			try {
				image = ImageIO.read(new File(fileName));
			} catch (IOException e) {
				System.exit(-1);
			}

		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}

	}
	
	public static void main(String[] args) {
		// This is where our game will be displayed
		GFX app = new World();
		app.start();

	}

	@Override
	public void draw(Graphics2D g) {
		ImagePanel image = new ImagePanel(fileName);
		image.paintComponent(g);
		
	}

}
