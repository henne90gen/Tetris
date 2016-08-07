package game.tiles;

import game.Direction;
import game.Game;
import game.graphics.Vector2D;

public abstract class Tetrimino {

	protected Tile[] tiles;
	protected Vector2D pos;
	private int id;
	Direction orientation;
	protected int color;

	public static final int ID_O = 0;
	public static final int ID_I = 1;
	public static final int ID_T = 2;
	public static final int ID_L = 3;
	public static final int ID_J = 4;
	public static final int ID_S = 5;
	public static final int ID_Z = 6;

	Tetrimino(Vector2D pos, int id, int color) {
		this.pos = pos;
		this.id = id;
		this.color = color;
		orientation = Direction.UP;
		updateTiles();
	}

	public abstract void turn(boolean[][] collisionMap);

	public abstract void move(boolean[][] collisionMap, Direction dir);

	/**
	 * Updating the position of all the small rectangles that make up a tile
	 */
	public abstract void updateTiles();

	public int[] addToPixels(int[] pixels) {
		if (tiles != null) {
			for (Tile tile : tiles) {
				for (int row = tile.getPos().getY(); row < tile.getPos().getY() + tile.getHeight(); row++) {
					for (int col = tile.getPos().getX(); col < tile.getPos().getX() + tile.getWidth(); col++) {
						pixels[row * Game.getCanvas().getWidth() + col] = tile.getColor();
					}
				}
			}
		}
		return pixels;
	}

}
