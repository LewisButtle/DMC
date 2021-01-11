package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ScoreboardSurface extends Surface {

	public ScoreboardSurface(){
	}

	@Override
	public void keyPressed(KeyEvent e){
		switch(e.getKeyChar()) {
			case 's': 
				Main.changeCard("main");
				break;

		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		g2d.setColor(Color.yellow);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, 0, w, 50);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, 50, w, 50);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, 100, w, 50);

		g2d.setPaint(Color.blue);
		g2d.fillRect((w/2)-50, 150, 50, h);
		g2d.setPaint(Color.green);
		g2d.fillRect(w/2, 150, 50, h);
		g2d.setPaint(Color.red);
		g2d.fillRect((w/2)+50, 150, 50, h);

		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-500, 10, 1000, 125);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-500, 10, 1000, 125);
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 100));
		g2d.drawString("Leaderboards", w/2-350, 125);

		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/10, 200, 350, 75);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/10, 200, 350, 75);

		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(2*w/3, 200, 350, 75);
		g2d.setPaint(Color.black);
		g2d.drawRect(2*w/3, 200, 350, 75);

		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 40));
		g2d.drawString("Floating Numbers", w/10+8, 250);
		g2d.drawString("Speed Numbers", 2*w/3+8, 250);

		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 20));
		g2d.setPaint(new Color(0, 204, 255));
		g2d.drawString("Press 'Start' to return to Main Menu", 5, 20);


		
		for (int x = 0; x<2; x++) {
			for (int y = 0; y<10; y++) {
				
				g2d.setColor(new Color(247, 245, 116));
				g2d.fillRect(w/10+(x*200), 300+(y*50), 150, 30);
				g2d.fillRect(2*w/3+(x*200), 300+(y*50), 150, 30);
				g2d.setPaint(Color.black);
				g2d.drawRect(w/10+(x*200), 300+(y*50), 150, 30);
				g2d.drawRect(2*w/3+(x*200), 300+(y*50), 150, 30);
				g2d.setPaint(Color.blue);
				g2d.drawString("test1", w/10+(x*200)+5, 325+(y*50));
				g2d.drawString("test2", 2*w/3+(x*200)+5, 325+(y*50));
			}
		}

		g2d.dispose();
	}
}
