package game.tiles;

import game.Game;
import game.graphics.Vector2D;

public class Tile {

	private Vector2D pos;
	private int color;
	private int shapeID, width, height;

	public Tile(Vector2D pos, int width, int height, int color, int shapeID) {
		this.pos = pos;
		this.color = color;
		this.shapeID = shapeID;
		this.width = width;
		this.height = height;
	}

	/**
	 * Adds shape to the given pixel-array and returns the new array
	 * 
	 * @param pixels
	 *            Pixel-array to which the shape is going to be added
	 * @return Resulting pixel-array
	 */
	public int[] addToPixels(int[] pixels) {
		for (int row = (int) pos.getY(); row < pos.getY() + height; row++) {
			for (int col = (int) pos.getX(); col < pos.getX() + width; col++) {
				pixels[row * Game.getCanvas().getWidth() + col] = color;
			}
		}

		return pixels;
	}

	public int getShapeID() {
		return shapeID;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Vector2D getPos() {
		return pos;
	}

	public void setPos(Vector2D pos) {
		this.pos = pos;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
