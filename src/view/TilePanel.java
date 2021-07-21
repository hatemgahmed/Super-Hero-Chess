
package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.game.Game;
import model.pieces.Piece;
import model.pieces.heroes.Armored;

public class TilePanel extends JPanel{
	
	
	private Game game;
	private Tile[][] board;
	private DTile[][] DirectionBoard;
	private JPanel extra;
	private JLabel txt1;
	private JLabel txt2;
	
	private JButton move;
	private JButton power;
	private JButton unselect;
	
	JLabel getTxt1() {
		return txt1;
	}

	Game getGame() {
		return game;
	}

	Tile[][] getBoard() {
		return board;
	}

	DTile[][] getDirectionBoard() {
		return DirectionBoard;
	}

	JLabel getTxt2() {
		return txt2;
	}
	public JPanel getExtra() {
		return extra;
	}

	public TilePanel(Game game,Tile[][] board,DTile[][] DirectionBoard) {
		super();
		this.game = game;
		this.board=board;
		this.DirectionBoard=DirectionBoard;
		setLayout(null);
		setOpaque(false);
        setVisible(true);
        setSize(1066,647);
        setLocation(150,103);
        setBorder(null);
        extra = new JPanel(new GridLayout(1,3));
        extra.setOpaque(false);
        move = new JButton(new ImageIcon("move white.png"));
        move.setBackground(Color.YELLOW);
        move.setOpaque(false);
        move.setBorder(BorderFactory.createLineBorder(null));
        power = new JButton(new ImageIcon("Power.png"));
        power.setOpaque(false);
        power.setBackground(Color.yellow);
        power.setBorder(BorderFactory.createLineBorder(null));
        unselect = new JButton(new ImageIcon("Unselect.png"));
        unselect.setBackground(Color.YELLOW);
        unselect.setOpaque(false);
        unselect.setBorder(null);
        extra.setSize(240, 80);
        extra.setLocation(800,500);
        extra.setVisible(true);
        extra.add(move);
        extra.add(unselect);
        extra.add(power);
        add(extra);
	}
	
	JButton getMove() {
		return move;
	}

	JButton getPower() {
		return power;
	}

	JButton getUnselect() {
		return unselect;
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        BufferedImage CA=null;
        BufferedImage Hulk=null;
        BufferedImage IR=null;
        BufferedImage Cyborg=null;
        BufferedImage Batman =null;
        BufferedImage Aquaman=null;
        BufferedImage DarthVader =null;
        BufferedImage Thanos =null;
        BufferedImage Voldemort =null;
        BufferedImage Guko =null;
        BufferedImage Venom =null;
        BufferedImage Salah =null;
        BufferedImage BB8 =null;
        BufferedImage Groot =null;
        BufferedImage Deadshot =null;
        BufferedImage Joker =null;
        BufferedImage Stormtrooper =null;
        BufferedImage Greenarrow =null;
        BufferedImage arrows2 = null;
        BufferedImage CA2 = null;
        BufferedImage DV2 = null;
		try {
			CA = ImageIO.read(new File("Captain America.png"));
			IR = ImageIO.read(new File("Iron Man.png"));
			Batman = ImageIO.read(new File("Batman.png"));
			DarthVader = ImageIO.read(new File("Darth Vader.png"));
			Thanos = ImageIO.read(new File("Thanos.png"));
			Voldemort = ImageIO.read(new File("Voldemort.png"));
			Guko = ImageIO.read(new File("Guko.png"));
			Venom = ImageIO.read(new File("Venom.png"));
			Salah = ImageIO.read(new File("Salah.png"));
			BB8 = ImageIO.read(new File("BB8.png"));
			Groot = ImageIO.read(new File("Groot.png"));
			Deadshot = ImageIO.read(new File("Deadshot.png"));
			Joker = ImageIO.read(new File("Joker.png"));
			Stormtrooper = ImageIO.read(new File("Stormtrooper.png"));
			Greenarrow = ImageIO.read(new File("Green Arrow.png"));
			arrows2 = ImageIO.read(new File("White Arrows.png"));
			CA2 = ImageIO.read(new File("Captain America no Shield.png"));
			DV2 = ImageIO.read(new File("Darth Vader no shield.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        for(int i =0;i<7;i++) {
        	for(int j =0;j<6;j++) {
        		if((i+j)%2==0)
        		 g.setColor(Color.BLACK);
        		else
           		 g.setColor(Color.WHITE);
        		Tile t = new Tile(i,j,game.getCellAt(i, j));
        		g.fillPolygon(t);
        		board[i][j]=t;
        		g.setColor(Color.BLACK);
        		g.drawPolygon(t);
        	}
        }
        
        Polygon under1 = new Polygon(new int[] {80,500,500,80},new int[] {360,570,632,422},4);
        Polygon under2= new Polygon(new int[] {500,990,990,500},new int[] {570,325,387,632},4);
        g.setColor(Color.white.darker().darker());
        g.fillPolygon(under1);
        g.fillPolygon(under2);
        g.setColor(Color.BLACK);
        g.drawPolygon(under1);
        g.drawPolygon(under2);
        //Direction tiles
       
        g.setColor(Color.WHITE);
        for(int i =0;i<3;i++) {
        	for(int j =0;j<3;j++) {
        		g2.setColor(new Color(0,0,0,0));
        		DTile Dt = new DTile(i,j);
        		DirectionBoard[i][j]=Dt;
        		g2.fillPolygon(Dt);
        		g.setColor(new Color(0,0,0,0));
        		g.drawPolygon(Dt);
        	}
        } 
       
        g2.drawImage(arrows2, 35 , 483, null);
        
        for(int i =0;i<7;i++) {
        	for(int j =0;j<6;j++) { 
        		Piece check = game.getCellAt(i, j).getPiece();
        		if(game.getCellAt(i, j).getPiece()!= null)
        		switch(game.getCellAt(i, j).getPiece().getName()) {
        		case "Captain America" :
        			if(((Armored) check).isArmorUp())
        				g2.drawImage(CA, board[i][j].getX()+40,board[i][j].getY()-130, null);
        			else
        				g2.drawImage(CA2, board[i][j].getX()+40,board[i][j].getY()-150, null);
        			break;
        		case "Hulk" : g2.drawImage(Hulk, board[i][j].getX()+5,board[i][j].getY()-100, null);break;
        		case "Batman" :  g2.drawImage(Batman, board[i][j].getX()+15,board[i][j].getY()-110, null);break;
        		case "Cyborg" : g2.drawImage(Cyborg, board[i][j].getX()+40,board[i][j].getY()-100, null);break;
        		case "Iron Man" :  g2.drawImage(IR, board[i][j].getX()+15,board[i][j].getY()-100, null);break;
        		case "Aqua Man" :  g2.drawImage(Aquaman, board[i][j].getX()+30,board[i][j].getY()-140, null);break;
        		case "Darth Vader" :
        			if(((Armored) check).isArmorUp())
    				g2.drawImage(DarthVader, board[i][j].getX()-8,board[i][j].getY()-115, null);
    			else
    				g2.drawImage(DV2, board[i][j].getX()-10,board[i][j].getY()-130, null);
    			break;
        		case "Thanos" : g2.drawImage(Thanos, board[i][j].getX()+30,board[i][j].getY()-130, null);break;
        		case "Voldemort" : g2.drawImage(Voldemort, board[i][j].getX()+10,board[i][j].getY()-110, null);break;
        		case "Guko" : g2.drawImage(Guko, board[i][j].getX()+45,board[i][j].getY()-130, null);break;
        		case "Venom" : g2.drawImage(Venom, board[i][j].getX()+15,board[i][j].getY()-120, null);break;
        		case "Salah" : g2.drawImage(Salah, board[i][j].getX()+20,board[i][j].getY()-130, null);break;
        		case "BB8" : g2.drawImage(BB8, board[i][j].getX()+40,board[i][j].getY()-90, null);break;
        		case "Groot" : g2.drawImage(Groot, board[i][j].getX(),board[i][j].getY()-150, null);break;
        		case "Deadshot" : g2.drawImage(Deadshot, board[i][j].getX()+20,board[i][j].getY()-135, null);break;
        		case "Joker" : g2.drawImage(Joker, board[i][j].getX()-5,board[i][j].getY()-125, null);break;
        		case "Stormtrooper" : g2.drawImage(Stormtrooper, board[i][j].getX()+20,board[i][j].getY()-130, null);break;
        		case "Green Arrow" : g2.drawImage(Greenarrow, board[i][j].getX()+25,board[i][j].getY()-130, null);break;
        		}
        		
        	        
        	}
        }
       
        
        
        
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1366, 768);
    }
}