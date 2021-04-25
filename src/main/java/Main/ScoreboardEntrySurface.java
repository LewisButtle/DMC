package Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;

//Surface for inputting the player's name after they achieve a top 10 score.
public class ScoreboardEntrySurface extends Surface {

	//A hashmap to map controller inputs to the letters displayed on screen.
	private HashMap<String, String> alphaMap = new HashMap<String, String>();

	//To store the name the user inputs
	private String name;

	//A boolean representing if the first or second alphabet page is shown
	private boolean nextPage;

	//The 2D array to map inputs to characters
	private String[][] controls = {{"+","0","1","-"},
							{"2","3","4","5"},
							{"6","7","8","9"},
							{"*","b","s","="}};

	//First possible display								
	private String[][] alpha1 = {{"A","B","C","D"},
						 {"E","F","G","H"},
						 {"I","J","K","L"},
						 {"M","UNDO","END","=>"}};

	//Second possible display
	private String[][] alpha2 = {{"N","O","P","Q"},
						 {"R","S","T","U"},
						 {"V","W","X","Y"},
						 {"Z","UNDO","END","<="}};

	//The actual 16 values to be displayed on the screen, initialized as alpha1.
	private String[][] alpha = alpha1;

	//Initialize the surface by resetting the displayed alphabet segment
	public ScoreboardEntrySurface() {
		reset();
	}

	//Reset the surface
	public void reset() {
		changePage(false);
		name = "";
	}

	//Maps the controls to the corresponding alphabet, and switches between alpha1 and alpha2 if requested
	//'next': Will switch alphabet mappings depending on true/false.
	private void changePage(boolean next) {
		nextPage = next;
		if(next){
			alpha = alpha2;
		}
		else {
			alpha = alpha1;
		}
		//Maps the controls to the corresponding alphabet.
		for(int x=0; x<4; x++) {
			for(int y=0; y<4; y++) {
				alphaMap.put(controls[x][y], alpha[x][y]);
			}
		}
	}

	//Checks if an input is valid
	//'input' The character to be checked
	private boolean checkInput(String input){
		for(int x=0; x<4; x++) {
			if(Arrays.asList(controls[x]).contains(input)){
				return true;
			}
		}
		return false;
	}

	//Controls
	@Override
	public void keyPressed(KeyEvent e){
		String input = String.valueOf(e.getKeyChar());
		if (checkInput(input)) {
			switch(input) {
				//Swap alphabets
				case "=":
					changePage(!nextPage);
				break;
				//Remove the last character from the name. A 'backspace'
				case "b":
					if(name.length() > 0) {
						name = name.substring(0, name.length() - 1);
					}
				break;
				//Submit the name and score to the leaderboard, if the name has at least one letter.
				case "s":
					if(name.length() != 0){
						Main.database.insertScore(Main.currentScore[0], name, Integer.parseInt(Main.currentScore[1]));
						reset();
						Main.changeCard("score");
					}
				break;
				default:
				//If the name is larger than 8 letters, replace the last letter with the new input letter.
				if(name.length() > 8) {
					name = name.substring(0, name.length() - 1) + alphaMap.get(input);
				}
				//Otherwise, just add the new letter onto the end of 'name'.
				else {
					name += alphaMap.get(input);
				}
			}
		}
		repaint();
	}

	//Graphics
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		//Top rectangles
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, 0, w, 50);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, 50, w, 50);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, 100, w, 50);

		//Text
		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setPaint(Color.blue);
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 50));
		g2d.drawString("Congratulations, your score of " + Main.currentScore[1] + " made it to the leaderboards!" ,w/2-700 ,200 );
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 30));
		g2d.drawString("Enter your name:" ,w/2-150 , 250 );

		//Name rectangle
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 40));
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-500, 10, 1000, 125);
		g2d.setPaint(Color.black);
		g2d.drawString(name, w/2-475, 100);
		g2d.drawRect(w/2-500, 10, 1000, 125);

		//Each individual letter from the 4x4 grid, and the rectangle it resides in
		for (int x = 0; x<4; x++) {
			for (int y = 0; y<4; y++) {
				g2d.setColor(new Color(247, 245, 116));
				g2d.fillRect(w/2-300+(x*150), 300+(y*150), 125, 125);
				g2d.setPaint(Color.black);
				g2d.drawRect(w/2-300+(x*150), 300+(y*150), 125, 125);
				g2d.setPaint(Color.blue);
				g2d.drawString(alpha[y][x], w/2-296+(x*150), 375+(y*150));
			}
		}
		g2d.dispose();
	}


}
