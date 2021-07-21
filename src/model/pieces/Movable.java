package model.pieces;

import exceptions.GameActionException;
import model.game.Direction;

public interface Movable {
	public void move(Direction r)throws GameActionException;
}
