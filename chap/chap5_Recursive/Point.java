package chap5_Recursive;

public class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getIx() {
		return ix;
	}

	public int getIy() {
		return iy;
	}

	@Override
	public String toString() {
		return "(" + ix + ", " + iy + ")";
	}
}
