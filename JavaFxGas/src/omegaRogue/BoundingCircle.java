package omegaRogue;

public class BoundingCircle{
	
	public final double x;
	
	public final double y;
	
	public final double r;
	
	
	public BoundingCircle (double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
//	public boolean intersects(double x1, double y1, double x2, double y2)
//	{
//		return this.minX < x2 && this.maxX > x1 && this.minY < y2 && this.maxY > y1;
//	}
//	public boolean intersects(double x, double y, double r)
//	{
//		return this.minX < x2 && this.maxX > x1 && this.minY < y2 && this.maxY > y1;
//	}
	
	public BoundingCircle offset(double x, double y)
	{
		return new BoundingCircle(this.x + x, this.y + y, this.r);
	}
}
