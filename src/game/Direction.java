package game;

import java.awt.event.KeyEvent;

public enum Direction {
	LEFT("Left", KeyEvent.VK_LEFT, -1, 0), RIGHT("Right", KeyEvent.VK_RIGHT, 1, 0), UP("Up", KeyEvent.VK_UP, 0,
			-1), DOWN("Down", KeyEvent.VK_DOWN, 0, 1), TURN("Turn", KeyEvent.VK_SPACE, 0, 0), ESCAPE("Escape",
					KeyEvent.VK_ESCAPE, 0, 0);

	private String name;
	private int keyCode, x, y;

	Direction(String name, int keyCode, int x, int y) {
		this.name = name;
		this.keyCode = keyCode;
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
