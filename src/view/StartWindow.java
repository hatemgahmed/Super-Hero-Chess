package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StartWindow extends JFrame{
	JPanel p;
	String Player1;
	String Player2;
	JFrame window;
	StartWindow () {
		
		window=new JFrame();
		window.setSize(new Dimension(1366, 768));
		JButton start=new JButton(new ImageIcon("start.png"));
		start.setOpaque(true);
		start.setBackground(Color.BLACK);
		start.setBorder(null);
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer=JOptionPane.showConfirmDialog(null, "Do you want to play against the computer?");
	      		if(answer==3)
	      			return;
	      		if(answer==0)
	      			Player2="Player2";
	      		
				while(Player1==null||Player2==null||Player1.isEmpty()||Player2.isEmpty()) {
					Player1 = JOptionPane.showInputDialog("Enter Player1 Name");
					if(answer==1)
					Player2 = JOptionPane.showInputDialog("Enter Player2 Name");
				}
				GUI g=new GUI(Player1,Player2);
				g.setAgainstComputer(answer==0);
				window.dispose();
			}
		});
		JButton credits=new JButton(new ImageIcon("Credits.png"));
		credits.setOpaque(false);
		credits.setBorder(null);
		credits.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Made by team 001:-\n"+"Hatem,\n"+"Omar,\n"+"Yasser");
			}
		});
		JButton exit=new JButton(new ImageIcon("Exit.png"));
		exit.setBackground(Color.black);
		exit.setBorder(null);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer=JOptionPane.showConfirmDialog(null, "Are you sure you want to Exit?");
	      		if(answer==0) 
	      			window.dispose();
	      		
			}
		});
		window.setLayout(null);
		JLabel background = new JLabel(new ImageIcon("Start Page Copy.jpg"));
        background.setVisible(true);
        background.setOpaque(true);
        window.setContentPane(background);
        window.getContentPane().setVisible(true);
        window.setSize(1366,768);
        window.setTitle("SHC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(Color.black);
        window.setResizable(false);

        
        
	
      //  JPanel Title = new JPanel(new GridLayout(3, 1));
       
        
        
        JPanel p=new JPanel(new GridLayout(3, 1));
        p.setSize(150, 450);
        p.setOpaque(true);
        p.setBackground(Color.black);
        p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        p.setBorder(null);
        p.setLocation(1060, 150);
        p.add(start);
        p.add(credits);
        p.add(exit);
        window.add(p);
        
        JPanel l = new JPanel();
        l.setBackground(Color.BLACK);
        l.setSize(150,300);
        l.setVisible(true);
        l.setOpaque(true);
        l.setLocation(1060,148);
      //  window.add(Title);
        window.add(l);
        window.setVisible(true);
	}
	public static void main(String[] args) {
		new StartWindow();
	}
	
}

