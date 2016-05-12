package game.tiles;

import game.Direction;
import game.TileMap;
import game.tiles.Tile;
import game.graphics.Vector2D;

public class TO extends Tetrimino {

	public TO(Vector2D pos) {
		super(pos, Tetrimino.ID_O, 0xffff00);
	}

	@Override
	public void turn(boolean[][] collisionMap) {
		// Square doesn't need to be turned
	}

	@Override
	public void move(boolean[][] collisionMap, Direction dir) {
		Vector2D newPos = pos.addVector(new Vector2D(dir.getX(), dir.getY()));
		if (dir == Direction.LEFT) {
			if (!collisionMap[(int) newPos.getX()][(int) newPos.getY()]
					&& !collisionMap[(int) newPos.getX()][(int) newPos.getY() + 1]) {
				pos = newPos;
			}
		} else if (dir == Direction.RIGHT) {
			if (!collisionMap[(int) newPos.getX() + 1][(int) newPos.getY()]
					&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 1]) {
				pos = newPos;
			}
		} else if (dir == Direction.DOWN) {
			if (!collisionMap[(int) newPos.getX()][(int) newPos.getY() + 1]
					&& !collisionMap[(int) newPos.getX() + 1][(int) newPos.getY() + 1]) {
				pos = newPos;
			} else {
				TileMap.releaseBlocks(tiles);
			}
		}

		updateTiles();
	}

	@Override
	public void updateTiles() {
		tiles = new Tile[4];
		for (int i = 0; i < tiles.length; i++) {
			Vector2D rectPos = null;
			switch (i) {
			case 0:
				rectPos = new Vector2D(pos.getX() * TileMap.TILE_SIZE, pos.getY() * TileMap.TILE_SIZE);
				break;
			case 1:
				rectPos = new Vector2D(pos.getX() * TileMap.TILE_SIZE + TileMap.TILE_SIZE,
						pos.getY() * TileMap.TILE_SIZE);
				break;
			case 2:
				rectPos = new Vector2D(pos.getX() * TileMap.TILE_SIZE + TileMap.TILE_SIZE,
						pos.getY() * TileMap.TILE_SIZE + TileMap.TILE_SIZE);
				break;
			case 3:
				rectPos = new Vector2D(pos.getX() * TileMap.TILE_SIZE,
						pos.getY() * TileMap.TILE_SIZE + TileMap.TILE_SIZE);
				break;
			}
			tiles[i] = new Tile(rectPos, TileMap.TILE_SIZE, TileMap.TILE_SIZE, color, ID_O);
		}
	}
}
