package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//A class to hold a surface's graphical and control information.
public abstract class Surface extends JPanel {
	public Surface(){
	}

	public void keyPressed(KeyEvent e){ }

	public void paintComponent(Graphics g) {}
}
