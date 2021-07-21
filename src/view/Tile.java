package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import model.game.Cell;

public class Tile extends Polygon{

	 private Cell cell;
	

	 private static int polyX[] = {500,570,640,570};
     private static int polyY[] = {150,115,150,185};
     private static int halfHeight=70;
     private static int halfWidth=35;
	 private int i; 
	 private int j;
	 private int x;
	 private int y;
	 
	 public Cell getCell() {
			return cell;
		}

	public void setCell(Cell cell) {
			this.cell = cell;
		}
	 
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
	 
     public Tile(int i,int j,Cell c) {
		this.cell = c;
		this.i=i;
		this.j=j;
		this.x=polyX[0]+(j-i)*halfHeight;
    	this.y=polyY[0]+(j+i)*halfWidth;
         for(int k=0;k<polyX.length;k++) {
        	 addPoint(polyX[k]+(j-i)*halfHeight,polyY[k]+(i+j)*halfWidth);
         }
         
	}
	
}
