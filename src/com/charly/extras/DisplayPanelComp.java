package com.charly.extras;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.charly.creatures.Grass;
import com.charly.creatures.Rabbit;
import com.charly.creatures.Wolf;
import com.charly.simple.Environment;

public class DisplayPanelComp extends JComponent implements Runnable{
	/**
	 * This component will house the drawing of all
	 * our images and then we can add this component
	 * to the JFrame which we make later
	 * 
	 * **/
	
	private int nRows;
	private int nCols;
	private Environment environment;
//	a picture of the current environment
	private Object[][] myMaps;
	
//	========================
//	---some special vars---
	private int bw = 15;
	private int bh = 40;
	private int gap = 10;
	private int objWidth = 32;	//**
	private int objHeight = 32; //**
	
//	========================
	
	
	private List<Imageobj> bufferedImages = new ArrayList<Imageobj>();  //stores image to draw
	
	private int xPaintPos = bw;
	private int yPaintPos = bh;
	
	
//	constructor
	public DisplayPanelComp(Environment environment) throws IOException {
        this.environment = environment;
        this.nRows = environment.sizeY;
        this.nCols = environment.sizeX;
        this.myMaps = environment.getEnvMap();
        
        
//      Load the image(s) to draw
//        loadImages(environment);
        
        
    }
	
	
	
//	Cue up the Images to draw
	private void loadImages(Object[][] myMaps) throws IOException {
        // * *
		// its an array List, always clear it before reusing
		bufferedImages.clear();
        for (int i=0; i<nRows; i++) {
			for (int j=0; j<nCols; j++) {
//				careful, some places can be null
				if (myMaps[i][j] == null) {
					
				}
				else {
					yPaintPos = bh + (i*objHeight) + (i*gap);
					xPaintPos = bw + (j*objWidth) + (j*gap);
					
					if ((myMaps[i][j]).getClass().equals(Grass.class)) {
						Grass g = (Grass) myMaps[i][j];	//then a grass class, ok
						BufferedImage myImage = g.getImage();
						bufferedImages.add(new Imageobj(myImage, xPaintPos, yPaintPos));
						repaint();
					}
					if ((myMaps[i][j]).getClass().equals(Rabbit.class)) {
						Rabbit r = (Rabbit) myMaps[i][j];
						BufferedImage myImage = r.getImage();
						bufferedImages.add(new Imageobj(myImage, xPaintPos, yPaintPos));
						repaint();
						
					}
					if ((myMaps[i][j]).getClass().equals(Wolf.class)) {
						Wolf w = (Wolf) myMaps[i][j];
						BufferedImage myImage = w.getImage();
						bufferedImages.add(new Imageobj(myImage, xPaintPos, yPaintPos));
						repaint();
						
					}
					
				}
			}
//			System.out.println("Done!!");
		}
    }
	
	
	
	
//	repaint() calls this automatically
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Imageobj imageobj : bufferedImages) {
        	g.drawImage(imageobj.getImage(), imageobj.getxPaintPos(), imageobj.getyPaintPos(), this);
        }
    }

	

//	call each animal to think
	public void organismsThink(Environment environment) throws IOException {
		
		for (int i=0; i<nRows; i++) {
			for (int j=0; j<nCols; j++) {
				Object [][] myObj = environment.getEnvMap();
//				careful, some places can be null	
				if ((myObj[i][j] != null)&&(myObj[i][j]).getClass().equals(Rabbit.class)) {
					Rabbit r = (Rabbit) myObj[i][j];
					this.environment = r.think(environment);
				}
				if ((myObj[i][j] != null)&&(myObj[i][j]).getClass().equals(Wolf.class)) {
					Wolf w = (Wolf) myObj[i][j];
					this.environment = w.think(environment);
				}
					
			}
//			System.out.println("Done!!");
		}
		try {
			loadImages(this.environment.getEnvMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void run() {
		try {
//			at start, load images to screen
			loadImages(myMaps);
		} catch (IOException er) {
			er.printStackTrace();
		}
	
		while(true) {
			try {
//				organisms need think...
//				Thread.currentThread().sleep(1000);
                organismsThink(environment);
                Thread.currentThread().sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
	
	
	
	
	
	/* There isonly one environment that us ever created
	 * the envMap property of this environment is what 
	 * we rewrite*/
}
