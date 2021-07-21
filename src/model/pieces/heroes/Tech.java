package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Tech extends ActivatablePowerHero {

	public Tech(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "T";
	}
	@Override
	public void move(Direction r) throws OccupiedCellException ,UnallowedMovementException, WrongTurnException{
	    if(getGame().getCurrentPlayer()!=getOwner()) {
	    	throw new WrongTurnException("Mesh dorak ya Captain",this);
	    }
		switch(r) {
		case UPRIGHT : this.moveUpRight();break;
		case UPLEFT : this.moveUpLeft();break;
		case DOWNLEFT : this.moveDownLeft();break;
		case DOWNRIGHT : this.moveDownRight();break;
		default : throw new UnallowedMovementException("Can't move in this direction",this, r);
		}
		

	}
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException, InvalidPowerTargetException {
		super.usePower(d, target, newPos);
		if(newPos != null)
			teleport((int)(newPos.getX()),(int)(newPos.getY()),target);
		else
			hack(target);
		setPowerUsed(true);
	}
    public void teleport(int x , int y,Piece target) throws InvalidPowerTargetException {
    	if(getGame().getCellAt(x, y).getPiece()!=null) {
    		throw new InvalidPowerTargetException("Can't teleport to a non empty cell",this, getGame().getCellAt(x, y).getPiece());
    	}
    	if(target.getOwner()!=this.getOwner()) {
    		throw new InvalidPowerTargetException("Can't teleport an enemy piece" + "\n" + "سيب المثلث في حاله",this, getGame().getCellAt(x, y).getPiece());
    	}
    	getGame().getCellAt(target.getPosI(),target.getPosJ()).setPiece(null);
    	target.setPosI(x);
    	target.setPosJ(y);
    	getGame().getCellAt(target.getPosI(),target.getPosJ()).setPiece(target);
    }
    public void hack(Piece target) throws InvalidPowerTargetException {
    	if(target.getOwner()==getOwner()) {
    		if(target instanceof ActivatablePowerHero ) {
    			if(((ActivatablePowerHero) target).isPowerUsed())
    				((ActivatablePowerHero) target).setPowerUsed(false);
    			else
    				throw new InvalidPowerTargetException("El power lessa mawgoda asln !!",this, target);
    			}
    			else
    			if(target instanceof Armored) {
    				if(!((Armored) target).isArmorUp())
    					((Armored) target).setArmorUp(true);
    				else
    					throw new InvalidPowerTargetException("The Armor is already up",this, target);
    			}
    			else
    				throw new InvalidPowerTargetException("This piece doesn't even have a power :'(",this, target);
    		}
    		//hacking
    	else {
    		if(target instanceof ActivatablePowerHero ) {
    			if(!((ActivatablePowerHero) target).isPowerUsed()) 
    				((ActivatablePowerHero) target).setPowerUsed(true);
    			else
    				throw new InvalidPowerTargetException("Can't hack; the power is already used",this, target);}
    			else
    			if(target instanceof Armored) {
    				if(((Armored) target).isArmorUp())
    					((Armored) target).setArmorUp(false);
    				else
    					throw new InvalidPowerTargetException("Can't hack; the armor is already broken",this, target);
    			}
    			else
    				throw new InvalidPowerTargetException("De piece ghalbana malhaash power :'(",this, target);
    		}
    }
}
