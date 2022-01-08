package bugAnimation;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public interface BugWorldObject{
	public double getX();
	public double getY();
	public ImageView getSymbol();
	public double getSize();
	public Circle getCircle();
	public void setCircle(Circle color);
	public void setID(int id);
	public void setSymbol(ImageView i);
}
