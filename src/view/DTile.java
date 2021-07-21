package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import model.game.Cell;

public class DTile extends Polygon{

	 private final static int polyX[] = {102,150,198,150};
     private final static int polyY[] = {500,524,500,476};
     private static final int halfHeight=48;
     private static final int halfWidth=24;
	 private int i; 
	 private int j;
	 private int x;
	 private int y;
	 
	 public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}
	 
     public DTile(int i,int j) {
		this.i=i;
		this.j=j;
		this.x=polyX[0]+(j-i)*halfHeight;
    	this.y=polyY[0]+(j+i)*halfWidth;
         for(int k=0;k<polyX.length;k++) {
        	 addPoint(polyX[k]+(j-i)*halfHeight,polyY[k]+(i+j)*halfWidth);
         }
         
	}
	
}