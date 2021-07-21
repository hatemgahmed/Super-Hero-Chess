package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.pieces.Piece;
import model.pieces.heroes.*;

public class SideKickP1 extends SideKick {

	public SideKickP1(Game game, String name) {
		super(game.getPlayer1(), game, name);
	}

	@Override
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
		default : throw new UnallowedMovementException("Sidekick can't move back!",this, r);
		}
		

	}

	
}


