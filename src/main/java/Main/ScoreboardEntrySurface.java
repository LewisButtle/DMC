package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ScoreboardEntrySurface extends Surface {

	public ScoreboardEntrySurface() {
	}

	@Override
	public void keyPressed(KeyEvent e){
		System.out.println("Main was provided the key: " + e);
		if(e.getKeyChar() == 'e'){
			// TODO: WARN - must remember class hash name
			Main.changeCard("score");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, 0, w, 50);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, 50, w, 50);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, 100, w, 50);

		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-500, 10, 1000, 125);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-500, 10, 1000, 125);
		g2d.dispose();
	}


}
