package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import com.fazecast.jSerialComm.*;

public class Main extends JFrame {
	//Create a database object, which will initialize contact with the database
	public static DatabaseScores database = new DatabaseScores();

	//Initialize each surface AKA the individual screens
	private static final Surface mainSurface = new MainSurface();
	private static final Surface scoreSurface = new ScoreboardSurface();
	private static final Surface scoreEnterSurface = new ScoreboardEntrySurface();
	private static final Surface fNumSurface = new FNumSurface();
	private static final Surface speedNumSurface = new SpeedNumSurface();
	private static final Surface calculatorSurface = new CalculatorSurface();

	//An array of all possible inputs - to use to accept or reject user inputs
	final char[] inputs = {'0','1','2','3','4','5','6','7','8','9','p','-','x','=','s','b'};

	//A string to store the current on-screen surface, initialise it as 'main'
	private static String currentCard = "main";

	//Variables to better structure and interchange these surfaces.
	private static JPanel cards = new JPanel(new CardLayout());
	HashMap<String, Surface> surfaceMap = new HashMap<String, Surface>();
	
	//A public variable to pass a user's score between surfaces
	public static String[] currentScore = {"",""};

	//Initialize the software
	public Main(){
		//Customize and create the game window
		setTitle("Dance Mat Calculator");
		setSize(1750, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create a card layout by adding the surfaces with assigned string names
		surfaceMap.put("main", mainSurface);
		surfaceMap.put("score", scoreSurface);
		surfaceMap.put("enterscore", scoreEnterSurface);
		surfaceMap.put("calculator", calculatorSurface);
		surfaceMap.put("float", fNumSurface);
		surfaceMap.put("speed", speedNumSurface);

		//Add the "surfaces" to the hashmap, to attribute a string to each surface
		cards.add(surfaceMap.get("main"), "main");
		cards.add(surfaceMap.get("score"), "score");
		cards.add(surfaceMap.get("enterscore"), "enterscore");
		cards.add(surfaceMap.get("calculator"), "calculator");
		cards.add(surfaceMap.get("float"), "float");
		cards.add(surfaceMap.get("speed"), "speed");

		//Switch to the main layout (Main Menu)
		changeCard("main");

		//Add the card layout
		add(cards);

		//A key listener - transfers inputs to the current surface.
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				surfaceMap.get(currentCard).keyPressed(e);
			}
		});

		////Port Initialisation to connect to Dance Mat hardware//
		SerialPort comPort = SerialPort.getCommPort("COM4");
		comPort.setComPortParameters(9600, 8, 1, 0);

		//Detects and reports if connecting to the port is successful
		if(comPort.openPort()){
			System.out.println("Port initialized");
		}
		else{
			System.out.println("Port Unavailable - Unable to link to Dance Mat hardware.");
		}

		//For listening on the port for user inputs
		comPort.addDataListener(new SerialPortDataListener() {

		   @Override
		   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }

		   @Override
		   public void serialEvent(SerialPortEvent event)
		   {
			//Detect if port data is available and if so, process it
			if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
				return;
			}
			//Convert raw byte data from the port into a string character
			byte[] newData = new byte[comPort.bytesAvailable()];
			comPort.readBytes(newData, newData.length);
			char dmcInput = new String(newData).charAt(0);
			
			//If the string is recognized as a suitable input, pass the string to dmcInputKey to press it
			if(new String(inputs).contains(String.valueOf(dmcInput))){
				dmcInputKey(dmcInput);
			}
		   }
		});
	}

	//A string to virtually press a key of a given character
	//'input' : The character of which a KeyEvent will be activated
	public static void dmcInputKey(char input){
		int keyCode = 0;

		//Find the relevant keycode for input character
		switch(input) {
			case 'p': keyCode = KeyEvent.VK_ADD; break;
			case '0': keyCode = KeyEvent.VK_0; break;
			case '1': keyCode = KeyEvent.VK_1; break;
			case '-': keyCode = KeyEvent.VK_MINUS; break;

			case '2': keyCode = KeyEvent.VK_2; break;
			case '3': keyCode = KeyEvent.VK_3; break;	
			case '4': keyCode = KeyEvent.VK_4; break;
			case '5': keyCode = KeyEvent.VK_5; break;

			case '6': keyCode = KeyEvent.VK_6; break;
			case '7': keyCode = KeyEvent.VK_7; break;
			case '8': keyCode = KeyEvent.VK_8; break;
			case '9': keyCode = KeyEvent.VK_9; break;
			
			case 'x': keyCode = KeyEvent.VK_MULTIPLY; break;
			case 'b': keyCode = KeyEvent.VK_B; break;
			case 's': keyCode = KeyEvent.VK_S; break;
			case '=': keyCode = KeyEvent.VK_EQUALS; break;
		}

		//Try virtually pressing and releasing the keycode, to mimic a keypress
		try{
			Robot typer = new Robot();
			typer.keyPress(keyCode);
			typer.keyRelease(keyCode);
		}
		catch(AWTException ex){
			System.out.println("Error with input.");
		}
	}

	//A function to switch the current surface to a given surface
	//'name': The string identifier of the surface to be switched to
	public static void changeCard(String name){
		currentCard = name;
		((CardLayout) cards.getLayout()).show(cards, currentCard);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			//Make Main visible after initialision is complete.
			Main ex = new Main();
			ex.setVisible(true);
		});
	}
}
