package Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//Surface for the floating numbers minigame
public class FNumSurface extends Surface {

	//Class variables//
	//Stores the technical expression the user inputs
	private ExpressionSetup expression;
	//Stores the floating numbers currently on the screen
	private ArrayList<FloatingNumber> floatingnumbers = new ArrayList<FloatingNumber>();
	//Stores the time remaining and score
	private int timeRemaining;
	private int score;
	//Stores the expression to be displayed on the screen
	private String exp;
	//Stores whether or not the game has started
	private boolean gameStarted;
	//Stores timers for the game timer, and the frequency of the floating numbers vertical height
	private Timer timing;
	private Timer floating;

	//Initialize the minigame
	public FNumSurface() {
		//Initiate and configure the timer's frequencies
		timing = new Timer();
		floating = new Timer();
		timing.scheduleAtFixedRate(new ClockTimer(), 0, 1000);
		floating.scheduleAtFixedRate(new Floating(), 0, 100);

		//Initiate a blank expression
		expression = new ExpressionSetup();
		exp = "";
		//Initiate score, timing and the state of the game
		gameStarted = false;
		timeRemaining = 120;
		score = 0;
	}

	//Create, store and organize the original floating numbers and their positions
	//'amount': The number of floating numbers on the screen.
	public void makeNumbers(int amount) {
		for (int i = 100; i<getWidth()-100; i+=(getWidth()-100)/amount){
			floatingnumbers.add(new FloatingNumber(i, getHeight(), i%3));
		}
	}

	//Override the Surface controls
	@Override
	public void keyPressed(KeyEvent e) {
		String input = String.valueOf(e.getKeyChar());
		//Handle the inputs differently, depending on if the game has started or not
		if (!gameStarted) {
			switch(input) {
				//The user returns to the main menu
				case "b":
					reset();
					Main.changeCard("main");
				break;
				//If the user starts the game, create 7 floating numbers, start the game and repaint the screen
				case "s":
					reset();
					makeNumbers(7);
					gameStarted = true;
					repaint();
				break;
			}
		}
		//If the game has started...
		else if (gameStarted) {
			//If the input is mathematical, input it into the expression
			if (expression.check(input)) {
				exp = expression.add(input);
				
				//If the current expression matches any floating number,
				//update the score and replace the matched number
				for (FloatingNumber currentNumber: floatingnumbers) {
					if (currentNumber.retrieveStringValue().equals(expression.getValue())) {
						score += (currentNumber.retrieveScore() - expression.getExpressionSize());
						expression.resetExpressionCounter();
						replace(currentNumber);
						break;
					}
				}
				repaint();
			}

			//If the user presses 'b' to clear the current expression
			switch(input) {
				case "b":
					expression.reset();
					exp = "";
					repaint();
				break;
			}
		}
	}

	//Runs on every second of the the initiated minigame timer
	public class ClockTimer extends TimerTask {
		@Override
		public void run() {
			//If the game has started, check if the timer is at 0. If not, deduct a single second.
			if(gameStarted){
				if (timeRemaining > 0 ) {
					timeRemaining--;
					repaint();
				}
				else {
					//If time is up, display game over and decide next surface
					gameOverInstructions();
				}

			}
		}
	}

	//Runs frequently, to increase the height of floating numbers
	public class Floating extends TimerTask {
		@Override
		public void run() {
			if(gameStarted){
				//Check if any number has hit the top of the screen, if not, increase the number's height
				for (FloatingNumber currentNumber: floatingnumbers) {
					currentNumber.increaseHeight();
					//If the number is at the top of the screen, deduct score and replace the number
					if (currentNumber.retrieveYPosition() < 220) {
						score -= 100;
						replace(currentNumber);
						break;
					}
				}
				repaint();
			}
		}
	}

	//Reset the minigame, by reinitializing and clearing most variables
	public void reset(){
		expression.reset();
		exp = "";
		floatingnumbers.clear();
		gameStarted = false;
		timeRemaining = 30;
		score = 0;
	}

	//To replace a floating number when it hits the top of the screen
	//'replacedNumber': The floating number to be replaced
	public void replace(FloatingNumber replacedNumber){
		//Create a new floating number, in the same position as the number to be removed
		floatingnumbers.add(new FloatingNumber(replacedNumber.RetrieveXPosOriginal(), getHeight(), replacedNumber.retrieveColourValue()));
		floatingnumbers.remove(replacedNumber);
	}

	//Instructions to be followed when the timer reaches 0
	public void gameOverInstructions() {
		//Check if the user achieved a top 10 score by consulting the database
		if(Main.database.checkTopTen("FloatScores", score)) {
			Main.currentScore[0] = "FloatScores";
			Main.currentScore[1] = String.valueOf(score);
			//If yes, change the surface to allow the user to enter their leaderboard name
			Main.changeCard("enterscore");
		}
		//If the user did not achieve a top 10 score, just return to the main menu
		else {
			Main.changeCard("score");
		}
		reset();
	}

	//The minigame's graphics
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		//Top Rectangles
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, 0, w, 50);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, 50, w, 50);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, 100, w, 50);

		//The box holding the expression
		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-500, 10, 1000, 125);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-500, 10, 1000, 125);

		//The timer, expression and score text
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 100));
		g2d.drawString(String.format("%02d:%02d", timeRemaining / 60, timeRemaining % 60), 25, 110);
		g2d.drawString(exp, 375, 100);
		g2d.drawString(String.valueOf(score), w-285, 100);
		
		//Display the minigame instructions only if the game has not started
		if (!gameStarted) {
			g2d.setPaint(Color.black);
			g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 65));
			g2d.setColor(new Color(247, 245, 116));
			g2d.fillRect(100, 200, w-200, h-250);
			g2d.setColor(Color.black);
			g2d.drawRect(100, 200, w-200, h-250);
			g2d.drawString("Use Single digits to make the floating numbers,", 125, 255);
			g2d.drawString("before they hit the top of the screen!", 250, 350);
			g2d.drawString("More points are awarded for shorter expressions.", 125, 450);
			g2d.drawString("A number's colour represents its points worth.", 125, 550);
			g2d.drawString("(High to low: red, amber green)", 350, 700);
			g2d.drawString("Press Start when you are ready!", 350, 850);
		}
		//If the game has started, draw each floating number depending on their individual coordinates
		else if (gameStarted) {
			for (FloatingNumber currentNumber: floatingnumbers) {
				g2d.setColor(currentNumber.retrieveColour());
				g2d.drawString(currentNumber.retrieveStringValue(), currentNumber.retrieveXPosition(), currentNumber.retrieveYPosition());
			}
		}
		g2d.dispose();
	}


}
