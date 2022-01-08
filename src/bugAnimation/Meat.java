package bugAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Meat implements Food , BugWorldObject{
	private int ID;
	private double x, y, size = 3.0;
	private ImageView symbol = new ImageView(new Image(getClass().getResourceAsStream("meat.png")));
	private World world;
	private Circle color;

	public Meat(ImageView symbol, int iD, double size, double x, double y, World world, Circle color) {
		super();
		this.symbol = symbol;
		this.ID = iD;
		this.size = size;
		this.x = x;
		this.y = y;
		this.world = world;
		this.color = color;
	}

	public Meat(double x, double y, World world) {
		this.x = x;
		this.y = y;
		this.world = world;
		this.setSize(3.0);
		this.setSymbol(symbol);
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		size = 3.0;
		this.size = size;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
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
		return "Meat [symbol=" + symbol + ", size=" + size + ", x=" + x + ", y=" + y + "]";
	}
	@Override
	public ImageView getSymbol() {
		return new ImageView(new Image(getClass().getResourceAsStream("meat.png")));
	}
	
	@Override
	public void setSymbol(ImageView symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public void isEaten() {
		this.world.getObjects().remove(this);
		this.world.getFood().remove(this);
		this.world.getMeats().remove(this);
	}

}
