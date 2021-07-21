package view;

import java.awt.BorderLayout;

import javax.swing.*;

public class test {
public static void main(String[] args) {
	JFrame j = new JFrame();
	JPanel p = new JPanel();
	JLabel l = new JLabel();
	l.setText("a7a");
	l.setVisible(true);
	p.setVisible(true);
	p.add(l, BorderLayout.EAST);
	j.getContentPane().add(p);
	j.setSize(1000, 1000);
	j.setVisible(true);
	l.setText("a&ten");
}
}
