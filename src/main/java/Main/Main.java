package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.sql.*;  

public class Main extends JFrame {

	public static DatabaseScores database = new DatabaseScores();
	
	private static final Surface mainSurface = new MainSurface();
	private static final Surface scoreSurface = new ScoreboardSurface();
	private static final Surface scoreEnterSurface = new ScoreboardEntrySurface();
	private static final Surface fNumSurface = new FNumSurface();
	private static final Surface speedNumSurface = new SpeedNumSurface();
	private static final Surface calculatorSurface = new CalculatorSurface();

	private static String currentCard = "main";
	private static JPanel cards = new JPanel(new CardLayout());
	HashMap<String, Surface> surfaceMap = new HashMap<String, Surface>();
	
	public static String[] currentScore = {"",""};

	public Main(){
		setTitle("Dance Mat Calculator");
		setSize(1750, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println(Main.database.readDatabase("FloatScores").get(0).first);

		// Create a card layout
		surfaceMap.put("main", mainSurface);
		surfaceMap.put("score", scoreSurface);
		surfaceMap.put("enterscore", scoreEnterSurface);
		surfaceMap.put("calculator", calculatorSurface);
		surfaceMap.put("float", fNumSurface);
		surfaceMap.put("speed", speedNumSurface);

		// Add the "surfaces"
		cards.add(surfaceMap.get("main"), "main");
		cards.add(surfaceMap.get("score"), "score");
		cards.add(surfaceMap.get("enterscore"), "enterscore");
		cards.add(surfaceMap.get("calculator"), "calculator");
		cards.add(surfaceMap.get("float"), "float");
		cards.add(surfaceMap.get("speed"), "speed");

		// Switch to the main layout
		changeCard("main");

		// Add the card layout
		add(cards);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				surfaceMap.get(currentCard).keyPressed(e);
			}
		});
	}

	public static void changeCard(String name){
		currentCard = name;
		// TODO: Unsafe cast
		// TODO: Validate, when name is not valid throw error that can be caught
		((CardLayout) cards.getLayout()).show(cards, currentCard);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Main ex = new Main();
			ex.setVisible(true);
		});
	}
}
