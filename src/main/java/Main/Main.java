package Main;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame {
    Surface mainMenu;
    Surface calculatorMode;
    Surface floatingNumbers;
    Surface speedNumbers;
    Surface viewLeaderboards;
    Surface enterLeaderboards;

    public Main() {
        initUI();
    }

    private void initUI() {
		//final Surface surface = new Surface();
        //add(surface);
        initMainMenu();
        initCalculatorMode();
        initFloatingNumbers();
        initSpeedNumbers();
        initViewLeaderboards();
        initEnterLeaderboards();
        add(mainMenu);
        /*
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = mainMenu.getTimer();
                timer.stop();
            }
        });
        */
        setTitle("Dance Mat Calculator");
        setSize(1750, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //remove(surface);
    }
    
    private void initMainMenu() {
        mainMenu = new Surface(0);
        addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case '2':
                        mainMenu.setVisible(false);
                        remove(mainMenu);
                        add(calculatorMode);
                        calculatorMode.setVisible(true);
                        calculatorMode.repaint();
                        calculatorMode.revalidate();
                        break;
                    case '3':
                        mainMenu.setVisible(false);
                        remove(mainMenu);
                        add(floatingNumbers);
                        floatingNumbers.setVisible(true);
                        floatingNumbers.repaint();
                        floatingNumbers.revalidate();
                        break;
                    case '4':
                        mainMenu.setVisible(false);
                        remove(mainMenu);
                        add(speedNumbers);
                        speedNumbers.setVisible(true);
                        speedNumbers.repaint();
                        speedNumbers.revalidate();
                        break;
                    case '5':
                        mainMenu.setVisible(false);
                        remove(mainMenu);
                        add(viewLeaderboards);
                        viewLeaderboards.setVisible(true);
                        viewLeaderboards.repaint();
                        viewLeaderboards.revalidate();
                        break;
                    default:
                  }
			}
        });
    }

    private void initCalculatorMode() {
        calculatorMode = new Surface(1);
        addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case '0':
                    break;
                    case '1':
                    
                    break;
                    case '2':
                    
                        break;
                    case '3':
                    
                        break;
                    case '4':
                      
                        break;
                    case '5':
                    
                        break;
                    case '6':

                        break;
                    case '7':
                    
                        break;      
                    case '8':
                    
                        break;
                    case '9':
                    System.out.println("HIYA");
                    calculatorMode.setVisible(false);
                    remove(calculatorMode);
                    add(mainMenu);
                    mainMenu.setVisible(true);
                    mainMenu.repaint();
                    mainMenu.revalidate();
                        break;
                    case '*':
                    
                        break;
                    case '+':
                    
                        break;
                    case '-':
                    
                        break;
                    case '=':
                    
                        break;
                    case 'b':
                    
                        break;
                    case 's':

                        break;
                    default:
                      // code block
                  }
			}
        });
    }

    private void initFloatingNumbers() {
        floatingNumbers = new Surface(2);
        floatingNumbers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case '0':
                    
                    break;
                    case '1':
                    
                    break;
                    case '2':
                    
                        break;
                    case '3':
                      
                        break;
                    case '4':
                      
                        break;
                    case '5':
                      
                        break;
                    case '6':

                        break;
                    case '7':
                    
                        break;      
                    case '8':
                    
                        break;
                    case '9':
                    
                        break;
                    case '*':
                    
                        break;
                    case '+':
                    
                        break;
                    case '-':
                    
                        break;
                    case '=':
                    
                        break;
                    case 'b':
                    
                        break;
                    case 's':
                    
                        break;
                    default:
                      // code block
                  }
			}
        });
    }

    private void initSpeedNumbers() {
        speedNumbers = new Surface(3);
        speedNumbers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case '0':
                    
                    break;
                    case '1':
                    
                    break;
                    case '2':
                    
                        break;
                    case '3':
                      
                        break;
                    case '4':
                      
                        break;
                    case '5':
                      
                        break;
                    case '6':

                        break;
                    case '7':
                    
                        break;      
                    case '8':
                    
                        break;
                    case '9':
                    
                        break;
                    case '*':
                    
                        break;
                    case '+':
                    
                        break;
                    case '-':
                    
                        break;
                    case '=':
                    
                        break;
                    case 'b':
                    
                        break;
                    case 's':
                    
                        break;
                    default:
                      // code block
                  }
			}
        });
    }
   
    private void initViewLeaderboards() {
        viewLeaderboards = new Surface(4);
        viewLeaderboards.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case '0':
                    
                    break;
                    case '1':
                    
                    break;
                    case '2':
                    
                        break;
                    case '3':
                      
                        break;
                    case '4':
                      
                        break;
                    case '5':
                      
                        break;
                    case '6':

                        break;
                    case '7':
                    
                        break;      
                    case '8':
                    
                        break;
                    case '9':
                    
                        break;
                    case '*':
                    
                        break;
                    case '+':
                    
                        break;
                    case '-':
                    
                        break;
                    case '=':
                    
                        break;
                    case 'b':
                    
                        break;
                    case 's':
                    
                        break;
                    default:
                      // code block
                  }
			}
        });
    }
   
    private void initEnterLeaderboards() {
        enterLeaderboards = new Surface(5);
        enterLeaderboards.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case '0':
                    
                    break;
                    case '1':
                    
                    break;
                    case '2':
                    
                        break;
                    case '3':
                      
                        break;
                    case '4':
                      
                        break;
                    case '5':
                      
                        break;
                    case '6':

                        break;
                    case '7':
                    
                        break;      
                    case '8':
                    
                        break;
                    case '9':
                    
                        break;
                    case '*':
                    
                        break;
                    case '+':
                    
                        break;
                    case '-':
                    
                        break;
                    case '=':
                    
                        break;
                    case 'b':
                    
                        break;
                    case 's':
                    
                        break;
                    default:
                      // code block
                  }
			}
        });
    }
   
   
    public static void main(String[] args) {
		
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}