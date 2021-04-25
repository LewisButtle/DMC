package Main;

import java.awt.*;
import java.awt.event.KeyEvent;

//Surface for the calculator mode screen
public class CalculatorSurface extends Surface {

	//The technical and string expression
	private ExpressionSetup expression;
	private String exp;

	//Initialise the expression
	public CalculatorSurface() {
		expression = new ExpressionSetup();
		exp = "";
	}

	//Controls
	@Override
	public void keyPressed(KeyEvent e){
		String input = String.valueOf(e.getKeyChar());

		//If the input is mathematical, add it to the expression
		if (expression.check(input)) {
			exp = expression.add(input);
			repaint();
		}
		//If the input is 'back' or 'start'..
		switch(input) {
			//Back resets the current expression
			case "b":
				expression.reset();
				exp = "";
				repaint();
			break;
			//'Start' will reset the screen, and switch to the main menu surface.
			case "s":
				expression.reset();
				exp = "";
				Main.changeCard("main");
			break;
		}
	}

	//Graphics
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();
		//rectangles
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, h-100, w, 100);
		g2d.fillRect(0, 0, w, 100);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, h-200, w, 100);
		g2d.fillRect(0, 100, w, 100);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, h-300, w, 100);
		g2d.fillRect(0, 200, w, 100);

		//Expression Box
		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-700, h/2-75, 1300, 150);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-700, h/2-75, 1300, 150);

		//Text
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 80));
		g2d.drawString(exp, w/2-700, h/2+25);

		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 20));
		g2d.setPaint(new Color(0, 204, 255));
		g2d.drawString("Press 'Start' to return to Main Menu", 5, 20);

		g2d.dispose();
	}


}
