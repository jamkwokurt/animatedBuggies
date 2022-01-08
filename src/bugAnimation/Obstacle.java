package bugAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Obstacle implements BugWorldObject{
	private int id;
	private double x, y,size = 5.0;
	private Circle color;
	private ImageView symbol = new ImageView(new Image(getClass().getResourceAsStream("obstacle.png")));
	private World world;
	
	public Obstacle(double x, double y, World world) {
		this.x = x;
		this.y = y;
		this.world = world;
		this.setSize(5.0);
		this.setSymbol(symbol);
	}
	
	public Obstacle(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getID() {
		return id;
	}
	
	@Override
	public void setID(int id) {
		this.id = id;
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

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Circle getCircle() {
		return color;
	}
	
	public void setCircle(Circle color) {
		this.color = color;
	}
	
	@Override
	public void setSymbol(ImageView symbol) {
		this.symbol = symbol;		
	}
	
	public ImageView getSymbol() {
		return symbol;
	}

	
	


	
}
