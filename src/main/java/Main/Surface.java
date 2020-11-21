package Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BasicStroke;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel implements ActionListener {

	private int screen;
    private final int DELAY = 150;
	private Timer timer;

    public Surface(int screennum) {
		initTimer();
		this.screen = screennum;
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        
        return timer;
    }

    private void doDrawing(Graphics g) {
		
		switch(screen) {
			case 0:
				mainMenuScreen(g);
				System.out.println("case0");
			  	break;
			case 1:
				calculatorModeScreen(g);
				System.out.println("case1");
				break;
			case 2:
				floatingNumbersScreen(g);
				System.out.println("case2");
				break;
			case 3:
				speedNumbersScreen(g);
				System.out.println("case3");
				break;
			case 4:
				viewLeaderboardsScreen(g);
				System.out.println("case4");
				break;	
			case 5:
				enterLeaderboardsScreen(g);
				System.out.println("case5");
				break;
			default:
			  	// code block
		  }
    }

	private void mainMenuScreen(Graphics g) {
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
		
		//text
		g2d.setPaint(new Color(186, 131, 20));
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 80));
		g2d.drawString("Dance Mat Calculator", w/2-400, h/2-310);
		g2d.setPaint(Color.black);
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 60));
		g2d.drawString("Calculator Mode", w/2-250, h/2-125);
		g2d.drawString("Floating Numbers", w/2-250, h/2+25);
		g2d.drawString("Speed Numbers", w/2-250, h/2+175);
		g2d.drawString("View Leaderboards", w/2-250, h/2+325);

		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 100));
		g2d.setPaint(Color.red);
		g2d.drawString("2", w/2-470, h/2-115);
		g2d.setPaint(Color.green);
		g2d.drawString("3", w/2-470, h/2+35);
		g2d.setPaint(Color.blue);
		g2d.drawString("4", w/2-470, h/2+185);
		g2d.setPaint(Color.red);
		g2d.drawString("5", w/2-470, h/2+335);
		//Left diagonal rectangles
		g2d.rotate(Math.PI/4-0.2);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, -200, 100, 400);
		g2d.setPaint(Color.green);
		g2d.fillRect(100, -200, 100, 500);
		g2d.setPaint(Color.blue);
		g2d.fillRect(200, -200, 100, 700);

		/*
		g2d.setPaint(Color.black);
        Random r = new Random();
        for (int i = 0; i < 2000; i++) {

            int x = Math.abs(r.nextInt()) % w;
            int y = Math.abs(r.nextInt()) % h;
            g2d.drawLine(x, y, x, y);
		}
		*/
		g2d.dispose();
	}

	private void calculatorModeScreen(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();
		//rectangles
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, h-100, w, 100);
		g2d.fillRect(0, 0, w, 100);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, h-200, w, 100);
		g2d.fillRect(0, 100, w, 100);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, h-300, w, 100);
		g2d.fillRect(0, 200, w, 100);

		//Expression Box
		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-700, h/2-75, 1300, 150);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-700, h/2-75, 1300, 150);

		//Text
		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 80));
		g2d.drawString("EXPRESSION PLACEHOLDER", w/2-700, h/2+25);

		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 20));
		g2d.setPaint(new Color(0, 204, 255));
		g2d.drawString("Press 'Start' to return to Main Menu", 5, 20);

		g2d.dispose();
	}
	
	private void floatingNumbersScreen(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, 0, w, 50);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, 50, w, 50);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, 100, w, 50);

		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-500, 10, 1000, 125);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-500, 10, 1000, 125);

		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 80));
		g2d.drawString("TIMER", 25, 100);
		g2d.drawString("SCORE", w-285, 100);
		

		g2d.dispose();
	}
	
	private void speedNumbersScreen(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, 0, w, 50);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, 50, w, 50);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, 100, w, 50);

		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-500, 10, 1000, 125);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-500, 10, 1000, 125);
		g2d.dispose();
	}

	private void viewLeaderboardsScreen(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, 0, w, 50);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, 50, w, 50);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, 100, w, 50);

		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-500, 10, 1000, 125);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-500, 10, 1000, 125);

		g2d.setFont(new Font("Ebrima Bold", Font.PLAIN, 20));
		g2d.setPaint(new Color(0, 204, 255));
		g2d.drawString("Press 'Start' to return to Main Menu", 5, 20);
		
		g2d.dispose();
	}

	private void enterLeaderboardsScreen(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();

		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, w, h);
		g2d.setPaint(Color.blue);
		g2d.fillRect(0, 0, w, 50);
		g2d.setPaint(Color.green);
		g2d.fillRect(0, 50, w, 50);
		g2d.setPaint(Color.red);
		g2d.fillRect(0, 100, w, 50);

		BasicStroke bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(bs1);
		g2d.setColor(new Color(247, 245, 116));
		g2d.fillRect(w/2-500, 10, 1000, 125);
		g2d.setPaint(Color.black);
		g2d.drawRect(w/2-500, 10, 1000, 125);
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

