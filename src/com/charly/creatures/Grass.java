package com.charly.creatures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Grass {
//	ref'd with 'this' downwards
	private String name;
	private Integer yPos;
	private Integer xPos;
	private Integer lifeTime;
	
	private BufferedImage image;
	private String filePath = "src\\com\\charly\\assets\\Grass.png";
	
	public Grass (Integer yPos, Integer xPos) throws IOException {
		this.name = "G";
		this.yPos = yPos;		//pick yCoord
		this.xPos = xPos;		//pick xCoord
		this.lifeTime = 20;
		
		loadImage(filePath);
	}
	
//	method to load the image first
	private void loadImage(String filePath) throws IOException {
        File imageFile = new File(filePath);
        image = ImageIO.read(imageFile);
    }
	public Integer getxPos() {
		return xPos;
	}
	public void setxPos(Integer xPos) {
		this.xPos = xPos;
	}

	public Integer getyPos() {
		return yPos;
	}
	public void setyPos(Integer yPos) {
		this.yPos = yPos;
	}
	
	public Integer getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(Integer lifeTime) {
		this.lifeTime = lifeTime;
	}
//	for image
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
