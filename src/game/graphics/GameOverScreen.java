package game.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Game;
import game.TileMap;

public class GameOverScreen {

	private BufferedImage gosImg;
	private int[] gosImgPixels;
	private Vector2D pos;
	private ScoreCounter score;

	public GameOverScreen(Vector2D pos, ScoreCounter score) {
		this.score = score;
		try {
			gosImg = ImageIO.read(new File("./res/gameOver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] gosImgBytes = ((DataBufferByte) gosImg.getRaster().getDataBuffer()).getData();

		gosImgPixels = new int[gosImgBytes.length / 4];

		for (int x = 0; x < gosImg.getWidth(); x++) {
			for (int y = 0; y < gosImg.getHeight(); y++) {
				gosImgPixels[y * gosImg.getWidth() + x] = (gosImgBytes[(y * gosImg.getWidth() + x) * 4 + 3] << 16)
						+ (gosImgBytes[(y * gosImg.getWidth() + x) * 4 + 2] << 8)
						+ gosImgBytes[(y * gosImg.getWidth() + x) * 4 + 1];
			}
		}

		this.pos = new Vector2D(pos.getX() - gosImg.getWidth()/TileMap.TILE_SIZE, pos.getY() - gosImg.getHeight()/TileMap.TILE_SIZE-1);
		
	}

	public int[] addToPixels(int[] pixels) {

		// Make everything white
		for (int row = 0; row < TileMap.TILE_MAP_HEIGHT * TileMap.TILE_SIZE; row++) {
			for (int col = 0; col < TileMap.TILE_MAP_WIDTH * TileMap.TILE_SIZE; col++) {
				pixels[row * TileMap.TILE_MAP_WIDTH * TileMap.TILE_SIZE + col] = (255 << 16) + (255 << 8) + 255;
			}
		}
		
		// Print "Game Over"
		for (int row = 0; row < gosImg.getHeight(); row++) {
			for (int col = 0; col < gosImg.getWidth(); col++) {
				if (gosImgPixels[row * gosImg.getWidth() + col] >= 0) {
					pixels[(pos.getY()*TileMap.TILE_SIZE + row) * Game.WIDTH + pos.getX()*TileMap.TILE_SIZE + col] = gosImgPixels[row
							* gosImg.getWidth() + col];
				}
			}
		}
		
		score.setPos(new Vector2D(pos.getX(), pos.getY()+3));
		pixels = score.addToPixels(pixels);
		
		return pixels;
	}

}
