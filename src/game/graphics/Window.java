package game.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Window extends JFrame {

	private JPanel pMain = new JPanel();
	private static Canvas canvas = new Canvas();
	private static BufferedImage image = null;
	private String title;
	private int width, height;

	/**
	 * Main window with a canvas and a menu on the right.
	 * 
	 * @param title
	 *            Name of the window
	 * @param width
	 *            Width of the window
	 * @param height
	 *            Height of the window
	 */
	public Window(String title, int width, int height) {
		super(title);
		this.title = title;
		this.width = width;
		this.height = height;

		// Setting up window parameters
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLayout(new GridBagLayout());

		// Setting up the panel that will hold everything on the frame
		pMain.setLayout(new GridBagLayout());
		
		// Setting up the canvas
		canvas.setSize(width, height);
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));

		pMain.add(canvas);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		add(pMain);
		pack();
	}

	public InputMap getInputMap() {
		return pMain.getInputMap();
	}

	public ActionMap getActionMap() {
		return pMain.getActionMap();
	}

	public BufferedImage getBufferedImage() {
		return image;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Renders the canvas and all the shapes that are in the list
	 */
	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, width, height, null);

		g.dispose();

		bs.show();
	}
}
