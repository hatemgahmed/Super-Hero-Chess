package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;

public class SideKickP2 extends SideKick {

	public SideKickP2(Game game, String name) {
		super(game.getPlayer2(), game, name);
	}
	
	@Override
	public void move(Direction r) throws OccupiedCellException ,UnallowedMovementException, WrongTurnException{
	    if(getGame().getCurrentPlayer()!=getOwner()) {
	    	throw new WrongTurnException("Mesh dorak ya Captain",this);
	    }
		switch(r) {
		case DOWN : this.moveDown();break;
		case DOWNRIGHT : this.moveDownRight();break;
		case DOWNLEFT : this.moveDownLeft();break;
		case LEFT : this.moveLeft();break;
		case RIGHT : this.moveRight();break;
		default : throw new UnallowedMovementException("Sidekick can't move back!",this, r);
		}
		

	}


}
