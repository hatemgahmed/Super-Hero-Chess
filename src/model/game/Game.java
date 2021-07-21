package model.game;

import java.util.ArrayList;
import java.util.Collections;

import model.pieces.heroes.*;
import model.pieces.sidekicks.*;

public class Game implements Cloneable{

	private final int payloadPosTarget = 6;
	private final int boardWidth = 6;
	private final int boardHeight = 7;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Cell[][] board;
	
	
	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
		board = new Cell[boardHeight][boardWidth];
		for(int i=0;i<boardHeight;i++)
			for (int j=0;j<boardWidth;j++)
				board[i][j]=new Cell();
        assemblePieces();
	}
	public Game(String Player1, String Player2) {
		this.player1 = new Player(Player1);
		this.player2 = new Player(Player2);
		currentPlayer = player1;
		board = new Cell[boardHeight][boardWidth];
		for(int i=0;i<boardHeight;i++)
			for (int j=0;j<boardWidth;j++)
				board[i][j]=new Cell();
        assemblePieces();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public int getPayloadPosTarget() {
		return payloadPosTarget;
	}

	@Override
	public String toString() {
		String s = "";
		System.out.println("      " + getPlayer2().getName());
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null)
					System.out.print("n ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.print("| ");
		for (int i = 0; i < board[0].length; i++)
			System.out.print("--");
		System.out.println("|");
		System.out.println("    " + getPlayer1().getName());
		return s;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void assemblePieces() {
		String[] s={"Captain America","Groot","Green Arrow","Salah","Guko","Batman"};
		ArrayList<Hero> Avengers = assembleHelper(player1,s);
		String[] k={"Darth Vader","Voldemort","Deadshot","Venom","Thanos","Joker"};
		ArrayList<Hero> JusticeLeague = assembleHelper(player2,k);
		Collections.shuffle(Avengers);
		Collections.shuffle(JusticeLeague);
		for(int i=0;i<6;i++) {
			Avengers.get(i).setPosI(5);
			Avengers.get(i).setPosJ(i);
			board[5][i].setPiece(Avengers.get(i));
			player1.getAliveCharacters().add(Avengers.get(i));
		}
		for(int i=0;i<6;i++) {
			JusticeLeague.get(i).setPosI(1);
			JusticeLeague.get(i).setPosJ(i);
			board[1][i].setPiece(JusticeLeague.get(i));
			player2.getAliveCharacters().add(JusticeLeague.get(i));
		}
		for(int i=0;i<6;i++) {
			SideKickP1 p=new SideKickP1(this, "BB8");
			p.setPosI(4);
			p.setPosJ(i);
			board[4][i].setPiece(p);
			player1.getAliveCharacters().add(p);
		}
		for(int i=0;i<6;i++) {
			SideKickP2 p=new SideKickP2(this, "Stormtrooper");
			p.setPosI(2);
			p.setPosJ(i);
			board[2][i].setPiece(p);
			player2.getAliveCharacters().add(p);
		}
	}
	
	public ArrayList<Hero> assembleHelper(Player p,String[] names){
		ArrayList<Hero> a = new ArrayList<Hero>();
		a.add(new Armored(p, this, names[0]));
		a.add(new Medic(p, this,  names[1]));
		a.add(new Ranged(p, this,  names[2]));
		a.add(new Speedster(p, this,  names[3]));
		a.add(new Super(p, this,  names[4]));
		a.add(new Tech(p, this,  names[5]));
		return a;
	}
	
	public Cell getCellAt(int i,int j) {
		return board[i][j];
	}
	public void switchTurns() {
		if(this.currentPlayer==player1)
		this.currentPlayer=player2;
		else
			this.currentPlayer=player1;
		
	}
	public boolean checkWinner() {
		return player1.getPayloadPos()==6||player2.getPayloadPos()==6;
	}
	
}
