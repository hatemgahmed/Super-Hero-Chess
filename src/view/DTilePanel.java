package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DTilePanel extends JPanel{
	
	public DTilePanel() {
		super();
		setBackground(Color.YELLOW);
        setVisible(true);
        setSize(180, 180);
        setLocation(250, 600);
	}
	

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300,300);
    }
}