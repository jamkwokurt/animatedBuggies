package bugAnimation;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class World extends Pane{
	private List<Bug> bugs = new ArrayList<>();
	private List<Plant> plants = new ArrayList<>();
	private List<Meat> meats = new ArrayList<>();
	private List<Obstacle> obstacles = new ArrayList<>();
	private List<Food> food = new ArrayList<>();
	private List<BugWorldObject> objects = new ArrayList<>();

	public World(double x, double y) {
		this.setWidth(x);
		this.setHeight(y);
	}

	public void drawWorldOfImage() {
		for(BugWorldObject o : objects) {
			double x = o.getX();
			double y = o.getY();
			o.getSymbol().setFitWidth(o.getSize());
			o.getSymbol().setFitHeight(o.getSize());
			o.getSymbol().setTranslateX(x);
			o.getSymbol().setTranslateY(y);
		}
	}
	public void drawWorldOfCircle() {
		for(BugWorldObject o : objects) {
			if(o != null) {
				double x = o.getX();
				double y = o.getY();
				Circle c = new Circle(0,0,o.getSize());
				if(o instanceof Bug)
					if(o instanceof Beetle)
						c.setFill(Color.BLUE);
					else if(o instanceof Spider)
						c.setFill(Color.BLACK);
					else if(o instanceof Cockroach)
						c.setFill(Color.BROWN);
				if(o instanceof Plant)
					c.setFill(Color.GREEN);
				if(o instanceof Obstacle)
					c.setFill(Color.ORANGE);
				if(o instanceof Meat)
					c.setFill(Color.RED);
				o.setCircle(c);
				o.getCircle().setTranslateX(x);
				o.getCircle().setTranslateY(y);
			}
		}
	}

	public void updateWorldOfImage() {
		for(BugWorldObject o : objects) {
			if(o != null) {
				if(o instanceof Bug) {
					Bug b = (Bug)o;
					b.liveImageWorld();
				}
				if(o instanceof Plant) {
					Plant p = (Plant)o;
					p.grow();
				}
				double x = o.getX();
				double y = o.getY();
				o.getSymbol().setFitWidth(o.getSize());
				o.getSymbol().setFitHeight(o.getSize());
				o.getSymbol().setTranslateX(x);
				o.getSymbol().setTranslateY(y);
			}
		}	
	}	

	private BugWorldObject getObjectByPosition(double x, double y) {

		for (BugWorldObject b : objects) {
			if (x == b.getX() && y == b.getY()) {
				return b;
			}
		}
		return null;
	}

	public List<Bug> getBugs() {
		return bugs;
	}

	public List<Plant> getPlants() {
		return plants;
	}

	public List<Meat> getMeats() {
		return meats;
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public List<Food> getFood() {
		return food;
	}

	public List<BugWorldObject> getObjects() {
		return objects;
	}

}
