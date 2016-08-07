package game.tiles;

import game.Direction;
import game.TileMap;
import game.graphics.Vector2D;

public class TI extends Tetrimino {

	public TI(Vector2D pos) {
		super(pos, Tetrimino.ID_I, 0xff3300);
		orientation = Direction.LEFT;
		updateTiles();
	}

	public void turn(boolean[][] collisionMap) {
		if ((orientation == Direction.UP || orientation == Direction.DOWN)) {
			if (!collisionMap[pos.getX() + 1][pos.getY() + 1]
					&& !collisionMap[pos.getX() + 2][pos.getY() + 1]
					&& !collisionMap[pos.getX() + 3][pos.getY() + 1]) {
				orientation = Direction.LEFT;
			}
		} else if ((orientation == Direction.LEFT || orientation == Direction.RIGHT)) {
			if (!collisionMap[pos.getX() + 1][pos.getY() + 1]
					&& !collisionMap[pos.getX() + 1][pos.getY() + 2]
					&& !collisionMap[pos.getX() + 1][pos.getY() + 3]) {
				orientation = Direction.UP;
			}
		}
	}

	@Override
	public void move(boolean[][] collisionMap, Direction dir) {
		Vector2D newPos = pos.addVector(new Vector2D(dir.getX(), dir.getY()));
		if (orientation == Direction.UP || orientation == Direction.DOWN) {
			if (dir == Direction.LEFT) {
				if (!collisionMap[newPos.getX() + 1][newPos.getY()]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 1]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 2]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 3]) {
					pos = newPos;
				}
			} else if (dir == Direction.RIGHT) {
				if (!collisionMap[newPos.getX() + 1][newPos.getY()]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 1]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 2]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 3]) {
					pos = newPos;
				}
			} else if (dir == Direction.DOWN) {
				if (!collisionMap[newPos.getX() + 1][newPos.getY() + 3]) {
					pos = newPos;
				} else {
					TileMap.releaseBlocks(tiles);
				}
			}
		} else {
			if (dir == Direction.LEFT) {
				if (!collisionMap[newPos.getX()][newPos.getY() + 1]) {
					pos = newPos;
				}
			} else if (dir == Direction.RIGHT) {
				if (!collisionMap[newPos.getX() + 3][newPos.getY() + 1]) {
					pos = newPos;
				}
			} else if (dir == Direction.DOWN) {
				if (!collisionMap[newPos.getX()][newPos.getY() + 1]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 1]
						&& !collisionMap[newPos.getX() + 2][newPos.getY() + 1]
						&& !collisionMap[newPos.getX() + 3][newPos.getY() + 1]) {
					pos = newPos;
				} else {
					TileMap.releaseBlocks(tiles);
				}
			}
		}
		updateTiles();
	}

	@Override
	public void updateTiles() {
		tiles = new Tile[4];
		if (orientation == Direction.UP || orientation == Direction.DOWN) {
			for (int i = 0; i < tiles.length; i++) {
				Vector2D rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + i) * TileMap.TILE_SIZE);
				tiles[i] = new Tile(rectPos, TileMap.TILE_SIZE, TileMap.TILE_SIZE, color);
			}
		} else {
			for (int i = 0; i < tiles.length; i++) {
				Vector2D rectPos = new Vector2D((pos.getX() + i) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
				tiles[i] = new Tile(rectPos, TileMap.TILE_SIZE, TileMap.TILE_SIZE, color);
			}
		}
	}
}
