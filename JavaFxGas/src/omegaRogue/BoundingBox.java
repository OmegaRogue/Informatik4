package omegaRogue;

public class BoundingBox{
	
	public final double minX;
	
	public final double minY;
	
	public final double maxX;

	public final double maxY;
	
	public BoundingBox (double x1, double y1, double x2, double y2) {
		this.minX = Math.min(x1, x2);
		this.minY = Math.min(y1, y2);
		this.maxX = Math.max(x1, x2);
		this.maxY = Math.max(y1, y2);
	}
	
	public boolean intersects(double x1, double y1, double x2, double y2)
	{
		return this.minX < x2 && this.maxX > x1 && this.minY < y2 && this.maxY > y1;
	}
	
	public BoundingBox offset(double x, double y)
	{
		return new BoundingBox(this.minX + x, this.minY + y, this.maxX + x, this.maxY + y);
	}
}
