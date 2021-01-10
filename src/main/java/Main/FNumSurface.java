package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FNumSurface extends Surface {

	ExpressionSetup expression;
	ArrayList<FloatingNumber> floatingnumbers = new ArrayList<FloatingNumber>();
	int timeRemaining;
	int spread;
	int score;
	String time;
	String exp;
	boolean gameStarted;
	boolean gameOver;



	public FNumSurface() {
		expression = new ExpressionSetup();
		exp = "";
		gameStarted = false;
		gameOver = false;
		timeRemaining = 121;
		time = "02:00";
		score = 0;
	}

	public void makeNumbers(int amount) {
		spread = (getWidth()-100)/amount;
		for (int i = 100; i<getWidth()-100; i+=spread){
			floatingnumbers.add(new FloatingNumber(i, getHeight(), i%3));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String input = String.valueOf(e.getKeyChar());
		if (!gameStarted) {
			switch(input) {
				case "b":
					expression.reset();
					exp = "";
					Main.changeCard("main");
				break;
				case "s":
				makeNumbers(7);
				gameStarted = true;
				new Timer().scheduleAtFixedRate(new timer(), 0, 1000);
				new Timer().scheduleAtFixedRate(new floating(), 0, 100);
				repaint();
				break;
			}
		}


		else if (gameStarted && !gameOver) {
			if (expression.check(input)) {
				exp = expression.add(input);

				for (FloatingNumber currentNumber: floatingnumbers) {
					if (currentNumber.retrieveStringValue().equals(expression.getValue())) {
						score += (currentNumber.retrieveScore() - expression.getExpressionSize());
						expression.resetExpressionCounter();
						floatingnumbers.remove(currentNumber);
						break;
					}
				}
				repaint();
			}

			switch(input) {
				case "b":
					expression.reset();
					exp = "";
					repaint();
				break;
			}
		}


		else if (gameOver) {
			switch(input) {
				case "s":
					expression.reset();
					exp = "";
					Main.changeCard("main");
				break;
			}
			repaint();
		}
	}

	public class timer extends TimerTask {
		@Override
		public void run() {
			if (timeRemaining > 0 ) {
				timeRemaining--;
				time = String.format("%02d:%02d", timeRemaining / 60, timeRemaining % 60);
				repaint();
			}
			else {
			gameOver = true;
			repaint();
			}

		}
	}

	public class floating extends TimerTask {
		@Override
		public void run() {
			if (!gameOver) {
				for (FloatingNumber currentNumber: floatingnumbers) {
					currentNumber.increaseHeight();
					if (currentNumber.retrieveYPosition() < 220) {
						floatingnumbers.remove(currentNumber);
						break;
					}
				}
				repaint();
			}
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

		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 100));
		g2d.drawString(time, 25, 110);
		g2d.drawString(exp, 375, 100);
		g2d.drawString(String.valueOf(score), w-285, 100);
		
		if (!gameStarted) {
			g2d.setPaint(Color.black);
			g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 65));
			g2d.setColor(new Color(247, 245, 116));
			g2d.fillRect(100, 200, w-200, h-250);
			g2d.setColor(Color.black);
			g2d.drawString("Use Single digits to make the floating numbers,", 125, 250);
			g2d.drawString("before they hit the top of the screen!", 250, 350);
			g2d.drawString("More points are awarded for shorter expressions.", 125, 450);
			g2d.drawString("A number's colour represents its points worth.", 125, 550);
			g2d.drawString("(High to low: red, amber green)", 350, 700);
			g2d.drawString("Press Start when you are ready!", 350, 850);
		}
		else if (gameStarted && !gameOver) {
			for (FloatingNumber currentNumber: floatingnumbers) {
				g2d.setColor(currentNumber.retrieveColour());
				g2d.drawString(currentNumber.retrieveStringValue(), currentNumber.retrieveXPosition(), currentNumber.retrieveYPosition());
			}
		}
		else if (gameOver) {
			g2d.setColor(new Color(247, 245, 116));
			g2d.fillRect(100, 200, w-200, h-250);
		}
		g2d.dispose();
	}


}
