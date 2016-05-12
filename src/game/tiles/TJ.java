package game.tiles;

import game.Direction;
import game.TileMap;
import game.graphics.Vector2D;

public class TJ extends Tetrimino {

	/*
	 * Tile looks like an inverse "L"
	 */

	public TJ(Vector2D pos) {
		super(pos, Tetrimino.ID_J, 0x00ccff);
	}

	public void turn(boolean[][] collisionMap) {
		if (orientation == Direction.UP) {
			if (!collisionMap[(int) pos.getX() + 1][(int) pos.getY()]
					&& !collisionMap[(int) pos.getX() + 1][(int) pos.getY() + 1]
					&& !collisionMap[(int) pos.getX() + 1][(int) pos.getY() + 2]
					&& !collisionMap[(int) pos.getX() + 2][(int) pos.getY()]) {
				orientation = Direction.RIGHT;
			}

		} else if (orientation == Direction.RIGHT) {
			if (!collisionMap[(int) pos.getX()][(int) pos.getY() + 1]
					&& !collisionMap[(int) pos.getX() + 2][(int) pos.getY() + 2]
					&& !collisionMap[(int) pos.getX() + 1][(int) pos.getY() + 1]
					&& !collisionMap[(int) pos.getX() + 2][(int) pos.getY() + 1]) {
				orientation = Direction.DOWN;
			}

		} else if (orientation == Direction.DOWN) {
			if (!collisionMap[(int) pos.getX()][(int) pos.getY() + 2]
					&& !collisionMap[(int) pos.getX() + 1][(int) pos.getY()]
					&& !collisionMap[(int) pos.getX() + 1][(int) pos.getY() + 1]
					&& !collisionMap[(int) pos.getX() + 1][(int) pos.getY() + 2]) {
				orientation = Direction.LEFT;
			}

		} else if (orientation == Direction.LEFT) {
			if (!collisionMap[(int) pos.getX()][(int) pos.getY() + 1]
					&& !collisionMap[(int) pos.getX() + 1][(int) pos.getY() + 1]
					&& !collisionMap[(int) pos.getX()][(int) pos.getY()]
					&& !collisionMap[(int) pos.getX() + 2][(int) pos.getY() + 1]) {
				orientation = Direction.UP;
			}
		}
	}

	@Override
	public void move(boolean[][] collisionMap, Direction dir) {
		Vector2D newPos = pos.addVector(new Vector2D(dir.getX(), dir.getY()));
		if (orientation == Direction.UP) {
			if (dir == Direction.DOWN) {
				if (!collisionMap[(int) newPos.getX()][(int) newPos.getY() + 1]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 1]
						&& !collisionMap[(int) newPos.getX() + 2][(int) newPos.getY() + 1]) {
					pos = newPos;
				} else {
					TileMap.releaseBlocks(tiles);
				}
			} else if (dir == Direction.LEFT) {
				if (!collisionMap[(int) newPos.getX()][(int) newPos.getY()]
						&& !collisionMap[(int) newPos.getX()][(int) newPos.getY() + 1]) {
					pos = newPos;
				}
			} else if (dir == Direction.RIGHT) {
				if (!collisionMap[(int) newPos.getX()][(int) newPos.getY()]
						&& !collisionMap[(int) newPos.getX() + 2][(int) newPos.getY() + 1]) {
					pos = newPos;
				}
			}

		} else if (orientation == Direction.RIGHT) {
			if (dir == Direction.DOWN) {
				if (!collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 2]
						&& !collisionMap[(int) newPos.getX() + 2][(int) newPos.getY()]) {
					pos = newPos;
				} else {
					TileMap.releaseBlocks(tiles);
				}
			} else if (dir == Direction.LEFT) {
				if (!collisionMap[(int) newPos.getX() + 1][(int) newPos.getY()]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 1]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 2]) {
					pos = newPos;
				}
			} else if (dir == Direction.RIGHT) {
				if (!collisionMap[(int) newPos.getX() + 2][(int) newPos.getY()]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 1]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 2]) {
					pos = newPos;
				}
			}

		} else if (orientation == Direction.DOWN) {
			if (dir == Direction.DOWN) {
				if (!collisionMap[(int) newPos.getX()][(int) newPos.getY() + 1]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 1]
						&& !collisionMap[(int) newPos.getX() + 2][(int) newPos.getY() + 2]) {
					pos = newPos;
				} else {
					TileMap.releaseBlocks(tiles);
				}
			} else if (dir == Direction.LEFT) {
				if (!collisionMap[(int) newPos.getX()][(int) newPos.getY()]
						&& !collisionMap[(int) newPos.getX() + 2][(int) newPos.getY() + 1]) {
					pos = newPos;
				}
			} else if (dir == Direction.RIGHT) {
				if (!collisionMap[(int) newPos.getX() + 2][(int) newPos.getY() + 1]
						&& !collisionMap[(int) newPos.getX() + 2][(int) newPos.getY() + 2]) {
					pos = newPos;
				}
			}

		} else if (orientation == Direction.LEFT) {
			if (dir == Direction.DOWN) {
				if (!collisionMap[(int) newPos.getX()][(int) newPos.getY() + 2]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 2]) {
					pos = newPos;
				} else {
					TileMap.releaseBlocks(tiles);
				}
			} else if (dir == Direction.LEFT) {
				if (!collisionMap[(int) newPos.getX()][(int) newPos.getY() + 2]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 1]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY()]) {
					pos = newPos;
				}
			} else if (dir == Direction.RIGHT) {
				if (!collisionMap[(int) newPos.getX() + 1][(int) newPos.getY()]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 1]
						&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 2]) {
					pos = newPos;
				}
			}
		}

		updateTiles();
	}

	@Override
	public void updateTiles() {
		tiles = new Tile[4];
		if (orientation == Direction.UP) {
			for (int i = 0; i < tiles.length; i++) {
				Vector2D rectPos = null;
				switch (i) {
				case 0:
					rectPos = new Vector2D(pos.getX() * TileMap.TILE_SIZE, pos.getY() * TileMap.TILE_SIZE);
					break;
				case 1:
					rectPos = new Vector2D((pos.getX() + 2) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 2:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 3:
					rectPos = new Vector2D(pos.getX() * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				}
				tiles[i] = new Tile(rectPos, TileMap.TILE_SIZE, TileMap.TILE_SIZE, color, ID_J);
			}
		} else if (orientation == Direction.RIGHT) {
			for (int i = 0; i < tiles.length; i++) {
				Vector2D rectPos = null;
				switch (i) {
				case 0:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, pos.getY() * TileMap.TILE_SIZE);
					break;
				case 1:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 2:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 2) * TileMap.TILE_SIZE);
					break;
				case 3:
					rectPos = new Vector2D((pos.getX() + 2) * TileMap.TILE_SIZE, pos.getY() * TileMap.TILE_SIZE);
					break;
				}
				tiles[i] = new Tile(rectPos, TileMap.TILE_SIZE, TileMap.TILE_SIZE, color, ID_J);
			}
		} else if (orientation == Direction.DOWN) {
			for (int i = 0; i < tiles.length; i++) {
				Vector2D rectPos = null;
				switch (i) {
				case 0:
					rectPos = new Vector2D(pos.getX() * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 1:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 2:
					rectPos = new Vector2D((pos.getX() + 2) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 3:
					rectPos = new Vector2D((pos.getX() + 2) * TileMap.TILE_SIZE, (pos.getY() + 2) * TileMap.TILE_SIZE);
					break;
				}
				tiles[i] = new Tile(rectPos, TileMap.TILE_SIZE, TileMap.TILE_SIZE, color, ID_J);
			}
		} else if (orientation == Direction.LEFT) {
			for (int i = 0; i < tiles.length; i++) {
				Vector2D rectPos = null;
				switch (i) {
				case 0:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, pos.getY() * TileMap.TILE_SIZE);
					break;
				case 1:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 1) * TileMap.TILE_SIZE);
					break;
				case 2:
					rectPos = new Vector2D((pos.getX() + 1) * TileMap.TILE_SIZE, (pos.getY() + 2) * TileMap.TILE_SIZE);
					break;
				case 3:
					rectPos = new Vector2D(pos.getX() * TileMap.TILE_SIZE, (pos.getY() + 2) * TileMap.TILE_SIZE);
					break;
				}
				tiles[i] = new Tile(rectPos, TileMap.TILE_SIZE, TileMap.TILE_SIZE, color, ID_J);
			}
		}
	}
}
