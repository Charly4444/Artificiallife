package com.charly.extras;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Imageobj {

	private BufferedImage image;
	private int xPaintPos;
	private int yPaintPos;
	
	public Imageobj(BufferedImage image, int xPaint, int yPaint) {
		super();
		this.image = image;
		this.xPaintPos = xPaint;
		this.yPaintPos = yPaint;
	}

	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getxPaintPos() {
		return xPaintPos;
	}
	public void setxPaintPos(int xPaintPos) {
		this.xPaintPos = xPaintPos;
	}

	public int getyPaintPos() {
		return yPaintPos;
	}
	public void setyPaintPos(int yPaintPos) {
		this.yPaintPos = yPaintPos;
	}
	
	
	

	
}
