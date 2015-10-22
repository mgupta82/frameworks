package com.test.webapi.model;

import java.io.Serializable;

public class Position implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 594125414560213828L;
	
	int xpos;
	int ypos;
	Direction angle;
	
	public Position(int xpos,int ypos,Direction angle){
		this.xpos = xpos;
		this.ypos = ypos;
		this.angle = angle;
	}
	
	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public Direction getAngle() {
		return angle;
	}

	public void setAngle(Direction angle) {
		this.angle = angle;
	}

	public String toString(){
		return xpos + "," + ypos + "," + angle;
	}
}
