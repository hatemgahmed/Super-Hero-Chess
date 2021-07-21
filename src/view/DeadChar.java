package view;

import javax.swing.JButton;

public class DeadChar extends JButton{
	private int index=0;
	int getIndex() {
		return index;
	}
	public DeadChar(int index) {
		this.index=index;
	}
}
