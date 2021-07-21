package model.game;

import java.awt.Point;
import java.util.ArrayList;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Tech;

public class Player {

	private String name;
	private int payloadPos;
	private int sideKilled;
	private ArrayList<Piece> deadCharacters;
	private ArrayList<Piece> aliveCharacters;
	private static Direction[] directions= {Direction.RIGHT, Direction.LEFT, Direction.UP, Direction.DOWN, Direction.UPRIGHT, Direction.UPLEFT, Direction.DOWNRIGHT, Direction.DOWNLEFT};
	public ArrayList<Piece> getAliveCharacters() {
		return aliveCharacters;
	}

	void setAliveCharacters(ArrayList<Piece> aliveCharacters) {
		this.aliveCharacters = aliveCharacters;
	}

	public Player(String name) {
		this.name = name;
		this.deadCharacters = new ArrayList<Piece>();
		this.aliveCharacters=new ArrayList<Piece>();
	}

	public String getName() {
		return name;
	}

	public int getPayloadPos() {
		return payloadPos;
	}

	public void setPayloadPos(int payloadPos) {
		this.payloadPos = payloadPos;
	}

	public int getSideKilled() {
		return sideKilled;
	}

	public void setSideKilled(int sideKilled) {
		this.sideKilled = sideKilled;
	}

	public ArrayList<Piece> getDeadCharacters() {
		return deadCharacters;
	}

	public void updatePayload() {
		this.payloadPos=sideKilled/2+ deadCharacters.size();
		
		
	}
	public void play() throws PowerAlreadyUsedException, InvalidPowerDirectionException, InvalidPowerTargetException, WrongTurnException, OccupiedCellException, UnallowedMovementException
	{
		Player enemyPlayer=aliveCharacters.get(0).getGame().getPlayer1()==aliveCharacters.get(0).getGame().getCurrentPlayer()?aliveCharacters.get(0).getGame().getPlayer2():aliveCharacters.get(0).getGame().getPlayer1();
		while(enemyPlayer!=aliveCharacters.get(0).getGame().getCurrentPlayer()) {
		Direction direction=directions[getRandomIndex(8)];
		int index=getRandomIndex(aliveCharacters.size());
		Piece toPlay=aliveCharacters.get(index);
		System.out.print(toPlay.getName()+"  ");
		if(toPlay instanceof ActivatablePowerHero) {
			boolean usePower=(((int)Math.random())*10)>5;
			if(usePower) 
				if(toPlay instanceof Medic)
					((ActivatablePowerHero) toPlay).usePower(direction,
							deadCharacters.get(getRandomIndex(deadCharacters.size())),
							null);
				else
				if(toPlay instanceof Tech) {
					int powerUse=(((int)Math.random())*30+1);
					if(powerUse<=10) 
						((ActivatablePowerHero) toPlay).usePower(null, aliveCharacters.get(getRandomIndex(aliveCharacters.size())), null);
					
					else
					if(powerUse<=20) 
						((ActivatablePowerHero) toPlay).usePower(null, enemyPlayer.getAliveCharacters().get(getRandomIndex(aliveCharacters.size())), null);
					
					else
						((ActivatablePowerHero) toPlay).usePower(direction,
								aliveCharacters.get(getRandomIndex(aliveCharacters.size())),
								new Point(getRandomIndex(7), getRandomIndex(6)));
				}
				else
					((ActivatablePowerHero) toPlay).usePower(direction, null,null);
			System.out.println(" Direction "+direction);
			toPlay.move(direction);
			return;
		}
		System.out.println(" Direction "+direction);
		toPlay.move(direction);
		}
	}
	int getRandomIndex(int size) {
		return ((int)(Math.random()*size));
	}
	
}
