package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Target;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;


public class GUI {
	private JFrame mainMap;
	private Game game;
	private Tile[][] board;
	private DTile[][] DirectionBoard;
	private TilePanel p;
	private Tile selectedPerformer;
	private Direction selectedDirection;
	private Piece selectedTargetPiece;
	private Tile selectedTargetTile;
	private boolean TileSelected = false;
	private boolean TechSelected = false;
	private boolean MedicSelected=false;
	private boolean PieceSelected = false;
	private boolean againstComputer=false;

	private Tile Hoverer=null;
	private DeadChar[][] DCA1;
	private DeadChar[][] DCA2;

	private JLabel Current;
	private JLabel PL1;
	private JLabel PL2;
	private JPanel payload1;
	private JLabel[] payLoad1;
	private JPanel payload2;
	private JLabel[] payLoad2;
	
	private JTextArea[] txt;
	
	void setAgainstComputer(boolean againstComputer) {
		this.againstComputer = againstComputer;
	}

	//	public static void main(String[] args) {
//		GUI g=new GUI();
//		
//	}
	private void resetSelection(){
		selectedPerformer=null;
		selectedDirection=null;
		selectedTargetPiece=null;
		selectedTargetTile=null;
		TileSelected = false;
		TechSelected = false;
		PieceSelected = false;
		MedicSelected=false;
	}
	static String getClass(Piece p) {
		if(p instanceof Armored)
			return p.getOwner().getName()+"'s Armored";
		if(p instanceof Super)
			return p.getOwner().getName()+"'s Super";
		if(p instanceof Ranged)
			return p.getOwner().getName()+"'s Ranged";
		if(p instanceof Tech)
			return p.getOwner().getName()+"'s Tech";
		if(p instanceof Medic)
			return p.getOwner().getName()+"'s Medic";
		if(p instanceof Speedster)
			return p.getOwner().getName()+"'s Speedster";
		else
			return p.getOwner().getName()+"'s Sidekick";
	}
	
	static String getString(Piece piece) {
		return piece.getName() + "\n" + GUI.getClass(piece) + (piece instanceof Armored
				? "\n" +"Shield is " + (((Armored) piece).isArmorUp() ? "up" : "down")
				: (piece instanceof ActivatablePowerHero)
						?"\n" + "Power" + (((ActivatablePowerHero) piece).isPowerUsed() ? " is used"
								: " is not used")
						: "");
	}
	
	public GUI(String Player1,String Player2) {
    	Sound.play();
    	//Sound.getSound().loop();
        game = new Game(Player1, Player2);
        board = new Tile[7][6];
        DirectionBoard = new DTile[3][3];
        initComponents();
        p.getMove().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if(game.checkWinner()) {
					resetSelection();
					JOptionPane.showMessageDialog(null,"The game has already finished");
					return;
				}
				if(selectedPerformer==null) {
					JOptionPane.showMessageDialog(null, "Please select the desired piece !");
					return;
					}
				if(selectedDirection==null) {
					JOptionPane.showMessageDialog(null, "Please select the desired direction !");
					return;
					}
				try {
					selectedPerformer.getCell().getPiece().move(selectedDirection);
					
					//move other player
					if(againstComputer)
						while(true){
							try {
								game.getCurrentPlayer().play();
								break;
							} catch (PowerAlreadyUsedException e1) {
							} catch (InvalidPowerDirectionException e1) {
							} catch (InvalidPowerTargetException e1) {
							} catch (OccupiedCellException e1) {
							} catch (UnallowedMovementException e1) {
							} catch (WrongTurnException e1) {
								break;
							}
						}
						
					
					p.revalidate();
					p.repaint();
					resetSelection();
				} catch (OccupiedCellException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (UnallowedMovementException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (WrongTurnException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				finally {
					updateEverything();
				
			}}

		});
        p.getUnselect().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetSelection();
				
			}
		});
        p.getPower().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(game.checkWinner()) {
					resetSelection();
					JOptionPane.showMessageDialog(null,"The game has already finished");
					return;
				}
				if(TechSelected)
					try {
						if(selectedTargetPiece==null) {
							JOptionPane.showMessageDialog(null, "Select target piece");
							return;
						}
						((Tech)selectedPerformer.getCell().getPiece()).usePower(selectedDirection, selectedTargetPiece,
								selectedTargetTile==null?null:new Point(selectedTargetTile.getI(),selectedTargetTile.getJ() ) );
					} catch (PowerAlreadyUsedException | InvalidPowerDirectionException | InvalidPowerTargetException
							| WrongTurnException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				finally {
					updateEverything();
				}
				else
					if(MedicSelected)
						try {
							if(selectedDirection==null) {
								JOptionPane.showMessageDialog(null, "Select a Direction");
								return;
							}
							((Medic)selectedPerformer.getCell().getPiece()).usePower(selectedDirection, selectedTargetPiece,null);

							//move other player
							if(againstComputer)
								while(true){
									try {
										game.getCurrentPlayer().play();
										break;
									} catch (PowerAlreadyUsedException e1) {
									} catch (InvalidPowerDirectionException e1) {
									} catch (InvalidPowerTargetException e1) {
									} catch (OccupiedCellException e1) {
									} catch (UnallowedMovementException e1) {
									} catch (WrongTurnException e1) {
										e1.printStackTrace();
										break;
									}
								}
								
							
						} catch (PowerAlreadyUsedException | InvalidPowerDirectionException | InvalidPowerTargetException
								| WrongTurnException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
				finally {
					updateEverything();
				}
				else {
					if(selectedPerformer==null)
					{JOptionPane.showMessageDialog(null, "Please select the desired piece !");return;}
					if(selectedDirection==null)
						{JOptionPane.showMessageDialog(null, "Please select the desired direction !");return;}
					if(!(selectedPerformer.getCell().getPiece() instanceof ActivatablePowerHero))
					{JOptionPane.showMessageDialog(null, "The selected piece is not an Activatable Power Hero !");return;}
					try {
						((ActivatablePowerHero)selectedPerformer.getCell().getPiece()).usePower(selectedDirection, selectedTargetPiece, null);

						//move other player
						if(againstComputer)
							while(true){
								try {
									game.getCurrentPlayer().play();
									break;
								} catch (PowerAlreadyUsedException e1) {
								} catch (InvalidPowerDirectionException e1) {
								} catch (InvalidPowerTargetException e1) {
								} catch (OccupiedCellException e1) {
								} catch (UnallowedMovementException e1) {
								} catch (WrongTurnException e1) {
									e1.printStackTrace();
									break;
								}
							}
							
						
					} catch (PowerAlreadyUsedException | InvalidPowerDirectionException | InvalidPowerTargetException
							| WrongTurnException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}catch(NullPointerException e1) {
						
					}
					finally {
					updateEverything();
					}
				}
				resetSelection();
				p.revalidate();
				p.repaint();
			}
		});
    }

    private void initComponents() {

        mainMap = new JFrame();
        mainMap.setLayout(null);
        JLabel background = new JLabel(new ImageIcon("back2.jpg"));
        background.setVisible(true);
        background.setOpaque(true);
        mainMap.setContentPane(background);
        mainMap.getContentPane().setVisible(true);
        mainMap.setSize(1366,768);
        mainMap.setTitle("SHC");
        mainMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMap.setResizable(false);
        

        
        p = new TilePanel(game,board,DirectionBoard);

        p.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				Performer
				
				for(int i=0;i<board.length;i++)
					for(int j=0;j<board[i].length;j++)
						if(board[i][j].contains(e.getPoint())) {
							if(board[i][j].getCell().getPiece()!=null)
							switch(board[i][j].getCell().getPiece().getName()) {
							case "Batman": Sound.getBatmanSound();break;
							case "Venom" : Sound.getVenomSound();break;
							case "Salah" : Sound.getSalahSound();break;
							case "Groot" : Sound.getGrootSound();break;
							case "Darth Vader": Sound.getDarthVaderSound();break;
							case "Guko" : Sound.getGukoSound();break;
							case "Thanos" : Sound.getThanosSound();break;
							case "Captain America" : Sound.getCapSound();break;
							case "Joker" : Sound.getJokerSound();break;
							case "Stormtrooper" : Sound.getStormSound();break;
							case "BB8" : Sound.getBB8Sound();break;
							case "Voldemort" : Sound.getVoldemortSound();break;
							case "Green Arrow" : Sound.getArrowSound();break;
							case "Deadshot" : Sound.getDeadshotSound();break;
							}
						if(TechSelected) {
							if(!PieceSelected) {
								selectedTargetPiece=board[i][j].getCell().getPiece();
								PieceSelected=true;
								
							}
							else
								if(!TileSelected) {
									selectedTargetTile=board[i][j];
									TileSelected=true;
								}
							return;
						}
							selectedPerformer=board[i][j];
							if(selectedPerformer.getCell().getPiece()!=null)
								if(selectedPerformer.getCell().getPiece() instanceof Tech)
								TechSelected=true;
								else
									if(selectedPerformer.getCell().getPiece() instanceof Medic)
										MedicSelected=true;
								
							return;
						}
//				Direction
				for(int i=0;i<3;i++)
					for(int j=0;j<3;j++)
						if(DirectionBoard[i][j].contains(e.getPoint()))
								switch(i) {
								case 0:
									switch(j) {
									case 0:selectedDirection=Direction.UPLEFT;break;
									case 1:selectedDirection=Direction.UP;break;
									case 2:selectedDirection=Direction.UPRIGHT;break;
									}break;
								case 1:
									switch(j) {
									case 0:selectedDirection=Direction.LEFT;break;
									case 1:selectedDirection=null;break;
									case 2:selectedDirection=Direction.RIGHT;break;
									}break;
								case 2:
									switch(j) {
									case 0:selectedDirection=Direction.DOWNLEFT;break;
									case 1:selectedDirection=Direction.DOWN;break;
									case 2:selectedDirection=Direction.DOWNRIGHT;break;
									}break;
								}
							
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

        
        p.addMouseMotionListener(new MouseMotionListener() {
			Tile previous=null;
        	Tile hovered=null;
        	
			@Override
			public void mouseMoved(MouseEvent e) {
              int x = e.getX();
              int y = e.getY();
            
				//updateEverything();
              txt[0].setText(null);
              txt[1].setText(null);
      
              for(int i =0;i<board.length;i++) {
            	  for(int j=0;j<board[i].length;j++) {
            		  if(board[i][j].contains(e.getPoint())) {
            			  if(board[i][j].getCell().getPiece()!=null) {
            				  if(board[i][j].getCell().getPiece().getOwner()==game.getPlayer1())
            				  txt[0].setText(getString(board[i][j].getCell().getPiece()));
            				  else
            					  txt[1].setText(getString(board[i][j].getCell().getPiece()));
            			  }
            		  }
            	  }
              }
              
              
//           
              mainMap.repaint();
              mainMap.revalidate();
				
				
//			
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        JPanel info = new JPanel(null);
        info.setBackground(new Color(0,0,0,0));
        info.setVisible(true);
        info.setOpaque(true);
        info.setSize(1066,300);
        info.setLocation(150,103);
     // Players Labels
        JTextArea txt1 = new JTextArea();
       txt1.setEditable(false);
      
        txt1.setBackground(new Color(0,0,0,0));
        txt1.setBorder(null);
       // txt1.setBorder(BorderFactory.createLineBorder(Color.white.darker().darker(),2));
        txt1.setSize(310,300);
        txt1.setOpaque(true);
        
        txt1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        txt1.setForeground(Color.white);
        txt1.setLocation(0,0);
        txt1.setVisible(true);
        
        info.add(txt1);
        
        
        JTextArea txt2 = new JTextArea();
        txt2.setEditable(false);
        txt2.setBackground(new Color(0,0,0,0));
        txt2.setBorder(null);
        txt2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        txt2.setForeground(Color.white);
        txt2.setOpaque(true);
       // txt2.setBorder(BorderFactory.createLineBorder(Color.white.darker().darker(),2));
        txt2.setSize(310,300);
        txt2.setLocation(780,0);
        txt2.setVisible(true);
        txt = new JTextArea[2];
        txt[0]=txt1;
        txt[1]=txt2;
       
        info.add(txt2);
        
        
        JPanel DC1= new JPanel(new GridLayout(6, 2));
        DC1.setOpaque(false);
        DC1.setSize(150,647);
        DC1.setLocation(0, 100);
        DCA1=new DeadChar[6][2];
        int k =0;
        for(int i=0;i<6;i++) 
	        for(int j=0;j<2;j++) {
	        	DeadChar b = new DeadChar(k++);
	        	b.setOpaque(false);
	        	b.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
						selectedTargetPiece=game.getPlayer1().getDeadCharacters().get(((DeadChar)e.getSource()).getIndex());
						}catch(ArrayIndexOutOfBoundsException e1) {};
					}
				});
	        	b.setVisible(true);
	        	b.setBackground(Color.ORANGE);
	        	b.setBorder(null);
	        	DC1.add(b);
	        	DCA1[i][j]=b;
        	}
  
        DC1.setVisible(true);
        k=0;
        JPanel DC2= new JPanel(new GridLayout(6, 2));
        DC2.setOpaque(false);
        DC2.setSize(150, 647);
        DC2.setLocation(1216, 100);
        DCA2=new DeadChar[6][2];
        for(int i=0;i<6;i++) 
	        for(int j=0;j<2;j++) {
	        	DeadChar b = new DeadChar(k++);
	        	b.setOpaque(false);
	        	b.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
						selectedTargetPiece=game.getPlayer2().getDeadCharacters().get(((DeadChar)e.getSource()).getIndex());
						}catch(ArrayIndexOutOfBoundsException e1) {};
					}
				});
	        	
	        	b.setBackground(Color.ORANGE);
	        	b.setBorder(null);
	        	b.setVisible(true);
	        	DC2.add(b);
	        	DCA2[i][j]=b;
	        }
        DC2.setVisible(true);
        
        PL1 = new JLabel();
        BufferedImage pl = null;
        try {
        	switch(game.getPlayer1().getPayloadPos()) {
        	case 0 :pl= ImageIO.read(new File("0.png"));break;
        	case 1 :pl= ImageIO.read(new File("1.png"));break;
        	case 2 :pl= ImageIO.read(new File("2.png"));break;
        	case 3 :pl= ImageIO.read(new File("3.png"));break;
        	case 4 :pl= ImageIO.read(new File("4.png"));break;
        	case 5 :pl= ImageIO.read(new File("5.png"));break;
        	case 6 :pl= ImageIO.read(new File("6.png"));break;
        	}
        }
        	catch(IOException ex) {
        }
        PL1.setIcon(new ImageIcon(pl));
        PL1.setOpaque(false);
        PL1.setVerticalAlignment(SwingConstants.CENTER);
        PL1.setHorizontalAlignment(SwingConstants.CENTER);
        PL1.setBackground(Color.yellow);
        PL1.setSize(280,103 );
        PL1.setLocation(82, 0);
        PL1.setVisible(true);
        PL1.setBorder(null);

        
        PL2 = new JLabel();
        BufferedImage pl2 = null;
        try {
        	switch(game.getPlayer1().getPayloadPos()) {
        	case 0 :pl2= ImageIO.read(new File("0.png"));break;
        	case 1 :pl2= ImageIO.read(new File("1.png"));break;
        	case 2 :pl2= ImageIO.read(new File("2.png"));break;
        	case 3 :pl2= ImageIO.read(new File("3.png"));break;
        	case 4 :pl2= ImageIO.read(new File("4.png"));break;
        	case 5 :pl2= ImageIO.read(new File("5.png"));break;
        	case 6 :pl2= ImageIO.read(new File("6.png"));break;
        	}
        }
        	catch(IOException ex) {
        }
        PL2.setIcon(new ImageIcon(pl2));
        PL2.setBackground(Color.yellow);
        PL2.setOpaque(false);
        PL2.setVerticalAlignment(SwingConstants.CENTER);
        PL2.setHorizontalAlignment(SwingConstants.CENTER);
        PL2.setSize(340,103 );
        PL2.setLocation(972, 0);
        PL2.setVisible(true);
        PL2.setBorder(null);
        
        payload1 = new JPanel();
        payload1.setLayout(new GridLayout(2, 3));
        payload1.setSize(120, 103);
        payload1.setLocation(350,0);
        payload1.setOpaque(false);
        payload1.setVisible(true);
        payLoad1 = new JLabel[6];
        for(int i=0;i<6;i++) {
        	JLabel l = new JLabel();
        	l.setVisible(true);
        	l.setBackground(new Color(0,0,0,0));
        	l.setOpaque(true);
        	payload1.add(l);
        	payLoad1[i]=l;
        }
        
        payload2 = new JPanel();
        payload2.setLayout(new GridLayout(2, 3));
        payload2.setSize(120, 103);
        payload2.setLocation(910,0);
        payload2.setOpaque(false);
        payload2.setVisible(true);
        payLoad2 = new JLabel[6];
        for(int i=0;i<6;i++) {
        	JLabel l = new JLabel();
        	l.setVisible(true);
        	l.setBackground(new Color(0,0,0,0));
        	l.setOpaque(true);
        	payload2.add(l);
        	payLoad2[i]=l;
        }
        
        Current = new JLabel(game.getPlayer1().getName(),SwingConstants.CENTER);
        Current.setSize(500,103);
        Current.setLocation(440, 0);
        Current.setOpaque(false);
        Current.setFont(new Font("Serif",Font.BOLD,60));
        Current.setBorder(null);
        Current.setHorizontalAlignment(SwingConstants.CENTER);
        Current.setVerticalAlignment(SwingConstants.CENTER);

        
        mainMap.getContentPane().add(Current);
        mainMap.getContentPane().add(PL1);
        mainMap.getContentPane().add(PL2);
        mainMap.getContentPane().add(payload1);
        mainMap.getContentPane().add(payload2);
        mainMap.getContentPane().add(DC2);
        mainMap.getContentPane().add(DC1);
        mainMap.getContentPane().add(p);
        mainMap.getContentPane().add(info);
        mainMap.setVisible(true);
       // mainMap.pack();

    }

    /**
     * @param args
     */
    public void updateEverything() {
    	//Color of cureent player
    	Player cp = game.getCurrentPlayer();
    	Current.setText(cp.getName());

    	//Update Dead Charachters
    	int c = 0;
    	for(int i=0;i<6;i++) {
    		for(int j=0;j<2;j++) {
    			//DCA1[i][j]=null;
    			if(c<game.getPlayer1().getDeadCharacters().size())
    			switch(game.getPlayer1().getDeadCharacters().get(c).getName()) {
    				case "Captain America" : DCA1[i][j].setIcon(new ImageIcon("Captain America Mini.png"));break;
    				case "Groot" : DCA1[i][j].setIcon(new ImageIcon("Groot Mini.png"));break;
    				case "Salah" : DCA1[i][j].setIcon(new ImageIcon("Salah mini.png"));break;
    				case "Guko" : DCA1[i][j].setIcon(new ImageIcon("Guko Mini.png"));break;
    				case "BB8" : DCA1[i][j].setIcon(new ImageIcon("BB8 Mini.png"));break;
    				case "Green Arrow" : DCA1[i][j].setIcon(new ImageIcon("Green Arrow Mini.png"));break;
    				case "Batman" : DCA1[i][j].setIcon(new ImageIcon("Batman Mini.png"));break;
    			}
    			else
    			DCA1[i][j].setIcon(null);
    			c++;
    		}
    	}
    	c = 0;
    	for(int i=0;i<6;i++) {
    		for(int j=0;j<2;j++) {
    			if(c<game.getPlayer2().getDeadCharacters().size())
    			switch(game.getPlayer2().getDeadCharacters().get(c).getName()) {
    				case "Venom" : DCA2[i][j].setIcon(new ImageIcon("Venom Mini.png"));break;
    				case "Darth Vader" : DCA2[i][j].setIcon(new ImageIcon("Darth Vader Mini.png"));break;
    				case "Stormtrooper" : DCA2[i][j].setIcon(new ImageIcon("Stormtrooper Mini.png"));break;
    				case "Deadshot" : DCA2[i][j].setIcon(new ImageIcon("Deadshot Mini.png"));break;
    				case "Joker" : DCA2[i][j].setIcon(new ImageIcon("Joker Mini.png"));break;
    				case "Thanos" : DCA2[i][j].setIcon(new ImageIcon("Thanos Mini.png"));break;
    				case "Voldemort" : DCA2[i][j].setIcon(new ImageIcon("Voldemort Mini.png"));break;
    			}
    			else
    			DCA2[i][j].setIcon(null);
    			c++;
    		}
    	}
    	
    	//Update Payload
    	  
          BufferedImage pl = null;
          try {
          	switch(game.getPlayer1().getPayloadPos()) {
          	case 0 :pl= ImageIO.read(new File("0.png"));break;
          	case 1 :pl= ImageIO.read(new File("1.png"));break;
          	case 2 :pl= ImageIO.read(new File("2.png"));break;
          	case 3 :pl= ImageIO.read(new File("3.png"));break;
          	case 4 :pl= ImageIO.read(new File("4.png"));break;
          	case 5 :pl= ImageIO.read(new File("5.png"));break;
          	case 6 :pl= ImageIO.read(new File("6.png"));break;
          	}
          }
          	catch(IOException ex) {
          }
          PL1.setIcon(new ImageIcon(pl));
          

          PL1.setOpaque(false);
          PL1.setVerticalAlignment(SwingConstants.CENTER);
          PL1.setHorizontalAlignment(SwingConstants.CENTER);
          PL1.setBackground(null);
          PL1.setSize(280,103 );
          PL1.setLocation(83, 0);
          PL1.setVisible(true);
          PL1.repaint();
          PL1.setBorder(null);
          
          BufferedImage pl2 = null;
          try {
          	switch(game.getPlayer2().getPayloadPos()) {
          	case 0 :pl2= ImageIO.read(new File("0.png"));break;
          	case 1 :pl2= ImageIO.read(new File("1.png"));break;
          	case 2 :pl2= ImageIO.read(new File("2.png"));break;
          	case 3 :pl2= ImageIO.read(new File("3.png"));break;
          	case 4 :pl2= ImageIO.read(new File("4.png"));break;
          	case 5 :pl2= ImageIO.read(new File("5.png"));break;
          	case 6 :pl2= ImageIO.read(new File("6.png"));break;
          	}
          }
          	catch(IOException ex) {
          }
          PL2.setIcon(new ImageIcon(pl2));
          PL2.setBackground(null);
          PL2.setOpaque(false);
          PL2.setVerticalAlignment(SwingConstants.CENTER);
          PL2.setHorizontalAlignment(SwingConstants.CENTER);
          PL2.setSize(340,103 );
          PL2.setLocation(972, 0);
          PL2.setVisible(true);
          PL2.repaint();
          PL2.setBorder(null);
          
          if(game.getPlayer1().getPayloadPos()!=0)
        	  for(int i =0; i<game.getPlayer1().getPayloadPos();i++) {
//        		  payLoad1[i].setBackground(Color.GREEN);
//        		  payLoad1[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        		  payLoad1[i].setIcon(new ImageIcon("fire.png"));
        	  }
          if(game.getPlayer2().getPayloadPos()!=0)
        	  for(int i =0; i<game.getPlayer2().getPayloadPos();i++) {
//        		  payLoad2[i].setBackground(Color.GREEN);
//        		  payLoad2[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        		  payLoad2[i].setIcon(new ImageIcon("fire.png"));
        	  }

         if(game.checkWinner()) {
         //if(game.getPlayer2().getPayloadPos()==1) {
        	  
      		Sound.stop();
      		
      		 if(game.getPlayer1().getPayloadPos()==6) {
      			
            	 Video.startVideo(1,game.getPlayer1().getName());
             }
      		 else {
      			 Video.startVideo(2,game.getPlayer2().getName());
      		 }
      		int newGame=JOptionPane.showConfirmDialog(null, (game.getPlayer1().getPayloadPos()==6?game.getPlayer1().getName()+" wins":game.getPlayer2().getName()+" wins")+"\nDo you want to play a new game?");
      		mainMap.dispose();
      		if(newGame==0)
      			new StartWindow();
      	}
    
    }
}