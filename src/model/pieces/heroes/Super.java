package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Super extends ActivatablePowerHero {

	public Super(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "P";
	}
	@Override
	public void move(Direction r) throws OccupiedCellException ,UnallowedMovementException, WrongTurnException{
	    if(getGame().getCurrentPlayer()!=getOwner()) {
	    	throw new WrongTurnException("Mesh dorak ya Captain",this);
	    }
		switch(r) {
		case UP : this.moveUp();break;
		case DOWN : this.moveDown();break;
		case LEFT : this.moveLeft();break;
		case RIGHT : this.moveRight();break;
		default : throw new UnallowedMovementException("Can't move in this direction",this, r);
		}
		

	}
	
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException {
		 if(getGame().getCurrentPlayer()!=getOwner()) {
		    	throw new WrongTurnException("Mesh dorak ya Captain",this);
		    }
		 if(isPowerUsed())
			throw new PowerAlreadyUsedException("The power is already used" +"\n" +"هي فراخ يا ابني ؟",this);
		setPowerUsed(true);
		try {
		switch(d) {
		case UP : attackHelper(getPosI()-1, getPosJ());break;
		case DOWN :attackHelper(getPosI()+1, getPosJ());break;
		case LEFT :attackHelper(getPosI(), getPosJ()-1);break;
		case RIGHT : attackHelper(getPosI(), getPosJ()+1);break;
		default : throw new InvalidPowerDirectionException("Can't use power in this direction",this, d);
		}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			
		}
		catch(NullPointerException k) {}
		try {
			switch(d) {
			case UP : attackHelper(getPosI()-2, getPosJ());break;
			case DOWN :attackHelper(getPosI()+2, getPosJ());break;
			case LEFT :attackHelper(getPosI(), getPosJ()-2);break;
			case RIGHT : attackHelper(getPosI(), getPosJ()+2);break;
			default : throw new InvalidPowerDirectionException("Can't use power in this direction",this, d);
			}
			}
			catch(ArrayIndexOutOfBoundsException e) {
				
			}
			catch(NullPointerException k) {}
		getGame().switchTurns();
	}
	
	public void attackHelper(int x,int y) {
		if(!getGame().getCellAt(x, y).getPiece().getOwner().equals(getOwner()))
			attack(getGame().getCellAt(x, y).getPiece());
	}

}
