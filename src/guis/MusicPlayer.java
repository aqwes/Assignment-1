package guis;

import java.io.File;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class start a Thread and has a filechooser and music player. There are
 * also one method for play a song and one for pause
 * 
 * @author Dennis
 *
 */
public class MusicPlayer extends JFrame {
	private JFileChooser fileChooser;
	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;

	/**
	 * Constructor
	 */

	public MusicPlayer() {
		fileChooser = new JFileChooser();
		new JFXPanel();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {


			}
		});
	}

	/**
	 * Contains a filechooser with a filter that only shows waw and mp3 files.
	 */
	public void open() {

		FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 & WAW", "mp3", "waw");
		fileChooser.setFileFilter(filter);

		int option = fileChooser.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();

				String song = file.toString();
				media = new Media(Paths.get(song).toUri().toString());
				mediaPlayer = new MediaPlayer(media);
		}

	}

	/**
	 * This method starts thread and then plays the song
	 */
	public void play() {
		mediaPlayer.play();

	}

	/**
	 * This method interrupt the thread and then stops the song
	 */
	@SuppressWarnings("deprecation")
	public void stop() {
		mediaPlayer.stop();

	}

	/**
	 * This method returns the name of the song
	 * 
	 * @return
	 */
	public String title() {
		return file.getName();
	}
}