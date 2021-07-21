package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public abstract class ActivatablePowerHero extends Hero {

	private boolean powerUsed= false;

	public ActivatablePowerHero(Player player, Game game, String name) {
		super(player, game, name);
	}

	public boolean isPowerUsed() {
		return powerUsed;
	}

	public void setPowerUsed(boolean powerUsed) {
		this.powerUsed = powerUsed;
	}
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException, InvalidPowerTargetException {
		 if(getGame().getCurrentPlayer()!=getOwner()) {
		    	throw new WrongTurnException("Mesh dorak ya Captain",this);
		    }		
		 if(isPowerUsed())
		    	throw new PowerAlreadyUsedException("The power is already used" +"\n" +"هي فراخ يا ابني ؟",this);
	}
}
