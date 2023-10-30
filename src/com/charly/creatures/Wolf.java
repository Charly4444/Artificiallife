package com.charly.creatures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.charly.simple.Environment;

public class Wolf extends Animal {
	
	private int wolfYPos;
	private int wolfXPos;
	private BufferedImage image;
	private String filePath = "src\\com\\charly\\assets\\Wolf.png";
	
//	what the Wolf sees
	private Object[] lookDown = new Object[14];
	private Object[] lookUp = new Object[14];
	private Object[] surroundingCells = new Object[8];
	
	public Wolf(Integer wolfYPos, Integer wolfXPos) throws IOException {
		super("W", wolfYPos, wolfXPos, 8);	//added name and liveTime
		this.wolfYPos = wolfYPos;
		this.wolfXPos = wolfXPos;
		
		
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
		 * | 5  | 0  | "W" |  4  |  13 | 
		 * 
		 * 
		 * AND, FACING DOWN FOR INSTANCE:
		 * 
		 * | 5  | 0  | "W" |  4  |  13 |
		 *  ---- ---- ----- ----- -----
		 * | 6  | 1  |  2  |  3  |  12 |
		 *  ---- ---- ----- ----- -----
		 * | 7  | 8  |  9  | 10  |  11 | 
		 *  ---- ---- ----- ----- -----
		 * 
		 * 
		 * The Wolf can eat from inner cells: {0, 1, 2, 3, 4}
		 * */
		
		int xBounds = environment.sizeX;
		int yBounds = environment.sizeY;
		
//		the Wolf or anything knows it is after creation; i.e: (yPos,xPos) it can see 14 cells down
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
	
	
//	only called by Wolf ONLY AFTER CALLING "LookAt() !!!"
	public void populateSurroundingCell() {
//		observe surroundingCells too...		so he scans round in a clockwise movt.
		/* | 0  |  1  |  2  |
		 * | 7  | "W" |  3  |
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
	
	
//	isthere_RabbitorNot?
	public Boolean isThereRabbit() {
		Boolean food = false;
		for (Object o: surroundingCells) {
			if ((o != null)&&(o.getClass().equals(Rabbit.class))) {
				food = true;
			}
		}
		return food;
	}
	
	
	
//	only called by RABBIT after checking that it is safe and there's food
	public Environment eat(Environment environment) throws IOException {
		
//		Eating logic
		int xBounds = environment.sizeX;
		int yBounds = environment.sizeY;
		
		if ((surroundingCells[0] != null) && surroundingCells[0].getClass().equals(Rabbit.class)) {
//			(xPos-1)(yPos-1)
			if((wolfYPos-1)>=0 && (wolfYPos-1)<yBounds && (wolfXPos-1)>=0 && (wolfXPos-1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				int oldWolfXPos = wolfXPos;
				this.setWolfYPos(wolfYPos-1);		//change to new coordinates				
				this.setWolfXPos(wolfXPos-1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][oldWolfXPos] = null;		//delete previous location
				
//				rewrite the environment
				environment.setEnvMap(newenvMap);
//				return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[1] != null) && surroundingCells[1].getClass().equals(Rabbit.class)) {
//			(yPos-1)
			if((wolfYPos-1)>=0 && (wolfYPos-1)<yBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				
				this.setWolfYPos(wolfYPos-1);		//change to new coordinates				

				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][wolfXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[2] != null) && surroundingCells[2].getClass().equals(Rabbit.class)) {
//			(xPos+1)(yPos-1)
			if((wolfYPos-1)>=0 && (wolfYPos-1)<yBounds && (wolfXPos+1)>=0 && (wolfXPos+1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				int oldWolfXPos = wolfXPos;
				this.setWolfYPos(wolfYPos-1);		//change to new coordinates				
				this.setWolfXPos(wolfXPos+1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][oldWolfXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[3] != null) && surroundingCells[3].getClass().equals(Rabbit.class)) {
//			(xPos+1)
			if((wolfXPos+1)>=0 && (wolfXPos+1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldWolfXPos = wolfXPos;
													//change to new coordinates
				this.setWolfXPos(wolfXPos+1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[wolfYPos][oldWolfXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[4] != null) && surroundingCells[4].getClass().equals(Rabbit.class)) {
//		(xPos+1)(yPos+1)	
			if((wolfYPos+1)>=0 && (wolfYPos+1)<yBounds && (wolfXPos+1)>=0 && (wolfXPos+1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				int oldWolfXPos = wolfXPos;
				this.setWolfYPos(wolfYPos+1);		//change to new coordinates				
				this.setWolfXPos(wolfXPos+1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][oldWolfXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[5] != null) && surroundingCells[5].getClass().equals(Rabbit.class)) {
//			(yPos+1)
			if((wolfYPos+1)>=0 && (wolfYPos+1)<yBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				
				this.setWolfYPos(wolfYPos+1);		//change to new coordinates				
				
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][wolfXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[6] != null) && surroundingCells[6].getClass().equals(Rabbit.class)) {
//			(xPos-1)(yPos+1)
			if((wolfYPos+1)>=0 && (wolfYPos+1)<yBounds && (wolfXPos-1)>=0 && (wolfXPos-1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				int oldWolfXPos = wolfXPos;
				this.setWolfYPos(wolfYPos+1);		//change to new coordinates				
				this.setWolfXPos(wolfXPos-1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][oldWolfXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
			}
			
		}else if ((surroundingCells[7] != null) && surroundingCells[7].getClass().equals(Rabbit.class)) {
//			(xPos-1)
			if((wolfXPos-1)>=0 && (wolfXPos-1)<xBounds) {
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldWolfXPos = wolfXPos;
														//change to new coordinates
				this.setWolfXPos(wolfXPos-1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[wolfYPos][oldWolfXPos] = null;		//delete previous location
				
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
	
//	only called by WOLF after seeing there's NO food
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
				int oldWolfYPos = wolfYPos;
				int oldWolfXPos = wolfXPos;
				this.setWolfYPos(wolfYPos-1);		//change to new coordinates				
				this.setWolfXPos(wolfXPos-1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][oldWolfXPos] = null;		//delete previous location
				
//					rewrite the environment
				environment.setEnvMap(newenvMap);
//					return the new face of the environment
				return environment;
				
			}else if (indexToGo == 1) {
//				(yPos-1)
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				
				this.setWolfYPos(wolfYPos-1);		//change to new coordinates				

				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][wolfXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 2) {
//				(xPos+1)(yPos-1)
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				int oldWolfXPos = wolfXPos;
				this.setWolfYPos(wolfYPos-1);		//change to new coordinates				
				this.setWolfXPos(wolfXPos+1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][oldWolfXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 3) {
//				(xPos+1)
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldWolfXPos = wolfXPos;
													//change to new coordinates
				this.setWolfXPos(wolfXPos+1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[wolfYPos][oldWolfXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 4) {
//			(xPos+1)(yPos+1)	
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				int oldWolfXPos = wolfXPos;
				this.setWolfYPos(wolfYPos+1);		//change to new coordinates				
				this.setWolfXPos(wolfXPos+1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][oldWolfXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 5) {
//				(yPos+1)
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				
				this.setWolfYPos(wolfYPos+1);		//change to new coordinates				
				
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][wolfXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 6) {
//				(xPos-1)(yPos+1)
				Object[][] newenvMap = environment.getEnvMap();
				int oldWolfYPos = wolfYPos;
				int oldWolfXPos = wolfXPos;
				this.setWolfYPos(wolfYPos+1);		//change to new coordinates				
				this.setWolfXPos(wolfXPos-1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[oldWolfYPos][oldWolfXPos] = null;		//delete previous location
				
//						rewrite the environment
				environment.setEnvMap(newenvMap);
//						return the new face of the environment
				return environment;
				
			}else if (indexToGo == 7) {
//				(xPos-1)
				Object[][] newenvMap = environment.getEnvMap();
				
				int oldWolfXPos = wolfXPos;
														//change to new coordinates
				this.setWolfXPos(wolfXPos-1);
				newenvMap[wolfYPos][wolfXPos] = this;		//move rabbit to there
				newenvMap[wolfYPos][oldWolfXPos] = null;		//delete previous location
				
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
	
	
//	for image
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
	
	
	public Environment think(Environment environment) throws IOException {
//		to think; think method should start with looking around:
//		Wolf calls 'LookAt()' then populatesSurroundingCell
		this.LookAt(environment,wolfYPos,wolfXPos);
		this.populateSurroundingCell();
		
//		wolf is either going to eat or  move()
		if(this.isThereRabbit()) {
			return (this.eat(environment));//*
		}else {
			return (this.move(environment));//*
			
		}
			
		
	};
	
	
	public int getWolfYPos() {
		return wolfYPos;
	}
	public void setWolfYPos(int wolfYPos) {
		this.wolfYPos = wolfYPos;
	}
	
	public int getWolfXPos() {
		return wolfXPos;
	}
	public void setWolfXPos(int wolfXPos) {
		this.wolfXPos = wolfXPos;
	}
	
	//	for Wolfsight
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
}
