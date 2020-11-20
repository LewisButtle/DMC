package Main;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame {

    public Main() {
        initUI();
    }

    private void initUI() {
		final Surface surface = new Surface();
        add(surface);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();

				if (keycode == 'p' || keycode == 'P') {
					surface.screen = 1;
					return;
				}
			}
		});
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });
        setTitle("Dance Mat Calculator");
        setSize(1750, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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