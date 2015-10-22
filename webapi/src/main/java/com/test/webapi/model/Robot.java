package com.test.webapi.model;

public class Robot {
	
	Position position;
	Grid grid;
	
	public Robot(Grid grid){
		this.grid = grid;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Grid getGrid() {
		return grid;
	}
	
	private void stepEast(){
		if(position.xpos < grid.getBreadth()-1){
			position.xpos++;
		}	
	}
	
	private void stepNorth(){
		if(position.ypos < grid.getLength()-1){
			position.ypos++;
		}			
	}	
	
	private void stepWest(){
		if(position.xpos > -1){
			position.xpos--;
		}		
	}	
	
	private void stepSouth(){
		if(position.ypos > -1){
			position.ypos--;
		}			
	}		
	
	public void moveForward(){
		if(position == null){
			return;
		}		
		switch(position.angle){
			case NORTH: stepNorth();
						break;
			case SOUTH: stepSouth();
						break;
			case EAST:  stepEast();
						break;
			case WEST:  stepWest();
						break;
		}
	}
	
	public void turnLeft(){
		if(position == null){
			return;
		}
		switch(position.angle){
			case NORTH: position.setAngle(Direction.WEST);
						break;
			case SOUTH: position.setAngle(Direction.EAST);
						break;
			case EAST:  position.setAngle(Direction.NORTH);
						break;
			case WEST:  position.setAngle(Direction.SOUTH);
						break;
		}		
	}
	
	public void turnRight(){
		if(position.angle == null){
			return;
		}
		switch(position.angle){
		case NORTH: position.setAngle(Direction.EAST);
					break;
		case SOUTH: position.setAngle(Direction.WEST);
					break;
		case EAST:  position.setAngle(Direction.SOUTH);
					break;
		case WEST:  position.setAngle(Direction.NORTH);
					break;
		}			
	}
}
