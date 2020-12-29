package Main;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Main extends JFrame {
    Surface mainMenu;
    Surface calculatorMode;
    Surface floatingNumbers;
    Surface speedNumbers;
    Surface viewLeaderboards;
    Surface enterLeaderboards;

    private static final String CHANGE_SCREENS = "change screens";

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

    private class ChangeScreens extends AbstractAction {
        Surface original;
        Surface swapper;
        ChangeScreens(Surface original, Surface swapper) {
            this.original = original;
            this.swapper = swapper;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("test");
            original.setVisible(false);
            remove(original);
            add(swapper);
            swapper.setVisible(true);
            swapper.repaint();
            revalidate();
            // Same as the move method in the question code.
            // Player can be detected by e.getSource() instead and call its own move method.
        }
    }

    private void swapScreens(Surface first, Surface second) {
        first.setVisible(false);
        remove(first);
        add(second);
        second.setVisible(true);
        second.repaint();
        second.revalidate();
    }

    private void initMainMenu() {
        mainMenu = new Surface(0);
        mainMenu.getInputMap().put(KeyStroke.getKeyStroke("2"), CHANGE_SCREENS);
        mainMenu.getActionMap().put(CHANGE_SCREENS, new ChangeScreens(mainMenu, calculatorMode));

    }

    private void initCalculatorMode() {
        calculatorMode = new Surface(1);


    }

    private void initFloatingNumbers() {
        floatingNumbers = new Surface(2);
 
    }

    private void initSpeedNumbers() {
        speedNumbers = new Surface(3);

    }
   
    private void initViewLeaderboards() {
        viewLeaderboards = new Surface(4);

    }
   
    private void initEnterLeaderboards() {
        enterLeaderboards = new Surface(5);

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