package Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.SplittableRandom;
import java.util.Timer;
import java.util.TimerTask;

//Surface for the speed numbers minigame
public class SpeedNumSurface extends Surface {

	//The expression
	private ExpressionSetup expression;
	//Store the remaining time and score
	private int timeRemaining;
	private int score;
	//The current number on the screen
	private int currentNumber;
	//The increasing range for the next random number
	private int increase;
	//The value of the expression to display
	private String exp;
	//The state of the game, if it has started or not
	private boolean gameStarted;
	//The method of timing
	private Timer timing;

	//Initialise the minigame
	public SpeedNumSurface() {
		timing = new Timer();
		timing.scheduleAtFixedRate(new timer(), 0, 1000);
		expression = new ExpressionSetup();
		exp = "";
		gameStarted = false;
		timeRemaining = 120;
		score = 0;
		increase = 0;
	}

	//Controls
	@Override
	public void keyPressed(KeyEvent e) {
		String input = String.valueOf(e.getKeyChar());
		//Before the game has started...
		if (!gameStarted) {
			switch(input) {
				//The player presses back to return to the main menu
				case "b":
					reset();
					Main.changeCard("main");
				break;
				//The player starts the game
				case "s":
					reset();
					gameStarted = true;
					currentNumber = randomNumber(10, 20);
					repaint();
				break;
			}
		}
		//If the game has started..
		else if (gameStarted) {
			//If the input is a mathematical symbol, add it to the current expression
			if (expression.check(input)) {
				exp = expression.add(input);
				//If the expression matches the number on screen, increase score and time remaining - and reroll the current number
				if (currentNumber == Integer.parseInt(expression.getValue())) {
					score += 100;
					timeRemaining += 5;
					increase += 10;
					currentNumber = randomNumber(10, 50 + increase);
				}
				repaint();
			}

			switch(input) {
				//If 'back' is pressed, reset the expression
				case "b":
					expression.reset();
					exp = "";
					repaint();
				break;
			}
		}
	}
	//Timer to deduct a second every second, and checks if the game is over.
	public class timer extends TimerTask {
		@Override
		public void run() {
			if (gameStarted){
				if (timeRemaining > 0 ) {
					timeRemaining--;
					repaint();
				}
				else {
					gameOverInstructions();
				}
			}
		}
	}

	//Reset the expression and the entire minigame
	public void reset(){
		expression.reset();
		exp = "";
		gameStarted = false;
		timeRemaining = 120;
		score = 0;
	}

	//Instructions to be followed when the timer reaches 0
	public void gameOverInstructions() {
		//Check if the user achieved a top 10 score by consulting the database
		if(Main.database.checkTopTen("SpeedScores", score)) {
			Main.currentScore[0] = "SpeedScores";
			Main.currentScore[1] = String.valueOf(score);
			reset();
			//If yes, change the surface to allow the user to enter their leaderboard name
			Main.changeCard("enterscore");
		}
		else {
			//If the user did not achieve a top 10 score, just return to the main menu
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

		//Rectangles
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, 0, w, 50);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, 50, w, 50);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, 100, w, 50);

		//Expression rectangle and text
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
		
		//Display the instructions, if the game hasn't started
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
		//If the game is started, display the current number with a random colour
		else if (gameStarted) {
			g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 400));
			g2d.setColor(new Color(randomNumber(0,255), randomNumber(0,255), 250));
			g2d.drawString(String.valueOf(currentNumber), w/2-300, h/2+200);

		}
		g2d.dispose();
	}
	//Generate a random number
    private int randomNumber(int min, int max){
        return new SplittableRandom().nextInt(min, max+1);
    }

}
