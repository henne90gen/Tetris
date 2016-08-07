package game.graphics;

public class Vector2D {

	private int x;
	private int y;
	private final int z;

	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 1;
	}

	public Vector2D addVector(Vector2D vec) {
		return new Vector2D(x + vec.getX(), y + vec.getY());
	}

	public Vector2D subVector(Vector2D vec) {
		return new Vector2D(x - vec.getX(), y - vec.getY());
	}

	public Vector2D crossProduct(Vector2D vec) {
		return new Vector2D(y * vec.getZ() - z * vec.getY(), z * vec.getX() - x * vec.getZ());
	}

	public Vector2D mulScalar(int scalar) {
		return new Vector2D(x * scalar, y * scalar);
	}

	public double scalarProduct(Vector2D vec) {
		return x * vec.getX() + y * vec.getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private int getZ() {
		return z;
	}

	public double distanceToVector(Vector2D vec) {
		return Math.sqrt(Math.pow(x - vec.getX(), 2) + Math.pow(y - vec.getY(), 2));
	}
}
