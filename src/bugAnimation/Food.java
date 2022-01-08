package bugAnimation;
import javafx.scene.image.ImageView;

public interface Food {
	public double getX();
	public double getY();
	public double getSize();
	public ImageView getSymbol();
	public void isEaten();
}
