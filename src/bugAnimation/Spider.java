package bugAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spider extends Bug implements BugWorldObject, Food{
	public Spider(String name, double x, double y, double energy, World world, boolean isAlive) {
		super(name, x, y, energy, world, isAlive);
		this.setSymbol(new ImageView(new Image(getClass().getResourceAsStream("spider.png"))));
		this.setSize(4.0);
		this.setSpeed(3.0);
		this.getFoodType().add(Plant.class.toString());
	}
}