package model.pieces.heroes;

import exceptions.OccupiedCellException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;

public class Speedster extends NonActivatablePowerHero {

	public Speedster(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public String toString() {
		return "S";
	}
	
	public void moveDownLeft() throws OccupiedCellException {
		int x = (this.getPosI()+2);	
		int y = (this.getPosJ()-2);	
		this.updateMove((x<0? x+7:x%7), (y<0? y+6:y%6), Direction.DOWNLEFT);
		
	}

	public void moveDownRight() throws OccupiedCellException {
		int x = (this.getPosI()+2);	
		int y = (this.getPosJ()+2);	
		this.updateMove((x<0? x+7:x%7), (y<0? y+6:y%6), Direction.DOWNRIGHT);
		
	}

	public void moveDown() throws OccupiedCellException {
		int x = (this.getPosI()+2);	
		this.updateMove((x<0? x+7:x%7),getPosJ(), Direction.DOWN);
	}

	public void moveRight() throws OccupiedCellException {
		int x = (this.getPosJ()+2);	
		this.updateMove(getPosI(),(x<0? x+6:x%6), Direction.RIGHT);
		
	}

	public void moveLeft() throws OccupiedCellException {
		int x = (this.getPosJ()-2);	
		this.updateMove(getPosI(),(x<0? x+6:x%6), Direction.LEFT);
		
	}

	public void moveUp() throws OccupiedCellException {
		int x = (this.getPosI()-2);		
		this.updateMove((x<0? x+7:x%7), getPosJ(), Direction.UPLEFT);
	}

	public void moveUpRight() throws OccupiedCellException {
		int x = (this.getPosI()-2);	
		int y = (this.getPosJ()+2);	
		this.updateMove((x<0? x+7:x%7), (y<0? y+6:y%6),Direction.UPRIGHT);
		
	}

	public void moveUpLeft() throws OccupiedCellException {
		int x = (this.getPosI()-2);	
		int y = (this.getPosJ()-2);	
		this.updateMove((x<0? x+7:x%7), (y<0? y+6:y%6),Direction.UP);
	}
}
