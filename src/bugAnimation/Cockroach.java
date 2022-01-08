package bugAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cockroach extends Bug implements BugWorldObject, Food{
	public Cockroach(String name, double x, double y, double energy, World world, boolean isAlive) {
		super(name, x, y, energy, world, isAlive);
		this.setSymbol(new ImageView(new Image(getClass().getResourceAsStream("cockroach.png"))));
		this.setSize(5.0);
		this.setSpeed(4.0);
		this.getFoodType().add(Plant.class.toString());
	}
}
