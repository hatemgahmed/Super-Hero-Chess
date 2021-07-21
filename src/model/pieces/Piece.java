package model.pieces;

import exceptions.GameActionException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import exceptions.OccupiedCellException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.sidekicks.SideKick;

public abstract class Piece implements Movable {

	private String name;
	private Player owner;
	private Game game;
	private int posI;
	private int posJ;
	
	public Piece(Player p, Game g, String name) {
		this.owner = p;
		this.game = g;
		this.name = name;
	}
	
	public void attack(Piece target) {
		if(target.getOwner()==getOwner())
			return;
		if(target instanceof Armored) {
			if(((Armored) target).isArmorUp()) {
				((Armored) target).setArmorUp(false);
				
				return;
			}
		}
		target.getOwner().getDeadCharacters().add(target);
		target.getOwner().getAliveCharacters().remove(target);
		if(target instanceof SideKick) {
			getOwner().setSideKilled(getOwner().getSideKilled()+1);
		}
		// payload update
		if(target instanceof Hero) {
			getOwner().setPayloadPos(getOwner().getPayloadPos()+1);
		}
		else {
			if(getOwner().getSideKilled()%2==0)
				getOwner().setPayloadPos(getOwner().getPayloadPos()+1);
		}
		target.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
		getGame().checkWinner();
	}
	
	public String getName() {
		return name;
	}

	public int getPosI() {
		return posI;
	}

	public void setPosI(int i) {
		posI = i;
	}

	public int getPosJ() {
		return posJ;
	}

	public void setPosJ(int j) {
		posJ = j;
	}

	public Game getGame() {
		return game;
	}

	public Player getOwner() {
		return owner;
	}
     
	public void move(Direction r) throws OccupiedCellException ,UnallowedMovementException, WrongTurnException{
	    if(getGame().getCurrentPlayer()!=getOwner()) {
	    	throw new WrongTurnException("Mesh dorak ya Captain",this);
	    }
		switch(r) {
	     case UP : this.moveUp();break;
	     case UPRIGHT : this.moveUpRight();break;
	     case UPLEFT : this.moveUpLeft();break;
	     case LEFT : this.moveLeft();break;
	     case RIGHT : this.moveRight();break;
	     case DOWN : this.moveDown();break;
	     case DOWNRIGHT : this.moveDownRight();break;
	     case DOWNLEFT : this.moveDownLeft();break;
	    }
	}
	
	
		

	public void moveDownLeft() throws OccupiedCellException {
		int x = (this.getPosI()+1);	
		int y = (this.getPosJ()-1);	
		this.updateMove((x<0? x+7:x%7), (y<0? y+6:y%6), Direction.DOWNLEFT);
		
	}

	public void moveDownRight() throws OccupiedCellException {
		int x = (this.getPosI()+1);	
		int y = (this.getPosJ()+1);	
		this.updateMove((x<0? x+7:x%7), (y<0? y+6:y%6), Direction.DOWNRIGHT);
		
	}

	public void moveDown() throws OccupiedCellException {
		int x = (this.getPosI()+1);	
		this.updateMove((x<0? x+7:x%7),getPosJ(), Direction.DOWN);
	}

	public void moveRight() throws OccupiedCellException {
		int x = (this.getPosJ()+1);	
		this.updateMove(getPosI(),(x<0? x+6:x%6), Direction.RIGHT);
		
	}

	public void moveLeft() throws OccupiedCellException {
		int x = (this.getPosJ()-1);	
		this.updateMove(getPosI(),(x<0? x+6:x%6), Direction.LEFT);
		
	}

	public void moveUp() throws OccupiedCellException {
		int x = (this.getPosI()-1);		
		this.updateMove((x<0? x+7:x%7), getPosJ(), Direction.UP);
	}

	public void moveUpRight() throws OccupiedCellException {
		int x = (this.getPosI()-1);	
		int y = (this.getPosJ()+1);	
		this.updateMove((x<0? x+7:x%7), (y<0? y+6:y%6),Direction.UPRIGHT);
		
	}

	public void moveUpLeft() throws OccupiedCellException {
		int x = (this.getPosI()-1);	
		int y = (this.getPosJ()-1);	
		this.updateMove((x<0? x+7:x%7), (y<0? y+6:y%6),Direction.UPLEFT);
	}
	
	public void updateMove(int i , int j,Direction r) throws OccupiedCellException {
	   if(getGame().getCellAt(i, j).getPiece()==null) {
	   getGame().getCellAt(getPosI(), getPosJ()).setPiece(null);
	   getGame().getCellAt(i, j).setPiece(this);
	   setPosI(i);
	   setPosJ(j);
	   }
	   else {
		   if(getGame().getCellAt(i, j).getPiece().getOwner()==getGame().getCurrentPlayer())
		        throw new OccupiedCellException("sa7bak wa2ef fe el 7etta de !",this, r);
	       attack(getGame().getCellAt(i, j).getPiece());
		   if(getGame().getCellAt(i, j).getPiece()==null) {
			   getGame().getCellAt(getPosI(), getPosJ()).setPiece(null);
			   getGame().getCellAt(i, j).setPiece(this);
			   setPosI(i);
			   setPosJ(j);
	   }
	}
	   
	   
	getGame().switchTurns();
  }
}
