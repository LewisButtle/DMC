package Main;

import java.awt.*;
import java.awt.event.KeyEvent;

//The surface for the Main Menu screen
public class MainSurface extends Surface {

	public MainSurface() {
	}

	//Override controls to map inputs to switch screens - to navigate the software.
	@Override
	public void keyPressed(KeyEvent e){
		switch(e.getKeyChar()) {
			//If the input is '2', switch to the calculator screen - and so on
			case '2': 
				Main.changeCard("calculator");
				break;
			case '3': 
				Main.changeCard("float");
				break;
			case '4': 
				Main.changeCard("speed");
				break;
			case '5': 
				Main.changeCard("score");
				break;

		}
		
	}

	//Override the graphical function, to draw the main menu using Graphics2D
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		int w = getWidth();
		int h = getHeight();

		//Right side rectangles
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(w-100, 0, 100, h);
		g2d.setPaint(Color.green);
		g2d.fillRect(w-200, 0, 100, h);
		g2d.setPaint(Color.red);
		g2d.fillRect(w-300, 0, 100, h);

		//Centre 'buttons'
		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-400, h/2-200, 800, 100);
		g2d.fillRect(w/2-400, h/2-50, 800, 100);
		g2d.fillRect(w/2-400, h/2+100, 800, 100);
		g2d.fillRect(w/2-400, h/2+250, 800, 100);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-400, h/2-200, 800, 100);
		g2d.drawRect(w/2-400, h/2-50, 800, 100);
		g2d.drawRect(w/2-400, h/2+100, 800, 100);
		g2d.drawRect(w/2-400, h/2+250, 800, 100);

		//Text on Screen
		g2d.setPaint(new Color(186, 131, 20));
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 80));
		g2d.drawString("Dance Mat Calculator", w/2-400, h/2-310);
		g2d.setPaint(Color.black);
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 60));
		g2d.drawString("Calculator Mode", w/2-250, h/2-125);
		g2d.drawString("Floating Numbers", w/2-250, h/2+25);
		g2d.drawString("Speed Numbers", w/2-250, h/2+175);
		g2d.drawString("View Leaderboards", w/2-250, h/2+325);

		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 100));
		g2d.setPaint(Color.red);
		g2d.drawString("2", w/2-470, h/2-115);
		g2d.setPaint(Color.green);
		g2d.drawString("3", w/2-470, h/2+35);
		g2d.setPaint(Color.blue);
		g2d.drawString("4", w/2-470, h/2+185);
		g2d.setPaint(Color.red);
		g2d.drawString("5", w/2-470, h/2+335);

		//Left diagonal rectangles
		g2d.rotate(Math.PI/4-0.2);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, -200, 100, 400);
		g2d.setPaint(Color.green);
		g2d.fillRect(100, -200, 100, 500);
		g2d.setPaint(Color.blue);
		g2d.fillRect(200, -200, 100, 700);

		//Cease the drawing tool
		g2d.dispose();
	}


}
