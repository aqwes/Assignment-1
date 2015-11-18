package guis;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.JPanel;

/**
 * This class start a Thread and has a triangle that circulates . There are one
 * method for start the triangle and one for pausing it
 * 
 * @author Dennis
 *
 */


@SuppressWarnings("serial")
public class GUIMonitor extends JPanel implements Runnable {
	
	private double angle = 0;
	private Color color;
	private float luminance;
	private float saturation;
	private float hue;
	private Random rand;
	private Thread t;
	boolean start = false;

	/**
	 * Constructor
	 */

	public GUIMonitor() {
		rand = new Random();
		this.setPreferredSize(new Dimension(195, 190));
	}

	/**
	 * This method starts the tread
	 */
	public void startRot() {
		t = new Thread(this);
		t.start();
		start = true;

	}

	/**
	 * This method stops the tread
	 */
	public void stopRot() {
		start = false;
		t.interrupt();
		}

	/**
	 * This method changes the color with random values
	 */
	public void colorChange() {
			rand = new Random();
			hue = rand.nextFloat();
			saturation = (rand.nextInt(2000) + 4000) / 10000f;
			luminance = 0.4f;
			color = Color.getHSBColor(hue, saturation, luminance);

	}

	/**
	 * Here I call the method colorChange, increase the angle, pains the
	 * triangle and makes the tread sleep in intervals.
	 */
	@Override
	public void run() {
		try {
			while (start == true && !Thread.interrupted()) {
				colorChange();
				angle += 0.05;
				repaint();
				Thread.sleep(100);
			}

		} catch (InterruptedException e) {
		}
		t = null;


	}

	/**
	 * This method makes draws the triangle.
	 */
	public void paint(Graphics g) {
		Graphics2D graph2 = (Graphics2D) g;

		int h = getHeight();
		int w = getWidth();

		graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graph2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		graph2.translate(w / 2, h / 2);
		graph2.rotate(angle);

		Polygon triangle = new Polygon();
		triangle.translate(h, w);
		triangle.addPoint(0, 20);
		triangle.addPoint(80, 55);
		triangle.addPoint(80, 0);
		graph2.setColor(color);

		if (start == true) {
			graph2.fill(triangle);
		graph2.draw(triangle);
		}

	}
}
