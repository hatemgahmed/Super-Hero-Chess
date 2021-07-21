package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Medic extends ActivatablePowerHero {

	public Medic(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "M";
	}
	
	public void move(Direction r) throws OccupiedCellException ,UnallowedMovementException, WrongTurnException{
	    if(getGame().getCurrentPlayer()!=getOwner()) {
	    	throw new WrongTurnException("Mesh dorak ya Captain",this);
	    }
		switch(r) {
		case UP : this.moveUp();break;
		case DOWN : this.moveDown();break;
		case LEFT : this.moveLeft();break;
		case RIGHT : this.moveRight();break;
		default : throw new UnallowedMovementException("This piece can't move in this direction",this, r);
		}
		

	}

	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException, InvalidPowerTargetException {
		super.usePower(d, target, newPos);
		if(!(this.getOwner().getDeadCharacters().contains(target)))
			throw new InvalidPowerTargetException("Can't revive an enemy piece",this, target);
		switch(d) {
	     case UP : this.powerUp(target);break;
	     case UPRIGHT : this.powerUpRight(target);break;
	     case UPLEFT : this.powerUpLeft(target);break;
	     case LEFT : this.powerLeft(target);break;
	     case RIGHT : this.powerRight(target);break;
	     case DOWN : this.powerDown(target);break;
	     case DOWNRIGHT : this.powerDownRight(target);break;
	     case DOWNLEFT : this.powerDownLeft(target);break;
	     default: throw new InvalidPowerDirectionException("Can't use power in this direction",this, d);
	    }
		setPowerUsed(true);
		getGame().switchTurns();
		
	}
		public void powerHelper(Piece target,int x, int y) throws InvalidPowerTargetException {
			if(getGame().getCellAt(x,y).getPiece()!=null) {
				throw new InvalidPowerTargetException("Can't revive a piece in a non empty cell",this, getGame().getCellAt(x, y).getPiece());
			}
			this.getOwner().getDeadCharacters().remove(target);
			target.getOwner().getAliveCharacters().add(target);
			if(target instanceof ActivatablePowerHero) {
				((ActivatablePowerHero) target).setPowerUsed(false);
			}	
			getGame().getCellAt(x,y).setPiece(target);
			target.setPosI(x);
			target.setPosJ(y);
			if(target instanceof Armored)
				((Armored) target).setArmorUp(true);
		}
		
	public void powerDownLeft(Piece target) throws InvalidPowerTargetException {
		int x = (this.getPosI()+1);
		int y = (this.getPosJ()-1);
		x = (x<0) ? x+7:x%7;
		y = (y<0) ? y+6:y%6;
		
		powerHelper(target, x,y);
		
	}

	public void powerDownRight(Piece target) throws InvalidPowerTargetException {
		int x = (this.getPosI()+1);	
		int y = (this.getPosJ()+1);
		x = (x<0) ? x+7:x%7;
		y = (y<0) ? y+6:y%6;
				powerHelper(target, x,y);
		
	}

	public void powerDown(Piece target) throws InvalidPowerTargetException {
		int x = (this.getPosI()+1);	

		x = (x<0) ? x+7:x%7;
		powerHelper(target, x,getPosJ());
	}

	public void powerRight(Piece target) throws InvalidPowerTargetException {
		int y = (this.getPosJ()+1);	
		y = (y<0) ? y+6:y%6;
		
		powerHelper(target, getPosI(),y);
	}

	public void powerLeft(Piece target) throws InvalidPowerTargetException {
		int y= (this.getPosJ()-1);	
		y = (y<0) ? y+6:y%6;
		
		powerHelper(target, getPosI(),y);
		
	}

	public void powerUp(Piece target) throws InvalidPowerTargetException {
		int x = (this.getPosI()-1);
		x = (x<0) ? x+7:x%7;
		powerHelper(target, x,getPosJ());
	}

	public void powerUpRight(Piece target) throws InvalidPowerTargetException {
		int x = (this.getPosI()-1);	
		int y = (this.getPosJ()+1);	
		x = (x<0) ? x+7:x%7;
		y = (y<0) ? y+6:y%6;
		powerHelper(target, x,y);
	}

	public void powerUpLeft(Piece target) throws InvalidPowerTargetException {
		int x = (this.getPosI()-1);	
		int y = (this.getPosJ()-1);	
		x = (x<0) ? x+7:x%7;
		y = (y<0) ? y+6:y%6;
		powerHelper(target, x,y);
	}
	// check later
	
}
