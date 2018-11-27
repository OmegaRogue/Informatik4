package omegaRogue;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;



public class BoundingBox extends Rectangle implements Behaviour{
	
	public final double minX;

	public final double minY;

	public final double maxX;

	public final double maxY;
	Color c= Color.FIREBRICK;
	
	public BoundingBox (double x1, double y1, double x2, double y2) {
		super(Math.min(x1, x2),Math.min(y1, y2),Math.max(x1, x2)-Math.min(x1, x2),Math.max(y1, y2)-Math.min(y1, y2));

		this.minX = Math.min(x1, x2);
		this.minY = Math.min(y1, y2);
		this.maxX = Math.max(x1, x2);
		this.maxY = Math.max(y1, y2);
		setColor(Color.FIREBRICK);
		setStrokeWidth(2);
	}
	public BoundingBox (Rectangle rect) {
		super(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
		this.minX = rect.getX();
		this.minY = rect.getY();
		this.maxX = rect.getX() + rect.getWidth();
		this.maxY = rect.getY() + rect.getHeight();
		setColor(Color.FIREBRICK);
		setStrokeWidth(2);
	}

	public boolean intersects(double x1, double y1, double x2, double y2)
	{

		return this.getBoundsInParent().intersects(Math.min(x1,x2),Math.min(y1,y2),Math.max(x1,x2)-Math.min(x1,x2),Math.max(y1,y2)-Math.min(y1,y2));
	}
	public boolean intersects(BoundingBox box)
	{
		return this.getBoundsInParent().intersects(box.getBoundsInParent());
	}
	public boolean intersects(Bounds bound)
	{
		return this.getBoundsInParent().intersects(bound);
	}
	public Direction intersectsAt(Node node) {
		if(intersects(node.getBoundsInParent())) {
			return whereRelative(node);
		}
		else
		return Direction.NONE;
	}
	public Direction whereRelative(Node node) {
		double nMinX = node.getBoundsInParent().getMinX();
		double nMinY = node.getBoundsInParent().getMinY();
		double nMaxX = node.getBoundsInParent().getMaxX();
		double nMaxY = node.getBoundsInParent().getMaxY();
		if(nMaxX<minX)
		    return Direction.LEFT;
        if(nMinX>maxX)
        	return Direction.RIGHT;
        if(nMaxY<minY)
        	return Direction.DOWN;
        if(nMinY>maxY)
        	return Direction.UP;
        return Direction.NONE;
	}

	public BoundingBox offset(double x, double y)
	{
		return new BoundingBox(this.minX + x, this.minY + y, this.maxX + x, this.maxY + y);
	}
	public void Start() {}
	public void Update() {}
	public void setColor(Color farbe)
	{
		c = farbe;
		setFill(farbe);
	}
}
