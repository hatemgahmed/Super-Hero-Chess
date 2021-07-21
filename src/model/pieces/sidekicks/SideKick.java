package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public abstract class SideKick extends Piece {

	public SideKick(Player player, Game game, String name) {
		super(player, game, name);
	}
    
	
	public void updateMove(int i , int j,Direction r) throws OccupiedCellException{
		   if(getGame().getCellAt(i, j).getPiece()==null) {
		   super.updateMove(i,j,r);
		   return;
		   }
		   else {
			   if(getGame().getCellAt(i, j).getPiece().getOwner()==getGame().getCurrentPlayer())
			        throw new OccupiedCellException("sa7bak wa2ef fe el 7etta de !",this, r);
		       Piece p = getGame().getCellAt(i, j).getPiece();
			   attack(getGame().getCellAt(i, j).getPiece());
			   if(getGame().getCellAt(i, j).getPiece()==null) {
			   p.getGame().getCellAt(this.getPosI(), this.getPosJ()).getPiece().updateMove(i, j, r);
			   return;
			   }
			   else{
				   getGame().switchTurns();
			   }
		}
	  }
	
	public void attack(Piece p) {
		Piece target = p;
		  Piece h=null;
		if(target.getOwner()==getOwner())
			return;
		if(target instanceof Armored) {
			if(((Armored) target).isArmorUp()) {
				((Armored) target).setArmorUp(false);
				
				return;
			}
			else {
					//put Armored of current player in the place of the dead armored p
			    h = new Armored(p.getGame().getCurrentPlayer(), p.getGame(),
						(p.getGame().getCurrentPlayer() == p.getGame().getPlayer1()) ? "Captain America" : "Darth Vader");
			    target.getOwner().getDeadCharacters().add(target);	
				target.getOwner().getAliveCharacters().remove(target);
			    target.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
				getOwner().setPayloadPos(getOwner().getPayloadPos()+1);
				
				
			}
		}
			else {	
				// payload update
				if(target instanceof Hero) {
					getOwner().setPayloadPos(getOwner().getPayloadPos()+1);
				}
		target.getOwner().getDeadCharacters().add(target);
		target.getOwner().getAliveCharacters().remove(target);
		if(target instanceof SideKick) {
			getOwner().setSideKilled(getOwner().getSideKilled()+1);
			if(getOwner().getSideKilled()%2==0)
				getOwner().setPayloadPos(getOwner().getPayloadPos()+1);
		}
		
		target.getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null);
		int i = p.getPosI();
		int j = p.getPosJ();
		
		

   if (p instanceof Medic) {
		//put Medic of current player in the place of the dead Medic p
	   h = new Medic(p.getGame().getCurrentPlayer(), p.getGame(),
				(p.getGame().getCurrentPlayer() == p.getGame().getPlayer1()) ? "Groot" : "Voldemort");

}
   else
if (p instanceof Ranged) {
	//put Ranged of current player in the place of the dead Ranged p
	 h = new Ranged(p.getGame().getCurrentPlayer(), p.getGame(),
				(p.getGame().getCurrentPlayer() == p.getGame().getPlayer1()) ? "Green Arrow" : "Deadshot");


}
else
	
if (p instanceof Speedster) {
	//put Speedster of current player in the place of the dead Speedster p
	 h = new Speedster(p.getGame().getCurrentPlayer(), p.getGame(),
				(p.getGame().getCurrentPlayer() == p.getGame().getPlayer1()) ? "Salah" : "Venom");


}
else
	
if (p instanceof Super) {
	//put Super of current player in the place of the dead Super p
	 h = new Super(p.getGame().getCurrentPlayer(), p.getGame(),
				(p.getGame().getCurrentPlayer() == p.getGame().getPlayer1()) ? "Guko" : "Thanos");


}
else
	
if (p instanceof Tech) {
	//put Tech of current player in the place of the dead Tech p
	 h = new Tech(p.getGame().getCurrentPlayer(), p.getGame(),
				(p.getGame().getCurrentPlayer() == p.getGame().getPlayer1()) ? "Batman" : "Joker");


}
else {
	h = this;
}
}
		h.setPosI(this.getPosI());
		h.setPosJ(this.getPosJ());
		getGame().getCellAt(getPosI(), getPosJ()).setPiece(h);
		getOwner().getAliveCharacters().remove(this);
		getOwner().getAliveCharacters().add(h);
		
	getGame().checkWinner();
	
}

	
	@Override
	public String toString() {
		return "K";
	}
}
