package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import com.fazecast.jSerialComm.*;
import java.util.Arrays;

public class Main extends JFrame {

	public static DatabaseScores database = new DatabaseScores();

	private static final Surface mainSurface = new MainSurface();
	private static final Surface scoreSurface = new ScoreboardSurface();
	private static final Surface scoreEnterSurface = new ScoreboardEntrySurface();
	private static final Surface fNumSurface = new FNumSurface();
	private static final Surface speedNumSurface = new SpeedNumSurface();
	private static final Surface calculatorSurface = new CalculatorSurface();


	final char[] inputs = {'0','1','2','3','4','5','6','7','8','9','p','-','x','=','s','b'};

	private static String currentCard = "main";
	private static JPanel cards = new JPanel(new CardLayout());
	HashMap<String, Surface> surfaceMap = new HashMap<String, Surface>();
	
	public static String[] currentScore = {"",""};

	public Main(){
		setTitle("Dance Mat Calculator");
		setSize(1750, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		SerialPort comPort = SerialPort.getCommPort("COM4");
		comPort.setComPortParameters(9600, 8, 1, 0);
		//comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);

		if(comPort.openPort()){
			System.out.println("Port initialized");
		}
		else{
			System.out.println("Port Unavailable - Unable to link to Dance Mat hardware.");
		}

		comPort.addDataListener(new SerialPortDataListener() {

		   @Override
		   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }

		   @Override
		   public void serialEvent(SerialPortEvent event)
		   {
			if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
				return;
			}
			byte[] newData = new byte[comPort.bytesAvailable()];
			comPort.readBytes(newData, newData.length);
			
			char dmcInput = new String(newData).charAt(0);
			
			if(new String(inputs).contains(String.valueOf(dmcInput))){
				dmcInputKey(dmcInput);
			}
		   }
		});
	}

	public static void dmcInputKey(char input){
		int keyCode = 0;

		switch(input) {
			case 'p': keyCode = KeyEvent.VK_P; break;
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
			
			case 'x': keyCode = KeyEvent.VK_X; break;
			case 'b': keyCode = KeyEvent.VK_B; break;
			case 's': keyCode = KeyEvent.VK_S; break;
			case '=': keyCode = KeyEvent.VK_EQUALS; break;
		}
		System.out.println(input);
		System.out.println(keyCode);
		try{
			Robot typer = new Robot();
			typer.keyPress(keyCode);
			typer.keyRelease(keyCode);
		}
		catch(AWTException ex){
			System.out.println("Error with input.");
		}
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
