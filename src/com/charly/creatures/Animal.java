package com.charly.creatures;

public class Animal {
//	ref'd with 'this' downwards
	private String name;
	private Integer livingTime; //do we need ?
	private Integer yPos;
	private Integer xPos;
	
	
	public Animal (String name, Integer yPos, Integer xPos, Integer livingTime) {
		this.name = name;
		this.livingTime = livingTime;
		this.yPos = yPos;		//pick yCoord
		this.xPos = xPos;		//pick xCoord
		
	}


//	getters and setters.
//	public Integer getyPos() {
//			return yPos;
//		}
//	public void setyPos(Integer yPos) {
//		this.yPos = yPos;
//	}
//	public Integer getxPos() {
//		return xPos;
//	}
//	public void setxPos(Integer xPos) {
//		this.xPos = xPos;
//	}


	//	I use this specially for names 
	@Override
	public String toString() {
		return name;
	}

	
}
