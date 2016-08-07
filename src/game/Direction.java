package game;

import java.awt.event.KeyEvent;

public enum Direction {

	LEFT(KeyEvent.VK_LEFT, -1, 0),
	RIGHT(KeyEvent.VK_RIGHT, 1, 0),
    UP(KeyEvent.VK_UP, 0, -1),
    DOWN(KeyEvent.VK_DOWN, 0, 1),
    TURN(KeyEvent.VK_SPACE, 0, 0),
    ESCAPE(KeyEvent.VK_ESCAPE, 0, 0);

	private int keyCode, x, y;

	Direction(int keyCode, int x, int y) {
		this.keyCode = keyCode;
		this.x = x;
		this.y = y;
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
