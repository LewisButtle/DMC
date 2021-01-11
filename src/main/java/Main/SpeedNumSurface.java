package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

public class SpeedNumSurface extends Surface {

	ExpressionSetup expression;
	int timeRemaining;
	int spread;
	int score;
	int currentNumber;
	int increase;
	String exp;
	boolean gameStarted;
	boolean gameOver;

	public SpeedNumSurface() {
		expression = new ExpressionSetup();
		exp = "";
		gameStarted = false;
		gameOver = false;
		timeRemaining = 60;
		score = 0;
		increase = 0;
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
					gameStarted = true;
					currentNumber = randomNumber(10, 20);
					new Timer().scheduleAtFixedRate(new timer(), 0, 1000);
					repaint();
				break;
			}
		}

		else if (gameStarted && !gameOver) {
			if (expression.check(input)) {
				exp = expression.add(input);
				if (currentNumber == Integer.parseInt(expression.getValue())) {
					score += 100;
					timeRemaining += 5;
					increase += 10;
					currentNumber = randomNumber(10, 50 + increase);
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
				repaint();
			}
			else {
			gameOver = true;
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
		g2d.drawString(String.format("%02d:%02d", timeRemaining / 60, timeRemaining % 60), 25, 110);
		g2d.drawString(exp, 375, 100);
		g2d.drawString(String.valueOf(score), w-285, 100);
		

		if (!gameStarted) {
			g2d.setPaint(Color.black);
			g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 65));
			g2d.setColor(new Color(247, 245, 116));
			g2d.fillRect(100, 200, w-200, h-250);
			g2d.setColor(Color.black);
			g2d.drawRect(100, 200, w-200, h-250);
			g2d.drawString("Use Single digits to make the number.", 250, 300);
			g2d.drawString("For each number you make, you gain 5 seconds!", 125, 450);
			g2d.drawString("Each number is worth 100 points.", 350, 550);
			g2d.drawString("Press Start when you are ready!", 350, 850);
		}
		else if (gameStarted && !gameOver) {
			g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 400));
			g2d.setColor(new Color(randomNumber(0,255), randomNumber(0,255), 250));
			g2d.drawString(String.valueOf(currentNumber), w/2-300, h/2+200);

		}
		else if (gameOver) {
			g2d.setColor(new Color(247, 245, 116));
			g2d.fillRect(100, 200, w-200, h-250);
		}
		g2d.dispose();
	}
	
    private int randomNumber(int min, int max){
        return new SplittableRandom().nextInt(min, max+1);
    }

}
