package guis;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

/**
 * This class start a Thread and has a text that bounces. There are one method
 * for start the text and one for pausing it
 * 
 * @author Dennis
 *
 */

public class Text extends JPanel implements Runnable {

	private Thread t2;
	private Random rand;
	private int dx = 3;
	private int dy = -1;
	private int x = 100;
	private int y = 200;
	private boolean start = false;
	/**
	 * Constructor
	 */
	public Text() {
		rand = new Random();
		this.setPreferredSize(new Dimension(195, 190));
	}

	/**
	 * This method create and starts thread
	 */
	public void startText() {
		t2 = new Thread(this);
		t2.start();
		start = true;
	}

	/**
	 * This method interrupt the thread
	 */
	public void stopText() {
		start = false;
		t2.interrupt();

	}

	/**
	 * This run method moves the text
	 */
	@Override
	public void run() {
		try {
			while (start == true && !Thread.interrupted()) {
				x += dx;
				y += dy;
				
				if (x < 0 || x > 100) {
					dx = -dx;
				}
				if (y < 0 || y > 200) {
					dy = -dy;
				}
				
				Thread.sleep(20);
				repaint();

			}
		} catch (InterruptedException e) {
		}
		t2 = null;

	}

	/**
	 * Makes a object of Graphics2D and
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graph2 = (Graphics2D) g;
		Font font = new Font("Tahoma", Font.BOLD + Font.PLAIN, 15);
		graph2.setFont(font);
		if (start == true) {
			graph2.drawString(t2.getName(), x, y);
		}

	}

	/**
	 * I use these methods for change the speed of the text
	 */
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}
}
