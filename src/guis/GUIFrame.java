
package guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The GUI for assignment 1
 */
public class GUIFrame extends JFrame implements ActionListener 
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;		// The Main window
	private JButton btnOpen;	// Open sound file button
	private JButton btnPlay;	// Play selected file button
	private JButton btnStop;	// Stop music button
	private JButton btnDisplay;	// Start thread moving display
	private JButton btnDStop;	// Stop moving display thread
	private JButton btnTriangle;// Start moving graphics thread
	private JButton btnTStop;	// Stop moving graphics thread
	private JButton btnTSpeed;
	private JLabel lblPlaying;	// Hidden, shown after start of music
	private JLabel lblPlayURL;	// The sound file path
	private JPanel pnlMove;		// The panel to move display in
	private JPanel pnlRotate;	// The panel to move graphics in
	private MusicPlayer player;
	private GUIMonitor gui;
	private Text text;

	/**
	 * Constructor
	 */
	public GUIFrame() {
		player = new MusicPlayer();
		gui = new GUIMonitor();
		text = new Text();
	
	}

	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 494, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI()
	{
		// The play panel
		JPanel pnlSound = new JPanel();

		Border b1 = BorderFactory.createTitledBorder("Music Player");
		pnlSound.setBorder(b1);
		pnlSound.setBounds(12, 12, 450, 100);
		pnlSound.setLayout(null);
		
		// Add the buttons and labels to this panel
		btnOpen = new JButton("Open");
		btnOpen.setBounds(6, 71, 75, 23);
		pnlSound.add(btnOpen);
		btnOpen.addActionListener(this);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(88, 71, 75, 23);
		pnlSound.add(btnPlay);
		btnPlay.addActionListener(this);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(169, 71, 75, 23);
		pnlSound.add(btnStop);
		btnStop.addActionListener(this);
		
		lblPlaying = new JLabel("Now Playing...",JLabel.CENTER);
		lblPlaying.setFont(new Font("Serif", Font.BOLD, 20));

		lblPlaying.setBounds(128, 16, 200, 30);

		pnlSound.add(lblPlaying);

		
		lblPlayURL = new JLabel("Music url goes here");
		lblPlayURL.setBounds(10, 44, 300, 13);

		pnlSound.add(lblPlayURL);
		// Then add this to main window
		frame.add(pnlSound);
		
		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 118, 222, 269);
		pnlDisplay.setLayout(null);
		
		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		pnlDisplay.add(btnDisplay);
		btnDisplay.addActionListener(this);
		
		btnTSpeed = new JButton("Speed");
		btnTSpeed.setBounds(70, 246, 121, 23);
		pnlDisplay.add(btnTSpeed);
		btnTSpeed.addActionListener(this);

		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		pnlDisplay.add(btnDStop);
		btnDStop.addActionListener(this);
		
		pnlMove = new JPanel();
		pnlMove.setBounds(10, 19, 200, 200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
		pnlDisplay.add(pnlMove);

		pnlMove.add(text);
		// Then add this to main window
		frame.add(pnlDisplay);
		
		// The moving graphics outer panel
		JPanel pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
		pnlTriangle.setBorder(b3);

		pnlTriangle.setBounds(240, 118, 222, 269);
		pnlTriangle.setLayout(null);
		
		// Add buttons and drawing panel to this panel
		btnTriangle = new JButton("Start Rotate");
		btnTriangle.setBounds(10, 226, 121, 23);
		btnTriangle.addActionListener(this);
		pnlTriangle.add(btnTriangle);
		
		
		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		btnTStop.addActionListener(this);
		pnlTriangle.add(btnTStop);
		

		pnlRotate = new JPanel();
		pnlRotate.setBounds(10, 19, 200, 200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.setBorder(b31);

		pnlRotate.add(gui);

		pnlTriangle.add(pnlRotate);
		// Add this to main window
		frame.add(pnlTriangle);
	
		btnPlay.setEnabled(false);
		btnStop.setEnabled(false);
		btnDStop.setEnabled(false);
		btnTStop.setEnabled(false);

	}


	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if (o == btnOpen) {
			player.open();
			btnPlay.setEnabled(true);
			btnOpen.setEnabled(false);
			lblPlayURL.setText(player.title());
			}
			if (o == btnPlay) {

			btnPlay.setEnabled(false);
			btnStop.setEnabled(true);
			player.play();

		}
		if (o == btnStop) {
			btnOpen.setEnabled(true);
			btnPlay.setEnabled(true);
			btnStop.setEnabled(false);
			player.stop();

			}
		if (o == btnDisplay) {
			btnDisplay.setEnabled(false);
			btnDStop.setEnabled(true);
			text.startText();
		}
		if (o == btnDStop) {
			btnDisplay.setEnabled(true);
			btnDStop.setEnabled(false);
			text.stopText();
		}
		if (o == btnTriangle) {
			btnTriangle.setEnabled(false);
			btnTStop.setEnabled(true);
			gui.startRot();
		}
		if (o == btnTStop) {
			btnTriangle.setEnabled(true);
			btnTStop.setEnabled(false);
			gui.stopRot();
		}

		if (o == btnTSpeed) {
			int h = text.getDx();
			h += 1;
			text.setDx(h);
		}
			}
}
