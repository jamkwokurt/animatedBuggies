package bugAnimation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Plant implements BugWorldObject,Food{
	private int ID;
	private double size;
	private double x;
	private double y;
	private Circle color;
	private World world;
	private ImageView symbol = new ImageView(new Image(getClass().getResourceAsStream("plant.png")));
	public Plant(double x, double y, double size, World world) {
		this.x = x;
		this.y = y;
		this.setSymbol(symbol);
		this.world = world;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	@Override
	public ImageView getSymbol() {
		return new ImageView(new Image(getClass().getResourceAsStream("plant.png")));
	}
	
	@Override
	public void setSymbol(ImageView symbol) {
		this.symbol = symbol;
	}
	
	public double getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
	public Circle getCircle() {
		return color;
	}
	public void setCircle(Circle color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Plant ['0 '+size=" + size + ", x=" + x + ", y=" + y+"]";
	}
	@Override
	public void isEaten() {
		this.world.getObjects().remove(this);
		this.world.getFood().remove(this);
		this.world.getPlants().remove(this);
	}

	public void grow() {
		if(this.size < 10.0)
			this.size += 1.0;
	}

}
