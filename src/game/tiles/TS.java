package game.tiles;

import game.Direction;
import game.TileMap;
import game.graphics.Vector2D;

public class TS extends Tetrimino {

	public TS(Vector2D pos) {
		super(pos, Tetrimino.ID_S, 0xff00ff);
	}

	@Override
	public void turn(boolean[][] collisionMap) {
		if (orientation == Direction.UP || orientation == Direction.DOWN) {
			if (!collisionMap[pos.getX()][pos.getY()]
					&& !collisionMap[pos.getX()][pos.getY() + 1]
					&& !collisionMap[pos.getX() + 1][pos.getY() + 1]
					&& !collisionMap[pos.getX() + 1][pos.getY() + 2]) {
				orientation = Direction.RIGHT;
			}

		} else if (orientation == Direction.RIGHT || orientation == Direction.LEFT) {
			if (!collisionMap[pos.getX()][pos.getY() + 2]
					&& !collisionMap[pos.getX() + 1][pos.getY() + 2]
					&& !collisionMap[pos.getX() + 1][pos.getY() + 1]
					&& !collisionMap[pos.getX() + 2][pos.getY() + 1]) {
				orientation = Direction.UP;
			}
		}
	}

	@Override
	public void move(boolean[][] collisionMap, Direction dir) {
		Vector2D newPos = pos.addVector(new Vector2D(dir.getX(), dir.getY()));
		if (orientation == Direction.UP || orientation == Direction.DOWN) {
			if (dir == Direction.DOWN) {
				if (!collisionMap[newPos.getX()][newPos.getY() + 2]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 2]
						&& !collisionMap[newPos.getX() + 2][newPos.getY() + 1]) {
					pos = newPos;
				} else {
					TileMap.releaseBlocks(tiles);
				}
			} else if (dir == Direction.LEFT) {
				if (!collisionMap[newPos.getX() + 1][newPos.getY() + 1]
						&& !collisionMap[newPos.getX()][newPos.getY() + 2]) {
					pos = newPos;
				}
			} else if (dir == Direction.RIGHT) {
				if (!collisionMap[newPos.getX() + 2][newPos.getY() + 1]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 2]) {
					pos = newPos;
				}
			}

		} else if (orientation == Direction.RIGHT || orientation == Direction.LEFT) {
			if (dir == Direction.DOWN) {
				if (!collisionMap[newPos.getX()][newPos.getY() + 1]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 2]) {
					pos = newPos;
				} else {
					TileMap.releaseBlocks(tiles);
				}
			} else if (dir == Direction.LEFT) {
				if (!collisionMap[newPos.getX()][newPos.getY()]
						&& !collisionMap[newPos.getX()][newPos.getY() + 1]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 2]) {
					pos = newPos;
				}
			} else if (dir == Direction.RIGHT) {
				if (!collisionMap[newPos.getX()][newPos.getY()]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 1]
						&& !collisionMap[newPos.getX() + 1][newPos.getY() + 2]) {
					pos = newPos;
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
				Vector2D rectPos = null;
				switch (i) {
				case 0:
					rectPos = new Vector2D(pos.getX() * TileMap.TILE_SIZE, (pos.getY() + 2) * TileMap.TILE_SIZE);
					break;
				case 1:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 2) * TileMap.TILE_SIZE);
					break;
				case 2:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 3:
					rectPos = new Vector2D((pos.getX() + 2) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				}
				tiles[i] = new Tile(rectPos, TileMap.TILE_SIZE, TileMap.TILE_SIZE, color);
			}
		} else if (orientation == Direction.RIGHT) {
			for (int i = 0; i < tiles.length; i++) {
				Vector2D rectPos = null;
				switch (i) {
				case 0:
					rectPos = new Vector2D((pos.getX()) * TileMap.TILE_SIZE, pos.getY() * TileMap.TILE_SIZE);
					break;
				case 1:
					rectPos = new Vector2D((pos.getX()) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 2:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 3:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 2) * TileMap.TILE_SIZE);
					break;
				}
				tiles[i] = new Tile(rectPos, TileMap.TILE_SIZE, TileMap.TILE_SIZE, color);
			}
		}
	}

}
