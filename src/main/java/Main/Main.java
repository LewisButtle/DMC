package Main;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BasicStroke;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel implements ActionListener {

    private final int DELAY = 150;
	private Timer timer;

    public Surface() {
        initTimer();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        
        return timer;
    }

    private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		
        int w = getWidth();
		int h = getHeight();
		//right side rectangles
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(w-100, 0, 100, h);
		g2d.setPaint(Color.green);
		g2d.fillRect(w-200, 0, 100, h);
		g2d.setPaint(Color.red);
		g2d.fillRect(w-300, 0, 100, h);

		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		//centre 'buttons'
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-400, h/2-200, 800, 100);
		g2d.fillRect(w/2-400, h/2-50, 800, 100);
		g2d.fillRect(w/2-400, h/2+100, 800, 100);
		g2d.fillRect(w/2-400, h/2+250, 800, 100);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-400, h/2-200, 800, 100);
		g2d.drawRect(w/2-400, h/2-50, 800, 100);
		g2d.drawRect(w/2-400, h/2+100, 800, 100);
		g2d.drawRect(w/2-400, h/2+250, 800, 100);

		//Left diagonal rectangles
		g2d.rotate(Math.PI/4-0.2);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, -200, 100, 400);
		g2d.setPaint(Color.green);
		g2d.fillRect(100, -200, 100, 500);
		g2d.setPaint(Color.blue);
		g2d.fillRect(200, -200, 100, 700);

		g2d.setPaint(Color.black);
        Random r = new Random();
        for (int i = 0; i < 2000; i++) {

            int x = Math.abs(r.nextInt()) % w;
            int y = Math.abs(r.nextInt()) % h;
            g2d.drawLine(x, y, x, y);
		}
		
		g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

public class Main extends JFrame {

    public Main() {
        initUI();
    }

    private void initUI() {
		final Surface surface = new Surface();
        add(surface);

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