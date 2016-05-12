package game.graphics;

/**
 * Created by henne90gen on 20.06.15. Class represents a vector in 2D-Space
 */
public class Vector2D {

	private double x;
	private double y;
	private final double z;

	public Vector2D(double x, double y) {
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

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private double getZ() {
		return z;
	}

	public double distanceToVector(Vector2D vec) {
		return Math.sqrt(Math.pow(x - vec.getX(), 2) + Math.pow(y - vec.getY(), 2));
	}
}
