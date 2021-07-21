package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Ranged extends ActivatablePowerHero {

	public Ranged(Player player, Game game, String name) {
		super(player, game, name);
	}

	public String toString() {
		return "R";
	}
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException {
		 if(getGame().getCurrentPlayer()!=getOwner()) {
		    	throw new WrongTurnException("Mesh dorak ya Captain",this);
		    }
		if(isPowerUsed())
			throw new PowerAlreadyUsedException("The power is already used" +"\n" +"هي فراخ يا ابني ؟",this);
		int x = getPosI();
		int y = getPosJ();
		try {
			switch(d) {
		case UP : powerHelper(-1,0,d);break;
		case DOWN :powerHelper(1,0,d);break;
		case LEFT :powerHelper(0,-1,d);break;
		case RIGHT : powerHelper(0,1,d);break;
		default : throw new InvalidPowerDirectionException("Can't use power in this direction",this, d);
		}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			throw new InvalidPowerDirectionException("Keda mish btdrab 7ad ! shooflak direction tany",this, d);
		}
		getGame().switchTurns();
	}
	
	public void powerHelper(int x,int y,Direction r) throws InvalidPowerDirectionException {
		int i = getPosI()+x;
		int j = getPosJ()+y;
		while(true) {
			if(getGame().getCellAt(i, j).getPiece()==null) {
				i+=x;
				j+=y;
				continue;
			}
			if(getGame().getCellAt(i, j).getPiece().getOwner()==getOwner())
				throw new InvalidPowerDirectionException("Btdrab sa7bak ya ahbal ?!",this, r);
			if(getGame().getCellAt(i, j).getPiece()!= null) {
			attack(getGame().getCellAt(i, j).getPiece());
			break;
			}
			i+=x;
			j+=y;
		}
		setPowerUsed(true);
	}
}
