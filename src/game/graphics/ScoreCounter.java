package game.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.TileMap;

public class ScoreCounter {

	private static final int FONT_SIZE = TileMap.TILE_SIZE;
	private static final int SCORECOUNTER_SIZE_X = 3 * FONT_SIZE;
	private static final int SCORECOUNTER_SIZE_Y = FONT_SIZE;
	private int[] score;
	private Vector2D pos;
	private BufferedImage numImg;
	private int[][] numPixels;

	public ScoreCounter(Vector2D pos) {
		score = new int[3];
		this.pos = pos;
		try {
			numImg = ImageIO.read(new File("./res/numbers.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytes = ((DataBufferByte) numImg.getRaster().getDataBuffer()).getData(); // ABGR

		numPixels = new int[10][FONT_SIZE * FONT_SIZE];

		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				for (int x = i * FONT_SIZE; x < (i + 1) * FONT_SIZE; x++) {
					for (int y = 0; y < FONT_SIZE; y++) {
						numPixels[i][y * FONT_SIZE
								+ (x - (i * FONT_SIZE))] = (((bytes[(y * numImg.getWidth() + x) * 4 + 3] == -1) ? (255)
										: (bytes[(y * numImg.getWidth() + x) * 4 + 3])) << 16)
										+ (((bytes[(y * numImg.getWidth() + x) * 4 + 2] == -1) ? (255)
												: (bytes[(y * numImg.getWidth() + x) * 4 + 2])) << 8)
										+ ((bytes[(y * numImg.getWidth() + x) * 4 + 1] == -1) ? (255)
												: (bytes[(y * numImg.getWidth() + x) * 4 + 1]));
					}
				}
			} else {
				for (int x = (i - 5) * FONT_SIZE; x < (i - 4) * FONT_SIZE; x++) {
					for (int y = FONT_SIZE; y < FONT_SIZE * 2; y++) {
						numPixels[i][(y - FONT_SIZE) * FONT_SIZE
								+ (x - ((i - 5) * FONT_SIZE))] = (((bytes[(y * numImg.getWidth() + x) * 4 + 3] == -1)
										? (255) : (bytes[(y * numImg.getWidth() + x) * 4 + 3])) << 16)
										+ (((bytes[(y * numImg.getWidth() + x) * 4 + 2] == -1) ? (255)
												: (bytes[(y * numImg.getWidth() + x) * 4 + 2])) << 8)
										+ ((bytes[(y * numImg.getWidth() + x) * 4 + 1] == -1) ? (255)
												: (bytes[(y * numImg.getWidth() + x) * 4 + 1]));
					}
				}
			}
		}
	}

	public void updateScore(int scoreAmount) {
		if (score[2] + scoreAmount < 10) {
			score[2] += scoreAmount;
		} else {
			if (score[1] + 1 < 10) {
				score[1]++;
				score[2] = scoreAmount - (10 - score[2]);
			} else {
				if (score[0] + 1 < 10) {
					score[0]++;
					score[1] = 0;
					score[2] = scoreAmount - (10 - score[2]);
				}
			}
		}
		for (int i = score.length - 1; i >= 0; i--) {
			if (score[i] > 9) {
				score[i] = score[i] - 10;
				if (i > 0) {
					score[i - 1]++;
				} else {
					score[i] = 0;
				}
			}
		}
	}

	public int[] addToPixels(int[] pixels) {
		for (int globalY = pos.getY() * TileMap.TILE_SIZE; globalY < pos.getY() * TileMap.TILE_SIZE + SCORECOUNTER_SIZE_Y; globalY++) {
			for (int globalX = pos.getX() * TileMap.TILE_SIZE; globalX < pos.getX() * TileMap.TILE_SIZE + SCORECOUNTER_SIZE_X; globalX++) {
				int index = 0;
				if (globalX < pos.getX() * TileMap.TILE_SIZE + FONT_SIZE) {
					index = 0;
				} else if (globalX < pos.getX() * TileMap.TILE_SIZE + FONT_SIZE*2) {
					index = 1;
				} else if (globalX < pos.getX() * TileMap.TILE_SIZE + FONT_SIZE * 3) {
					index = 2;
				}
				int localX = globalX - pos.getX() * TileMap.TILE_SIZE - index * FONT_SIZE;
				int localY = globalY - pos.getY() * TileMap.TILE_SIZE;
				int color = numPixels[score[index]][localY * FONT_SIZE + localX];
				if (color < (255 << 16) + (255 << 8) + 255) {
					pixels[globalY * TileMap.TILE_MAP_WIDTH * TileMap.TILE_SIZE + globalX] = color;
				}
			}
		}
		return pixels;
	}

	public Vector2D getPos() {
		return pos;
	}

	public void setPos(Vector2D pos) {
		this.pos = pos;
	}
}
