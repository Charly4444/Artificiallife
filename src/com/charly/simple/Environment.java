package com.charly.simple;


import java.io.IOException;

import com.charly.creatures.Grass;
import com.charly.creatures.Rabbit;
import com.charly.creatures.Wolf;

public class Environment {
	
	/**
	 * The Environment where we make all the necessary changes
	 * all the logic changes, after that we pass its current
	 * state to the JComponent to paint it
	 * sounds more reasonable
	 * 
	 * **/
	
	public Integer sizeY;
	public Integer sizeX; //what size of 2D array you want 
	private Integer wolfsQuantity;
	private Integer rabbitsQuantity;
	private Integer grassQuantity;
	
	private Integer grassCount=0;
	private Integer rabbitCount=0;
	private Integer wolfCount=0;
	
//	the environment should contain objects: Grass, Rabbit, Wolf
	private Object[][] envMap;

	public Environment(Integer sizeY, Integer sizeX, Integer wolfsQuantity, Integer rabbitsQuantity, Integer grassQuantity) throws IOException {
		
		this.sizeY = sizeY;
		this.sizeX = sizeX;
		this.wolfsQuantity = wolfsQuantity;
		this.rabbitsQuantity = rabbitsQuantity;
		this.grassQuantity = grassQuantity;
		
		this.envMap = new Object [sizeY][sizeX];
		
//		calls the constructEnv method in this class
		this.constructEnv ();
	}
	
	
	
//	========================================================
//	method to create the environment
	private void constructEnv () throws IOException {
		if (wolfsQuantity+rabbitsQuantity+grassQuantity > (sizeX*sizeY)) return ;
//		lets add grass ...
		
		while (grassCount < this.grassQuantity) {
			int yPos= (int) Math.round((sizeY-1) * Math.random());
			int xPos= (int) Math.round((sizeX-1) * Math.random());
//			System.out.print("x chosen: "+xPos+", y chosen: "+yPos);
			
//			if the position is free...  add grass
			if (envMap[yPos][xPos] == null) {
				envMap[yPos][xPos] = new Grass(yPos, xPos);
//				System.out.println("Added grass!");
				grassCount++;
			} 
			else{
//				System.out.println("we try again !!");
			};
		}
		
		
		
//		lets add rabbits ...
		while (rabbitCount < this.rabbitsQuantity) {
			int yPos= (int) Math.round((sizeY-1) * Math.random());
			int xPos= (int) Math.round((sizeX-1) * Math.random());			
//			if the position is free...  add rabbit
			if (envMap[yPos][xPos] == null) {
				envMap[yPos][xPos] = new Rabbit(yPos, xPos);
//				System.out.println("Added rabbit!");
				rabbitCount++;
			} 
			else{
//				System.out.println("we try again !!");
			};
		}
		
		
		
//		lets add wolves ...
		while (wolfCount < this.wolfsQuantity) {
			int yPos= (int) Math.round((sizeY-1) * Math.random());
			int xPos= (int) Math.round((sizeX-1) * Math.random());			
//			if the position is free...  add wolf
			if (envMap[yPos][xPos] == null) {
				envMap[yPos][xPos] = new Wolf(yPos, xPos);
//				System.out.println("Added wolf!");
				wolfCount++;
			} 
			else{
//				System.out.println("we try again !!");
			};
		}
		
	}



//	getters and setters
	public Object[][] getEnvMap() {
		return envMap;
	}
	public void setEnvMap(Object[][] envMap) {
		this.envMap = envMap;
	}
	
//	======================================================
	
////	special funcs... INVALIDATED!!
//	public void displayEnv (){
//		for (int i=0; i<this.sizeY; i++) {
//			String Aline = "[ ";
//			for (int j=0; j<this.sizeX; j++) {
////				careful, some places can be null
//				if (envMap[i][j] == null) {Aline += "N ";}
//				else {Aline += envMap[i][j].toString()+" ";}
//			}
//			Aline += "]\n";
//			System.out.println(Aline);
//		}
//	}
	
	
	
}
