package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

// TODO: Should be interface?
public abstract class Surface extends JPanel {
	public Surface(){
	}

	public void keyPressed(KeyEvent e){ }

	public void paintComponent(Graphics g) {}
}
