package com.charly.creatures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.charly.simple.Environment;

public class Rabbit extends Animal {

	private int rabbitYPos;
	private int rabbitXPos;
	private BufferedImage image;
	private String filePath = "src\\com\\charly\\assets\\Rabbit.png";
	
	
//	what the rabbit can See
//	private List<Object> rabbitCanSee = new ArrayList<Object>();
	private Object[] lookDown = new Object[14];
	private Object[] lookUp = new Object[14];
	private Object[] surroundingCells = new Object[8];
	
	
	public Rabbit(Integer rabbitYPos, Integer rabbitXPos) throws IOException {
		super("R", rabbitYPos, rabbitXPos, 15);	//added name and liveTime
		this.rabbitYPos = rabbitYPos;
		this.rabbitXPos = rabbitXPos;
		
		
		loadImage(filePath);
		
	}
	
//	method to load the image first
	private void loadImage(String filePath) throws IOException {
        File imageFile = new File(filePath);
        image = ImageIO.read(imageFile);
    }
	

	public void LookAt(Environment environment, int yPos, int xPos) {
//		to lookAT
//		lets create a range of cells to see

		/* 
		 * | 7  | 8  |  9  | 10  |  11 |
		 *  ---- ---- ----- ----- -----
		 * | 6  | 1  |  2  |  3  |  12 |
		 *  ---- ---- ----- ----- -----
		 * | 5  | 0  | "R" |  4  |  13 | 
		 * 
		 * 
		 * AND, FACING DOWN FOR INSTANCE:
		 * 
		 * | 5  | 0  | "R" |  4  |  13 |
		 *  ---- ---- ----- ----- -----
		 * | 6  | 1  |  2  |  3  |  12 |
		 *  ---- ---- ----- ----- -----
		 * | 7  | 8  |  9  | 10  |  11 | 
		 *  ---- ---- ----- ----- -----
		 * 
		 * 
		 * The Rabbit can eat from inner cells: {0, 1, 2, 3, 4}
		 * */
		
		int xBounds = environment.sizeX;
		int yBounds = environment.sizeY;
		
//		the rabbit or anything will tell us where it is; i.e: (yPos,xPos) it can see 14 cells down
		lookDown[0] =((xPos-1) >=0 && (xPos-1) < xBounds) ? environment.getEnvMap()[yPos][xPos-1] : "OUTOFBOUNDS";
		lookDown[1] =((xPos-1) >=0 && (xPos-1) < xBounds && (yPos+1) >=0 && (yPos+1) < yBounds) ? environment.getEnvMap()[yPos+1][xPos-1] : "OUTOFBOUNDS";
		lookDown[2] =((yPos+1) >=0 && (yPos+1) < yBounds) ? environment.getEnvMap()[yPos+1][xPos] : "OUTOFBOUNDS";
		lookDown[3] =((xPos+1) >=0 && (xPos+1) < xBounds && (yPos+1) >=0 && (yPos+1) < yBounds) ? environment.getEnvMap()[yPos+1][xPos+1] : "OUTOFBOUNDS";
		lookDown[4] =((xPos+1) >=0 && (xPos+1) < xBounds) ? environment.getEnvMap()[yPos][xPos+1] : "OUTOFBOUNDS";
		lookDown[5] =((xPos-2) >=0 && (xPos-2) < xBounds) ? environment.getEnvMap()[yPos][xPos-2] : "OUTOFBOUNDS";
		lookDown[6] =((xPos-2) >=0 && (xPos-2) < xBounds && (yPos+1) >=0 && (yPos+1) < yBounds) ? environment.getEnvMap()[yPos+1][xPos-2] : "OUTOFBOUNDS";
		lookDown[7] =((xPos-2) >=0 && (xPos-2) < xBounds && (yPos+2) >=0 && (yPos+2) < yBounds) ? environment.getEnvMap()[yPos+2][xPos-2] : "OUTOFBOUNDS";
		lookDown[8] =((xPos-1) >=0 && (xPos-1) < xBounds && (yPos+2) >=0 && (yPos+2) < yBounds) ? environment.getEnvMap()[yPos+2][xPos-1] : "OUTOFBOUNDS";
		lookDown[9] =((yPos+2) >=0 && (yPos+2) < yBounds) ? environment.getEnvMap()[yPos+2][xPos] : "OUTOFBOUNDS";
		lookDown[10] =((xPos+1) >=0 && (xPos+1) < xBounds && (yPos+2) >=0 && (yPos+2) < yBounds) ? environment.getEnvMap()[yPos+2][xPos+1] : "OUTOFBOUNDS";
		lookDown[11] =((xPos+2) >=0 && (xPos+2) < xBounds && (yPos+2) >=0 && (yPos+2) < yBounds) ? environment.getEnvMap()[yPos+2][xPos+2] : "OUTOFBOUNDS";
		lookDown[12] =((xPos+2) >=0 && (xPos+2) < xBounds && (yPos+1) >=0 && (yPos+1) < yBounds) ? environment.getEnvMap()[yPos+1][xPos+2] : "OUTOFBOUNDS";
		lookDown[13] =((xPos+2) >=0 && (xPos+2) < xBounds) ? environment.getEnvMap()[yPos][xPos+2] : "OUTOFBOUNDS";
		
//		and UPWARDS TOO
		lookUp[0] =((xPos-1) >=0 && (xPos-1) < xBounds) ? environment.getEnvMap()[yPos][xPos-1] : "OUTOFBOUNDS";
		lookUp[1] =((xPos-1) >=0 && (xPos-1) < xBounds && (yPos-1) >=0 && (yPos-1) < yBounds) ? environment.getEnvMap()[yPos-1][xPos-1] : "OUTOFBOUNDS";
		lookUp[2] =((yPos-1) >=0 && (yPos-1) < yBounds) ? environment.getEnvMap()[yPos-1][xPos] : "OUTOFBOUNDS";
		lookUp[3] =((xPos+1) >=0 && (xPos+1) < xBounds && (yPos-1) >=0 && (yPos-1) < yBounds) ? environment.getEnvMap()[yPos-1][xPos+1] : "OUTOFBOUNDS";
		lookUp[4] =((xPos+1) >=0 && (xPos+1) < xBounds) ? environment.getEnvMap()[yPos][xPos+1] : "OUTOFBOUNDS";
		lookUp[5] =((xPos-2) >=0 && (xPos-2) < xBounds) ? environment.getEnvMap()[yPos][xPos-2] : "OUTOFBOUNDS";
		lookUp[6] =((xPos-2) >=0 && (xPos-2) < xBounds && (yPos-1) >=0 && (yPos-1) < yBounds) ? environment.getEnvMap()[yPos-1][xPos-2] : "OUTOFBOUNDS";
		lookUp[7] =((xPos-2) >=0 && (xPos-2) < xBounds && (yPos-2) >=0 && (yPos-2) < yBounds) ? environment.getEnvMap()[yPos-2][xPos-2] : "OUTOFBOUNDS";
		lookUp[8] =((xPos-1) >=0 && (xPos-1) < xBounds && (yPos-2) >=0 && (yPos-2) < yBounds) ? environment.getEnvMap()[yPos-2][xPos-1] : "OUTOFBOUNDS";
		lookUp[9] =((yPos-2) >=0 && (yPos-2) < yBounds) ? environment.getEnvMap()[yPos-2][xPos] : "OUTOFBOUNDS";
		lookUp[10] =((xPos+1) >=0 && (xPos+1) < xBounds && (yPos-2) >=0 && (yPos-2) < yBounds) ? environment.getEnvMap()[yPos-2][xPos+1] : "OUTOFBOUNDS";
		lookUp[11] =((xPos+2) >=0 && (xPos+2) < xBounds && (yPos-2) >=0 && (yPos-2) < yBounds) ? environment.getEnvMap()[yPos-2][xPos+2] : "OUTOFBOUNDS";
		lookUp[12] =((xPos+2) >=0 && (xPos+2) < xBounds && (yPos-1) >=0 && (yPos-1) < yBounds) ? environment.getEnvMap()[yPos-1][xPos+2] : "OUTOFBOUNDS";
		lookUp[13] =((xPos+2) >=0 && (xPos+2) < xBounds) ? environment.getEnvMap()[yPos][xPos+2] : "OUTOFBOUNDS";
		
		
	};
	
//	only called by Rabbit ONLY AFTER CALLING "LookAt() !!!"
	public void populateSurroundingCell() {
//		observe surroundingCells too...		so he scans round in a clockwise movt.
		/* | 0  |  1  |  2  |
		 * | 7  | "R" |  3  |
		 * | 6  |  5  |  4  |
		 */
		surroundingCells[0]=lookUp[1];
		surroundingCells[1]=lookUp[2];
		surroundingCells[2]=lookUp[3];
		surroundingCells[3]=lookUp[4];
		surroundingCells[4]=lookDown[3];
		surroundingCells[5]=lookDown[2];
		surroundingCells[6]=lookDown[1];
		surroundingCells[7]=lookDown[0];	//check with map above for clarity.
	}
	
	
	public Boolean inDanger() {
		Boolean danger = false;
		for (Object o: surroundingCells) {
			if ((o != null)&&(o.getClass().equals(Wolf.class))) {
				danger = true;
			}
		}
		return danger;
	}
	
	public Boolean isTherePlant() {
		Boolean food = false;
		for (Object o: surroundingCells) {
			if ((o != null)&&(o.getClass().equals(Grass.class))) {
				food = true;
			}
		}
		return food;
	}
	
	
//	called by RABBIT after checking, seeing that there's danger
	public Environment runAway(Environment environment) throws IOException {
//		to do
//		run away logic
		int xBounds = environment.sizeX;
		int yBounds = environment.sizeY;
		
		if ((surroundingCells[0] != null) && surroundingCells[0].getClass().equals(Wolf.class)) {
			if((rabbitYPos+1)>=0 && (rabbitYPos+1)<yBounds && (rabbitXPos+1)>=0 && (rabbitXPos+1)<xBounds && surroundingCells[4]==null) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos+1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos+1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//				rewrite the environment
				environment.setEnvMap(newenvMap);
//				return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[1] != null) && surroundingCells[1].getClass().equals(Wolf.class)) {
			if((rabbitYPos+1)>=0 && (rabbitYPos+1)<yBounds && surroundingCells[5]==null) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				
				this.setRabbitYPos(rabbitYPos+1);		//change to new coordinates				

				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][rabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[2] != null) && surroundingCells[2].getClass().equals(Wolf.class)) {
			if((rabbitYPos+1)>=0 && (rabbitYPos+1)<yBounds && (rabbitXPos-1)>=0 && (rabbitXPos-1)<xBounds && surroundingCells[6]==null) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos+1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos-1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[3] != null) && surroundingCells[3].getClass().equals(Wolf.class)) {
			if((rabbitXPos-1)>=0 && (rabbitXPos-1)<xBounds && surroundingCells[7]==null) {
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldRabbitXPos = rabbitXPos;
													//change to new coordinates
				this.setRabbitXPos(rabbitXPos-1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[rabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[4] != null) && surroundingCells[4].getClass().equals(Wolf.class)) {
			if((rabbitYPos-1)>=0 && (rabbitYPos-1)<yBounds && (rabbitXPos-1)>=0 && (rabbitXPos-1)<xBounds && surroundingCells[0]==null) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos-1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos-1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[5] != null) && surroundingCells[5].getClass().equals(Wolf.class)) {
			if((rabbitYPos-1)>=0 && (rabbitYPos-1)<yBounds && surroundingCells[1]==null) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				
				this.setRabbitYPos(rabbitYPos-1);		//change to new coordinates				
				
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][rabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[6] != null) && surroundingCells[6].getClass().equals(Wolf.class)) {
			if((rabbitYPos-1)>=0 && (rabbitYPos-1)<yBounds && (rabbitXPos+1)>=0 && (rabbitXPos+1)<xBounds && surroundingCells[2]==null) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos-1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos+1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[7] != null) && surroundingCells[7].getClass().equals(Wolf.class)) {
			if((rabbitXPos+1)>=0 && (rabbitXPos+1)<xBounds && surroundingCells[3]==null) {
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldRabbitXPos = rabbitXPos;
														//change to new coordinates
				this.setRabbitXPos(rabbitXPos+1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[rabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}
		
//		...
//		..
//		.
		return environment;		// return as is, if nothing can be done
	}
	

//	only called by RABBIT after checking that it is safe and there's food
	public Environment eat(Environment environment) throws IOException {
		
//		Eating logic
		int xBounds = environment.sizeX;
		int yBounds = environment.sizeY;
		
		if ((surroundingCells[0] != null) && surroundingCells[0].getClass().equals(Grass.class)) {
//			(xPos-1)(yPos-1)
			if((rabbitYPos-1)>=0 && (rabbitYPos-1)<yBounds && (rabbitXPos-1)>=0 && (rabbitXPos-1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos-1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos-1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//				rewrite the environment
				environment.setEnvMap(newenvMap);
//				return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[1] != null) && surroundingCells[1].getClass().equals(Grass.class)) {
//			(yPos-1)
			if((rabbitYPos-1)>=0 && (rabbitYPos-1)<yBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				
				this.setRabbitYPos(rabbitYPos-1);		//change to new coordinates				

				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][rabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[2] != null) && surroundingCells[2].getClass().equals(Grass.class)) {
//			(xPos+1)(yPos-1)
			if((rabbitYPos-1)>=0 && (rabbitYPos-1)<yBounds && (rabbitXPos+1)>=0 && (rabbitXPos+1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos-1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos+1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[3] != null) && surroundingCells[3].getClass().equals(Grass.class)) {
//			(xPos+1)
			if((rabbitXPos+1)>=0 && (rabbitXPos+1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldRabbitXPos = rabbitXPos;
													//change to new coordinates
				this.setRabbitXPos(rabbitXPos+1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[rabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[4] != null) && surroundingCells[4].getClass().equals(Grass.class)) {
//		(xPos+1)(yPos+1)	
			if((rabbitYPos+1)>=0 && (rabbitYPos+1)<yBounds && (rabbitXPos+1)>=0 && (rabbitXPos+1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos+1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos+1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[5] != null) && surroundingCells[5].getClass().equals(Grass.class)) {
//			(yPos+1)
			if((rabbitYPos+1)>=0 && (rabbitYPos+1)<yBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				
				this.setRabbitYPos(rabbitYPos+1);		//change to new coordinates				
				
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][rabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[6] != null) && surroundingCells[6].getClass().equals(Grass.class)) {
//			(xPos-1)(yPos+1)
			if((rabbitYPos+1)>=0 && (rabbitYPos+1)<yBounds && (rabbitXPos-1)>=0 && (rabbitXPos-1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos+1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos-1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[7] != null) && surroundingCells[7].getClass().equals(Grass.class)) {
//			(xPos-1)
			if((rabbitXPos-1)>=0 && (rabbitXPos-1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldRabbitXPos = rabbitXPos;
														//change to new coordinates
				this.setRabbitXPos(rabbitXPos-1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[rabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}
		
//		...
//		..
//		.
		return environment;		// return as is, if no food
	}
	
	
//	only called by RABBIT after checking that it is safe and there's NO food
	public Environment move(Environment environment) {
//		to move; means to change cell to one random of the 'null' surrounding cells
		
		List<Integer> availableMoves = new ArrayList<Integer>();
		for (int i=0; i<surroundingCells.length;i++) {
			if (surroundingCells[i] == null) {
				availableMoves.add(i);
			}
		}
		
//		choose from the nulls randomly.. is it possible that you will have no nulls ? idk so i guard
		if (availableMoves.size()!=0) {
			int idx = new Random().nextInt(availableMoves.size());
			int indexToGo = availableMoves.get(idx);  //this is the index of a random free loc 
			
//			that means surroundCell[0] was null
//			===
			if ((indexToGo == 0)) {
//				(xPos-1)(yPos-1)
//				basically once what we get back is a 'sure' null position, thus no need for extra checks that protect against OUTOFBOUNDS here
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos-1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos-1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
				
			}else if (indexToGo == 1) {
//				(yPos-1)
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				
				this.setRabbitYPos(rabbitYPos-1);		//change to new coordinates				

				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][rabbitXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 2) {
//				(xPos+1)(yPos-1)
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos-1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos+1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 3) {
//				(xPos+1)
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldRabbitXPos = rabbitXPos;
													//change to new coordinates
				this.setRabbitXPos(rabbitXPos+1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[rabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 4) {
//			(xPos+1)(yPos+1)	
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos+1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos+1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 5) {
//				(yPos+1)
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				
				this.setRabbitYPos(rabbitYPos+1);		//change to new coordinates				
				
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][rabbitXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 6) {
//				(xPos-1)(yPos+1)
				Object[][] newenvMap = environment.getEnvMap();
				int oldRabbitYPos = rabbitYPos;
				int oldRabbitXPos = rabbitXPos;
				this.setRabbitYPos(rabbitYPos+1);		//change to new coordinates				
				this.setRabbitXPos(rabbitXPos-1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[oldRabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 7) {
//				(xPos-1)
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldRabbitXPos = rabbitXPos;
														//change to new coordinates
				this.setRabbitXPos(rabbitXPos-1);
				newenvMap[rabbitYPos][rabbitXPos] = this;		//move rabbit to there
				newenvMap[rabbitYPos][oldRabbitXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			};
			

			
		}
//		...
//		..
//		.
		return environment;
		
		
	};
	
	
	public Environment think(Environment environment) throws IOException {
//		to think; think method should start with looking around:
//		Rabbit calls 'LookAt()' then populatesSurroundingCell
		this.LookAt(environment,rabbitYPos,rabbitXPos);
		this.populateSurroundingCell();
		
//		Rabbit checks inDanger()?
		if(this.inDanger()) {
//			that means you're in danger, RunAway()
			return (this.runAway(environment));
		}else {
//			that means you're Safe --> Eat or Move ()
			if(this.isTherePlant()) {
//				that means there's food so eat it
				return (this.eat(environment));//*
			}else {
//				that means there's no food so move to another null cell
				return (this.move(environment));//*
			}
			
		}
		
	};
	

	
//	for image
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	
	
	
	
//	for sightofRabbit
	public Object[] getLookDown() {
		return lookDown;
	}
	public void setLookDown(Object[] lookDown) {
		this.lookDown = lookDown;
	}
	public Object[] getLookUp() {
		return lookUp;
	}
	public void setLookUp(Object[] lookUp) {
		this.lookUp = lookUp;
	}


	
	public int getRabbitYPos() {
		return rabbitYPos;
	}
	public void setRabbitYPos(int rabbitYPos) {
		this.rabbitYPos = rabbitYPos;
	}
	public int getRabbitXPos() {
		return rabbitXPos;
	}
	public void setRabbitXPos(int rabbitXPos) {
		this.rabbitXPos = rabbitXPos;
	}
	
	
	
	
}
