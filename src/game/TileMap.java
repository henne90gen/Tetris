package game;

import game.graphics.ScoreCounter;
import game.graphics.Vector2D;
import game.tiles.TI;
import game.tiles.TJ;
import game.tiles.TL;
import game.tiles.TO;
import game.tiles.TS;
import game.tiles.TT;
import game.tiles.TZ;
import game.tiles.Tetrimino;
import game.tiles.Tile;

public class TileMap {

	public static final int TILE_SIZE = 20;
	public static final int TILE_MAP_WIDTH = 14;
	public static final int TILE_MAP_HEIGHT = 20;
	private static Tile[][] blocks = new Tile[TILE_MAP_HEIGHT][TILE_MAP_WIDTH];
	private static boolean[][] collisionMap = new boolean[TILE_MAP_WIDTH][TILE_MAP_HEIGHT];
	private static Tetrimino currentTetr;
	private static ScoreCounter scoreCounter;

	static void init(ScoreCounter counter) {
		scoreCounter = counter;
		addNewTetr();
	}

	public static void turn() {
		currentTetr.turn(collisionMap);
	}

	public static void move(Direction dir) {
		currentTetr.move(collisionMap, dir);
		updateTileMap();
	}

	static int[] addToPixels(int[] pixels) {
		for (int row = 0; row < TILE_MAP_HEIGHT; row++) {
			for (int col = 0; col < TILE_MAP_WIDTH; col++) {
				if (blocks[row][col] != null) {
					pixels = blocks[row][col].addToPixels(pixels);
				}
			}
		}
		pixels = currentTetr.addToPixels(pixels);
		for (int row = 0; row < TILE_MAP_HEIGHT * TILE_SIZE; row++) {
			for (int col = 0; col < TILE_MAP_WIDTH * TILE_SIZE; col++) {
				if (row >= (TILE_MAP_HEIGHT - 1) * TILE_SIZE || col <= TILE_SIZE
						|| col >= (TILE_MAP_WIDTH - 1) * TILE_SIZE) {
					pixels[row * (TILE_MAP_WIDTH * TILE_SIZE) + col] = (100 << 16) + (200 << 8) + 20;
				}
			}
		}
		return pixels;
	}

	private static void addNewTetr() {
		
		int id = 10;
		while (id > 6) {
			id = Math.round((float) Math.random() * 10);
		}
		// id = 1;

		switch (id) {
		case Tetrimino.ID_O:
			currentTetr = new TO(new Vector2D(1, 1));
			break;
		case Tetrimino.ID_I:
			currentTetr = new TI(new Vector2D(1, 1));
			break;
		case Tetrimino.ID_T:
			currentTetr = new TT(new Vector2D(1, 1));
			break;
		case Tetrimino.ID_L:
			currentTetr = new TL(new Vector2D(1, 1));
			break;
		case Tetrimino.ID_J:
			currentTetr = new TJ(new Vector2D(1, 1));
			break;
		case Tetrimino.ID_S:
			currentTetr = new TS(new Vector2D(1, 1));
			break;
		case Tetrimino.ID_Z:
			currentTetr = new TZ(new Vector2D(1, 1));
			break;
		}
		updateTileMap();
		checkRows();
	}

	public static void releaseBlocks(Tile[] releasedBlocks) {
		for (Tile newBlock : releasedBlocks) {
			if (!collisionMap[newBlock.getPos().getX() / TILE_SIZE][newBlock.getPos().getY() / TILE_SIZE]) {
				blocks[newBlock.getPos().getY() / TILE_SIZE][newBlock.getPos().getX()
						/ TILE_SIZE] = newBlock;
			} else {
				Game.gameOver();
			}
		}
		addNewTetr();
	}

	private static void updateTileMap() {
		collisionMap = new boolean[TILE_MAP_WIDTH][TILE_MAP_HEIGHT];
		for (int i = 0; i < TILE_MAP_HEIGHT; i++) {
			if (i == TILE_MAP_HEIGHT - 1) {
				for (int j = 0; j < TILE_MAP_WIDTH; j++) {
					collisionMap[j][i] = true;
				}
			} else {
				collisionMap[0][i] = true;
				collisionMap[TILE_MAP_WIDTH - 1][i] = true;
			}
		}
		for (int row = 0; row < TILE_MAP_HEIGHT; row++) {
			for (int col = 0; col < TILE_MAP_WIDTH; col++) {
				if (blocks[row][col] != null) {
					collisionMap[col][row] = true;
				}
			}
		}
	}

	/**
	 * Goes through the every row and checks if there is one completed in which
	 * case that row is removed
	 */
	private static void checkRows() {
		int currentRow = TILE_MAP_HEIGHT - 1;
		while (currentRow > 0) {
			boolean lineComplete = true;
			for (int row = currentRow; row >= 0; row--) {
				lineComplete = true;
				for (int col = 1; col < TILE_MAP_WIDTH - 1; col++) {
					if (blocks[row][col] == null) {
						lineComplete = false;
						break;
					}
				}
				currentRow = row - 1;
				if (lineComplete) {
					currentRow = row;
					break;
				}
			}
			if (lineComplete) {
				for (int row = currentRow; row > 1; row--) {
					for (int col = 1; col < TILE_MAP_WIDTH - 1; col++) {
						blocks[row][col] = blocks[row - 1][col];
						blocks[row - 1][col] = null;
						if (blocks[row][col] != null) {
							blocks[row][col].setPos(new Vector2D(col * TILE_SIZE, row * TILE_SIZE));
						}
						updateTileMap();
					}
				}
				scoreCounter.updateScore(1);
			}
		}

	}
}